package trenduce.com.trenduce.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import trenduce.com.trenduce.R;
import trenduce.com.trenduce.Utils.Constants;
import trenduce.com.trenduce.Utils.NetworkUtils;
import trenduce.com.trenduce.asynctasks.HttpGetAsyncTask;
import trenduce.com.trenduce.asynctasks.HttpPostAsyncTask;
import trenduce.com.trenduce.model.LoginRequest;
import trenduce.com.trenduce.model.UserProfile;


public class LoginActivity extends ActionBarActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private Button btnLogin;
    private TextView tvRegister;

    private EditText etEmailId;
    private EditText etPassword;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
    }


    private void initialize(){

        initializeViews();
    }

    private void initializeViews() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvRegister = (TextView) findViewById(R.id.tvRegister);

        etEmailId = (EditText)findViewById(R.id.etEmailAddress);
        etPassword = (EditText)findViewById(R.id.etPassword);
    }



    public void actionRequested(View view){

        if(view.getId() == R.id.btnLogin) {
            Log.d(TAG, "Login requested");
            doLogin();
        }

        if(view.getId() == R.id.tvRegister){
            Log.d(TAG, "Signup form requested");
            showSignUpForm();
        }
    }


    private void showSignUpForm(){
        Intent intent = new Intent(getBaseContext(), SignupActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    private void doLogin(){

        UserProfile.getInstance().reset();
        String emailId = etEmailId.getText().toString();
        String password = etPassword.getText().toString();


        if(emailId == null || emailId.isEmpty() ||
                password == null || password.isEmpty()){

            Toast.makeText(this, getString(R.string.login_input_error), Toast.LENGTH_LONG).show();

            return;
        }

        if(NetworkUtils.isNetworkAvailable(getBaseContext()) == false){

            Toast.makeText(this, getString(R.string.network_Connection_error), Toast.LENGTH_LONG).show();

            return;
        }

        progressDialog = ProgressDialog.show(this,"", getString(R.string.login_progress_message));

        try {
            LoginRequest loginRequest = new LoginRequest(emailId, password);

            new HttpPostAsyncTask(loginRequest.toJSONObject(), handler, Constants.LOGIN_API, Constants.HandlerIds.LOGIN).execute();
        }
        catch (Exception ex){
            if(progressDialog != null){
                progressDialog.dismiss();
            }
        }
    }

    private void showMain(){
        if(progressDialog != null){
            progressDialog.dismiss();
        }

        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
        finish();
    }

    private void getUserProfile(){
        new HttpGetAsyncTask( handler, Constants.USERS_API + "/" + UserProfile.getInstance().getId(), Constants.HandlerIds.USER_PROFILE).execute();
    }


    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {



            String response = (String)msg.obj;

            Log.d(TAG, "Response Message: .... \r\n" + response);
            Log.d(TAG, "What Message: .... \r\n" + msg.what);

            if(response != null && response.equals(Constants.STATUS_FAILURE)){

                if(progressDialog != null){
                    progressDialog.dismiss();
                }

                Toast.makeText(getApplicationContext(), R.string.network_Connection_error, Toast.LENGTH_LONG).show();

                return;
            }

            if (msg.what == Constants.HandlerIds.LOGIN) {
                try {
                    JSONObject data = new JSONObject(response);
                    if (data.get(Constants.STATUS_TEXT).equals(Constants.STATUS_SUCCESS)) {

                        Log.d(TAG, "Login is successful");

                        String userId = data.getString("userId");

                        UserProfile.getInstance().setId(userId);

                        getUserProfile();

                    } else {
                        Toast.makeText(getApplicationContext(), R.string.authentication_error, Toast.LENGTH_LONG).show();
                        if(progressDialog != null){
                            progressDialog.dismiss();
                        }
                    }
                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(), R.string.authentication_error, Toast.LENGTH_LONG).show();
                    if(progressDialog != null){
                        progressDialog.dismiss();
                    }
                }
            }

            if (msg.what == Constants.HandlerIds.USER_PROFILE) {
                try {
                    JSONObject data = new JSONObject(response);

                    Log.d(TAG, "User profile retrieved successfully");

                    UserProfile.getInstance().fromJSON(data);

                    Log.d(TAG, UserProfile.getInstance().toString());

                    showMain();

                }
                catch (Exception ex){
                    if(progressDialog != null){
                        progressDialog.dismiss();
                    }
                }
            }
        }
    };





}

package trenduce.com.trenduce.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import trenduce.com.trenduce.R;
import trenduce.com.trenduce.Utils.Constants;
import trenduce.com.trenduce.Utils.NetworkUtils;
import trenduce.com.trenduce.asynctasks.HttpPostAsyncTask;
import trenduce.com.trenduce.model.RegistrationRequest;
import trenduce.com.trenduce.model.UserProfile;


public class SignupActivity extends Activity {

    private static final String TAG = SignupActivity.class.getSimpleName();

    private EditText etEmailId;
    private EditText etPassword;
    private EditText etFirstName;
    private EditText etLastName;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initialize();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void initialize(){

        initializeViews();
    }


    private void initializeViews(){

        etEmailId = (EditText)findViewById(R.id.etEmailAddress);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etFirstName = (EditText)findViewById(R.id.etFirstName);
        etLastName = (EditText)findViewById(R.id.etLastName);

    }

    public void register(View view){

        UserProfile.getInstance().reset();

        String emailId = etEmailId.getText().toString();
        String password = etPassword.getText().toString();
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();


        if(firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                emailId == null || emailId.isEmpty() ||
                password == null || password.isEmpty()){

            Toast.makeText(this, getString(R.string.registration_input_error), Toast.LENGTH_LONG).show();

            return;
        }


        if(NetworkUtils.isNetworkAvailable(getBaseContext()) == false){

            Toast.makeText(this, getString(R.string.network_Connection_error), Toast.LENGTH_LONG).show();

            return;
        }

        progressDialog = ProgressDialog.show(this, "", getString(R.string.registration_progress_message));

        try {

            new HttpPostAsyncTask(getRegistrationObject(), handler, Constants.REGISTER_API, Constants.HandlerIds.REGISTER).execute();
        }
        catch (Exception ex){
            if(progressDialog != null){
                progressDialog.dismiss();
            }
        }
    }

    private JSONObject getRegistrationObject(){
        RegistrationRequest request = new RegistrationRequest();
        request.setFirstName(etFirstName.getText().toString());
        request.setLastName(etLastName.getText().toString());
        request.setEmailID(etEmailId.getText().toString());
        request.setPassword(etPassword.getText().toString());

        return request.toJSONObject();
    }

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            if(progressDialog != null){
                progressDialog.dismiss();
            }

            String response = (String)msg.obj;

            Log.d(TAG, "Response Message: .... \r\n" + response);
            Log.d(TAG, "What Message: .... \r\n" + msg.what);

            if(response != null && response.equals(Constants.STATUS_FAILURE)){

                Toast.makeText(getApplicationContext(), R.string.network_Connection_error, Toast.LENGTH_LONG).show();
                return;
            }

            try {
                if (msg.what == Constants.HandlerIds.REGISTER) {
                    JSONObject data = new JSONObject(response);
                    if (data.get(Constants.STATUS_TEXT).equals(Constants.STATUS_SUCCESS)) {

                        Log.d(TAG, "Registration successful");

                        showNext();

                    }
                    else{
                        String errorMessage = null;

                        try {
                            errorMessage = data.getString(Constants.ERROR_CODE);
                        }
                        catch (Exception ex){

                        }

                        if(errorMessage != null  && !errorMessage.isEmpty()) {
                            Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), getString(R.string.registration_general_error), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
            catch (Exception ex){

                Toast.makeText(getApplicationContext(), R.string.authentication_error, Toast.LENGTH_LONG).show();
            }
        }
    };

    private void showNext(){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }
}

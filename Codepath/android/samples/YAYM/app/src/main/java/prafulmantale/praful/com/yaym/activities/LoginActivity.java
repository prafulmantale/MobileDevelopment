package prafulmantale.praful.com.yaym.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

import org.json.JSONException;
import org.json.JSONObject;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.application.YMApplication;
import prafulmantale.praful.com.yaym.asynctasks.HttpGetAsyncTask;
import prafulmantale.praful.com.yaym.asynctasks.HttpPostAsyncTask;
import prafulmantale.praful.com.yaym.caches.RulesCache;
import prafulmantale.praful.com.yaym.enums.APIRequest;
import prafulmantale.praful.com.yaym.enums.RequestStatus;
import prafulmantale.praful.com.yaym.helpers.AppConstants;
import prafulmantale.praful.com.yaym.interfaces.NetworkResponseListener;
import prafulmantale.praful.com.yaym.models.LoginRequest;


public class LoginActivity extends Activity  implements NetworkResponseListener{

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText etOrg;
    private EditText etUserName;
    private EditText etPassword;
    private BootstrapButton btnLogin;
    private TextView tvCopyright;
    private ProgressDialog progressDialog;
    private RadioGroup serverGroup;
    private YMApplication application;
    private CheckBox cbRememberMe;

    private LoginRequest loginRequest = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
        initializeActionBar();

        updateFromPreferences();

        setupListeners();
    }

    private void updateFromPreferences() {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        String org = null;
        String userName = null;
        String password = null;
        boolean rememberMe = false;
        String server = null;

        if(pref != null){

            rememberMe = pref.getBoolean(AppConstants.PREF_KEY_REMEMBER_ME, rememberMe);
            cbRememberMe.setChecked(rememberMe);
            if(rememberMe){
                org = pref.getString(AppConstants.PREF_KEY_USER_ORG, org);
                userName = pref.getString(AppConstants.PREF_KEY_USER_NAME, userName);
                password = pref.getString(AppConstants.PREF_KEY_USER_PASSWORD, password);
                server = pref.getString(AppConstants.PREF_KEY_SERVER, server);
            }
        }

        if(org != null){
            etOrg.setText(org);
        }

        if(userName != null){
            etUserName.setText(userName);
        }

        if(password != null){
            etPassword.setText(password);
        }

        if(server != null){
            RadioButton demo3 = (RadioButton)findViewById(R.id.demo3Server);
            RadioButton demo = (RadioButton)findViewById(R.id.demoServer);
            if(server.equals(getString(R.string.server_demo3))){
                demo3.setChecked(true);
                application.setYmServer(getString(R.string.server_demo3));
            }

            if(server.equals(getString(R.string.server_demo))){
                demo.setChecked(true);
                application.setYmServer(getString(R.string.server_demo));
            }
        }
    }

    private void savePreferences(){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = prefs.edit();
        boolean rememberMe = cbRememberMe.isChecked();
        editor.putBoolean(AppConstants.PREF_KEY_REMEMBER_ME, rememberMe);

        if(rememberMe == false) {
            editor.remove(AppConstants.PREF_KEY_USER_ORG);
            editor.remove(AppConstants.PREF_KEY_USER_NAME);
            editor.remove(AppConstants.PREF_KEY_USER_PASSWORD);
            editor.remove(AppConstants.PREF_KEY_SERVER);
        }
        else{
            editor.putString(AppConstants.PREF_KEY_USER_ORG, etOrg.getText().toString());
            editor.putString(AppConstants.PREF_KEY_USER_NAME, etUserName.getText().toString());
            editor.putString(AppConstants.PREF_KEY_USER_PASSWORD, etPassword.getText().toString());
            editor.putString(AppConstants.PREF_KEY_SERVER, application.getYmServer());
        }

        editor.commit();
    }

    private void setupListeners() {
        serverGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.demo3Server){
                   application.setYmServer(getString(R.string.server_demo3));
                }
                if(checkedId == R.id.demoServer){
                    application.setYmServer(getString(R.string.server_demo));
                }
            }
        });

        cbRememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == false){
                    savePreferences(); //Immediately clear everything
                }
            }
        });
    }

    private void initialize(){

        application = (YMApplication)getApplication();

        //??Read from shared preferences. Once user makes changes save them
        application.setYmServer(getString(R.string.server_demo));

        etOrg = (EditText)findViewById(R.id.etOrganization);
        etOrg.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Roboto-Thin.ttf"));
        etUserName = (EditText)findViewById(R.id.etUserName);
        etUserName.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Roboto-Thin.ttf"));
        etPassword = (EditText)findViewById(R.id.etPassword);
        etPassword.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Roboto-Thin.ttf"));

        btnLogin = (BootstrapButton)findViewById(R.id.btnLogin);

        tvCopyright = (TextView) findViewById(R.id.tvCopyright);
        tvCopyright.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Roboto-Thin.ttf"));

        serverGroup = (RadioGroup)findViewById(R.id.rgServerGroup);

        cbRememberMe = (CheckBox)findViewById(R.id.cbRemember);

    }

    private void initializeActionBar(){

        ActionBar actionBar = getActionBar();

        View view = getLayoutInflater().inflate(R.layout.action_bar_title, null);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setCustomView(view, params);
        actionBar.setDisplayShowHomeEnabled(true);

        //Hack to hide the home icon -- Otherwise the action bar was getting displayed on top of Tabs
        View homeIcon = findViewById(android.R.id.home);
        ((View) homeIcon.getParent()).setVisibility(View.GONE);

    }

    public void doLogin(View view){

        savePreferences();
        loginRequest = getLoginRequest();

        YMApplication.appCookies = null;

        new HttpPostAsyncTask(handler, YMApplication.getLoginUrl(),
                AppConstants.HandlerMessageIds.LOGIN, loginRequest.toJSONObject())
                .execute();
    }

    @Override
    public void OnNetworkResponseReceived(RequestStatus status, APIRequest requestType, Object responseObject) {
        System.out.println("OnNetworkResponseReceived: " + status + "|" + requestType);
        if(requestType == APIRequest.LOGIN){
            if(RequestStatus.SUCCESS == status){
                showMain();
            }

            if(progressDialog != null){
                progressDialog.dismiss();
            }
        }
    }

    private void showMain(){
        Intent intent = new Intent(this, YieldMangerActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
    }

    private LoginRequest getLoginRequest(){
        String org = etOrg.getText().toString();
        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        return new LoginRequest(org, userName, password);
    }

    private void getRiskRules(){
        new HttpGetAsyncTask(handler, YMApplication.getRiskRulesUrl(loginRequest),
                AppConstants.HandlerMessageIds.RULE)
                .execute();
    }

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            String response = (String)msg.obj;
            System.out.println("Response: " + response);

            if(response != null && response.isEmpty() == false){
                if(response.equals(AppConstants.STATUS_FAILURE)){
                    Toast.makeText(getApplicationContext(), R.string.error_cannot_connect_to_server, Toast.LENGTH_LONG).show();

                    return;
                }

                try {
                    JSONObject data = new JSONObject(response);

                    if(msg.what == AppConstants.HandlerMessageIds.LOGIN){

                        if(data.get(AppConstants.STATUS_TEXT).equals(AppConstants.STATUS_OK)){

                            Log.d(TAG, "Login is successful");

                            getRiskRules();
                        }
                    }

                    if(msg.what == AppConstants.HandlerMessageIds.RULE){

                        Log.d(TAG, "Risk Rules received");
                        RulesCache.getInstance().updateCache(data);
                        showMain();
                    }
                }
                catch (JSONException jex){
                    Log.e(TAG, "Exception in handleMessage: " + jex.getMessage());
                    jex.printStackTrace();
                }
            }
        }
    };
}


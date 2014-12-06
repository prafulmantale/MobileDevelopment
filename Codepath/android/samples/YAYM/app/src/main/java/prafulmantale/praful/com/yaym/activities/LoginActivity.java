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
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.application.YMApplication;
import prafulmantale.praful.com.yaym.asynctasks.HttpGetAsyncTask;
import prafulmantale.praful.com.yaym.asynctasks.HttpPostAsyncTask;
import prafulmantale.praful.com.yaym.caches.RefDataCache;
import prafulmantale.praful.com.yaym.caches.RulesCache;
import prafulmantale.praful.com.yaym.helpers.AppConstants;
import prafulmantale.praful.com.yaym.helpers.NetworkUtils;
import prafulmantale.praful.com.yaym.models.LoginRequest;


public class LoginActivity extends Activity{

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText etOrg;
    private EditText etUserName;
    private EditText etPassword;
    private Button btnLogin;
    private ProgressDialog progressDialog;
    private RadioGroup serverGroup;
    private YMApplication application;
    private CheckBox cbRememberMe;

    private LoginRequest loginRequest = null;


    private Animation fadeOutAnimationSignUpBar;
    private Animation fadeInAnimationSignUpBar;

    private Animation fadeOutAnimationLoginPanel;
    private Animation fadeInAnimationLoginPanel;

    private LinearLayout llSignUpBar;
    private RelativeLayout rlCenter;
    private RelativeLayout rlWelcomeMessage;
    private TextView tvCopyright;
    private Typeface typeface;
    private RelativeLayout containerPanel;
    private Button launchLogin;
    private Button btnSignup;
    private Button btnCancel;



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

        fadeInAnimationLoginPanel.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                rlWelcomeMessage.setVisibility(View.GONE);
                rlCenter.setVisibility(View.VISIBLE);
                tvCopyright.setVisibility(View.VISIBLE);
                containerPanel.setBackgroundColor(getResources().getColor(R.color.white));
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeOutAnimationLoginPanel.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                rlCenter.setVisibility(View.GONE);
                tvCopyright.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeInAnimationSignUpBar.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llSignUpBar.setVisibility(View.VISIBLE);
                rlWelcomeMessage.setVisibility(View.VISIBLE);
                containerPanel.setBackgroundDrawable(getResources().getDrawable(R.drawable.welcome_backgroud));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeOutAnimationSignUpBar.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llSignUpBar.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initialize(){

        containerPanel = (RelativeLayout)findViewById(R.id.containerPanel);

        typeface =Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf");

        TextView tvAppName = (TextView)findViewById(R.id.tvAppName);
        tvAppName.setTypeface(typeface);

        TextView tvAppUSP = (TextView)findViewById(R.id.tvAppUSP);
        tvAppUSP.setTypeface(typeface);

        launchLogin = (Button)findViewById(R.id.btnLoginWelcom);
        launchLogin.setTypeface(typeface);

        btnSignup = (Button)findViewById(R.id.btnSignUp);
        btnSignup.setTypeface(typeface);

        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnCancel.setTypeface(typeface);

        application = (YMApplication)getApplication();

        //??Read from shared preferences. Once user makes changes save them
        application.setYmServer(getString(R.string.server_demo));

        etOrg = (EditText)findViewById(R.id.etOrganization);
        etOrg.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Roboto-Thin.ttf"));
        etUserName = (EditText)findViewById(R.id.etUserName);
        etUserName.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Roboto-Thin.ttf"));
        etPassword = (EditText)findViewById(R.id.etPassword);
        etPassword.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Roboto-Thin.ttf"));

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setTypeface(typeface);

        serverGroup = (RadioGroup)findViewById(R.id.rgServerGroup);

        RadioButton rbDemo = (RadioButton)findViewById(R.id.demoServer);
        rbDemo.setTypeface(typeface);

        RadioButton rbDemo3 = (RadioButton)findViewById(R.id.demo3Server);
        rbDemo3.setTypeface(typeface);

        cbRememberMe = (CheckBox)findViewById(R.id.cbRemember);
        cbRememberMe.setTypeface(typeface);

        fadeInAnimationLoginPanel = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_in);
        fadeOutAnimationLoginPanel = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_out);

        fadeInAnimationSignUpBar = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_in);
        fadeOutAnimationSignUpBar = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_out);

        llSignUpBar = (LinearLayout)findViewById(R.id.llSignUpBar);
        rlCenter = (RelativeLayout)findViewById(R.id.rlCenter);
        rlWelcomeMessage = (RelativeLayout)findViewById(R.id.rlWelcomeMessage);
        tvCopyright = (TextView)findViewById(R.id.tvCopyright);


    }

    private void initializeActionBar(){

        ActionBar actionBar = getActionBar();
        actionBar.hide();

//        View view = getLayoutInflater().inflate(R.layout.action_bar_title, null);
//
//        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
//                ActionBar.LayoutParams.MATCH_PARENT,
//                Gravity.CENTER);
//
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setHomeButtonEnabled(false);
//        actionBar.setCustomView(view, params);
//        actionBar.setDisplayShowHomeEnabled(true);
//
//        //Hack to hide the home icon -- Otherwise the action bar was getting displayed on top of Tabs
//        View homeIcon = findViewById(android.R.id.home);
//        ((View) homeIcon.getParent()).setVisibility(View.GONE);

    }

    public void doLogin(View view){

        if(!NetworkUtils.isNetworkAvailable(getBaseContext())){
            Toast.makeText(getApplicationContext(), R.string.error_cannot_connect_to_server, Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog = ProgressDialog.show(this, "", getString(R.string.login_progress_message));

        TextView tvPDMessage = (TextView)progressDialog.findViewById(android.R.id.message);
        tvPDMessage.setTypeface(typeface);

        try {

            savePreferences();
            loginRequest = getLoginRequest();

            YMApplication.appCookies = null;

            System.out.println(loginRequest.toJSONObject());
            new HttpPostAsyncTask(handler, YMApplication.getLoginUrl(),
                    AppConstants.HandlerMessageIds.LOGIN, loginRequest.toJSONObject())
                    .execute();

        }catch (Exception ex){
            if(progressDialog != null){
                progressDialog.dismiss();
            }
        }
    }

    private void showMain(){
        if(progressDialog != null){
            progressDialog.dismiss();
        }

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

    private void getRefData(){
        new HttpGetAsyncTask(handler, YMApplication.getRefDataUrl(),
                AppConstants.HandlerMessageIds.REFDATA)
                .execute();
    }

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            String response = (String)msg.obj;

            Log.d(TAG, "Response Message: .... \r\n" + response);

            if(response != null && response.isEmpty() == false){
                if(response.equals(AppConstants.STATUS_FAILURE)){

                    if(progressDialog != null){
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getApplicationContext(), R.string.error_cannot_connect_to_server, Toast.LENGTH_LONG).show();

                    return;
                }

                try {


                    if(msg.what == AppConstants.HandlerMessageIds.LOGIN){
                        JSONObject data = new JSONObject(response);
                        if(data.get(AppConstants.STATUS_TEXT).equals(AppConstants.STATUS_OK)){

                            Log.d(TAG, "Login is successful");

                            getRiskRules();
                        }
                    }

                    if(msg.what == AppConstants.HandlerMessageIds.RULE){
                        JSONObject data = new JSONObject(response);
                        Log.d(TAG, "Risk Rules received");
                        RulesCache.getInstance().updateCache(data);
                        getRefData();
                    }

                    if(msg.what == AppConstants.HandlerMessageIds.REFDATA){

                        Log.d(TAG, "Reference data received");
                        RefDataCache.getInstance().updateCache(new JSONArray(response));
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(getCurrentFocus() == null || getCurrentFocus().getWindowToken() == null){
            return super.onTouchEvent(event);
        }
        InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        return true;
    }


    public void showLogin(View view){

        if(view.getId() == R.id.btnLoginWelcom){
            llSignUpBar.startAnimation(fadeOutAnimationSignUpBar);
            rlCenter.startAnimation(fadeInAnimationLoginPanel);
        }
    }

    public void hideLogin(View view){
        rlCenter.startAnimation(fadeOutAnimationLoginPanel);
        llSignUpBar.startAnimation(fadeInAnimationSignUpBar);
    }
}


package duggleetech.com.guardianangel.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import duggleetech.com.guardianangel.R;
import duggleetech.com.guardianangel.utils.Constants;


public class LoginActivity extends Activity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText etMobileNumber;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvForgotPassword;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        getActionBar().hide();

        initialize();
    }


    private void initialize(){

        initializeViews();
        setupListeners();
    }


    private void initializeViews(){

        etMobileNumber = (EditText)findViewById(R.id.etMobileNumber);
        etPassword = (EditText)findViewById(R.id.etPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        tvForgotPassword = (TextView)findViewById(R.id.tvForgotPassword);
        tvRegister = (TextView)findViewById(R.id.tvRegister);
    }


    private void setupListeners() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doLogin();
            }
        });


        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegistration();
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showForgotPassword();

            }
        });
    }

    private void showRegistration(){

        Intent intent = new Intent(getBaseContext(), RegistrationActivity.class);
        startActivity(intent);

        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_from_right);
    }

    private void showForgotPassword(){
        Intent intent = new Intent(this, ForgotPasswdActivity.class);

        String mobNumber = etMobileNumber.getText().toString();

        if(mobNumber != null && mobNumber.trim().isEmpty() == false){
            intent.putExtra(Constants.INTENT_MOB_NUM_KEY, mobNumber);
        }

        startActivity(intent);

        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_from_right);
    }

    private void doLogin(){
        boolean isValid = validateInput();

        if(!isValid){
            return;
        }

        showMain();
    }


    private void showMain(){

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(getCurrentFocus() == null || getCurrentFocus().getWindowToken() == null){
            return super.onTouchEvent(event);
        }

        InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        return true;
    }


    private boolean validateInput(){

        String mobNumber = etMobileNumber.getText().toString();
        String pass = etPassword.getText().toString();

        if(mobNumber == null || mobNumber.trim().isEmpty() ||
                pass == null || pass.trim().isEmpty()){

            Toast.makeText(this, getString(R.string.login_input_error), Toast.LENGTH_LONG).show();

        }


        return true;
    }
}

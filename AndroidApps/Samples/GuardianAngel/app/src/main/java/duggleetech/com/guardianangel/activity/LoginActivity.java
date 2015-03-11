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

        getActionBar().hide();

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
                Intent intent = new Intent(getBaseContext(), RegistrationActivity.class);
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_from_right);
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Forgot password", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void doLogin(){
        boolean isValid = validateInput();

        if(!isValid){
            return;
        }

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

        if(mobNumber == null || mobNumber.isEmpty() ||
                pass == null || pass.isEmpty()){

            Toast.makeText(this, getString(R.string.login_input_error), Toast.LENGTH_LONG).show();

        }


        return true;
    }
}

package duggleetech.com.guardianangel.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import duggleetech.com.guardianangel.R;
import duggleetech.com.guardianangel.utils.Constants;

public class ForgotPasswdActivity extends Activity {

    private static final String TAG = ForgotPasswdActivity.class.getSimpleName();

    private EditText etMobileNumber;
    private Button btnResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_passwd);

        getActionBar().hide();

        initialize();
    }


    private void initialize(){

        initializeViews();
        setupListeners();

        Intent intent = getIntent();

        if(intent != null && intent.getExtras() != null && intent.hasExtra(Constants.INTENT_MOB_NUM_KEY)){
            String mobileNumber = intent.getStringExtra(Constants.INTENT_MOB_NUM_KEY);

            if(mobileNumber != null && !mobileNumber.trim().isEmpty()){
                etMobileNumber.setText(mobileNumber);
            }
        }
    }


    private void initializeViews(){

        etMobileNumber = (EditText)findViewById(R.id.etMobileNumber);
        btnResetPassword = (Button)findViewById(R.id.btnResetPassword);
    }


    private void setupListeners() {

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doResetPassword();
            }
        });
    }

    private void doResetPassword() {

        if(!validateInput()){
            return;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
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

        if(mobNumber == null || mobNumber.trim().isEmpty()){

            Toast.makeText(this, getString(R.string.forgot_pwd_input_error), Toast.LENGTH_LONG).show();

        }


        return true;
    }

}

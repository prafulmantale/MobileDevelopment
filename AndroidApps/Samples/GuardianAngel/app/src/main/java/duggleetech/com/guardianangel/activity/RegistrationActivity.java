package duggleetech.com.guardianangel.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import duggleetech.com.guardianangel.R;


public class RegistrationActivity extends Activity {

    private static final String TAG = RegistrationActivity.class.getSimpleName();

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etMobileNumber;
    private EditText etPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getActionBar().hide();

        initialize();
    }


    private void initialize(){

        initializeViews();
        setupListeners();
    }


    private void initializeViews(){

        etFirstName = (EditText)findViewById(R.id.etFirstName);
        etLastName = (EditText)findViewById(R.id.etLastName);
        etMobileNumber = (EditText)findViewById(R.id.etMobileNumber);
        etPassword = (EditText)findViewById(R.id.etPassword);
        btnRegister = (Button)findViewById(R.id.btnRegister);
    }


    private void setupListeners() {

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doRegistration();
            }
        });
    }

    private void doRegistration(){
        boolean isValid = validateInput();

        if(!isValid){
            return;
        }

    }

    private boolean validateInput(){

        String mobNumber = etMobileNumber.getText().toString();
        String pass = etPassword.getText().toString();
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();

        if(firstName == null || firstName.isEmpty() ||
               lastName == null || lastName.isEmpty() ||
                mobNumber == null || mobNumber.isEmpty() ||
                pass == null || pass.isEmpty()){

            Toast.makeText(this, getString(R.string.registration_input_error), Toast.LENGTH_LONG).show();

        }


        return true;
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
}

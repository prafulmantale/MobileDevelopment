package duggleetech.com.guardianangel.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import duggleetech.com.guardianangel.R;


public class WelcomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //ActionBar actionBar = ;


    }

}

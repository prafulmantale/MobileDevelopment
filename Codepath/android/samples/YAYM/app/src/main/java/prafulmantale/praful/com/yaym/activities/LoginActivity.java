package prafulmantale.praful.com.yaym.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.PersistentCookieStore;

import org.apache.http.cookie.Cookie;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.enums.APIRequest;
import prafulmantale.praful.com.yaym.enums.RequestStatus;
import prafulmantale.praful.com.yaym.handlers.NetworkResponseHandler;
import prafulmantale.praful.com.yaym.interfaces.NetworkResponseListener;
import prafulmantale.praful.com.yaym.models.LoginRequest;
import prafulmantale.praful.com.yaym.networking.RestClient;

public class LoginActivity extends Activity  implements NetworkResponseListener{

    public static RestClient client;
    private EditText etOrg;
    private EditText etUserName;
    private EditText etPassword;
    private Button btnLogin;

    public static PersistentCookieStore cookieStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cookieStore = new PersistentCookieStore(this);
        client = new RestClient(cookieStore);

        initialize();
        initializeActionBar();
    }

    private void initialize(){

        etOrg = (EditText)findViewById(R.id.etOrganization);
        etOrg.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Roboto-Thin.ttf"));
        etUserName = (EditText)findViewById(R.id.etUserName);
        etUserName.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Roboto-Thin.ttf"));
        etPassword = (EditText)findViewById(R.id.etPassword);
        etPassword.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Roboto-Thin.ttf"));

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Roboto-Bold.ttf"));

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

        client.login(this, new NetworkResponseHandler(this, APIRequest.LOGIN), getLoginRequest());
    }

    @Override
    public void OnNetworkResponseReceived(RequestStatus status, APIRequest requestType, Object responseObject) {
        System.out.println("OnNetworkResponseReceived: " + status + "|" + requestType);
        if(requestType == APIRequest.LOGIN){
            if(RequestStatus.SUCCESS == status){
                System.out.println("PersistentCookieStore: ");
                for(Cookie cookie : cookieStore.getCookies()){
                    System.out.println(cookie.getName() + "|" + cookie.getValue());
                }
                showMain();
            }
        }
    }

    private void showMain(){
        Intent intent = new Intent(this, YieldMangerActivity.class);
        startActivity(intent);
    }

    private LoginRequest getLoginRequest(){
        String org = etOrg.getText().toString();
        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        return new LoginRequest(org, userName, password);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


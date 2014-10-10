package prafulmantale.praful.com.yaym.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

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

    private PersistentCookieStore cookieStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cookieStore = new PersistentCookieStore(this);
        client = new RestClient(cookieStore);

        initialize();
    }

    private void initialize(){

        etOrg = (EditText)findViewById(R.id.etOrganization);
        etUserName = (EditText)findViewById(R.id.etUserName);
        etPassword = (EditText)findViewById(R.id.etPassword);
    }

    public void doLogin(View view){

        client.login(this, new NetworkResponseHandler(this, APIRequest.LOGIN), getLoginRequest());
    }

    @Override
    public void OnNetworkResponseReceived(RequestStatus status, APIRequest requestType, Object responseObject) {
        if(requestType == APIRequest.LOGIN){
            if(RequestStatus.SUCCESS == status){
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
        getMenuInflater().inflate(R.menu.login, menu);
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


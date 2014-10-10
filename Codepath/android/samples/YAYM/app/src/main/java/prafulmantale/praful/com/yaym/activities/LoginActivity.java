package prafulmantale.praful.com.yaym.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.models.LoginRequest;
import prafulmantale.praful.com.yaym.networking.RestClient;

public class LoginActivity extends Activity {

    private RestClient client;
    private EditText etOrg;
    private EditText etUserName;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        client = new RestClient();

        initialize();
    }

    private void initialize(){

        etOrg = (EditText)findViewById(R.id.etOrganization);
        etUserName = (EditText)findViewById(R.id.etUserName);
        etPassword = (EditText)findViewById(R.id.etPassword);
    }

    public void doLogin(View view){

        new DownloadFilesTask().execute("","","");

//        new RestClient().login(new JsonHttpResponseHandler(){
//            @Override
//            public void onSuccess(JSONArray response) {
//                super.onSuccess(response);
//            }
//
//            @Override
//            public void onSuccess(int statusCode, JSONObject response) {
//                super.onSuccess(statusCode, response);
//            }
//
//            @Override
//            public void onSuccess(int statusCode, JSONArray response) {
//                super.onSuccess(statusCode, response);
//            }
//
//            @Override
//            public void onFailure(Throwable e, JSONObject errorResponse) {
//                super.onFailure(e, errorResponse);
//            }
//
//            @Override
//            public void onFailure(Throwable e, JSONArray errorResponse) {
//                super.onFailure(e, errorResponse);
//            }
//
//            @Override
//            protected void handleFailureMessage(Throwable e, String responseBody) {
//                super.handleFailureMessage(e, responseBody);
//            }
//        }, getLoginRequest());

        // Load CAs from an InputStream
// (could be from a resource or ByteArrayInputStream or ...)
//        try {
////            CertificateFactory cf = CertificateFactory.getInstance("X.509");
////// From https://www.washington.edu/itconnect/security/ca/load-der.crt
////            InputStream caInput = new BufferedInputStream(new FileInputStream("load-der.crt"));
////            Certificate ca;
////            try {
////                ca = cf.generateCertificate(caInput);
////                System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
////            } finally {
////                caInput.close();
////            }
////
////// Create a KeyStore containing our trusted CAs
////            String keyStoreType = KeyStore.getDefaultType();
////            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
////            keyStore.load(null, null);
////            keyStore.setCertificateEntry("ca", ca);
////
////// Create a TrustManager that trusts the CAs in our KeyStore
////            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
////            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
////            tmf.init(keyStore);
////
////// Create an SSLContext that uses our TrustManager
////            SSLContext context = SSLContext.getInstance("TLS");
////            context.init(null, tmf.getTrustManagers(), null);
//
//// Tell the URLConnection to use a SocketFactory from our SSLContext
//            URL url = new URL("https://demo3.ym.integral.net/fxi/admin/auth/login");
//
//            HttpsURLConnection urlConnection =
//                    (HttpsURLConnection) url.openConnection();
//            //urlConnection.setSSLSocketFactory(context.getSocketFactory());
//
//            String urlParameters = getLoginRequest().toJSON();
//            //add reuqest header
//            urlConnection.setRequestMethod("POST");
//            urlConnection.setRequestProperty("Accept-Encoding", "gzip, compress");
//            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//            urlConnection.setRequestProperty("Content-Length", String.valueOf(urlParameters.length()));
//            // Send post request
//            urlConnection.setDoOutput(true);
//            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
//            wr.writeBytes(urlParameters);
//            wr.flush();
//            wr.close();
//
//            System.out.println(getResponse(urlConnection));
//            //InputStream in = urlConnection.getInputStream();
//            //copyInputStreamToOutputStream(in, System.out);
//        }
//        catch (Exception ex){
//            Log.d("EX", ex.getMessage());
//        }
//        finally {
//
//        }
    }

    private void showMain(){
        Intent intent = new Intent(this, YieldMangerActivity.class);
        startActivity(intent);
    }

    private class DownloadFilesTask extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... post) {

            try {
                URL url = new URL("https://demo3.ym.integral.net/fxi/admin/auth/login");

                HttpsURLConnection urlConnection =
                        (HttpsURLConnection) url.openConnection();
                //urlConnection.setSSLSocketFactory(context.getSocketFactory());

                String urlParameters = getLoginRequest().toJSON();
                //add reuqest header
                //urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                urlConnection.setRequestProperty("Content-Length", String.valueOf(urlParameters.length()));
                // Send post request
                urlConnection.setUseCaches(false);
                urlConnection.setDoOutput(true);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setReadTimeout(15000);
                urlConnection.connect();
                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

                System.out.println(getResponse(urlConnection));
            }
            catch (Exception ex){
                ex.printStackTrace();
            }

            return "";
        }

        private  String getResponse(HttpsURLConnection con)
        {
            String inputLine;
            StringBuffer response = new StringBuffer();
            BufferedReader in = null;
            try {
                if(con.getHeaderField("Content-Encoding") != null && con.getHeaderField("Content-Encoding").equalsIgnoreCase("gzip")){
                    GZIPInputStream gzis = new GZIPInputStream(con.getInputStream());
                    InputStreamReader reader = new InputStreamReader(gzis);
                    in = new BufferedReader(reader);
                }
                else
                {
                    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                }

                while ((inputLine = in.readLine()) != null)
                    response.append(inputLine);

            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            finally {
                try {
                    if(in != null)
                        in.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            return response.toString();
        }

//        protected void onProgressUpdate(Integer... progress) {
//            //setProgressPercent(progress[0]);
//        }
//
        protected void onPostExecute(String result) {
            showMain();
        }
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


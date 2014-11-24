package prafulmantale.praful.com.yaym.asynctasks;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.MalformedURLException;

import prafulmantale.praful.com.yaym.application.YMApplication;
import prafulmantale.praful.com.yaym.helpers.AppConstants;

/**
 * Created by prafulmantale on 11/20/14.
 */
public class HttpGetAsyncTask extends AsyncTask<String, Void, String> {

    private static final String TAG = HttpGetAsyncTask.class.getSimpleName();
    private Handler handler;
    private String uri;
    private int what = -1;

    public HttpGetAsyncTask(Handler handler, String uri, int what) {
        this.handler = handler;
        this.uri = uri;
        this.what = what;
    }

    @Override
    protected String doInBackground(String... params) {

        Message response = new Message();
        boolean isSuccess = false;
        String responseString = null;
        DefaultHttpClient client = null;

        try{

            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
            HttpConnectionParams.setSoTimeout(httpParams, 5000);

            client = new DefaultHttpClient(httpParams);


            if (YMApplication.appCookies != null) {
                for (Cookie ck : YMApplication.appCookies) {
                    client.getCookieStore().addCookie(ck);
                }
            }

            HttpGet get = new HttpGet(uri);
            get.setHeader(HTTP.CONTENT_TYPE, "application/json");
            HttpResponse httpResp  = client.execute(get);

            if (httpResp.getEntity() != null) {
                responseString = EntityUtils.toString(httpResp.getEntity());
            }

            int statusCode = httpResp.getStatusLine().getStatusCode();

            if(statusCode == HttpStatus.SC_OK) {
                isSuccess = true;
            }
        }
        catch (MalformedURLException mfex){
            Log.e(TAG, "Exception: " + mfex.getMessage());
            mfex.printStackTrace();
        }
        catch (IOException ioex){
            Log.e(TAG, "Exception: " + ioex.getMessage());
            ioex.printStackTrace();
        }
        finally {

            if(client != null) {
                client.getConnectionManager().closeExpiredConnections();
            }

            response.what = what;
            response.obj = isSuccess ? responseString : AppConstants.STATUS_FAILURE;
            if(handler != null) {
                handler.sendMessage(response);
            }
        }

        return isSuccess ? AppConstants.STATUS_SUCCESS : AppConstants.STATUS_FAILURE;
    }
}

package prafulmantale.praful.com.yaym.asynctasks;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

import prafulmantale.praful.com.yaym.application.YMApplication;
import prafulmantale.praful.com.yaym.helpers.AppConstants;

/**
 * Created by prafulmantale on 11/20/14.
 */
public class HttpPostAsyncTask extends AsyncTask<String, Void, String> {

    private static final String TAG = HttpPostAsyncTask.class.getSimpleName();

    private JSONObject data;
    private Handler handler;
    private String uri;
    private int what;


    public HttpPostAsyncTask(Handler handler, String uri, int what, JSONObject data) {
        this.handler = handler;
        this.uri = uri;
        this.what = what;
        this.data = data;
    }

    @Override
    protected String doInBackground(String... params) {

        boolean isSuccess = false;
        Message response = new Message();
        String responseString = null;
        DefaultHttpClient client = null;

        try{

            client = new DefaultHttpClient();
            HttpPost post = new HttpPost(uri);
            post.setHeader(HTTP.CONTENT_TYPE, "application/json");

            StringEntity entity = new StringEntity(data.toString());
            post.setEntity(entity);

            HttpResponse httpResponse = client.execute(post);
            if (httpResponse.getEntity() != null) {
                responseString = EntityUtils.toString(httpResponse.getEntity());
            }

            isSuccess = true;
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

            if(client != null){
                client.getConnectionManager().closeExpiredConnections();
            }

            response.what = what;
            response.obj = isSuccess ? responseString : AppConstants.STATUS_FAILURE;
            handler.sendMessage(response);
        }

        if(isSuccess && what == AppConstants.HandlerMessageIds.LOGIN){
            YMApplication.appCookies = client.getCookieStore().getCookies();
        }

        return isSuccess ? AppConstants.STATUS_SUCCESS : AppConstants.STATUS_FAILURE;
    }
}

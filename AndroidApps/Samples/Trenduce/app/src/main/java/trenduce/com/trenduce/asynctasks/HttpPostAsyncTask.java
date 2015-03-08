package trenduce.com.trenduce.asynctasks;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import trenduce.com.trenduce.Utils.Constants;

/**
 * Created by prafulmantale on 3/7/15.
 */
public class HttpPostAsyncTask extends AsyncTask<Void, Void, String> {


    private static final String TAG = HttpPostAsyncTask.class.getSimpleName();

    private JSONObject data;
    private Handler handler;
    private String uri;
    private int what;


    public HttpPostAsyncTask(JSONObject data, Handler handler, String uri, int what) {
        this.data = data;
        this.handler = handler;
        this.uri = uri;
        this.what = what;
    }


    @Override
    protected String doInBackground(Void... params) {

        Message message = new Message();
        boolean isSuccess = false;

        String respString = null;

        DefaultHttpClient client = null;

        try{

            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
            HttpConnectionParams.setSoTimeout(httpParams, 5000);

            client = new DefaultHttpClient(httpParams);

            HttpPost post = new HttpPost(uri);
            post.setHeader(HTTP.CONTENT_TYPE, "application/json");

            StringEntity entity = new StringEntity(data.toString());
            post.setEntity(entity);

            HttpResponse response = client.execute(post);

            if (response.getEntity() != null) {
                respString = EntityUtils.toString(response.getEntity());
            }

            int statusCode = response.getStatusLine().getStatusCode();

            if(statusCode == HttpStatus.SC_OK){
                isSuccess = true;
            }

            Log.d(TAG, "Response String: " + respString + "\nStatus Code: " + statusCode + "\nIsSuccess: " + isSuccess);

        }
        catch (MalformedURLException mex){

        }
        catch (UnsupportedEncodingException uex){

        }
        catch (IOException ioex){

        }
        finally {

            if(client != null){
                client.getConnectionManager().closeExpiredConnections();
            }

            message.what = what;
            message.obj = (respString != null) ? respString : Constants.STATUS_FAILURE;
            handler.sendMessage(message);
        }

//        if(isSuccess && what == AppConstants.HandlerMessageIds.LOGIN){
//            YMApplication.appCookies = client.getCookieStore().getCookies();
//        }

        return isSuccess ? Constants.STATUS_SUCCESS : Constants.STATUS_FAILURE;
    }
}

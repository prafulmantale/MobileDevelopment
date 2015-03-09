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
public class HttpPostAsyncTask extends AsyncTask<Void, Void, Integer> {


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
    protected Integer doInBackground(Void... params) {

        Message message = new Message();
        boolean isSuccess = false;

        String respString = null;

        DefaultHttpClient client = null;

        int statusCode = -1;

        try{

            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
            HttpConnectionParams.setSoTimeout(httpParams, 5000);

            client = new DefaultHttpClient(httpParams);

            HttpPost post = new HttpPost(uri);
            post.setHeader(HTTP.CONTENT_TYPE, "application/json");

            StringEntity entity = new StringEntity(data.toString());
            post.setEntity(entity);

            Log.d(TAG, "URI:" + uri + "\nRequest data: " + data.toString());

            HttpResponse response = client.execute(post);

            if (response.getEntity() != null) {
                respString = EntityUtils.toString(response.getEntity());
            }

            statusCode = response.getStatusLine().getStatusCode();

            Log.d(TAG, "Response String: " + respString + "\nStatus Code: " + statusCode + "\nIsSuccess: " + isSuccess);

        }
        catch (MalformedURLException mex){

            Log.d(TAG, "MalformedURLException is thrown. Something is wrong");
        }
        catch (UnsupportedEncodingException uex){
            Log.d(TAG, "UnsupportedEncodingException is thrown. Something is wrong");
        }
        catch (IOException ioex){

            return statusCode;
        }
        finally {

            if(client != null){
                client.getConnectionManager().closeExpiredConnections();
            }

            message.what = what;
            message.obj = (respString != null) ? respString : Constants.STATUS_FAILURE;
            handler.sendMessage(message);
        }

        if(statusCode != HttpStatus.SC_OK){
            Log.d(TAG, "Something is wrong" + statusCode);
        }

        return statusCode;
    }
}

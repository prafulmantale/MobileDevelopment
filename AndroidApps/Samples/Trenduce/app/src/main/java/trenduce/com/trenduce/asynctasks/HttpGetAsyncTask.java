package trenduce.com.trenduce.asynctasks;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.MalformedURLException;

import trenduce.com.trenduce.Utils.Constants;

/**
 * Created by prafulmantale on 3/7/15.
 */
public class HttpGetAsyncTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = HttpGetAsyncTask.class.getSimpleName();

    private Handler handler;
    private String uri;
    private int what;


    public HttpGetAsyncTask(Handler handler, String uri, int what) {
        this.handler = handler;
        this.uri = uri;
        this.what = what;
    }

    @Override
    protected String doInBackground(Void... params) {

        Message message = new Message();
        boolean isSuccess = false;

        String respString = null;

        DefaultHttpClient client = null ;

        try{

            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
            HttpConnectionParams.setSoTimeout(httpParams, 5000);

            client = new DefaultHttpClient(httpParams);


//            if (YMApplication.appCookies != null) {
//                for (Cookie ck : YMApplication.appCookies) {
//                    client.getCookieStore().addCookie(ck);
//                }
//            }

            HttpGet get = new HttpGet(uri);
            get.setHeader(HTTP.CONTENT_TYPE, "application/json");
            HttpResponse httpResp  = client.execute(get);

            if (httpResp.getEntity() != null) {
                respString = EntityUtils.toString(httpResp.getEntity());
            }

            int statusCode = httpResp.getStatusLine().getStatusCode();


            if(statusCode == HttpStatus.SC_OK) {
                isSuccess = true;
            }

            Log.d(TAG, "Response String: " + respString + "\nStatus Code: " + statusCode + "\nIsSuccess: " + isSuccess);
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

            message.what = what;
            message.obj = isSuccess ? respString : Constants.STATUS_FAILURE;
            if(handler != null) {
                handler.sendMessage(message);
            }
        }

        return isSuccess ? Constants.STATUS_SUCCESS : Constants.STATUS_FAILURE;

    }
}

package prafulmantale.praful.com.yaym.asynctasks;

import android.os.AsyncTask;
import android.os.Message;

import org.apache.http.HttpResponse;
import org.json.JSONObject;

import java.util.logging.Handler;

import prafulmantale.praful.com.yaym.helpers.AppConstants;

/**
 * Created by prafulmantale on 11/20/14.
 */
public class HttpGetAsyncTask extends AsyncTask<String, Void, String> {

    private static final String TAG = HttpGetAsyncTask.class.getSimpleName();
    private JSONObject data;
    private Handler handler;
    private String uri;
    private int what = -1;

    public HttpGetAsyncTask(JSONObject data, Handler handler, String uri, int what) {
        this.data = data;
        this.handler = handler;
        this.uri = uri;
        this.what = what;
    }

    @Override
    protected String doInBackground(String... params) {

        Message response = new Message();
        String status = AppConstants.STATUS_FAILURE;

        try{

            HttpResponse httpResponse = null;


        }
        catch (Exception ex){

        }

        return status;
    }
}

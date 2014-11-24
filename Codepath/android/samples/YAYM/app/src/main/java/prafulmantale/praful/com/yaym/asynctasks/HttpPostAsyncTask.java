package prafulmantale.praful.com.yaym.asynctasks;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

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

        HttpURLConnection connection = null;
        try{
            URL url = new URL(uri);
            connection = (HttpURLConnection)url.openConnection();

            connection.setRequestProperty("Content-Type", "application/json");

            connection.setDoOutput(true);
            connection.setChunkedStreamingMode(0);

            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(data.toString());
            out.flush();
            out.close();

            responseString = readStream(connection);

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
            if(connection != null){
                connection.disconnect();
            }

            response.what = what;
            response.obj = isSuccess ? responseString : AppConstants.STATUS_FAILURE;
            handler.sendMessage(response);
        }

        if(isSuccess && what == AppConstants.HandlerMessageIds.LOGIN){
            //Store cookies
        }

        return isSuccess ? AppConstants.STATUS_SUCCESS : AppConstants.STATUS_FAILURE;
    }

    private String readStream(HttpURLConnection connection){

        String inputLine = null;
        StringBuffer response = new StringBuffer();
        BufferedReader in = null;


        try{
            InputStream inputStream = connection.getInputStream();

            if(connection.getHeaderField("Content-Encoding") != null &&
                    connection.getHeaderField("Content-Encoding").equalsIgnoreCase("gzip")){

                GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
                InputStreamReader reader = new InputStreamReader(gzipInputStream);

                in = new BufferedReader(reader);
            }
            else{
                in = new BufferedReader(new InputStreamReader(inputStream));
            }

            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
        }
        catch (IOException ioex){
            Log.e(TAG, "Exception: " + ioex.getMessage());
            ioex.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ioExp){
                Log.e(TAG, "Exception: " + ioExp.getMessage());
            }
        }

        return response.toString();
    }
}

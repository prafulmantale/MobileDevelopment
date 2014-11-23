package prafulmantale.praful.com.yaym.asynctasks;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
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
        HttpURLConnection connection = null;
        boolean isSuccess = false;
        String responseString = null;

        try{

            URL url = new URL(this.uri);

            Log.d(TAG, "Sending request for: " + uri);

            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");

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

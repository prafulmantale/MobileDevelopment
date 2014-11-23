package prafulmantale.praful.com.yaym.helpers;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class Utils {

    private final static String TAG = Utils.class.getSimpleName();

    private Utils(){

    }


    public static String getAPIUrl(String appURL, String apiUrl){
       return appURL + apiUrl;
    }


}

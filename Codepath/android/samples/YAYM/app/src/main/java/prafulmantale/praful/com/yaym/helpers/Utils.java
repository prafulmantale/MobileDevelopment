package prafulmantale.praful.com.yaym.helpers;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.net.URL;
import java.net.URLConnection;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class Utils {

    private Utils(){

    }


    public static String getAPIUrl(String appURL, String apiUrl){

       return appURL + apiUrl;
    }


}

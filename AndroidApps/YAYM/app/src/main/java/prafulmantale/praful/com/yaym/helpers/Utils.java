package prafulmantale.praful.com.yaym.helpers;

import android.graphics.Paint;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.application.YMApplication;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class Utils {

    private final static String TAG = Utils.class.getSimpleName();

    public static final DecimalFormat zeroPrecisionFormat = new DecimalFormat("##,###");
    public static SimpleDateFormat scaleTimeFormat = new SimpleDateFormat("HH:mm");

    static
    {
        scaleTimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private Utils(){

    }


    public static String getAPIUrl(String appURL, String apiUrl){
       return appURL + apiUrl;
    }


}

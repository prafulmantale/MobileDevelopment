package prafulmantale.praful.com.yaym.helpers;

import java.text.DecimalFormat;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class Utils {

    private final static String TAG = Utils.class.getSimpleName();

    public static final DecimalFormat zeroPrecisionFormat = new DecimalFormat("##,###");

    private Utils(){

    }


    public static String getAPIUrl(String appURL, String apiUrl){
       return appURL + apiUrl;
    }


}

package prafulmantale.praful.com.yaym.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by prafulmantale on 10/9/14.
 * Utilities related to Network
 */
public class NetworkUtils {


    /*
        Get Active Network info
     */
    public static NetworkInfo getActiveNetworkInfo(Context context){

        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return manager.getActiveNetworkInfo();
    }


    public static boolean isNetworkAvailable(Context context){

        NetworkInfo networkInfo = getActiveNetworkInfo(context);

        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }

    public static boolean isConnectedToWifi(Context context){

        return (isNetworkAvailable(context) && getActiveNetworkInfo(context).getType() == ConnectivityManager.TYPE_WIFI);
    }

    public static boolean isConnectedToProvider(Context context){
        return (isNetworkAvailable(context) && getActiveNetworkInfo(context).getType() == ConnectivityManager.TYPE_MOBILE);
    }



}

package praful.com.kidsonbus.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by prafulmantale on 4/11/15.
 */
public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private NetworkUtils(){

    }

    public static NetworkInfo getActiveNetworkInfo(Context context){
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo();
    }

    public static boolean isNetworkAvailable(Context context){
        NetworkInfo networkInfo = getActiveNetworkInfo(context);
        return (networkInfo!= null && networkInfo.isConnected());
    }

    public static boolean isConnectedToWifi(Context context){
        return (isNetworkAvailable(context) && getActiveNetworkInfo(context).getType() == ConnectivityManager.TYPE_WIFI);
    }

    public static boolean isConnectedToProvider(Context context){
        return (isNetworkAvailable(context) && getActiveNetworkInfo(context).getType() == ConnectivityManager.TYPE_MOBILE);
    }
}

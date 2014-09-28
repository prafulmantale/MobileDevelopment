package prafulmantale.praful.com.twitterapp.helpers;

import java.net.URLConnection;

/**
 * Created by prafulmantale on 9/26/14.
 */
public class Utils {

    private Utils(){

    }

    public static boolean isNetworkAvailable(){
        boolean isAvailable = false;

//        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivity != null)
//        {
//            NetworkInfo[] info = connectivity.getAllNetworkInfo();
//            if (info != null)
//                for (int i = 0; i < info.length; i++)
//                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
//                    {
//                        return true;
//                    }
//
//        }
//        return false;

        return isAvailable;
    }
}

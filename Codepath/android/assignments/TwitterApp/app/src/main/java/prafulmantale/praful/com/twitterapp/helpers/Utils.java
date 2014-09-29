package prafulmantale.praful.com.twitterapp.helpers;

import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

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

    public static String getElapsedDisplayTime(long createdTime){
        String display = "";

        Date date = new Date();
        date.setTime(createdTime * 1000);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date nowDate = calendar1.getTime();

        long diff = nowDate.getTime() - date.getTime();

        long hrDiff = TimeUnit.MILLISECONDS.toHours(diff);
        long minDiff = TimeUnit.MILLISECONDS.toMinutes(diff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diff));
        if(hrDiff > 0){
            display = String.format("%dh", hrDiff);
        }
        else{
            if(minDiff < 0){
                minDiff = 0;
            }

            display = String.format("%dm", minDiff);
        }


        return display;
    }
}

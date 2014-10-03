package prafulmantale.praful.com.twitterapp.helpers;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by prafulmantale on 9/26/14.
 */
public class Utils {



    public static DisplayImageOptions roundedImageOptions = new DisplayImageOptions.Builder()
            .cacheInMemory()
            .displayer(new RoundedBitmapDisplayer(10))
            .cacheOnDisc()
            .build();

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
        date.setTime(createdTime);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date nowDate = calendar1.getTime();

        long diff = nowDate.getTime() - date.getTime();

        long dayDiff = TimeUnit.MILLISECONDS.toDays(diff);

        if(dayDiff > 0){
            display = String.format("%dd", dayDiff);
        }
        else {
            long hrDiff = TimeUnit.MILLISECONDS.toHours(diff);
            long minDiff = TimeUnit.MILLISECONDS.toMinutes(diff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diff));
            if (hrDiff > 0) {
                display = String.format("%dh", hrDiff);
            } else {
                if (minDiff < 0) {
                    minDiff = 0;
                }

                display = String.format("%dm", minDiff);
            }
        }

        return display;
    }

    public static String getFormattedCountDisplay(long count){

        if(count >= AppConstants.MILLION){
            return getFormattedCountDisplay(count, AppConstants.MILLION, AppConstants.MILLION_SYM);
        }

        if(count >= AppConstants.THOUSAND){
            return getFormattedCountDisplay(count, AppConstants.THOUSAND, AppConstants.THOUSAND_SYM);
        }


        return String.valueOf(count);
    }

    private static String getFormattedCountDisplay(long count, long unit, String symbol){
        double tempDisplaycount = count;
        tempDisplaycount = count/unit;
        return String.valueOf(tempDisplaycount) + symbol;
    }
}

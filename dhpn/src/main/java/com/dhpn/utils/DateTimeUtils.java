package com.dhpn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by prafulmantale on 3/1/15.
 */
public class DateTimeUtils {

    private static SimpleDateFormat DISPLAY_TIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private static final String TAG = DateTimeUtils.class.getSimpleName();

    private DateTimeUtils(){

    }

    public static String getFormattedCurrentTime(){

        String formattedTime = "";

        try {

            formattedTime = DISPLAY_TIME_FORMAT.format(new Date());
        }
        catch (Exception ex){

            System.out.println("Exception in " + TAG + ".getFormattedCurrentTime: " + ex.getMessage());
            ex.printStackTrace();
        }

        return formattedTime;
    }
}

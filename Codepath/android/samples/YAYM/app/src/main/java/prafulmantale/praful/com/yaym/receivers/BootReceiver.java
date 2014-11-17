package prafulmantale.praful.com.yaym.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import prafulmantale.praful.com.yaym.services.RefreshService;

/**
 * Created by prafulmantale on 11/16/14.
 */
public class BootReceiver extends BroadcastReceiver {

    private static final String TAG = BootReceiver.class.getSimpleName();
    private PendingIntent lastPendingIntent = null;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "onReceive");

        //When user says auto log me in, start the service, not need to login again with user intervention
        //Login using intent service

       //context.startService(new Intent(context, RWPollService.class));

        //Take the interval from preferences

        PendingIntent pendingIntent = PendingIntent.getService(context, 101, new Intent(context, RefreshService.class), PendingIntent.FLAG_UPDATE_CURRENT );
        AlarmManager manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        if(lastPendingIntent != null) {
            manager.cancel(lastPendingIntent);
        }

        lastPendingIntent = pendingIntent;

        manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, System.currentTimeMillis(), 3000, pendingIntent);
    }
}

package prafulmantale.praful.com.yaym.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by prafulmantale on 11/16/14.
 */
public class ConnectivityChangeReceiver extends BroadcastReceiver {

    private static final String TAG = ConnectivityChangeReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "onReceive");

        //Stop polling when the connectivity changes

    }
}

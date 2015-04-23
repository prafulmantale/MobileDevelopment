package praful.com.kidsonwheels.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import praful.com.kidsonwheels.utils.NetworkUtils;

/**
 * Created by prafulmantale on 4/22/15.
 */
public class NetworkStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

       boolean isNetworkAvailable = NetworkUtils.isNetworkAvailable(context);
        if(isNetworkAvailable){
            //all is fine in network world
        }else{
            //Inform app about the network connectivity change
        }
    }
}

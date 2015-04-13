package praful.com.kidsonbus.application;

import android.app.Application;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import praful.com.kidsonbus.caches.ApplicationData;
import praful.com.kidsonbus.utils.Constants;

/**
 * Created by prafulmantale on 4/12/15.
 */
public class KOBApplication extends Application {

    private static final String TAG = KOBApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationData.getInstance();
    }
}

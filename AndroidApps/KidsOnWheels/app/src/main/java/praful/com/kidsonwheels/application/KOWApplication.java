package praful.com.kidsonwheels.application;

import android.app.Application;
import android.content.Context;

import praful.com.kidsonwheels.manager.DirectionsManager;

/**
 * Created by prafulmantale on 4/12/15.
 */
public class KOWApplication extends Application {

    private static final String TAG = KOWApplication.class.getSimpleName();
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        DirectionsManager.getInstance().initialize();
    }

    //Global - process level context
    public static Context getContext(){
        return mContext;
    }
}

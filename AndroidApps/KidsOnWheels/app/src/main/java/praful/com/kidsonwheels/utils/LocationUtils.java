package praful.com.kidsonwheels.utils;

import android.content.Context;
import android.location.LocationManager;

/**
 * Created by prafulmantale on 4/19/15.
 */
public class LocationUtils {

    private static LocationManager mLocationManager = null;

    public static boolean isLocationProviderAvailable(Context context){
        return (isGPSLocationProviderEnabled(context) || isNetworkLocationProviderEnabled(context));
    }

    public static boolean isGPSLocationProviderEnabled(Context context){
        return getLocationManager(context).isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static boolean isNetworkLocationProviderEnabled(Context context){
        return getLocationManager(context).isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private static LocationManager getLocationManager(Context context){
        if(mLocationManager == null){
            mLocationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        }

        return mLocationManager;
    }
}

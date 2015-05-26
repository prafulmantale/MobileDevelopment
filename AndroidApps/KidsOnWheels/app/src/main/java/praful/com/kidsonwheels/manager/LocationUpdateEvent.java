package praful.com.kidsonwheels.manager;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by prafulmantale on 5/26/15.
 */
public class LocationUpdateEvent {
    private LatLng mLatLang;

    public LocationUpdateEvent(double latitude, double longitude){
        mLatLang = new LatLng(latitude, longitude);
    }

    public LocationUpdateEvent(LatLng latLng){
        mLatLang = latLng;
    }

    public LatLng getLatLang(){
        return mLatLang;
    }
}

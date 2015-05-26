package praful.com.kidsonwheels.manager;

/**
 * Created by prafulmantale on 5/26/15.
 */
public class LocationUpdateEvent {
    private double mLatitude;
    private double mLongitude;

    public LocationUpdateEvent(double latitude, double longitude){
        mLatitude = latitude;
        mLongitude = longitude;
    }
}

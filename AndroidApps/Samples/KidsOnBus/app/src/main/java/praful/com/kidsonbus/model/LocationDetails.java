package praful.com.kidsonbus.model;

/**
 * Created by prafulmantale on 4/11/15.
 */
public class LocationDetails {

    private float mLatitude;

    private float mLongitude;

    public LocationDetails(float latitude, float longitude) {
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public float getLatitude(){
        return mLatitude;
    }

    public void setLatitude(float latitude){
        mLatitude = latitude;
    }

    public float getLongitude(){
        return mLongitude;
    }

    public void setLongitude(float longitude){
        mLongitude = longitude;
    }

    @Override
    public String toString() {
        return "LocationDetails{" +
                "mLatitude=" + mLatitude +
                ", mLongitude=" + mLongitude +
                '}';
    }
}

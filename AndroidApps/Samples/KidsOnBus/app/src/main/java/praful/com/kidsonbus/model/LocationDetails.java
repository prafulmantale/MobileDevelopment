package praful.com.kidsonbus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by prafulmantale on 4/11/15.
 */
public class LocationDetails {

    @JsonProperty("lat")
    private double mLatitude;

    @JsonProperty("lng")
    private double mLongitude;

    public LocationDetails() {
    }

    public LocationDetails(double latitude, double longitude) {
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public double getLatitude(){
        return mLatitude;
    }

    public void setLatitude(double latitude){
        mLatitude = latitude;
    }

    public double getLongitude(){
        return mLongitude;
    }

    public void setLongitude(double longitude){
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

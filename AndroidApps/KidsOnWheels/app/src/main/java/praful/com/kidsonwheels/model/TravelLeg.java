package praful.com.kidsonwheels.model;

import android.graphics.Color;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prafulmantale on 4/12/15.
 */
public class TravelLeg {

    @JsonProperty("distance")
    private Distance mDistance;

    @JsonProperty("duration")
    private Duration mDuration;

    @JsonProperty("start_address")
    private String mStartAddress;

    @JsonProperty("end_address")
    private String mEndAddress;

    @JsonProperty("start_location")
    private LocationDetails mStartLocation;

    @JsonProperty("end_location")
    private LocationDetails mEndLocation;

    @JsonProperty("steps")
    private List<TravelStep> mSteps;

    private PolylineOptions mPolylineOptions;


    public Distance getDistance() {
        return mDistance;
    }

    public void setDistance(Distance distance) {
        mDistance = distance;
    }

    public Duration getDuration() {
        return mDuration;
    }

    public void setDuration(Duration duration) {
        mDuration = duration;
    }

    public String getStartAddress() {
        return mStartAddress;
    }

    public void setStartAddress(String startAddress) {
        mStartAddress = startAddress;
    }

    public String getEndAddress() {
        return mEndAddress;
    }

    public void setEndAddress(String endAddress) {
        mEndAddress = endAddress;
    }

    public LocationDetails getStartLocation() {
        return mStartLocation;
    }

    public void setStartLocation(LocationDetails startLocation) {
        mStartLocation = startLocation;
    }

    public LocationDetails getEndLocation() {
        return mEndLocation;
    }

    public void setEndLocation(LocationDetails endLocation) {
        mEndLocation = endLocation;
    }

    public List<TravelStep> getSteps() {
        return mSteps;
    }

    public void setSteps(List<TravelStep> steps) {
        mSteps = steps;
    }

    public PolylineOptions getPolylineOptions() {
        if(mPolylineOptions == null) {
            mPolylineOptions = new PolylineOptions();
            mPolylineOptions.width(10);
            mPolylineOptions.color(Color.BLUE);

            for(TravelStep step : getSteps()) {
                mPolylineOptions.addAll(PolyUtil.decode(step.getPolyline().getPoints()));
            }
        }
        return mPolylineOptions;
    }
    @Override
    public String toString() {
        return "TravelLeg{" +
                "mDistance=" + mDistance +
                ", mDuration=" + mDuration +
                ", mStartAddress='" + mStartAddress + '\'' +
                ", mEndAddress='" + mEndAddress + '\'' +
                ", mStartLocation=" + mStartLocation +
                ", mEndLocation=" + mEndLocation +
                ", mSteps=" + mSteps +
                '}';
    }
}

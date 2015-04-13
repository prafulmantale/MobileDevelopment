package praful.com.kidsonbus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    private LocationDetails mStartLocationDetails;

    @JsonProperty("end_location")
    private LocationDetails mEndLocationDetails;

    @JsonProperty("steps")
    private List<TravelStep> mSteps;


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

    public LocationDetails getStartLocationDetails() {
        return mStartLocationDetails;
    }

    public void setStartLocationDetails(LocationDetails startLocationDetails) {
        mStartLocationDetails = startLocationDetails;
    }

    public LocationDetails getEndLocationDetails() {
        return mEndLocationDetails;
    }

    public void setEndLocationDetails(LocationDetails endLocationDetails) {
        mEndLocationDetails = endLocationDetails;
    }

    public List<TravelStep> getSteps() {
        return mSteps;
    }

    public void setSteps(List<TravelStep> steps) {
        mSteps = steps;
    }

    @Override
    public String toString() {
        return "TravelLeg{" +
                "mDistance=" + mDistance +
                ", mDuration=" + mDuration +
                ", mStartAddress='" + mStartAddress + '\'' +
                ", mEndAddress='" + mEndAddress + '\'' +
                ", mStartLocationDetails=" + mStartLocationDetails +
                ", mEndLocationDetails=" + mEndLocationDetails +
                ", mSteps=" + mSteps +
                '}';
    }
}

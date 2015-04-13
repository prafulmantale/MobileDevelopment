package praful.com.kidsonbus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by prafulmantale on 4/12/15.
 */
public class TravelStep {

    @JsonProperty("distance")
    private Distance mDistance;

    @JsonProperty("duration")
    private Duration mDuration;

    @JsonProperty("html_instructions")
    private String mInstruction;

    @JsonProperty("travel_mode")
    private String mTravelMode;

    @JsonProperty("maneuver")
    private String mManeuver;

    @JsonProperty("start_location")
    private LocationDetails mStartLocationDetails;

    @JsonProperty("end_location")
    private LocationDetails mEndLocationDetails;


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

    public String getInstruction() {
        return mInstruction;
    }

    public void setInstruction(String instruction) {
        mInstruction = instruction;
    }

    public String getTravelMode() {
        return mTravelMode;
    }

    public void setTravelMode(String travelMode) {
        mTravelMode = travelMode;
    }

    public String getManeuver() {
        return mManeuver;
    }

    public void setManeuver(String maneuver) {
        mManeuver = maneuver;
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

    @Override
    public String toString() {
        return "TravelStep{" +
                "mDistance=" + mDistance +
                ", mDuration=" + mDuration +
                ", mInstruction='" + mInstruction + '\'' +
                ", mTravelMode='" + mTravelMode + '\'' +
                ", mManeuver='" + mManeuver + '\'' +
                '}';
    }
}

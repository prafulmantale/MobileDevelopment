package praful.com.kidsonwheels.model;

import android.text.Html;
import android.text.Spanned;

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
    private LocationDetails mStartLocation;

    @JsonProperty("end_location")
    private LocationDetails mEndLocation;

    @JsonProperty("polyline")
    private Routes.OverviewPolyLine mPolyline;

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

    public Spanned getDisplayInstruction(){
        return Html.fromHtml(mInstruction);
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

    public Routes.OverviewPolyLine getPolyline() {
        return mPolyline;
    }

    public void setPolyline(Routes.OverviewPolyLine polyline) {
        mPolyline = polyline;
    }

    @Override
    public String toString() {
        return "TravelStep{" +
                "mDistance=" + mDistance +
                ", mDuration=" + mDuration +
                ", mInstruction='" + mInstruction + '\'' +
                ", mTravelMode='" + mTravelMode + '\'' +
                ", mManeuver='" + mManeuver + '\'' +
                ", mStartLocation=" + mStartLocation +
                ", mEndLocation=" + mEndLocation +
                ", mPolyline=" + mPolyline +
                '}';
    }
}

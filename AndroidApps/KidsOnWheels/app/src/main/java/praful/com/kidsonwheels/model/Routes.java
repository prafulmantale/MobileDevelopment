package praful.com.kidsonwheels.model;

import android.graphics.Color;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.util.List;

/**
 * Created by prafulmantale on 4/12/15.
 */

public class Routes {

    @JsonProperty("legs")
    private List<TravelLeg> mLegs;

    @JsonProperty("waypoint_order")
    private List<Integer> mWayPointOrder;

    @JsonProperty("overview_polyline")
    private OverviewPolyLine mOverviewPolyLine;

    private PolylineOptions mPolylineOptions;

    public List<TravelLeg> getLegs() {
        return mLegs;
    }

    public void setLegs(List<TravelLeg> legs) {
        this.mLegs = legs;
    }

    public List<Integer> getWayPointOrder() {
        return mWayPointOrder;
    }

    public void setWayPointOrder(List<Integer> wayPointOrder) {
        this.mWayPointOrder = wayPointOrder;
    }

    public OverviewPolyLine getOverviewPolyLine() {
        return mOverviewPolyLine;
    }

    public void setOverviewPolyLine(OverviewPolyLine overviewPolyLine) {
        this.mOverviewPolyLine = overviewPolyLine;
    }

    public PolylineOptions getPolylineOptions() {
        if(mPolylineOptions == null) {
            mPolylineOptions = new PolylineOptions();
            mPolylineOptions.width(10);
            mPolylineOptions.color(Color.BLUE);
            mPolylineOptions.addAll(PolyUtil.decode(mOverviewPolyLine.getPoints()));
        }
        return mPolylineOptions;
    }

    @Override
    public String toString() {
        return "Routes{" +
                "mWayPointOrder=" + mWayPointOrder +
                ", mLegs=" + mLegs +
                '}';
    }

    public static class OverviewPolyLine {
        @JsonProperty("points")
        public String mPoints;

        public String getPoints() {
            return mPoints;
        }

        public void setPoints(String points) {
            mPoints = points;
        }
    }
}

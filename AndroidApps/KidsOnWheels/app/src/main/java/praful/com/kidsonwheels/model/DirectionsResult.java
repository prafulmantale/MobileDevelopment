package praful.com.kidsonwheels.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by prafulmantale on 4/12/15.
 */
public class DirectionsResult {

    @JsonProperty("routes")
    private List<Routes> mRoutes;

    public DirectionsResult() {
    }

    public List<Routes> getRoutes() {
        return mRoutes;
    }

    public void setRoutes(List<Routes> routes) {
        this.mRoutes = routes;
    }

    @Override
    public String toString() {
        return "DirectionsResult{" +
                "mRoutes=" + mRoutes +
                '}';
    }
}



package praful.com.kidsonwheels.manager;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.util.Collections;
import java.util.List;

import praful.com.kidsonwheels.application.KOWApplication;
import praful.com.kidsonwheels.model.DirectionsResult;
import praful.com.kidsonwheels.model.Routes;
import praful.com.kidsonwheels.model.Student;
import praful.com.kidsonwheels.service.FetchDirectionsService;
import praful.com.kidsonwheels.utils.Constants;

/**
 * Created by prafulmantale on 4/14/15.
 */
public class DirectionsManager implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = DirectionsManager.class.getSimpleName();
    private static DirectionsManager INSTANCE = new DirectionsManager();

    private GoogleApiClient mGoogleApiClient;
    private DirectionsResult mDirectionsResult;
    private LatLng mLastLocation;

    private DirectionsManager() {
    }

    public static DirectionsManager getInstance() {
        return INSTANCE;
    }

    public void initialize() {
        buildGoogleApiClient();
    }

    private synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(KOWApplication.getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();
    }

    public LatLng getLastLocation() {
        return mLastLocation;
    }

    public DirectionsResult getDirectionsResult() {
        return mDirectionsResult;
    }

    public void setDirectionsResult(DirectionsResult result) {
        mDirectionsResult = result;
        if (mDirectionsResult != null) {
            Routes routes = mDirectionsResult.getRoutes().get(0);
            List<Integer> order = routes.getWayPointOrder();
            List<Student> students = DataManager.getInstance().getStudents();
            for (int i = 0; i < order.size(); i++) {
                students.get(order.get(i)).setPickupOrder(i);
                students.get(order.get(i)).setDistance(routes.getLegs().get(i).getDistance().getText());
            }

            Collections.sort(students, Student.PickupOrderComparator);
        }
        Intent intent = new Intent(Constants.ROUTE_INFO_RECEIVED);
        KOWApplication.getContext().sendBroadcast(intent);
    }

    private void startIntentService() {
        Intent intent = new Intent(KOWApplication.getContext(), FetchDirectionsService.class);
        KOWApplication.getContext().startService(intent);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG, "GoogleApiClient Connection established");
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location != null) {
            mLastLocation = new LatLng(location.getLatitude(), location.getLongitude());
            Intent locationIntent = new Intent(Constants.LATEST_LOCATION_RECEIVED);
            LocalBroadcastManager.getInstance(KOWApplication.getContext()).sendBroadcast(locationIntent);
            startIntentService();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "GoogleApiClient Connection suspended");
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "GoogleApiClient Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }
}

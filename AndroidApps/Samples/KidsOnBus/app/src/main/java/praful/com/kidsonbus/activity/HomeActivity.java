package praful.com.kidsonbus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import praful.com.kidsonbus.R;
import praful.com.kidsonbus.caches.ApplicationData;
import praful.com.kidsonbus.fragment.PickupFragment;
import praful.com.kidsonbus.fragment.StudentsFragment;
import praful.com.kidsonbus.interfaces.FragmentActionsListener;
import praful.com.kidsonbus.utils.Constants;


public class HomeActivity extends ActionBarActivity implements FragmentActionsListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener/*, com.google.android.gms.location.LocationListener*/ {

    private static final String TAG = HomeActivity.class.getSimpleName();
//
//    protected static final String ADDRESS_REQUESTED_KEY = "address-request-pending";
//    protected static final String LOCATION_ADDRESS_KEY = "location-address";
//
    private GoogleApiClient mGoogleApiClient;
//    private LocationRequest mLocationRequest;
//
//    protected boolean mAddressRequested;
//    protected String mAddressOutput;
//    private AddressResultReceiver mResultReceiver;
//    private ProgressBar mProgressBar;
//    protected TextView mLocationAddressTextView;
//    Button mFetchAddressButton;
//
//    GoogleMap googleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setBackgroundDrawable(null);
        buildGoogleApiClient();

//        mResultReceiver = new AddressResultReceiver(new Handler());

        // Set defaults, then update using values stored in the Bundle.
//        mAddressRequested = false;
//        mAddressOutput = "";
//        updateValuesFromBundle(savedInstanceState);
//

//        createLocationRequest();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.activity_home_container, new StudentsFragment()).commit();
        }

//        try{
//            initializeMap();
//            new KOBRestClient().getDirections(googleMap);
//        }catch (Exception ex){
//
//        }
    }

    private synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getBaseContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

//    private void initializeMap(){
//
//        if(googleMap == null){
//            googleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
//        }
//
//        if(googleMap == null){
//            Toast.makeText(this, "No maps for you", Toast.LENGTH_LONG).show();
//        }
//
//    }

    private void updateValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
//            // Update the value of mRequestingLocationUpdates from the Bundle, and
//            // make sure that the Start Updates and Stop Updates buttons are
//            // correctly enabled or disabled.
//            if (savedInstanceState.keySet().contains(REQUESTING_LOCATION_UPDATES_KEY)) {
//                mRequestingLocationUpdates = savedInstanceState.getBoolean(
//                        REQUESTING_LOCATION_UPDATES_KEY);
//                setButtonsEnabledState();
//            }
//
//            // Update the value of mCurrentLocation from the Bundle and update the
//            // UI to show the correct latitude and longitude.
//            if (savedInstanceState.keySet().contains(LOCATION_KEY)) {
//                // Since LOCATION_KEY was found in the Bundle, we can be sure that
//                // mCurrentLocationis not null.
//                mCurrentLocation = savedInstanceState.getParcelable(LOCATION_KEY);
//            }
//
//            // Update the value of mLastUpdateTime from the Bundle and update the UI.
//            if (savedInstanceState.keySet().contains(LAST_UPDATED_TIME_STRING_KEY)) {
//                mLastUpdateTime = savedInstanceState.getString(
//                        LAST_UPDATED_TIME_STRING_KEY);
//            }
//            updateUI();


//            // Check savedInstanceState to see if the address was previously requested.
//            if (savedInstanceState.keySet().contains(ADDRESS_REQUESTED_KEY)) {
//                mAddressRequested = savedInstanceState.getBoolean(ADDRESS_REQUESTED_KEY);
//            }
//            // Check savedInstanceState to see if the location address string was previously found
//            // and stored in the Bundle. If it was found, display the address string in the UI.
//            if (savedInstanceState.keySet().contains(LOCATION_ADDRESS_KEY)) {
//                mAddressOutput = savedInstanceState.getString(LOCATION_ADDRESS_KEY);
//                displayAddressOutput();
//            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if(mGoogleApiClient != null && mGoogleApiClient.isConnected()){
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


//
//    @Override
//    public void onConnected(Bundle bundle) {
//         mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//        if(mLastLocation != null){
//            Log.d("Location", "Lat:" + String.valueOf(mLastLocation.getLatitude()) + "   Long: " + String.valueOf(mLastLocation.getLongitude()));
//        }
//
//        MarkerOptions marker = new MarkerOptions().position(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude())).title("My Location");
//        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//        googleMap.addMarker(marker);
//
//        for (Student student : ApplicationData.getInstance().getStudents()){
//            MarkerOptions marker1 = new MarkerOptions().position(new LatLng(student.getAddress().getLocationDetails().getLatitude(), student.getAddress().getLocationDetails().getLongitude())).title(student.getDisplayName());
//            marker1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//            googleMap.addMarker(marker1);
//        }
//
//        MarkerOptions marker4 = new MarkerOptions().position(new LatLng(ApplicationData.getInstance().getSchool().getAddress().getLocationDetails().getLatitude(), ApplicationData.getInstance().getSchool().getAddress().getLocationDetails().getLongitude())).title(ApplicationData.getInstance().getSchool().getName());
//        marker4.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//        googleMap.addMarker(marker4);
//
//        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude())).zoom(16).build();
//
//        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//
//        googleMap.setMyLocationEnabled(true);
//        googleMap.getUiSettings().setCompassEnabled(true);
//        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
//
//        startLocationUpdates();
//
//        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//        if (mLastLocation != null) {
//            // Determine whether a Geocoder is available.
//            if (!Geocoder.isPresent()) {
//                Toast.makeText(this, R.string.no_geocoder_available, Toast.LENGTH_LONG).show();
//                return;
//            }
////            if (mAddressRequested) {
//                startIntentService();
////            }
//        }
//
//
//    }
//
//    protected void startIntentService() {
//        // Create an intent for passing to the intent service responsible for fetching the address.
//        Intent intent = new Intent(this, FetchAddressIntentService.class);
//
//        // Pass the result receiver as an extra to the service.
//        intent.putExtra(Constants.RECEIVER, mResultReceiver);
//
//        // Pass the location data as an extra to the service.
//        intent.putExtra(Constants.LOCATION_DATA_EXTRA, mLastLocation);
//
//        // Start the service. If the service isn't already running, it is instantiated and started
//        // (creating a process for it if needed); if it is running then it remains running. The
//        // service kills itself automatically once all intents are processed.
//        startService(intent);
//    }
//
//    protected void displayAddressOutput() {
//        //mLocationAddressTextView.setText(mAddressOutput);
//        Toast.makeText(this,  mAddressOutput, Toast.LENGTH_LONG).show();
//    }
//
//

//
//
//    protected void createLocationRequest(){
//        mLocationRequest = new LocationRequest();
//        mLocationRequest.setInterval(10000);
//        mLocationRequest.setFastestInterval(5000);
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//    }
//
//    private void startLocationUpdates(){
////        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
//    }
//
//    private void stopLocationUpdates(){
////        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        stopLocationUpdates();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(mGoogleApiClient.isConnected()){
//            startLocationUpdates();
//        }
//        initializeMap();
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        if(location != null){
//            Toast.makeText(this, "Lat:" + String.valueOf(location.getLatitude()) + "   Long: " + String.valueOf(location.getLongitude()), Toast.LENGTH_SHORT).show();
//        }
//    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
////        outState.putBoolean(REQUESTING_LOCATION_UPDATES_KEY,
////                mRequestingLocationUpdates);
////        outState.putParcelable(LOCATION_KEY, mCurrentLocation);
////        outState.putString(LAST_UPDATED_TIME_STRING_KEY, mLastUpdateTime);
//
////        savedInstanceState.putBoolean(ADDRESS_REQUESTED_KEY, mAddressRequested);
////
////        // Save the address string.
////        savedInstanceState.putString(LOCATION_ADDRESS_KEY, mAddressOutput);
////
//        super.onSaveInstanceState(outState);
//    }


//    class AddressResultReceiver extends ResultReceiver {
//        public AddressResultReceiver(Handler handler) {
//            super(handler);
//        }
//
//        /**
//         *  Receives data sent from FetchAddressIntentService and updates the UI in MainActivity.
//         */
//        @Override
//        protected void onReceiveResult(int resultCode, Bundle resultData) {
//
//            // Display the address string or an error message sent from the intent service.
//            mAddressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
//            displayAddressOutput();
//
//            // Show a toast message if an address was found.
//            if (resultCode == Constants.SUCCESS_RESULT) {
//                showToast(getString(R.string.address_found));
//            }
//
//            // Reset. Enable the Fetch Address button and stop showing the progress bar.
//            //mAddressRequested = false;
////            updateUIWidgets();
//        }
//    }
//
//    protected void showToast(String text) {
//        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
//    }


    @Override
    public void onPickupRequested(int index) {
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_home_container, new PickupFragment()).commit();
    }

        @Override
    public void onConnected(Bundle bundle) {
         ApplicationData.getInstance().mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(ApplicationData.getInstance().mLastLocation != null){
            Intent locationIntent = new Intent(Constants.LAST_LOCATION_RECEIVED);
            LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(locationIntent);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }
}

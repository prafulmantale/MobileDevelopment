package praful.com.kidsonwheels.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import praful.com.kidsonwheels.R;
import praful.com.kidsonwheels.interfaces.DirectionsFragmentActionsListener;
import praful.com.kidsonwheels.manager.DataManager;
import praful.com.kidsonwheels.manager.DirectionsManager;
import praful.com.kidsonwheels.model.Student;
import praful.com.kidsonwheels.model.TravelLeg;
import praful.com.kidsonwheels.utils.Constants;

/**
 * Created by prafulmantale on 4/11/15.
 */
public class DirectionsFragment extends Fragment{

    private static final String TAG = DirectionsFragment.class.getSimpleName();
    private GoogleMap googleMap;
    private ImageView mAvatar;
    private TextView mName;
    private TextView mDistance;
    private TextView mAddress;
    private TextView mPickupState;
    private Button mNoShowButton;
    private Button mPickupButton;
    private Student mStudent;
    private DirectionsFragmentActionsListener mListener;
    private Button mShowStepsButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_directions, container, false);

        try {
            initializeMap();
        } catch (Exception ex) {
            Log.d(TAG, "Map initialization failed!!!");
        }

        mStudent = DataManager.getInstance().getStudents().get(DataManager.getInstance().getCurrentPickup());
        mStudent.setPickupState(Student.PickupState.ONWAY);

        setupViews(view);
        setListeners();
        populateData(mStudent);
        updateLocationData();
        updateRouteData();

        return view;
    }

    private void initializeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
        }
        if (googleMap == null) {
            Toast.makeText(getActivity(), "No maps for you", Toast.LENGTH_LONG).show();
        }
    }


    private void setupViews(View view) {
        mAvatar = (ImageView) view.findViewById(R.id.student_avatar);
        mName = (TextView) view.findViewById(R.id.student_name);
        mDistance = (TextView) view.findViewById(R.id.student_distance);
        mAddress = (TextView) view.findViewById(R.id.student_address);
        mPickupState = (TextView)view.findViewById(R.id.student_pickup_state);
        View divider = view.findViewById(R.id.end_divider);
        divider.setVisibility(View.VISIBLE);
        mPickupButton = (Button)view.findViewById(R.id.pickup_done);
        mNoShowButton = (Button)view.findViewById(R.id.student_no_show);

        View view1 = view.findViewById(R.id.action_buttons_layout);
        view1.setVisibility(View.VISIBLE);
        mShowStepsButton = (Button)view.findViewById(R.id.list_steps);
    }

    private void setListeners(){
        mNoShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStudent.setPickupState(Student.PickupState.NOSHOW);
                if(mListener != null) {
                    mListener.pickupStateUpdated(mStudent, Student.PickupState.NOSHOW);
                }
            }
        });

        mPickupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStudent.setPickupState(Student.PickupState.PICKEDUP);
                if(mListener != null) {
                    mListener.pickupStateUpdated(mStudent, Student.PickupState.PICKEDUP);
                }
            }
        });

        mShowStepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(mListener != null){
                   mListener.ShowTravelSteps();
               }
            }
        });
    }

    protected void populateData(Student student) {
        if (student == null) {
            return;
        }
        mName.setText(student.getDisplayName());
        mAddress.setText(student.getDisplayAddress());

        if (student.getAvatarId() != 0) {
            Picasso.with(getActivity()).load(student.getAvatarId()).into(mAvatar);
        } else {
            Picasso.with(getActivity()).load(android.R.color.transparent).into(mAvatar);
        }

        mPickupState.setText(student.getDisplayPickupState());
    }

    BroadcastReceiver mLastLocationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateLocationData();
        }
    };


    @Override
    public void onStart() {
        super.onStart();
        if(mLastLocationReceiver != null) {
            getActivity().registerReceiver(mLastLocationReceiver,
                    new IntentFilter(Constants.LATEST_LOCATION_RECEIVED));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mLastLocationReceiver != null) {
            getActivity().unregisterReceiver(mLastLocationReceiver);
        }
    }

    private void updateLocationData(){
        if (googleMap != null) {
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setCompassEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            LatLng myLocation = DirectionsManager.getInstance().getLastLocation();

            if(myLocation != null) {
//                MarkerOptions marker = new MarkerOptions().position(myLocation).title("My Location");
//                marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//                googleMap.addMarker(marker);

//                CameraPosition cameraPosition = new CameraPosition.Builder().target(myLocation).zoom(16).build();
//                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        }
    }

    private void updateRouteData(){
        if(googleMap != null
                && DirectionsManager.getInstance().getDirectionsResult() != null
                && DirectionsManager.getInstance().getDirectionsResult().getRoutes() != null
                && DirectionsManager.getInstance().getDirectionsResult().getRoutes().size() > 0)
        {
            TravelLeg leg = DirectionsManager.getInstance().getDirectionsResult().getRoutes().get(0).getLegs().get(DataManager.getInstance().getCurrentPickup());


            MarkerOptions markerO = new MarkerOptions().position(new LatLng(leg.getStartLocation().getLatitude(), leg.getStartLocation().getLongitude())).title(leg.getStartAddress());
            markerO.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            googleMap.addMarker(markerO);

            CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(leg.getStartLocation().getLatitude(), leg.getStartLocation().getLongitude())).zoom(16).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            MarkerOptions marker = new MarkerOptions().position(new LatLng(leg.getEndLocation().getLatitude(), leg.getEndLocation().getLongitude())).title(leg.getEndAddress());
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            googleMap.addMarker(marker);

            googleMap.addPolyline(leg.getPolylineOptions());
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (DirectionsFragmentActionsListener)getActivity();
    }
}

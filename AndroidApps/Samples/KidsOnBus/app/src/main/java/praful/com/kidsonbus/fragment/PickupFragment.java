package praful.com.kidsonbus.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import praful.com.kidsonbus.R;
import praful.com.kidsonbus.caches.ApplicationData;
import praful.com.kidsonbus.model.Student;
import praful.com.kidsonbus.rest.KOBRestClient;
import praful.com.kidsonbus.utils.Constants;

/**
 * Created by prafulmantale on 4/11/15.
 */
public class PickupFragment extends Fragment {

    private static final String TAG = PickupFragment.class.getSimpleName();
    private GoogleMap googleMap;
    private ImageView mAvatar;
    private TextView mName;
    private TextView mDistance;
    private TextView mAddress;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pickup, container, false);

        try {
            initializeMap();
        } catch (Exception ex) {
            Log.d(TAG, "Map initialization failed!!!");
        }

        setupViews(view);
        populateData(ApplicationData.getInstance().getStudents().get(0));
        updateLocationData();
        new KOBRestClient().getDirections(googleMap);
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
        getActivity().registerReceiver(mLastLocationReceiver,
                new IntentFilter(Constants.LAST_LOCATION_RECEIVED));
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(mLastLocationReceiver);
    }

    private void updateLocationData(){
        if (googleMap != null) {
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setCompassEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            Location myLocation = ApplicationData.getInstance().mLastLocation;
            MarkerOptions marker = new MarkerOptions().position(new LatLng(myLocation.getLatitude(), myLocation.getLongitude())).title("My Location");
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            googleMap.addMarker(marker);

            CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(myLocation.getLatitude(), myLocation.getLongitude())).zoom(16).build();

            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }
}

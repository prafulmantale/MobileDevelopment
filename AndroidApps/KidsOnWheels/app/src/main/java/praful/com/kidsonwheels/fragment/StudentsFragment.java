package praful.com.kidsonwheels.fragment;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import praful.com.kidsonwheels.R;
import praful.com.kidsonwheels.adapter.StudentsViewAdapter;
import praful.com.kidsonwheels.manager.DataManager;
import praful.com.kidsonwheels.manager.DirectionsManager;
import praful.com.kidsonwheels.model.Student;
import praful.com.kidsonwheels.utils.Constants;

/**
 * Created by praful.mantale on 4/14/15.
 */
public class StudentsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Student> mStudents;
    private StudentsViewAdapter mAdapter;
    private ProgressDialog progressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStudents = DataManager.getInstance().getToBePickedStudents();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(llm);
        mAdapter= new StudentsViewAdapter(mStudents);
        mRecyclerView.setAdapter(mAdapter);
        if(DirectionsManager.getInstance().getDirectionsResult() == null) {
            progressDialog = ProgressDialog.show(getActivity(), "", getString(R.string.home_screen_wait_message));
        }
        return view;
    }

    BroadcastReceiver mRouteInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //todo wrong place and logic
            Collections.sort(mStudents, Student.PickupOrderComparator);
            mAdapter.notifyDataSetChanged();
            if(progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        if(mRouteInfoReceiver != null) {
            getActivity().registerReceiver(mRouteInfoReceiver,
                    new IntentFilter(Constants.ROUTE_INFO_RECEIVED));
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mRouteInfoReceiver != null) {
            getActivity().unregisterReceiver(mRouteInfoReceiver);
        }
    }
}
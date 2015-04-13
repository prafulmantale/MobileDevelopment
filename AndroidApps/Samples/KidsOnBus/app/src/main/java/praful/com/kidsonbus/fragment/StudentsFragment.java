package praful.com.kidsonbus.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import praful.com.kidsonbus.R;
import praful.com.kidsonbus.adapter.StudentAdapter;
import praful.com.kidsonbus.caches.ApplicationData;
import praful.com.kidsonbus.interfaces.FragmentActionsListener;
import praful.com.kidsonbus.model.Student;

/**
 * Created by prafulmantale on 4/11/15.
 */
public class StudentsFragment extends Fragment {

    private List<Student> mStudents;
    private ListView mListView;

    private FragmentActionsListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStudents = ApplicationData.getInstance().getStudents();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students, container, false);

        setupViews(view);

        setupListeners();

        return view;
    }

    private void setupViews(View view){

        mListView = (ListView)view.findViewById(R.id.listview_students);
        StudentAdapter adapter = new StudentAdapter(getActivity(), mStudents);
        mListView.setAdapter(adapter);
    }

    private void setupListeners(){

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mListener != null){
                    mListener.onPickupRequested(position);
                }
            }
        });
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mListener = (FragmentActionsListener)getActivity();
    }
}

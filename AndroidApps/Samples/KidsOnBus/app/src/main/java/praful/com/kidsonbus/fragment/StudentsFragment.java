package praful.com.kidsonbus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import praful.com.kidsonbus.R;
import praful.com.kidsonbus.adapter.StudentAdapter;
import praful.com.kidsonbus.model.Address;
import praful.com.kidsonbus.model.Student;

/**
 * Created by prafulmantale on 4/11/15.
 */
public class StudentsFragment extends Fragment {

    private List<Student> students;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        students = new ArrayList<Student>();
        populateStudents();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students, container, false);

        ListView listView = (ListView)view.findViewById(R.id.listview_students);
        StudentAdapter adapter = new StudentAdapter(getActivity(), students);
        listView.setAdapter(adapter);

        return view;
    }



    private void populateStudents(){
        students.add(new Student("Reyansh", "Mantale", R.drawable.reyansh, new Address("600", "Rainbow Drive", "Mountain View")));
        students.add(new Student("Praful", "Mantale", R.drawable.praful, new Address("870", "East El Camino Real", "Mountain View")));
        students.add(new Student("Ritu", "Raj", R.drawable.ritu, new Address("450", "North Mathilda Avenue", "Sunnyvale")));
    }
}

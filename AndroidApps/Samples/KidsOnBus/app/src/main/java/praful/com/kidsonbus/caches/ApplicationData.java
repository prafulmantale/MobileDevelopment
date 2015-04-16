package praful.com.kidsonbus.caches;

import android.location.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import praful.com.kidsonbus.R;
import praful.com.kidsonbus.model.Address;
import praful.com.kidsonbus.model.DirectionsResult;
import praful.com.kidsonbus.model.LocationDetails;
import praful.com.kidsonbus.model.School;
import praful.com.kidsonbus.model.Student;
import praful.com.kidsonbus.rest.KOBRestClient;

/**
 * Created by prafulmantale on 4/11/15.
 */
public final class ApplicationData {

    private static final String TAG = ApplicationData.class.getSimpleName();
    private static final ApplicationData INSTANCE = new ApplicationData();
    private School mSchool;
    private String mMyLastLocation;

    private List<Student> students;

    public DirectionsResult mDirectionsResult;

    public Location mLastLocation;

    private ApplicationData(){
        students = new ArrayList<Student>();

        //Populate seed data
        populateStudents();

        populateSchoolDetails();
    }

    public static ApplicationData getInstance(){
        return INSTANCE;
    }

    public School getSchool(){
        return mSchool;
    }

    public void setSchool(School school){
        mSchool = school;
    }


    public List<Student> getStudents(){
        return Collections.unmodifiableList(students);
    }

    public void setLastLocation(Location location){
    }


    private void populateStudents(){
        Student student1 = new Student("Tom", "Cruise", R.drawable.one, new Address("101", "Green Street", "San Francisco", "CA"));
        students.add(student1);

        Student student2 = new Student("Julia", "Cary", R.drawable.two, new Address("850", "Cherry Avenue", "San Bruno", "CA"));
        students.add(student2);

        Student student3 = new Student("Hrithik", "Roshan", R.drawable.three, new Address("450", "North Mathilda Avenue", "Sunnyvale", "CA"));
        students.add(student3);

        Student student4 = new Student("Kate", "Williams", R.drawable.four, new Address("3400", "HillsView Ave", "Palo Alto", "CA"));
        students.add(student4);

        Student student5 = new Student("Jenny", "Smith", R.drawable.five, new Address("23445", "Camino Hermoso Dr", "Los Altos", "CA"));
        students.add(student5);

        Student student6 = new Student("Leonardo", "deCaprio", R.drawable.six, new Address("600", "W California Ave", "Sunnyvale", "CA"));
        students.add(student6);

        Collections.sort(students, Student.NameComparator);

        updateLatLang();
    }

    private void populateSchoolDetails(){
        mSchool = new School("Cupertino High School", new Address("21370", "Homestead Rd", "Cupertino", "CA"));
        mSchool.getAddress().setLocationDetails(new LocationDetails(37.337203, -122.0497469));
    }

    private void updateLatLang(){

        for (Student student : students){
            new KOBRestClient().getLatLang(student.getAddress());
        }
    }
}

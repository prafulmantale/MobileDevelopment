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
        Student student1 = new Student("Reyansh", "Mantale", R.drawable.reyansh, new Address("600", "Rainbow Drive", "Mountain View", "CA"));
        student1.getAddress().setLocationDetails(new LocationDetails(37.379016, -122.065538));
        students.add(student1);

        Student student2 = new Student("Praful", "Mantale", R.drawable.praful, new Address("870", "East El Camino Real", "Mountain View", "CA"));
        student2.getAddress().setLocationDetails(new LocationDetails(37.3767882, -122.060383));
        students.add(student2);

        Student student3 = new Student("Ritu", "Raj", R.drawable.ritu, new Address("450", "North Mathilda Avenue", "Sunnyvale", "CA"));
        student3.getAddress().setLocationDetails(new LocationDetails(37.3869826, -122.0300896));
        students.add(student3);

        Student student4 = new Student("Harpal", "Sandhu", R.drawable.ritu, new Address("3400", "HillsView Ave", "Palo Alto", "CA"));
        student4.getAddress().setLocationDetails(new LocationDetails(37.4041546, -122.1481243));
        students.add(student4);

        Student student5 = new Student("Nav", "Sandhu", R.drawable.ritu, new Address("23445", "Camino Hermoso Dr", "Los Altos", "CA"));
        student5.getAddress().setLocationDetails(new LocationDetails(37.341106, -122.111097));
        students.add(student5);

        Collections.sort(students, Student.NameComparator);
    }

    private void populateSchoolDetails(){
        mSchool = new School("Cupertino High School", new Address("21370", "Homestead Rd", "Cupertino", "CA"));
        mSchool.getAddress().setLocationDetails(new LocationDetails(37.337203, -122.0497469));
    }

//    private void updateLatLang(){
//
//        for (Student student : students){
//            new KOBRestClient().getLatLang(student.getAddress());
//        }
//    }
}

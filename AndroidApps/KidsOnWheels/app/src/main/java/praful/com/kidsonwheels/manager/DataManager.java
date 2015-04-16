package praful.com.kidsonwheels.manager;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import praful.com.kidsonwheels.R;
import praful.com.kidsonwheels.model.Address;
import praful.com.kidsonwheels.model.School;
import praful.com.kidsonwheels.model.Student;

/**
 * Created by prafulmantale on 4/14/15.
 */
public class DataManager {

    private static final String TAG = DataManager.class.getSimpleName();

    private static DataManager INSTANCE = new DataManager();

    private List<Student> mStudents;
    private School mSchool;
    private Student mHeader;

    private DataManager() {
        mStudents = new ArrayList<>();
//        mHeader = new Student("","",0,null);
//        mHeader.setHeader(true);
//        mStudents.add(mHeader);

        populateSeedData(); //Fetch from server
    }

    public static DataManager getInstance() {
        return INSTANCE;
    }

    public List<Student> getStudents() {
        return mStudents;
    }

    public List<Student> getToBePickedStudents() {
        List<Student> students = new ArrayList<>();
        for (Student student : mStudents) {
            if (student.getPickupState() == Student.PickupState.NONE ||
                    student.getPickupState() == Student.PickupState.ONWAY) {
                students.add(student);
            }
        }

        return students;
    }

    public School getSchoolDetails() {
        return mSchool;
    }

    /*
    todo - improe logic by maintaining local state
     */
    public int getCurrentPickup() {
        for (int i = 0; i < mStudents.size(); i++) {
            Student student = mStudents.get(i);
            if (student.getPickupState() == Student.PickupState.NONE ||
                    student.getPickupState() == Student.PickupState.ONWAY) {
                return i;
            }
        }
        return -1;
    }

    /*
    todo - improe logic by maintaining local state
     */
    public boolean isAllDone() {
        for (int i = 0; i < mStudents.size(); i++) {
            Student student = mStudents.get(i);
            if (student.getPickupState() == Student.PickupState.NONE ||
                    student.getPickupState() == Student.PickupState.ONWAY) {
                return false;
            }
        }

        return true;
    }


    private void populateSeedData() {
        populateSchoolDetails();
        populateStudents();
    }

    private void populateStudents() {

//        Address: Address{mCountry='', mState='CA', mCity='Sunnyvale', mStreet='North Mathilda Avenue', mHouseNumber='450', mPinCode='', mLocationDetails= LocationDetails
//            {mLatitude=37.3869826, mLongitude=-122.0300896}}
//        Address: Address{mCountry='', mState='CA', mCity='San Bruno', mStreet='Cherry Avenue', mHouseNumber='850', mPinCode='',
// mLocationDetails=LocationDetails{mLatitude=37.6273511, mLongitude=-122.424687}}
//        Address: Address{mCountry='', mState='CA', mCity='Palo Alto', mStreet='HillsView Ave', mHouseNumber='3400', mPinCode='',
// mLocationDetails=LocationDetails{mLatitude=37.4041546, mLongitude=-122.1481243}}
//        Address: Address{mCountry='', mState='CA', mCity='Los Altos', mStreet='Camino Hermoso Dr', mHouseNumber='23445', mPinCode='',
// mLocationDetails=LocationDetails{mLatitude=37.341106, mLongitude=-122.111097}}
//        Address: Address{mCountry='', mState='CA', mCity='San Francisco', mStreet='Green Street', mHouseNumber='101', mPinCode='',
// mLocationDetails=LocationDetails{mLatitude=37.8001545, mLongitude=-122.401883}}
//         Address: Address{mCountry='', mState='CA', mCity='Sunnyvale', mStreet='W California Ave', mHouseNumber='600', mPinCode='',
// mLocationDetails=LocationDetails{mLatitude=37.3820513, mLongitude=-122.03559}}

        Student student1 = new Student("Tom", "Cruise", R.drawable.one, new Address("101", "Green Street", "San Francisco", "CA"));
        student1.getAddress().setLocation(new LatLng(37.8001545, -122.401883));
        mStudents.add(student1);

        Student student2 = new Student("Julia", "Cary", R.drawable.two, new Address("850", "Cherry Avenue", "San Bruno", "CA"));
        student2.getAddress().setLocation(new LatLng(37.6273511, -122.424687));
        mStudents.add(student2);

        Student student3 = new Student("Hrithik", "Roshan", R.drawable.three, new Address("450", "North Mathilda Avenue", "Sunnyvale", "CA"));
        student3.getAddress().setLocation(new LatLng(37.3869826, -122.0300896));
        mStudents.add(student3);

        Student student4 = new Student("Kate", "Williams", R.drawable.four, new Address("3400", "HillsView Ave", "Palo Alto", "CA"));
        student4.getAddress().setLocation(new LatLng(37.4041546, -122.1481243));
        mStudents.add(student4);

        Student student5 = new Student("Jenny", "Smith", R.drawable.five, new Address("23445", "Camino Hermoso Dr", "Los Altos", "CA"));
        student5.getAddress().setLocation(new LatLng(37.341106, -122.111097));
        mStudents.add(student5);

        Student student6 = new Student("Leonardo", "deCaprio", R.drawable.six, new Address("600", "W California Ave", "Sunnyvale", "CA"));
        student6.getAddress().setLocation(new LatLng(37.3820513, -122.03559));
        mStudents.add(student6);

        Collections.sort(mStudents, Student.NameComparator);
    }

    private void populateSchoolDetails() {
        mSchool = new School("Cupertino High School", new Address("21370", "Homestead Rd", "Cupertino", "CA"));
        mSchool.getAddress().setLocation(new LatLng(37.337203, -122.0497469));
    }

    //    private void populateLatLang(){
//
//        for (Student student : students){
//            new KOBRestClient().getLatLang(student.getAddress());
//        }
//    }

}

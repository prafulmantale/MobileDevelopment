package praful.com.kidsonbus.model;

import android.text.TextUtils;

import java.util.Comparator;

/**
 * Created by prafulmantale on 4/11/15.
 */
public class Student {

    private String mFirstName;

    private String mLastName;

    private int mAvatarId;

    private Address mAddress;

    private String mDisplayName;

    public Student(String firstName, String lastName, int avatarId, Address address) {
        mFirstName = firstName;
        mLastName = lastName;
        mAvatarId = avatarId;
        mAddress = address;
        mDisplayName = "";
    }

    public String getFirstName() {
        return mFirstName;
    }


    public String getLastName() {
        return mLastName;
    }

    public int getAvatarId(){
        return mAvatarId;
    }

    public Address getAddress(){
        return mAddress;
    }

    public String getDisplayName(){
        if(TextUtils.isEmpty(mDisplayName)){
            StringBuilder sb = new StringBuilder();
            if(!TextUtils.isEmpty(mFirstName)){
                sb.append(mFirstName);
            }
            if(!TextUtils.isEmpty(mLastName)){
                 sb.append(" ").append(mLastName);
            }
            mDisplayName = sb.toString();
            if(TextUtils.isEmpty(mDisplayName)){
                mDisplayName = "";
            }

            mDisplayName = mDisplayName.toUpperCase();
        }

        return mDisplayName;
    }

    public String getDisplayAddress(){
        return mAddress.getDisplayAddress();
    }


    @Override
    public String toString() {
        return "Student{" +
                "mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mAvatarId=" + mAvatarId +
                ", mAddress=" + mAddress +
                '}';
    }


    public static Comparator<Student> NameComparator = new Comparator<Student>() {
        @Override
        public int compare(Student lhs, Student rhs) {
             if(lhs.mFirstName.compareTo(rhs.mFirstName) == 0){
                return lhs.mLastName.compareTo(rhs.mLastName);
             }

            return lhs.mFirstName.compareTo(rhs.mFirstName);
        }
    };
}

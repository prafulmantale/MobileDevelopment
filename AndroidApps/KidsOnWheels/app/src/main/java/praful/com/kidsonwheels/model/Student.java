package praful.com.kidsonwheels.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.Comparator;

/**
 * Created by prafulmantale on 4/11/15.
 */
public class Student {

    public enum PickupState {
        NONE(""),
        ONWAY("On the way"),
        NOSHOW("No show"),
        PICKEDUP("Picked");

        private String mValue;

        PickupState(String value) {
            mValue = value;
        }

        public String getValue() {
            return mValue;
        }
    }

    private String mFirstName;
    private String mLastName;
    private int mAvatarId;
    private Address mAddress;
    private String mDisplayName;
    private boolean bHeader;
    private PickupState mPickupState;
    private int mPickupOrder;
    private String mDistance;

    public Student(@NonNull String firstName, @NonNull String lastName, @NonNull int avatarId, @NonNull Address address) {
        mFirstName = firstName;
        mLastName = lastName;
        mAvatarId = avatarId;
        mAddress = address;
        mDisplayName = "";
        mPickupState = PickupState.NONE;
        mDistance = "";
    }

    public String getFirstName() {
        return mFirstName;
    }


    public String getLastName() {
        return mLastName;
    }

    public int getAvatarId() {
        return mAvatarId;
    }

    public Address getAddress() {
        return mAddress;
    }

    public boolean isHeader() {
        return bHeader;
    }

    public void setHeader(boolean header) {
        bHeader = header;
    }

    public PickupState getPickupState() {
        return mPickupState;
    }

    public void setPickupState(PickupState pickupState) {
        mPickupState = pickupState;
    }

    public int getPickupOrder() {
        return mPickupOrder;
    }

    public void setPickupOrder(int pickupOrder) {
        mPickupOrder = pickupOrder;
    }

    public String getDistance() {
        return mDistance;
    }

    public void setDistance(String distance) {
        this.mDistance = distance;
    }

    public String getDisplayName() {
        if (TextUtils.isEmpty(mDisplayName)) {
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(mFirstName)) {
                sb.append(mFirstName);
            }
            if (!TextUtils.isEmpty(mLastName)) {
                sb.append(" ").append(mLastName);
            }
            mDisplayName = sb.toString();
            if (TextUtils.isEmpty(mDisplayName)) {
                mDisplayName = "";
            }

            mDisplayName = mDisplayName.toUpperCase();
        }

        return mDisplayName;
    }

    public String getDisplayAddress() {
        return mAddress.getDisplayAddress();
    }


    public String getDisplayPickupState() {
        return mPickupState.getValue();
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
            if (lhs.mFirstName.compareTo(rhs.mFirstName) == 0) {
                return lhs.mLastName.compareTo(rhs.mLastName);
            }

            return lhs.mFirstName.compareTo(rhs.mFirstName);
        }
    };

    public static Comparator<Student> PickupOrderComparator = new Comparator<Student>() {
        @Override
        public int compare(Student lhs, Student rhs) {
            return lhs.mPickupOrder - rhs.mPickupOrder;
        }
    };
}

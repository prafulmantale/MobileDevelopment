package praful.com.kidsonbus.model;

/**
 * Created by prafulmantale on 4/11/15.
 */
public class School {

    private String mName;

    private Address mAddress;

    public School(String name, Address address) {
        mName = name;
        mAddress = address;
    }

    public String getName(){
        return mName;
    }

    public Address getAddress(){
        return mAddress;
    }

    @Override
    public String toString() {
        return "School{" +
                "mName='" + mName + '\'' +
                ", mAddress=" + mAddress +
                '}';
    }
}

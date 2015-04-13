package praful.com.kidsonbus.model;

import android.text.TextUtils;

/**
 * Created by prafulmantale on 4/11/15.
 */
public class Address {

    private String mHouseNumber;
    private String mStreet;
    private String mCity;
    private String mState;
    private String mCountry;
    private String mPinCode;
    private LocationDetails mLocationDetails;
    private String mDisplayAddress;;

    public Address(String houseNumber, String street, String city, String state) {
        mHouseNumber = houseNumber;
        mStreet = street;
        mCity = city;
        mCountry = "";
        mState = state;
        mPinCode = "";
        mDisplayAddress = "";
    }

    public Address(String houseNumber, String street, String city, String state, String pinCode) {
        this(houseNumber, street, city, state);
        mPinCode = pinCode;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getCity() {
        return mCity;
    }

    public String getStreet() {
        return mStreet;
    }

    public String getHouseNumber() {
        return mHouseNumber;
    }

    public String getPinCode() {
        return mPinCode;
    }

    public void setPinCode(String pinCode) {
        this.mPinCode = pinCode;
    }

    public LocationDetails getLocationDetails(){
        return mLocationDetails;
    }

    public void setLocationDetails(LocationDetails locationDetails){
        mLocationDetails = locationDetails;
    }

    public String getDisplayAddress(){
        if(TextUtils.isEmpty(mDisplayAddress)){
            StringBuilder sb = new StringBuilder();
            if(!TextUtils.isEmpty(mHouseNumber)){
                sb.append(mHouseNumber);
            }
            if(!TextUtils.isEmpty(mStreet)){
                sb.append(" ").append(" ").append(mStreet);
            }
            if(!TextUtils.isEmpty(mCity)){
                sb.append("\n").append(mCity);
            }
            if(!TextUtils.isEmpty(mState)){
                sb.append(", ").append(mState);
            }
            if(!TextUtils.isEmpty(mPinCode)){
                sb.append(" ").append(mPinCode);
            }
            mDisplayAddress = sb.toString();
            if(TextUtils.isEmpty(mDisplayAddress)){
                mDisplayAddress = "";
            }

            mDisplayAddress = mDisplayAddress.toUpperCase();
        }

        return mDisplayAddress;
    }

    public String getQueryAddress(){
        if(TextUtils.isEmpty(mDisplayAddress)){
            StringBuilder sb = new StringBuilder();
            if(!TextUtils.isEmpty(mHouseNumber)){
                sb.append(mHouseNumber);
            }
            if(!TextUtils.isEmpty(mStreet)){
                sb.append(" ").append(" ").append(mStreet);
            }
            if(!TextUtils.isEmpty(mCity)){
                sb.append(" ").append(mCity);
            }
            if(!TextUtils.isEmpty(mState)){
                sb.append(" ").append(mCity);
            }
            if(!TextUtils.isEmpty(mPinCode)){
                sb.append(" ").append(mPinCode);
            }
            mDisplayAddress = sb.toString();
            if(TextUtils.isEmpty(mDisplayAddress)){
                mDisplayAddress = "";
            }

            mDisplayAddress = mDisplayAddress.toUpperCase();
        }

        return mDisplayAddress;
    }

    @Override
    public String toString() {
        return "Address{" +
                "mCountry='" + mCountry + '\'' +
                ", mState='" + mState + '\'' +
                ", mCity='" + mCity + '\'' +
                ", mStreet='" + mStreet + '\'' +
                ", mHouseNumber='" + mHouseNumber + '\'' +
                ", mPinCode='" + mPinCode + '\'' +
                ", mLocationDetails=" + mLocationDetails +
                '}';
    }
}

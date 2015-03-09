package trenduce.com.trenduce.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by prafulmantale on 3/7/15.
 */
public class RegistrationRequest {

    private static final String TAG = RegistrationRequest.class.getSimpleName();

    private static final String FN_KEY = "fn";
    private static final String LN_KEY = "ln";
    private static final String EMAIL_KEY = "email";
    private static final String PWD_KEY = "pwd";

    private String firstName;

    private String lastName;

    private String emailID;

    private String password;

    public RegistrationRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(FN_KEY, firstName);
            jsonObject.put(LN_KEY, lastName);
            jsonObject.put(EMAIL_KEY, emailID);
            jsonObject.put(PWD_KEY, password);
        }
        catch (JSONException ex){

            Log.d(TAG, "Exception while converting object to JSON");
            return null;
        }

        return jsonObject;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailID='" + emailID + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

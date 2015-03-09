package trenduce.com.trenduce.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class LoginRequest {

    private static final String TAG = LoginRequest.class.getSimpleName();

    private static final String EMAIL_KEY = "email";
    private static final String PWD_KEY = "pwd";

    private String emailId;
    private String password;

    public LoginRequest(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }


    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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
            jsonObject.put(EMAIL_KEY, getEmailId());
            jsonObject.put(PWD_KEY, getPassword());
        }
        catch (JSONException ex){

            Log.d(TAG, "Exception while converting object to JSON");
            return null;
        }

        return jsonObject;
    }


    @Override
    public String toString() {
        return "LoginRequest{" +
                ", userName='" + emailId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

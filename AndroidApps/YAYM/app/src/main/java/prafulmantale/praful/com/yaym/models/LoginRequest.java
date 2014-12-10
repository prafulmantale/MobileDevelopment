package prafulmantale.praful.com.yaym.models;

import android.util.Log;

import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class LoginRequest {

    private static final String TAG = LoginRequest.class.getSimpleName();

    private static final String KEY_USER = "user";
    private static final String KEY_PASS = "pass";
    private static final String KEY_ORG = "org";

    private static final String ERROR_INVALID_USER_NAME = "User name is invalid.";
    private static final String ERROR_INVALID_PASSWORD = "Password is invalid.";
    private static final String ERROR_INVALID_ORGANIZATION = "Organization is invalid.";


    private String organization;
    private String userName;
    private String password;

    public LoginRequest(String organization, String userName, String password) {
        this.organization = organization;
        this.userName = userName;
        this.password = password;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public RequestParams getRequestParams(){

        RequestParams params = new RequestParams();
        params.put(KEY_USER, getUserName());
        params.put(KEY_PASS, getPassword());
        params.put(KEY_ORG, getOrganization());

        return params;
    }

    public String toJSON(){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(KEY_USER, getUserName());
            jsonObject.put(KEY_PASS, getPassword());
            jsonObject.put(KEY_ORG, getOrganization());
        }
        catch (JSONException ex){

            Log.d(TAG, "Exception while converting object to JSON");
            return "";
        }

        return jsonObject.toString();
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(KEY_USER, getUserName());
            jsonObject.put(KEY_PASS, getPassword());
            jsonObject.put(KEY_ORG, getOrganization());
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
                "organization='" + organization + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

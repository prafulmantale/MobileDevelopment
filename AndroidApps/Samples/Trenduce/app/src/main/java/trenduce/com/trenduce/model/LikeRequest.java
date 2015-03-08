package trenduce.com.trenduce.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by prafulmantale on 3/7/15.
 */
public class LikeRequest {

    private static final String TAG = LikeRequest.class.getSimpleName();

    private String user;

    public LikeRequest() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("User", user);
        }
        catch (JSONException ex){

            Log.d(TAG, "Exception while converting object to JSON");
            return null;
        }

        return jsonObject;
    }
}

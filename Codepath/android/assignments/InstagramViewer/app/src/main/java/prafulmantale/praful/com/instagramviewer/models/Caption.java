package prafulmantale.praful.com.instagramviewer.models;

import android.graphics.Paint;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by prafulmantale on 9/12/14.
 */
public class Caption implements Serializable{

    private String text;
    private UserDetails userDetails;

    private Caption() {
        text = "";
        userDetails = new UserDetails();
    }

    public String getText() {
        return text;
    }

    private void setText(String text) {
        this.text = text;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public boolean isValid(){

        return !(userDetails == null || !userDetails.isValid() || text == null || text.isEmpty());
    }

    public static Caption fromJSON(JSONObject jsonObject){
        Caption caption = new Caption();

        try {
            JSONObject captionObject = jsonObject.getJSONObject("caption");
            caption.setText(captionObject.getString("text"));
            caption.userDetails = UserDetails.fromJSON(captionObject, "from");
        }
        catch (JSONException ex){

        }

        return caption;
    }

    @Override
    public String toString() {
        return "Caption{" +
                "text='" + text + '\'' +
                ", userDetails=" + userDetails +
                '}';
    }
}

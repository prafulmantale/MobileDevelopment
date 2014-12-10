package prafulmantale.praful.com.instagramviewer.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by prafulmantale on 9/15/14.
 */
public class Like implements Serializable {

    private UserDetails userDetails;

    public Like() {
        this.userDetails = new UserDetails();
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public static Like fromJSON(JSONObject jsonObject){

        Like like = new Like();

        try {

            like.userDetails.setUsername(jsonObject.getString("username"));
            like.userDetails.setFullname(jsonObject.getString("full_name"));
            like.userDetails.setId(jsonObject.getString("id"));
            like.userDetails.setProfilePictureUrl(jsonObject.getString("profile_picture"));

        }
        catch (Exception ex){

        }

        return like;
    }
}

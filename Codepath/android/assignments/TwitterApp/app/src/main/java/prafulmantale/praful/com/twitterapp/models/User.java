package prafulmantale.praful.com.twitterapp.models;

import com.activeandroid.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by prafulmantale on 9/27/14.
 */
public class User{

    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;


    public User() {
        name = "";
        profileImageUrl = "";
        screenName = "";
    }

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public static User fromJSON(JSONObject jsonObject){
        User user = new User();

        try{
            user.name = jsonObject.getString("name");
            user.uid = jsonObject.getLong("id");
            user.screenName = jsonObject.getString("screen_name");
            user.profileImageUrl = jsonObject.getString("profile_image_url");
        }
        catch (JSONException ex){

            user = null;
        }

        return user;
    }

}
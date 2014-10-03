package prafulmantale.praful.com.twitterapp.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by prafulmantale on 10/2/14.
 */
public class UserProfile {

    private static final String TAG = UserProfile.class.getName();

    private long userId;
    private String userIdStr;
    private String name;
    private String screenName;
    private boolean followsYou;
    private String description;
    private String profileImageUrl;
    private String profileBannerUrl;

    private String friendsCount; //following
    private String followersCount;

    private String statusCount; //no. of tweets
    private String photosCount; //Photos posted
    private String favoritesCount;//Liked tweets






    public static UserProfile fromJSON(JSONObject jsonObject){
        UserProfile userProfile = new UserProfile();

        try{
            userProfile.userId = jsonObject.getLong("id");
            userProfile.userIdStr = jsonObject.getString("id_str");
            userProfile.name = jsonObject.getString("name");
            userProfile.screenName = jsonObject.getString("screen_name");
            userProfile.followsYou = jsonObject.getBoolean("following");
            userProfile.description = jsonObject.getString("description");
            userProfile.profileImageUrl = jsonObject.getString("profile_image_url");
            userProfile.profileBannerUrl = jsonObject.getString("profile_banner_url");

            userProfile.friendsCount = jsonObject.getString("friends_count");
            userProfile.followersCount = jsonObject.getString("followers_count");

            userProfile.statusCount = jsonObject.getString("statuses_count");
            //??userProfile.photosCount = jsonObject.getString("");
            userProfile.favoritesCount = jsonObject.getString("favourites_count");

        }
        catch (JSONException ex){

            Log.d(TAG, "Exception while extracting User profile from JSON\r\n" + ex.getMessage());
            ex.printStackTrace();

            userProfile = null;
        }



        return userProfile;
    }
}

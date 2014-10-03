package prafulmantale.praful.com.twitterapp.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by prafulmantale on 10/2/14.
 */
public class UserProfile implements Serializable{

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

    private String location;

    private String displayScreenName;

    public UserProfile() {
        displayScreenName = null;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserIdStr() {
        return userIdStr;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public boolean isFollowsYou() {
        return followsYou;
    }

    public void setFollowsYou(boolean followsYou) {
        this.followsYou = followsYou;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileBannerUrl() {
        return profileBannerUrl;
    }

    public void setProfileBannerUrl(String profileBannerUrl) {
        this.profileBannerUrl = profileBannerUrl;
    }

    public String getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(String friendsCount) {
        this.friendsCount = friendsCount;
    }

    public String getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(String followersCount) {
        this.followersCount = followersCount;
    }

    public String getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(String statusCount) {
        this.statusCount = statusCount;
    }

    public String getPhotosCount() {
        return photosCount;
    }

    public void setPhotosCount(String photosCount) {
        this.photosCount = photosCount;
    }

    public String getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(String favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getDisplayScreenName() {
        if(displayScreenName == null){
            displayScreenName = "@" + screenName;
        }

        return displayScreenName;
    }

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

            userProfile.location = jsonObject.getString("location");

        }
        catch (JSONException ex){

            Log.d(TAG, "Exception while extracting User profile from JSON\r\n" + ex.getMessage());
            ex.printStackTrace();

            userProfile = null;
        }

        return userProfile;
    }
}

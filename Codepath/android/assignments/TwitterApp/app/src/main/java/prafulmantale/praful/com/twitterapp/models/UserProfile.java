package prafulmantale.praful.com.twitterapp.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import prafulmantale.praful.com.twitterapp.helpers.AppConstants;
import prafulmantale.praful.com.twitterapp.helpers.Utils;

/**
 * Created by prafulmantale on 10/2/14.
 */
public class UserProfile implements Serializable{

    private static final String TAG = UserProfile.class.getName();

    private long userId;
    private String userIdStr;
    private String name;
    private String screenName;
    private boolean following;
    private String description;
    private String profileImageUrl;
    private String profileBannerUrl;

    private long friendsCount; //following
    private long followersCount;

    private long statusCount; //no. of tweets
    private long photosCount; //Photos posted
    private long favoritesCount;//Liked tweets

    private String location;

    //not serialized
    private String displayScreenName;
    private String displayStatusCount;
    private String displayFollowersCount;
    private String displayFriendsCount;

    public UserProfile() {
        displayScreenName = null;
        displayStatusCount = null;
        displayFollowersCount = null;
        displayFriendsCount = null;
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

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
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

    public long getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(long friendsCount) {
        this.friendsCount = friendsCount;
    }

    public long getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(long followersCount) {
        this.followersCount = followersCount;
    }

    public long getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(long statusCount) {
        this.statusCount = statusCount;
    }

    public long getPhotosCount() {
        return photosCount;
    }

    public void setPhotosCount(long photosCount) {
        this.photosCount = photosCount;
    }

    public long getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(long favoritesCount) {
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

    public String getHTMLDisplayStatusCount(){
        if(displayStatusCount == null){
            displayStatusCount =  Utils.getFormattedCountDisplay(statusCount);
            displayStatusCount = "<strong>" + displayStatusCount + "</strong><br><small>" + AppConstants.TWEETS_UPPER + "</small>";
        }

        return displayStatusCount;
    }

    public String getHTMLDisplayFollowersCount(){
        if(displayFollowersCount == null){
            displayFollowersCount =  Utils.getFormattedCountDisplay(followersCount);
            displayFollowersCount = "<strong>" + displayFollowersCount + "</strong><br><small>" + AppConstants.FOLLOWERS_UPPER + "</small>";
        }

        return displayFollowersCount;
    }

    public String getHTMLDisplayFriendsCount(){
        if(displayFriendsCount == null){
            displayFriendsCount =  Utils.getFormattedCountDisplay(friendsCount);
            displayFriendsCount = "<strong>" + displayFriendsCount + "</strong><br><small>" + AppConstants.FOLLOWING_UPPER + "</small>";
        }

        return displayFriendsCount;
    }

    public static UserProfile fromJSON(JSONObject jsonObject){
        UserProfile userProfile = new UserProfile();

        try{
            userProfile.userId = jsonObject.getLong("id");
            userProfile.userIdStr = jsonObject.getString("id_str");
            userProfile.name = jsonObject.getString("name");
            userProfile.screenName = jsonObject.getString("screen_name");
            userProfile.following = jsonObject.getBoolean("following");
            userProfile.description = jsonObject.getString("description");
            userProfile.profileImageUrl = jsonObject.getString("profile_image_url");
            userProfile.profileBannerUrl = jsonObject.getString("profile_banner_url");

            userProfile.friendsCount = jsonObject.getLong("friends_count");
            userProfile.followersCount = jsonObject.getLong("followers_count");

            userProfile.statusCount = jsonObject.getLong("statuses_count");
            //??userProfile.photosCount = jsonObject.getLong("");
            userProfile.favoritesCount = jsonObject.getLong("favourites_count");

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

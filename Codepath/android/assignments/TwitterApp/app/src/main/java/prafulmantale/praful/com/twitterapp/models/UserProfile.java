package prafulmantale.praful.com.twitterapp.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.twitterapp.helpers.AppConstants;
import prafulmantale.praful.com.twitterapp.helpers.Utils;

/**
 * Created by prafulmantale on 10/2/14.
 */
@Table(name="users")
public class UserProfile extends Model implements Parcelable {

    private static final String TAG = UserProfile.class.getName();

    @Column(name="userid", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private long userId;

    @Column(name="useridStr", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private String userIdStr;

    @Column(name="name")
    private String name;

    @Column(name="screenname")
    private String screenName;

    @Column(name = "following")
    private boolean following;

    @Column(name = "description")
    private String description;

    @Column(name = "profileurl")
    private String profileImageUrl;

    @Column(name = "profilebannerurl")
    private String profileBannerUrl;

    @Column(name = "friendsCount")
    private long friendsCount; //following

    @Column(name = "followersCount")
    private long followersCount;

    @Column(name = "statusCount")
    private long statusCount; //no. of tweets

    @Column(name = "photosCount")
    private long photosCount; //Photos posted

    @Column(name = "favoritesCount")
    private long favoritesCount;//Liked tweets

    @Column(name = "location")
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
        if (displayScreenName == null) {
            displayScreenName = "@" + screenName;
        }

        return displayScreenName;
    }

    public String getHTMLDisplayStatusCount() {
        if (displayStatusCount == null) {
            displayStatusCount = Utils.getFormattedCountDisplay(statusCount);
            displayStatusCount = "<strong>" + displayStatusCount + "</strong><br><small>" + AppConstants.TWEETS_UPPER + "</small>";
        }

        return displayStatusCount;
    }

    public String getHTMLDisplayFollowersCount() {
        if (displayFollowersCount == null) {
            displayFollowersCount = Utils.getFormattedCountDisplay(followersCount);
            displayFollowersCount = "<strong>" + displayFollowersCount + "</strong><br><small>" + AppConstants.FOLLOWERS_UPPER + "</small>";
        }

        return displayFollowersCount;
    }

    public String getHTMLDisplayFriendsCount() {
        if (displayFriendsCount == null) {
            displayFriendsCount = Utils.getFormattedCountDisplay(friendsCount);
            displayFriendsCount = "<strong>" + displayFriendsCount + "</strong><br><small>" + AppConstants.FOLLOWING_UPPER + "</small>";
        }

        return displayFriendsCount;
    }

    public static UserProfile fromJSON(JSONObject jsonObject) {
        UserProfile userProfile = new UserProfile();

        try {
            userProfile.userId = jsonObject.getLong("id");
            userProfile.userIdStr = jsonObject.getString("id_str");
            userProfile.name = jsonObject.getString("name");
            userProfile.screenName = jsonObject.getString("screen_name");
            userProfile.following = jsonObject.getBoolean("following");
            userProfile.description = jsonObject.getString("description");
            userProfile.profileImageUrl = jsonObject.getString("profile_image_url");
            userProfile.profileBannerUrl = jsonObject.optString("profile_banner_url", "");

            userProfile.friendsCount = jsonObject.getLong("friends_count");
            userProfile.followersCount = jsonObject.getLong("followers_count");

            userProfile.statusCount = jsonObject.getLong("statuses_count");
            //??userProfile.photosCount = jsonObject.getLong("");
            userProfile.favoritesCount = jsonObject.getLong("favourites_count");

            userProfile.location = jsonObject.getString("location");

        } catch (JSONException ex) {

            Log.d(TAG, "Exception while extracting User profile from JSON\r\n" + ex.getMessage());
            ex.printStackTrace();

            userProfile = null;
        }

        return userProfile;
    }

    public static List<UserProfile> fromJSON(JSONArray jsonArray) {
        List<UserProfile> list = new ArrayList<UserProfile>();

        for (int i = 0; i < jsonArray.length(); i++) {

            try {
                JSONObject obj = jsonArray.getJSONObject(i);

                if(obj == null){
                    continue;
                }

                UserProfile up = UserProfile.fromJSON(obj);

                if (up == null) {
                    continue;
                }

                list.add(up);

            } catch (JSONException ex) {
                Log.d(TAG, "Exception while extracting User profiles from Array");
            }
        }

        return list;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(userId);
        dest.writeString(userIdStr);
        dest.writeString(name);
        dest.writeString(screenName);
        dest.writeInt((following) ? 1 : 0);
        dest.writeString(description);
        dest.writeString(profileImageUrl);
        dest.writeString(profileBannerUrl);
        dest.writeLong(friendsCount);
        dest.writeLong(followersCount);
        dest.writeLong(statusCount);
        dest.writeLong(photosCount);
        dest.writeLong(favoritesCount);
        dest.writeString(location);
    }



    private UserProfile(Parcel in) {
        userId = in.readLong();
        userIdStr = in.readString();
        name = in.readString();
        screenName = in.readString();
        following = (in.readInt() == 1 ? true : false);
        description = in.readString();
        profileImageUrl = in.readString();
        profileBannerUrl = in.readString();
        friendsCount = in.readLong();
        followersCount = in.readLong();
        statusCount = in.readLong();
        photosCount = in.readLong();
        favoritesCount = in.readLong();
        location = in.readString();
    }

    public static final Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel source) {
            return new UserProfile(source);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId=" + userId +
                ", userIdStr='" + userIdStr + '\'' +
                ", name='" + name + '\'' +
                ", screenName='" + screenName + '\'' +
                ", following=" + following +
                ", description='" + description + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", profileBannerUrl='" + profileBannerUrl + '\'' +
                ", friendsCount=" + friendsCount +
                ", followersCount=" + followersCount +
                ", statusCount=" + statusCount +
                ", photosCount=" + photosCount +
                ", favoritesCount=" + favoritesCount +
                ", location='" + location + '\'' +
                '}';
    }

    public static UserProfile getLoggedInUserDetails(Context context){

        UserProfile loggedInUser = null;
        try {
            SharedPreferences settings = context.getSharedPreferences(AppConstants.PREFS_FILE, 0);

            String uname = settings.getString(AppConstants.KEY_SHARED_PREF_USER_NAME, null);

            if (uname == null || uname.isEmpty()) {
                return loggedInUser;
            }

            String screenname = settings.getString(AppConstants.KEY_SHARED_PREF_SCREEN_NAME, null);

            if (screenname == null || screenname.isEmpty()) {
                return loggedInUser;
            }

            long uid = settings.getLong(AppConstants.KEY_SHARED_PREF_USER_ID, -1);
            if (uid == -1) {
                return loggedInUser;
            }

            String url = settings.getString(AppConstants.KEY_SHARED_PREF_PROFILE_IMG_URL, null);

            if (url == null || url.isEmpty()) {
                return loggedInUser;
            }

            loggedInUser = new UserProfile();

            loggedInUser.userId = uid;
            loggedInUser.name = uname;
            loggedInUser.screenName = screenname;
            loggedInUser.profileImageUrl = url;
        }
        catch (Exception ex){
            Log.d(TAG, "Exception while extracting logged in user from shared preferences");
            loggedInUser = null;
        }

        return loggedInUser;
    }

    public static void saveLoggedInUserDetails(Context context, UserProfile user){

        try {
            SharedPreferences settings = context.getSharedPreferences(AppConstants.PREFS_FILE, 0);
            SharedPreferences.Editor editor = settings.edit();

            editor.putString(AppConstants.KEY_SHARED_PREF_USER_NAME, user.getName());
            editor.putString(AppConstants.KEY_SHARED_PREF_SCREEN_NAME, user.getScreenName());
            editor.putLong(AppConstants.KEY_SHARED_PREF_USER_ID, user.getUserId());
            editor.putString(AppConstants.KEY_SHARED_PREF_PROFILE_IMG_URL, user.getProfileImageUrl());

            editor.commit();
        }
        catch (Exception ex){
            Log.d(TAG, "Exception while saving logged in user to shared preferences");
        }
    }
}


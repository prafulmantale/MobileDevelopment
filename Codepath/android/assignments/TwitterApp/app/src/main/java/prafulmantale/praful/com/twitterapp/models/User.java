package prafulmantale.praful.com.twitterapp.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import prafulmantale.praful.com.twitterapp.helpers.AppConstants;

/**
 * Created by prafulmantale on 9/27/14.
 */
@Table(name="users")
public class User extends Model implements Parcelable{

    private static String TAG = User.class.getName();

    @Column(name="userid", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private long userID;

    @Column(name="uname")
    private String name;

    @Column(name="screenname")
    private String screenName;

    @Column(name = "profileurl")
    private String profileImageUrl;

    public List<Tweet> tweets() {
        return getMany(Tweet.class, "User");
    }

    public User() {
        super();
        name = "";
        profileImageUrl = "";
        screenName = "";
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }


    public static User fromJSON(JSONObject jsonObject){
        User user = null;

        try{
            long uid = jsonObject.getLong("id");
            //user = User.load(User.class, uid);
            //?? Is this an expensive operation, how to reduce DB hit
            user = new Select().from(User.class).where("userid = ?", uid).executeSingle();
            if(user == null) {
                user = new User();
                user.userID = jsonObject.getLong("id");
                user.name = jsonObject.getString("name");
                user.screenName = jsonObject.getString("screen_name");
                user.profileImageUrl = jsonObject.getString("profile_image_url");
                user.save();
            }
        }
        catch (JSONException ex){

            Log.d(TAG, "Exception while creating User from JSON" + ex.getMessage());
            ex.printStackTrace();
            user = null;
        }

        return user;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(userID);
        dest.writeString(name);
        dest.writeString(screenName);
        dest.writeString(profileImageUrl);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private User(Parcel in) {
        userID = in.readLong();
        name = in.readString();
        screenName = in.readString();
        profileImageUrl = in.readString();
    }


    public static User getLoggedInUserDetails(Context context){

        User loggedInUser = null;
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

            loggedInUser = new User();

            loggedInUser.userID = uid;
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

    public static void saveLoggedInUserDetails(Context context, User user){

        try {
            SharedPreferences settings = context.getSharedPreferences(AppConstants.PREFS_FILE, 0);
            SharedPreferences.Editor editor = settings.edit();

            editor.putString(AppConstants.KEY_SHARED_PREF_USER_NAME, user.getName());
            editor.putString(AppConstants.KEY_SHARED_PREF_SCREEN_NAME, user.getScreenName());
            editor.putLong(AppConstants.KEY_SHARED_PREF_USER_ID, user.getUserID());
            editor.putString(AppConstants.KEY_SHARED_PREF_PROFILE_IMG_URL, user.getProfileImageUrl());

            editor.commit();
        }
        catch (Exception ex){
            Log.d(TAG, "Exception while saving logged in user to shared preferences");
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", screenName='" + screenName + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                '}';
    }
}

package prafulmantale.praful.com.twitterapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import prafulmantale.praful.com.twitterapp.helpers.Utils;

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
            user = User.load(User.class, uid);
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

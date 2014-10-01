package prafulmantale.praful.com.twitterapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONException;
import org.json.JSONObject;

import prafulmantale.praful.com.twitterapp.helpers.Utils;

/**
 * Created by prafulmantale on 9/27/14.
 */
@Table(name="users")
public class User extends Model implements Parcelable{

    @Column(name="uid")
    private long uid;

    @Column(name="uname")
    private String name;

    @Column(name="screenname")
    private String screenName;

    @Column(name = "profileurl")
    private String profileImageUrl;


    public User() {
        super();
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(uid);
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
        name = in.readString();
        uid = in.readLong();
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
                "name='" + name + '\'' +
                ", uid=" + uid +
                ", screenName='" + screenName + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                '}';
    }
}

package prafulmantale.praful.com.instagramviewer.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by prafulmantale on 9/18/14.
 */
public class SocialDetails implements Serializable{

    private static final String TAG = "SOCIALDETAILS";

    private long posts;
    private long follows;
    private long followedBy;

    public SocialDetails() {
        posts = 0;
        follows = 0;
        followedBy = 0;
    }

    public long getPosts() {
        return posts;
    }

    public void setPosts(long posts) {
        this.posts = posts;
    }

    public long getFollows() {
        return follows;
    }

    public void setFollows(long follows) {
        this.follows = follows;
    }

    public long getFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(long followedBy) {
        this.followedBy = followedBy;
    }

    public static SocialDetails fromJSON(JSONObject jsonObject){

        SocialDetails socialDetails = new SocialDetails();

        if(jsonObject == null){
            return socialDetails;
        }

        try{
            socialDetails.posts = jsonObject.getLong("media");
            socialDetails.follows = jsonObject.getLong("follows");
            socialDetails.followedBy = jsonObject.getLong("followed_by");
        }
        catch (JSONException ex){
            Log.d(TAG, "Exception while extracting social details");
        }

        return socialDetails;
    }

    @Override
    public String toString() {
        return "SocialDetails{" +
                "posts=" + posts +
                ", follows=" + follows +
                ", followedBy=" + followedBy +
                '}';
    }
}

package prafulmantale.praful.com.twitterapp.models;

import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prafulmantale on 9/26/14.
 */
@Table(name = "Tweets")
public class Tweet extends Model{

    private static final String TAG = "TWEET";

    @Column(name="text")
    private String body;
    @Column(name="uid")
    private long uid;

    @Column(name = "createdAt")
    private String createdAt;

    private User user;

    public Tweet() {
        super();
        body = "";
        uid = 0;
        createdAt = "";
        user = new User();
    }

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public static Tweet fromJSON(JSONObject jsonObject){

        Tweet tweet = new Tweet();

        try{

            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));

        }
        catch (JSONException ex){
            Log.d(TAG, "Exception while creating Tweet: \r\n" + ex.getMessage());

            tweet = null;
        }

        return tweet;

    }

    public static List<Tweet> fromJSON(JSONArray jsonArray){
        List<Tweet> list = new ArrayList<Tweet>();

        for(int i = 0; i < jsonArray.length(); i ++){

            try {
                JSONObject obj = jsonArray.getJSONObject(i);

                if (obj == null) {
                    continue;
                }

                Tweet tweet = fromJSON(obj);

                if (tweet == null) {
                    continue;
                }

                tweet.save();
                list.add(tweet);
            }
            catch (JSONException ex){
                Log.d(TAG, "Exception while converting processing object at index " + i);
            }
        }

        return list;
    }
}

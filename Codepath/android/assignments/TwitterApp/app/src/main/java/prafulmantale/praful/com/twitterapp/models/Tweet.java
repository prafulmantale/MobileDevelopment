package prafulmantale.praful.com.twitterapp.models;

import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import prafulmantale.praful.com.twitterapp.helpers.Utils;

/**
 * Created by prafulmantale on 9/26/14.
 */
//@Table(name = "Tweets")
public class Tweet extends Model{

    private static final String TAG = "TWEET";

    //@Column(name="text")
    private String body;
    //@Column(name="uid")
    private long uid;

    //@Column(name = "createdAt")
    private String createdAt;

    //@Column(name = "retweet_count")
    private String retweet_count;

    //@Column(name="favorite_count")
    private String favorite_count;

    private User user;

    public Tweet() {
        super();
        body = "";
        uid = 0;
        createdAt = "";
        retweet_count = "";
        favorite_count = "";
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

    public String getRetweet_count() {
        return retweet_count;
    }

    public void setRetweet_count(String retweet_count) {
        this.retweet_count = retweet_count;
    }

    public String getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(String favorite_count) {
        this.favorite_count = favorite_count;
    }

    public User getUser() {
        return user;
    }

    public String getRelativeTimeAgo() {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(createdAt).getTime();
            relativeDate = Utils.getElapsedDisplayTime(dateMillis);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }

    public static Tweet fromJSON(JSONObject jsonObject){

        Tweet tweet = new Tweet();

        try{

            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            try {
                tweet.retweet_count = jsonObject.getString("retweet_count");
                tweet.favorite_count = jsonObject.getString("favorite_count");
            }
            catch (Exception e){

                System.out.println("Something wrong");

            }
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

                //tweet.save();
                list.add(tweet);
            }
            catch (JSONException ex){
                Log.d(TAG, "Exception while converting processing object at index " + i);
            }
        }

        return list;
    }
}

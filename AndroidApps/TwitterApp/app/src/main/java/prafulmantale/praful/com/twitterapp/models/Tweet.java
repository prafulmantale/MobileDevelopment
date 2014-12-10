package prafulmantale.praful.com.twitterapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

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
@Table(name = "Tweets")
public class Tweet extends Model implements Parcelable{

    private static final String TAG = Tweet.class.getName();

    @Column(name="tweetid", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private long tweetID;

    @Column(name="tweet")
    private String text;


    @Column(name = "createdat")
    private String createdAt;

    @Column(name = "retweet_count")
    private String retweet_count;

    @Column(name="favorite_count")
    private String favorite_count;

    @Column(name = "favorited")
    private boolean favorited;

    @Column(name = "user", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    private UserProfile user;

    @Column(name = "tweetEmbeddedUrl", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    private TweetEmbeddedUrl tweetEmbeddedUrl;

    @Column(name="mediaUrl")
    private String mediaUrl;

    private String formattedBody;
    private String relativeTimestamp;

    public Tweet() {
        super();

        tweetID = -1;
        text = "";

        createdAt = "";
        retweet_count = " ";
        favorite_count = " ";
        user = new UserProfile();
        tweetEmbeddedUrl = new TweetEmbeddedUrl();
        formattedBody = null;
        relativeTimestamp = null;
        mediaUrl = null;
    }

    public long getTweetID() {
        return tweetID;
    }

    public void setTweetID(long tweetID) {
        this.tweetID = tweetID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }

    public TweetEmbeddedUrl getTweetEmbeddedUrl() {
        return tweetEmbeddedUrl;
    }

    public void setTweetEmbeddedUrl(TweetEmbeddedUrl tweetEmbeddedUrl) {
        this.tweetEmbeddedUrl = tweetEmbeddedUrl;
    }

    public String getFormattedBody(){
        if(formattedBody != null){
            return formattedBody;
        }

        formattedBody = text;

        if(getTweetEmbeddedUrl() == null || getTweetEmbeddedUrl().isValid() == false){
            return formattedBody;
        }

        try {
            formattedBody = formattedBody.substring(0, tweetEmbeddedUrl.getStartIndex()) +
                    "<a href=\"" + getTweetEmbeddedUrl().getExpandedUrl() + "\">" + getTweetEmbeddedUrl().getDisplayUrl() + "</a> " +
                    formattedBody.substring(getTweetEmbeddedUrl().getEndIndex());
        }
        catch (Exception ex){
            formattedBody = text;
        }

        return formattedBody;
    }

    public String getRelativeTimeAgo() {

        if(relativeTimestamp != null && !relativeTimestamp.isEmpty()){
            return relativeTimestamp;
        }
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(createdAt).getTime();
            relativeDate = relativeTimestamp = Utils.getElapsedDisplayTime(dateMillis);
        } catch (ParseException e) {
            Log.d(TAG, "Excpetion in getRelativeTimeAgo" + e.getMessage());
            relativeTimestamp = null;
        }

        return relativeDate;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public static Tweet fromJSON(JSONObject jsonObject){

        Tweet tweet = new Tweet();

        try{
            tweet.tweetID = jsonObject.getLong("id");
            tweet.text = jsonObject.getString("text");
            tweet.createdAt = jsonObject.getString("created_at");

            try {
                tweet.retweet_count = jsonObject.getString("retweet_count");
                if(tweet.retweet_count.equals("0")){
                    tweet.retweet_count = " ";
                }

                tweet.favorite_count = jsonObject.getString("favorite_count");
                if(tweet.favorite_count.equals("0")){
                    tweet.favorite_count = " ";
                }
                }
            catch (Exception e){
                Log.d(TAG, "Exception while extracting Retweet and favorite count");
            }

            tweet.favorited = jsonObject.getBoolean("favorited");

            tweet.user = UserProfile.fromJSON(jsonObject.getJSONObject("user"));

            try {
                JSONObject entities = jsonObject.optJSONObject("entities");
                if(entities != null) {
                    JSONArray urls = entities.getJSONArray("urls");

                    for (int i = 0; i < urls.length(); i++) {
                        JSONObject obj = urls.getJSONObject(0);
                        tweet.tweetEmbeddedUrl = TweetEmbeddedUrl.fromJSON(obj, tweet.tweetID);
                        break;
                    }

                    JSONArray mediaArr = entities.optJSONArray("media");
                    if(mediaArr != null) {
                        for (int i = 0; i < mediaArr.length(); i++) {
                            JSONObject obj = mediaArr.getJSONObject(0);
                            tweet.mediaUrl = obj.getString("media_url");
                            break;
                        }
                    }
                }

            }
            catch (JSONException eh){
                Log.d(TAG, "Exception while extracting Embedded urls");
            }
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

    protected Tweet(Parcel in){

        tweetID = in.readLong();
        text = in.readString();

        createdAt = in.readString();
        retweet_count = in.readString();
        favorite_count = in.readString();
        favorited = in.readInt() == 1 ? true : false;
        if(in.readInt() == -1){
            user = null;
        }
        else{
            user = in.readParcelable(UserProfile.class.getClassLoader());
        }

        if(in.readInt() == -1){
            tweetEmbeddedUrl = null;
        }
        else{
            tweetEmbeddedUrl = in.readParcelable(TweetEmbeddedUrl.class.getClassLoader());
        }

        mediaUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(tweetID);
        dest.writeString(text);

        dest.writeString(createdAt);
        dest.writeString(retweet_count);
        dest.writeString(favorite_count);
        dest.writeInt(favorited ? 1 : 0);
        if(user == null){
            dest.writeInt(-1);
        }
        else {
            dest.writeInt(1);
            dest.writeParcelable(user, flags);
        }

        if(tweetEmbeddedUrl == null){
            dest.writeInt(-1);
        }
        else {
            dest.writeInt(1);
            dest.writeParcelable(tweetEmbeddedUrl, flags);
        }

        dest.writeString(mediaUrl);
    }

    public static final Creator<Tweet> CREATOR = new Creator<Tweet>() {
        @Override
        public Tweet createFromParcel(Parcel source) {
            return new Tweet(source);
        }

        @Override
        public Tweet[] newArray(int size) {
            return new Tweet[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }


    public static List<Tweet> getAllTweets(){
        List<Tweet> tweetList = new Select().from(Tweet.class).execute();
        System.out.println("Tweetlist: " + tweetList.size());
        return tweetList;
    }
}

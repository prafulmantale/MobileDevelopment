package prafulmantale.praful.com.twitterapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by prafulmantale on 9/30/14.
 */
@Table(name = "TweetEmbeddedUrl")
public class TweetEmbeddedUrl extends Model implements Parcelable{

    private static final String TAG = TweetEmbeddedUrl.class.getName();

    @Column(name = "tweetid", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private long tweetID;
    private int startIndex;
    private int endIndex;
    private String url;
    private String displayUrl;
    private String expandedUrl;

    public TweetEmbeddedUrl() {
        super();
        startIndex = -1;
        endIndex = -1;
    }

    public long getTweetID() {
        return tweetID;
    }

    public void setTweetID(long tweetID) {
        this.tweetID = tweetID;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDisplayUrl() {
        return displayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    public String getExpandedUrl() {
        return expandedUrl;
    }

    public void setExpandedUrl(String expandedUrl) {
        this.expandedUrl = expandedUrl;
    }

    public static  TweetEmbeddedUrl fromJSON(JSONObject jsonObject, long tweetID){

        TweetEmbeddedUrl tweetEmbeddedUrl = new TweetEmbeddedUrl();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("indices");
            tweetEmbeddedUrl.tweetID = tweetID;

            tweetEmbeddedUrl.startIndex = jsonArray.getInt(0);
            tweetEmbeddedUrl.endIndex = jsonArray.getInt(1);

            tweetEmbeddedUrl.url = jsonObject.getString("url");
            tweetEmbeddedUrl.displayUrl = jsonObject.getString("display_url");
            tweetEmbeddedUrl.expandedUrl = jsonObject.getString("expanded_url");

            tweetEmbeddedUrl.save();
        }
        catch (JSONException e){
            Log.d(TAG, "Exception while creating TweetEmbeddedUrl from JSON ");
            tweetEmbeddedUrl = null;
        }
        return tweetEmbeddedUrl;
    }

    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(startIndex);
        dest.writeInt(endIndex);
        dest.writeString(url);
        dest.writeString(displayUrl);
        dest.writeString(expandedUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TweetEmbeddedUrl> CREATOR = new Creator<TweetEmbeddedUrl>() {
        @Override
        public TweetEmbeddedUrl createFromParcel(Parcel source) {
            return new TweetEmbeddedUrl(source);
        }

        @Override
        public TweetEmbeddedUrl[] newArray(int size) {
            return new TweetEmbeddedUrl[size];
        }
    };

    private TweetEmbeddedUrl(Parcel in) {
        startIndex = in.readInt();
        endIndex = in.readInt();
        url = in.readString();
        displayUrl = in.readString();
        expandedUrl = in.readString();
    }

    public boolean isValid(){
        if(startIndex == -1 || endIndex == -1 ||
                url == null || url.isEmpty() ||
                displayUrl == null || displayUrl.isEmpty() ||
                expandedUrl == null || expandedUrl.isEmpty()){
            return false;
        }

        return true;
    }


    @Override
    public String toString() {
        return "TweetEmbeddedUrl{" +
                "tweetID=" + tweetID +
                ", startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                ", url='" + url + '\'' +
                ", displayUrl='" + displayUrl + '\'' +
                ", expandedUrl='" + expandedUrl + '\'' +
                '}';
    }
}

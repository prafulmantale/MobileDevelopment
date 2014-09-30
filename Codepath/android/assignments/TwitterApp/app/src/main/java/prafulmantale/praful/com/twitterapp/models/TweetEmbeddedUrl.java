package prafulmantale.praful.com.twitterapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by prafulmantale on 9/30/14.
 */
public class TweetEmbeddedUrl{

    private int startIndex;
    private int endIndex;
    private String url;
    private String displayUrl;
    private String expandedUrl;

    public TweetEmbeddedUrl() {
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

    public static  TweetEmbeddedUrl fromJSON(JSONObject jsonObject){

        TweetEmbeddedUrl tweetEmbeddedUrl = new TweetEmbeddedUrl();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("indices");

            tweetEmbeddedUrl.startIndex = jsonArray.getInt(0);
            tweetEmbeddedUrl.endIndex = jsonArray.getInt(1);

            tweetEmbeddedUrl.url = jsonObject.getString("url");
            tweetEmbeddedUrl.displayUrl = jsonObject.getString("display_url");
            tweetEmbeddedUrl.expandedUrl = jsonObject.getString("expanded_url");
        }
        catch (JSONException e){
            tweetEmbeddedUrl = null;
        }
        return tweetEmbeddedUrl;
    }

    @Override
    public String toString() {
        return "TweetEmbeddedUrl{" +
                "startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                ", url='" + url + '\'' +
                '}';
    }
}

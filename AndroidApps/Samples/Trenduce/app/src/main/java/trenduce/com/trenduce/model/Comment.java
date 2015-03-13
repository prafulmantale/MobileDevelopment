package trenduce.com.trenduce.model;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prafulmantale on 3/7/15.
 */
public class Comment {

    private static final String TAG = Comment.class.getSimpleName();

    @JsonProperty("User")
    private String user;

    private String userProfileImageUrl;

    @JsonProperty("text")
    private String text;

    @JsonProperty("createdAt")
    private long createdAt;

    private String displayCreatedAt;

    public Comment() {

        createdAt = System.currentTimeMillis();
        // displayCreatedAt = Utils.getFormattedCurrentTime();
    }

    public static Comment fromJSON(JSONObject jsonObject){

        Comment comment = new Comment();

        try{

            comment.user = jsonObject.getString("User");
            //comment.userProfileImageUrl
            comment.text = jsonObject.getString("text");
            comment.displayCreatedAt = jsonObject.getString("createdAt");

        }catch (JSONException jex){
            comment = null;
        }


        return comment;
    }



    public static List<Comment> fromJSONArray(JSONArray jsonArray){

        List<Comment> list = new ArrayList<>();

        if(jsonArray == null || jsonArray.length() == 0){
            return list;
        }

        int len = jsonArray.length();

        for(int i = 0; i < len; i ++){

            try{

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Comment comment = fromJSON(jsonObject);

                if(comment != null){
                    list.add(comment);
                }
            }catch (JSONException jex){
            }

        }

        return list;
    }



    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserProfileImageUrl() {
        return userProfileImageUrl;
    }

    public void setUserProfileImageUrl(String userProfileImageUrl) {
        this.userProfileImageUrl = userProfileImageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getDisplayCreatedAt() {
        return displayCreatedAt;
    }

    public void setDisplayCreatedAt(String displayCreatedAt) {
        this.displayCreatedAt = displayCreatedAt;
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("User", user);
            jsonObject.put("text", text);

        }
        catch (JSONException ex){

            Log.d(TAG, "Exception while converting object to JSON");
            return null;
        }

        return jsonObject;
    }
}

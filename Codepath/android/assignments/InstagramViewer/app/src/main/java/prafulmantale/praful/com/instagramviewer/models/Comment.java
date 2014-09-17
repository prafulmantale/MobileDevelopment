package prafulmantale.praful.com.instagramviewer.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

import prafulmantale.praful.com.instagramviewer.Utils.DateUtils;

/**
 * Created by prafulmantale on 9/12/14.
 */
public class Comment implements Serializable {


    private String text;
    private UserDetails userDetails;
    private String id;
    private long createdTime;

    public Comment() {
        createdTime = 0;
        this.text = "";
        this.userDetails = new UserDetails();
        id = "";
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getElapsedTime(){
        return DateUtils.getElapsedDisplayTime(createdTime);
    }

    public static Comment fromJSON(JSONObject jsonObject){

        Comment comment = new Comment();

        try {
            comment.createdTime = jsonObject.getLong("created_time");
            comment.id = jsonObject.getString("id");
            comment.text = jsonObject.getString("text");
            comment.userDetails = UserDetails.fromJSON(jsonObject, "from");
        }
        catch (JSONException ex){
            return null;
        }

        return comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "text='" + text + '\'' +
                ", userDetails=" + userDetails +
                '}';
    }
}

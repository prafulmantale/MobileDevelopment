package prafulmantale.praful.com.instagramviewer.models;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by prafulmantale on 9/12/14.
 */
public class Likes implements Serializable{

    private Likes() {
        this.count = 0;
    }

    private long count;

    //To Do add user details - user who did likes

    public long getCount() {
        return count;
    }

    private void setCount(long count) {
        this.count = count;
    }

    public static Likes fromJSON(JSONObject jsonObject){

        Likes likes = new Likes();

        try {
            JSONObject likesObject = jsonObject.getJSONObject("likes");
            likes.setCount(likesObject.getLong("count"));
        }
        catch (Exception ex){

        }

        return likes;
    }
}

package prafulmantale.praful.com.instagramviewer.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prafulmantale on 9/12/14.
 */
public class Likes implements Serializable{

    private Likes() {
        this.count = 0;
        likeList = new ArrayList<Like>();
    }

    private long count;
    private List<Like> likeList;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Like> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Like> likeList) {
        this.likeList = likeList;
    }

    public static Likes fromJSON(JSONObject jsonObject){

        Likes likes = new Likes();

        try {
            JSONObject likesObject = jsonObject.getJSONObject("likes");
            likes.setCount(likesObject.getLong("count"));

            JSONArray jsonArray = likesObject.getJSONArray("data");

            for(int i = 0; i < jsonArray.length(); i ++){
                JSONObject obj = null;
                try {
                    obj = jsonArray.getJSONObject(i);
                }
                catch (JSONException e){
                    e.printStackTrace();
                }

                if(obj == null){
                    continue;
                }

                likes.likeList.add(Like.fromJSON(obj));
            }
        }
        catch (Exception ex){

        }

        return likes;
    }
}

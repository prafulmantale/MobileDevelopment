package prafulmantale.praful.com.instagramviewer.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by prafulmantale on 9/12/14.
 */
public class Comments implements Serializable{

    private Comments() {
        this.count = 0;
        this.commentsList = new ArrayList<Comment>();
    }

    private long count;
    private List<Comment> commentsList;

    //To do -- add user details along with comments

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Comment> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comment> commentsList) {
        this.commentsList = commentsList;
    }



    public static Comments fromJSON(JSONObject jsonObject){

        Comments comments = new Comments();

        try {
            
            JSONObject commentsObject = jsonObject.getJSONObject("comments");
            comments.setCount(commentsObject.getLong("count"));

            JSONArray commentsArray = commentsObject.getJSONArray("data");

            for(int i = 0; i < commentsArray.length(); i++) {
                JSONObject obj = null;

                try {
                    obj = commentsArray.getJSONObject(i);
                }
                catch (JSONException ex){

                }

                if(obj == null){
                    continue;
                }

                Comment comment = Comment.fromJSON(obj);

                if(comment != null){
                    comments.commentsList.add(comment);
                }
            }
        }
        catch (Exception ex){

        }

        return comments;
    }
}

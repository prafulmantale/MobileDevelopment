package prafulmantale.praful.com.instagramviewer.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prafulmantale on 9/12/14.
 */
public class Comments implements Serializable{

    public Comments() {
        this.count = 0;
        this.commentsList = new ArrayList<String>();
    }

    private long count;
    private List<String> commentsList;

    //To do -- add user details along with comments

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<String> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<String> commentsList) {
        this.commentsList = commentsList;
    }
}

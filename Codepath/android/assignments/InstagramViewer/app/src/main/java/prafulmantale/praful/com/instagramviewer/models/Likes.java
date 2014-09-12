package prafulmantale.praful.com.instagramviewer.models;

import java.io.Serializable;

/**
 * Created by prafulmantale on 9/12/14.
 */
public class Likes implements Serializable{

    public Likes() {
        this.count = 0;
    }

    private long count;

    //To Do add user details - user who did likes

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}

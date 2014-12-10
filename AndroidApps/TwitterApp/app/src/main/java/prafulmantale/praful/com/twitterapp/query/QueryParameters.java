package prafulmantale.praful.com.twitterapp.query;

/**
 * Created by prafulmantale on 9/29/14.
 */
public class QueryParameters {

    private String userID;
    private String max_id; //Endless scrolling
    private String since_id; //pull to refresh

    public QueryParameters(String max_id, String since_id) {
        this.max_id = max_id;
        this.since_id = since_id;
        userID = null;
    }


    public String getMax_id() {
        return max_id;
    }

    public void setMax_id(String max_id) {
        this.max_id = max_id;
    }

    public String getSince_id() {
        return since_id;
    }

    public void setSince_id(String since_id) {
        this.since_id = since_id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}

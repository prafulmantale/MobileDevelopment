package prafulmantale.praful.com.twitterapp.enums;

/**
 * Created by prafulmantale on 10/6/14.
 */
public enum UserListType {
    Followers(0),
    Following(1);

    private int value;
    UserListType(int val){
        value = val;
    }

    public int getValue() {
        return value;
    }
}

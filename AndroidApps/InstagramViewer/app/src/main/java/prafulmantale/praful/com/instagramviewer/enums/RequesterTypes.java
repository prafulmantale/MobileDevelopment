package prafulmantale.praful.com.instagramviewer.enums;

/**
 * Created by prafulmantale on 9/17/14.
 */
public enum  RequesterTypes {

    VIEW_ALL_COMMENTS (0),
    ADD_COMMENT(1);

    private final int value;

    public int getValue() {
        return value;
    }

    RequesterTypes(int value){
        this.value = value;
    }
}

package prafulmantale.praful.com.yaym.helpers;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class AppConstants {

    private AppConstants(){

    }

    public static final String PARAM_KEY_ORG = "org";
    public static final String PARAM_KEY_NAMESPACE = "namespace";
    public static final String PARAM_KEY_CCYPAIR = "ccyPair";

    public static final String TEXT_ALL = "ALL";


    public static final String RW_SNAPSHOT_RECEIVED = "snapshotreceived";

    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_FAILURE = "FAILURE";
    public static final String STATUS_TEXT = "status";
    public static final String STATUS_OK = "OK";


    public static final String PREF_KEY_FREQUENCY = "freq";
    public static final String PREF_KEY_USER_NAME = "uname";
    public static final String PREF_KEY_USER_ORG = "uorg";
    public static final String PREF_KEY_USER_PASSWORD = "upass";
    public static final String PREF_KEY_SERVER = "server";
    public static final String PREF_KEY_REMEMBER_ME = "remember";


    public static class HandlerMessageIds{
        public static int INVALID = -1;
        public static int LOGIN = 5000;
        public static int LOGOUT = 6000;
        public static int RULE = 5001;
        public static int SNAPSHOT = 5002;
    }
}

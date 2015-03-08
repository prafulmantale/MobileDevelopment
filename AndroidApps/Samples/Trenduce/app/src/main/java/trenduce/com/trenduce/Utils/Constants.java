package trenduce.com.trenduce.Utils;

/**
 * Created by prafulmantale on 3/7/15.
 */
public class Constants {

    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_FAILURE = "FAILURE";
    public static final String STATUS_TEXT = "status";
    public static final String ERROR_CODE = "errorCode";

    public static final String LOGIN_EMAIL_ID_KEY = "emailID";
    public static final String LOGIN_PASSWORD_KEY = "password";

    public static final String BASE_API =  "http://ec2-54-69-50-109.us-west-2.compute.amazonaws.com/trenduce/";

    public static final String LOGIN_API = BASE_API +"login";
    public static final String REGISTER_API = BASE_API +"signup";
    public static final String USERS_API = BASE_API +"users";

    public static final String STYLES_API = BASE_API +"styles";



    public static class HandlerIds{

        public static final int LOGIN = 5000;
        public static final int REGISTER = 5001;
        public static final int USER_PROFILE = 5002;
        public static final int STYLES_ALL = 5003;
    }
}

package prafulmantale.praful.com.twitterapp.helpers;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by prafulmantale on 10/1/14.
 */
public class AppConstants {

    private AppConstants(){

    }

    public static final String KEY_TWEET_REQUEST = "KTR";
    public static final String KEY_TWEET = "KT";

    public static final String KEY_TWEET_ID = "KTI";
    public static final String KEY_USER_HANDLE = "KUH";

    public static final String PREFS_FILE = "TwitterAppPrefs";
    public static final String KEY_SHARED_PREF_USER_NAME = "USERNAME";
    public static final String KEY_SHARED_PREF_SCREEN_NAME = "SCREENNAME";
    public static final String KEY_SHARED_PREF_PROFILE_IMG_URL = "IMGURL";
    public static final String KEY_SHARED_PREF_USER_ID = "USERID";

    public static final String KEY_USER_LIST_TYPE = "USERLISTTYPE";



    public static class RequestCodes {

        private RequestCodes(){
        }

        public static final int COMPOSE_FROM_HOME = 101;
        public static final int TWEET_DETAILS_FROM_HOME = 102;
        public static final int TWEET_REPLY_FROM_HOME= 103;
    }

    public static final long MILLION = 1000000;
    public static final String MILLION_SYM = "M";
    public static final long THOUSAND = 1000;
    public static final String THOUSAND_SYM = "K";

    public static final String TWEETS_UPPER = "TWEETS";
    public static final String FOLLOWING_UPPER = "FOLLOWING";
    public static final String FOLLOWERS_UPPER = "FOLLOWERS";
}

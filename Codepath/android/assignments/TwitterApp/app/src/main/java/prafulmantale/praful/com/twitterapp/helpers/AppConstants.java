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

    public static final String PREFS_FILE = "TwitterAppPrefs";
    public static final String KEY_SHARED_PREF_USER_NAME = "USERNAME";
    public static final String KEY_SHARED_PREF_SCREEN_NAME = "SCREENNAME";
    public static final String KEY_SHARED_PREF_PROFILE_IMG_URL = "IMGURL";
    public static final String KEY_SHARED_PREF_USER_ID = "USERID";



    public static class RequestCodes {

        private RequestCodes(){
        }

        public static final int COMPOSE_FROM_HOME = 101;
        public static final int TWEET_DETAILS_FROM_HOME = 102;
    }
}

package prafulmantale.praful.com.yaym.application;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;

import com.loopj.android.http.PersistentCookieStore;

import prafulmantale.praful.com.yaym.networking.RestClient;

/**
 * Created by prafulmantale on 11/16/14.
 */
public class YMApplication extends Application implements SharedPreferences.OnSharedPreferenceChangeListener{

    //Add preference for poll frequency, if user wants to refresh only on demand etc

    private static final String TAG = YMApplication.class.getSimpleName();

    private static final String REFRESH_SERVICE = "com.yaym.RefreshService";

    private RestClient client;
    private PersistentCookieStore cookieStore;

    @Override
    public void onCreate() {
        super.onCreate();

        cookieStore = new PersistentCookieStore(this);
        client = new RestClient(cookieStore);
    }

    public RestClient getClient(){
        return client;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        sendBroadcast(new Intent(REFRESH_SERVICE));
    }
}

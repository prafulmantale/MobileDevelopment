package prafulmantale.praful.com.yaym.application;

import android.app.Application;

import com.loopj.android.http.PersistentCookieStore;

import prafulmantale.praful.com.yaym.networking.RestClient;

/**
 * Created by prafulmantale on 11/16/14.
 */
public class YMApplication extends Application {

    private static final String TAG = YMApplication.class.getSimpleName();

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
}

package prafulmantale.praful.com.twitterapp.application;


import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import prafulmantale.praful.com.twitterapp.networking.TwitterClient;

/**
 * Created by prafulmantale on 9/25/14.
 */
public class RestClientApp extends com.activeandroid.app.Application{

    private final static String TAG = "RestClientApp";

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        RestClientApp.context = this;

        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                .cacheInMemory()
                .cacheOnDisc()
                .build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(displayImageOptions)
                .build();

        ImageLoader.getInstance().init(configuration);
    }

    public static TwitterClient getTwitterClient(){
        return (TwitterClient)TwitterClient.getInstance(TwitterClient.class, context);
    }
}

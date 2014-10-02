package prafulmantale.praful.com.twitterapp.handlers;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import prafulmantale.praful.com.twitterapp.adapters.TimelineAdapter;
import prafulmantale.praful.com.twitterapp.models.Tweet;

/**
 * Created by prafulmantale on 9/30/14.
 */
public class TweetResponseHandler extends JsonHttpResponseHandler {

    private static String TAG = TweetResponseHandler.class.getName();

    private TimelineAdapter adapter;

    public TweetResponseHandler(TimelineAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onSuccess(JSONObject response) {

        Tweet tweet = Tweet.fromJSON(response);
        if(tweet == null){
            Log.d(TAG, tweet.toString());
            return;
        }

        adapter.insert(tweet, 0);
    }

    @Override
    protected void handleFailureMessage(Throwable e, String responseBody) {
        Log.d(TAG, "handleFailureMessage()");
    }

    @Override
    public void onFailure(Throwable e, JSONObject errorResponse) {
        Log.d(TAG, "onFailure()");
    }
}

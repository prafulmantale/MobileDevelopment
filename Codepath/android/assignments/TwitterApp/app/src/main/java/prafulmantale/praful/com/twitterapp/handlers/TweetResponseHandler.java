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

    private TimelineAdapter adapter;

    public TweetResponseHandler(TimelineAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onSuccess(JSONObject response) {

        Tweet tweet = Tweet.fromJSON(response);
        Log.d("TWEET", tweet.toString());
        adapter.insert(tweet, 0);

    }
}

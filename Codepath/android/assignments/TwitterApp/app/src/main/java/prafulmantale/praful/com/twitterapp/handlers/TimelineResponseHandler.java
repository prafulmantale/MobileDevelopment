package prafulmantale.praful.com.twitterapp.handlers;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import prafulmantale.praful.com.twitterapp.adapters.TimelineAdapter;
import prafulmantale.praful.com.twitterapp.models.Tweet;

/**
 * Created by prafulmantale on 9/27/14.
 */
public class TimelineResponseHandler extends JsonHttpResponseHandler {

    private static final String TAG = "TimelineResponseHandler";
    private TimelineAdapter adapter;


    public TimelineResponseHandler(TimelineAdapter adapter){
        this.adapter = adapter;
    }

    @Override
    public void onSuccess(JSONArray response) {

        Log.d("DEBUG: Response Body: \r\n" , response.toString());

        if(response == null){
            Log.d(TAG, "Null response for successful request");
            return;
        }

        List<Tweet> list = Tweet.fromJSON(response);

        adapter.addAll(list);
    }

    @Override
    public void onFailure(Throwable e, JSONObject errorResponse) {
        Log.d("DEBUG: Failure \r\n", errorResponse.toString());
    }
}

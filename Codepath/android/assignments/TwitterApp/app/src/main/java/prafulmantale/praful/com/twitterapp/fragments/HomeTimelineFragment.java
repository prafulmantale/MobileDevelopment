package prafulmantale.praful.com.twitterapp.fragments;

import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.List;

import prafulmantale.praful.com.twitterapp.application.RestClientApp;
import prafulmantale.praful.com.twitterapp.enums.APIRequest;
import prafulmantale.praful.com.twitterapp.handlers.TimelineResponseHandler;
import prafulmantale.praful.com.twitterapp.models.Tweet;
import prafulmantale.praful.com.twitterapp.query.QueryParameters;

/**
 * Created by prafulmantale on 10/7/14.
 */
public class HomeTimelineFragment extends TweetsFragment {

    private static final String TAG = HomeTimelineFragment.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null) {
            refresh(new QueryParameters(null, null));
        }
    }

    @Override
    protected void refresh(QueryParameters parameters){
        restClient.sendRequest(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray response) {
                try {
                    Log.d("DEBUG: Response Body:" + response.length() + " \r\n", response.toString());

                    if (response == null) {
                        Log.d(TAG, "Null response for successful request");
                        return;
                    }

                    List<Tweet> list = Tweet.fromJSON(response);
                    addAllStart(list);

                } finally {
                    if(swipeRefreshLayout != null) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
            }

            @Override
            public void onFailure(Throwable e, String s) {
                Log.d(TAG, e.toString());
                Log.d(TAG, s.toString());
                if(swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        }, APIRequest.HOME_TIMELINE, parameters);
    }

    @Override
    void fetchNextPage(QueryParameters parameters) {
        RestClientApp.getTwitterClient().sendRequest(new TimelineResponseHandler(adapter, swipeRefreshLayout), APIRequest.HOME_TIMELINE, parameters);
    }
}

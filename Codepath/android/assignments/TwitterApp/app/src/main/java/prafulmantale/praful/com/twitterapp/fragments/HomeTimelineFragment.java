package prafulmantale.praful.com.twitterapp.fragments;

import android.os.Bundle;

import prafulmantale.praful.com.twitterapp.enums.APIRequest;
import prafulmantale.praful.com.twitterapp.enums.RefreshType;
import prafulmantale.praful.com.twitterapp.handlers.NetworkResponseHandler;
import prafulmantale.praful.com.twitterapp.query.QueryParameters;

/**
 * Created by prafulmantale on 10/7/14.
 */
public class HomeTimelineFragment extends TweetsFragment {

    private static final String TAG = HomeTimelineFragment.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestType = APIRequest.HOME_TIMELINE;
        if(savedInstanceState == null) {
            refresh(new QueryParameters(null, null));
        }
    }

    @Override
    protected void refresh(QueryParameters parameters){
        restClient.sendRequest(new NetworkResponseHandler(this, requestType, RefreshType.LATEST), requestType, parameters);
    }

    @Override
    void fetchNextPage(QueryParameters parameters) {
        restClient.sendRequest(new NetworkResponseHandler(this, requestType, RefreshType.PAGINATION), requestType, parameters);
    }
}

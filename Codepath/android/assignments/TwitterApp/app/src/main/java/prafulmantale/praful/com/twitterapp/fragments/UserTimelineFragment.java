package prafulmantale.praful.com.twitterapp.fragments;

import android.os.Bundle;

import prafulmantale.praful.com.twitterapp.enums.APIRequest;
import prafulmantale.praful.com.twitterapp.enums.RefreshType;
import prafulmantale.praful.com.twitterapp.handlers.NetworkResponseHandler;
import prafulmantale.praful.com.twitterapp.helpers.AppConstants;
import prafulmantale.praful.com.twitterapp.models.User;
import prafulmantale.praful.com.twitterapp.query.QueryParameters;

/**
 * Created by prafulmantale on 10/7/14.
 */
public class UserTimelineFragment extends TweetsFragment {

    private static final String TAG = UserTimelineFragment.class.getName();
    private String userID = null;

    public static UserTimelineFragment newInstance(String userID){
        UserTimelineFragment fragment = new UserTimelineFragment();
        Bundle args = new Bundle();
        args.putString(AppConstants.KEY_USER_ID, userID);

        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestType = APIRequest.USER_TIMELINE;
        if(getArguments()!= null && getArguments().containsKey(AppConstants.KEY_USER_ID)){
            userID = getArguments().getString(AppConstants.KEY_USER_ID);
        }

        if(savedInstanceState == null) {
            QueryParameters parameters = new QueryParameters(null, null);
            parameters.setUserID(userID);
            refresh(parameters);
        }
    }

    @Override
    protected void refresh(QueryParameters parameters){
        parameters.setUserID(userID);
        restClient.sendRequest(new NetworkResponseHandler(this, requestType, RefreshType.LATEST), requestType, parameters);
    }

    @Override
    void fetchNextPage(QueryParameters parameters) {
        parameters.setUserID(userID);
        restClient.sendRequest(new NetworkResponseHandler(this, requestType, RefreshType.PAGINATION), requestType, parameters);
    }

    @Override
    public void OnUserProfileRequested(User user) {
       //NO OP
    }

}

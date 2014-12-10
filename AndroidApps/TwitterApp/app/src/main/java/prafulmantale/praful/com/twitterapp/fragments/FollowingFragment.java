package prafulmantale.praful.com.twitterapp.fragments;

import android.os.Bundle;

import prafulmantale.praful.com.twitterapp.enums.APIRequest;
import prafulmantale.praful.com.twitterapp.enums.RefreshType;
import prafulmantale.praful.com.twitterapp.handlers.NetworkResponseHandler;
import prafulmantale.praful.com.twitterapp.helpers.AppConstants;

/**
 * Created by prafulmantale on 10/7/14.
 */
public class FollowingFragment extends UsersListFragment {

    public static FollowingFragment newInstance(String userID){
        FollowingFragment fragment = new FollowingFragment();
        Bundle args = new Bundle();
        args.putString(AppConstants.KEY_USER_ID, userID);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestType = APIRequest.FRIENDS_LIST;
        if(savedInstanceState == null){
           fetchNextPage();
        }
    }

    @Override
    void fetchNextPage() {
        restClient.getFriendsList(new NetworkResponseHandler(this, requestType, RefreshType.PAGINATION), userID, nextCursor);
    }
}

package prafulmantale.praful.com.twitterapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.adapters.UserListAdapter;
import prafulmantale.praful.com.twitterapp.application.RestClientApp;
import prafulmantale.praful.com.twitterapp.enums.APIRequest;
import prafulmantale.praful.com.twitterapp.enums.RefreshType;
import prafulmantale.praful.com.twitterapp.handlers.NetworkResponseHandler;
import prafulmantale.praful.com.twitterapp.helpers.AppConstants;
import prafulmantale.praful.com.twitterapp.interfaces.NetworkResponseListener;
import prafulmantale.praful.com.twitterapp.listeners.EndlessScrollListener;
import prafulmantale.praful.com.twitterapp.models.UserProfile;
import prafulmantale.praful.com.twitterapp.networking.TwitterClient;

/**
 * Created by prafulmantale on 10/3/14.
 */
public abstract class UsersListFragment extends Fragment implements NetworkResponseListener{

    private static final String TAG = UsersListFragment.class.getName();

    protected UserListAdapter adapter;
    protected List<UserProfile> userProfileList;
    protected ListView lvUsers;
    protected String userID;
    protected TwitterClient restClient ;

    protected SwipeRefreshLayout swipeRefreshLayout;
    public String nextCursor = null;
    public String prev_nextCursor = null;

    protected APIRequest requestType;


    public UsersListFragment(){


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null && getArguments().containsKey(AppConstants.KEY_USER_ID)){
            userID = getArguments().getString(AppConstants.KEY_USER_ID);
        }

        //Non-view initialization
        restClient = RestClientApp.getTwitterClient();
        userProfileList = new ArrayList<UserProfile>();
        adapter = new UserListAdapter(getActivity(), userProfileList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_items_list, container, false);

        lvUsers = (ListView) view.findViewById(R.id.lvTweets_tweets);
        lvUsers.setAdapter(adapter);

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout_tweets_fragment);

        setupListeners();

        return view;
    }

    protected void setupListeners(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        lvUsers.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalCount) {

                if (totalCount == 0 || nextCursor == null ||
                        prev_nextCursor == nextCursor) {
                    return;
                }

                prev_nextCursor = nextCursor;
                fetchNextPage();
            }
        });
    }

    abstract void fetchNextPage();

    @Override
    public void OnNetworkResponseReceived(NetworkResponseHandler.RequestStatus status, APIRequest requestType, Object responseObject, RefreshType refreshType) {

        if(requestType == this.requestType){
            try {
                JSONObject response = (JSONObject) responseObject;
                String nextCur = response.optString("next_cursor", null);
                nextCursor = nextCur;
                JSONArray jsonArray = response.getJSONArray("users");
                adapter.addAll(UserProfile.fromJSON(jsonArray));
            }
            catch (Exception ex){
                Log.d(TAG, "Exception processing response for " + requestType.toString());
            }
        }
    }
}

package prafulmantale.praful.com.twitterapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.adapters.UserListAdapter;
import prafulmantale.praful.com.twitterapp.application.RestClientApp;
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
}

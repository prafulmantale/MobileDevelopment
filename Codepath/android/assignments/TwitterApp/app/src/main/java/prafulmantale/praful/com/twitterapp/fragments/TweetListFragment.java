package prafulmantale.praful.com.twitterapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.activities.CreateTweetActivity;
import prafulmantale.praful.com.twitterapp.adapters.TimelineAdapter;
import prafulmantale.praful.com.twitterapp.adapters.UserListAdapter;
import prafulmantale.praful.com.twitterapp.application.RestClientApp;
import prafulmantale.praful.com.twitterapp.enums.APIRequest;
import prafulmantale.praful.com.twitterapp.enums.UserListType;
import prafulmantale.praful.com.twitterapp.handlers.TimelineResponseHandler;
import prafulmantale.praful.com.twitterapp.handlers.UsersListResponseHandler;
import prafulmantale.praful.com.twitterapp.helpers.AppConstants;
import prafulmantale.praful.com.twitterapp.interfaces.ViewsClickListener;
import prafulmantale.praful.com.twitterapp.listeners.EndlessScrollListener;
import prafulmantale.praful.com.twitterapp.models.Tweet;
import prafulmantale.praful.com.twitterapp.models.User;
import prafulmantale.praful.com.twitterapp.models.UserProfile;
import prafulmantale.praful.com.twitterapp.query.QueryParameters;

/**
 * Created by prafulmantale on 10/3/14.
 */
public class TweetListFragment extends Fragment implements ViewsClickListener {

    private static final String TAG = TweetListFragment.class.getName();

    private TimelineAdapter adapter;
    private List<Tweet> tweetList;
    private ListView lvTweets;
    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String preMaxId = null;
    private String userID;

    public TweetListFragment(){


    }

    public static TweetListFragment newInstance(String userID){
        TweetListFragment fragment = new TweetListFragment();

        Bundle args = new Bundle();
        args.putString(AppConstants.KEY_USER_ID, userID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userID = getArguments().getString(AppConstants.KEY_USER_ID);

        tweetList = new ArrayList<Tweet>();
        adapter = new TimelineAdapter(getActivity(), tweetList);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_users_list, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout_user_list);
        lvTweets = (ListView) view.findViewById(R.id.lvItemsList_users_list);

        lvTweets.setAdapter(adapter);


        QueryParameters parameters = new QueryParameters(null, null);
        parameters.setUserID(userID);
        RestClientApp.getTwitterClient().sendRequest(new TimelineResponseHandler(adapter, null), APIRequest.USER_TIMELINE, parameters);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String since_id = null;
                if (adapter.getCount() != 0) {
                    Tweet tweet = adapter.getItem(0);
                    since_id = String.valueOf(tweet.getTweetID());
                }

                QueryParameters parameters = new QueryParameters(null, since_id);
                parameters.setUserID(userID);
                RestClientApp.getTwitterClient().sendRequest(new TimelineResponseHandler(adapter, swipeRefreshLayout), APIRequest.USER_TIMELINE, parameters);
            }
        });

        swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        if (adapter.getCount() != 0) {
            Tweet tweet = adapter.getItem(adapter.getCount() - 1);
            preMaxId = String.valueOf(tweet.getTweetID());
        }
        setupListeners();
    }

    private void setupListeners() {

        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalCount) {

                if (totalCount == 0) {
                    return;
                }

                String max_id = null;
                if (adapter.getCount() != 0) {
                    Tweet tweet = adapter.getItem(adapter.getCount() - 1);
                    max_id = String.valueOf(tweet.getTweetID());

                    if (preMaxId != null && preMaxId.equalsIgnoreCase(max_id)) {
                        return;
                    }

                    preMaxId = max_id;
                }

                QueryParameters parameters = new QueryParameters(max_id, null);
                parameters.setUserID(userID);
                RestClientApp.getTwitterClient().sendRequest(new TimelineResponseHandler(adapter, swipeRefreshLayout), APIRequest.USER_TIMELINE, parameters);
            }
        });
    }

    @Override
    public void OnReplyToTweetRequested(Tweet tweet) {

    }

    @Override
    public void OnCreateFavoriteTweetRequested(Tweet tweet) {

    }

    @Override
    public void OnDestroyFavoriteTweetRequested(Tweet tweet) {

    }

    @Override
    public void OnUserProfileRequested(User user) {

    }
}

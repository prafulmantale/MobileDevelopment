package prafulmantale.praful.com.twitterapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.adapters.TimelineAdapter;
import prafulmantale.praful.com.twitterapp.adapters.UserListAdapter;
import prafulmantale.praful.com.twitterapp.application.RestClientApp;
import prafulmantale.praful.com.twitterapp.enums.APIRequest;
import prafulmantale.praful.com.twitterapp.enums.UserListType;
import prafulmantale.praful.com.twitterapp.handlers.TimelineResponseHandler;
import prafulmantale.praful.com.twitterapp.handlers.UsersListResponseHandler;
import prafulmantale.praful.com.twitterapp.helpers.AppConstants;
import prafulmantale.praful.com.twitterapp.interfaces.ViewsClickListener;
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

    public TweetListFragment(){


    }

    public static TweetListFragment newInstance(){
        TweetListFragment fragment = new TweetListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tweetList = new ArrayList<Tweet>();
        adapter = new TimelineAdapter(getActivity(), tweetList);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_users_list, container, false);

        lvTweets = (ListView) view.findViewById(R.id.lvItemsList_users_list);

        lvTweets.setAdapter(adapter);

        QueryParameters parameters = new QueryParameters(null, null);
        //parameters.setUserID(String.valueOf(user.getUserID()));
        //RestClientApp.getTwitterClient().getFollowersList(new TimelineResponseHandler(adapter, null), "");
        RestClientApp.getTwitterClient().sendRequest(new TimelineResponseHandler(adapter, null), APIRequest.USER_TIMELINE, parameters);

        return view;
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

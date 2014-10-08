package prafulmantale.praful.com.twitterapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.activities.CreateTweetActivity;
import prafulmantale.praful.com.twitterapp.activities.TweetDetailsActivity;
import prafulmantale.praful.com.twitterapp.activities.UserProfileActivity;
import prafulmantale.praful.com.twitterapp.adapters.TimelineAdapter;
import prafulmantale.praful.com.twitterapp.application.RestClientApp;
import prafulmantale.praful.com.twitterapp.enums.APIRequest;
import prafulmantale.praful.com.twitterapp.handlers.TweetResponseHandler;
import prafulmantale.praful.com.twitterapp.helpers.AppConstants;
import prafulmantale.praful.com.twitterapp.interfaces.NetworkOperationsListener;
import prafulmantale.praful.com.twitterapp.interfaces.ViewsClickListener;
import prafulmantale.praful.com.twitterapp.listeners.EndlessScrollListener;
import prafulmantale.praful.com.twitterapp.models.Tweet;
import prafulmantale.praful.com.twitterapp.models.TweetRequest;
import prafulmantale.praful.com.twitterapp.models.User;
import prafulmantale.praful.com.twitterapp.models.UserProfile;
import prafulmantale.praful.com.twitterapp.networking.TwitterClient;
import prafulmantale.praful.com.twitterapp.query.QueryParameters;

/**
 * Created by prafulmantale on 10/7/14.
 */
public abstract class TweetsFragment extends Fragment  implements ViewsClickListener{

    protected TimelineAdapter adapter;
    protected List<Tweet> tweetsList;
    protected ListView lvTweets;
    protected TwitterClient restClient ;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected String preMaxId = null;

    protected NetworkOperationsListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Non-view initialization
        restClient = RestClientApp.getTwitterClient();
        tweetsList = new ArrayList<Tweet>();
        adapter = new TimelineAdapter(getActivity(), tweetsList, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout
        View view = inflater.inflate(R.layout.fragment_items_list, container, false);

        //Assign view references
        lvTweets = (ListView)view.findViewById(R.id.lvTweets_tweets);
        lvTweets.setAdapter(adapter);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout_tweets_fragment);
        swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        setupListeners();
        return view;
    }

    protected void setupListeners(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String since_id = null;
                if (adapter.getCount() != 0) {
                    Tweet tweet = adapter.getItem(0);
                    since_id = String.valueOf(tweet.getTweetID());
                }

                QueryParameters parameters = new QueryParameters(null, since_id);
                parameters.setUserID(null);
                refresh(parameters);
            }
        });

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

                fetchNextPage(new QueryParameters(max_id, null));
            }
        });

        lvTweets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showTweetDetails(adapter.getItem(position));
            }
        });
    }

    private void showTweetDetails(Tweet tweet) {
        Intent intent = new Intent(getActivity(), TweetDetailsActivity.class);
        intent.putExtra(AppConstants.KEY_TWEET, tweet);
        startActivity(intent);
    }

    abstract void refresh(QueryParameters parameters);

    abstract void fetchNextPage(QueryParameters parameters);

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



    public void addAll(List<Tweet> tweets){
        if(tweets == null || tweets.size() == 0){
            return;
        }

        adapter.addAll(tweets);

        preMaxId = String.valueOf(adapter.getItem(adapter.getCount() - 1).getTweetID());
    }

    public void addAllStart(List<Tweet> tweets){
        if(tweets == null || tweets.size() == 0){
            return;
        }

        tweetsList.addAll(0, tweets);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void OnReplyToTweetRequested(Tweet tweet) {
        startComposeForReply(tweet);
    }

    private void startComposeForReply(Tweet tweet) {

        Intent intent = new Intent(getActivity(), CreateTweetActivity.class);
        intent.putExtra(AppConstants.KEY_TWEET_ID, tweet.getTweetID());
        intent.putExtra(AppConstants.KEY_USER_HANDLE, tweet.getUser().getScreenName());

        startActivityForResult(intent, AppConstants.RequestCodes.TWEET_REPLY_FROM_HOME);
    }

    @Override
    public void OnCreateFavoriteTweetRequested(final Tweet tweet) {
        TweetRequest request = new TweetRequest();
        request.setTweetID(tweet.getTweetID());
        RestClientApp.getTwitterClient().postCreateFavoriteTweet(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONObject response) {
                //super.onSuccess(response);
                tweet.setFavorited(true);
            }
        }, request);
    }

    @Override
    public void OnDestroyFavoriteTweetRequested(final Tweet tweet) {

        TweetRequest request = new TweetRequest();
        request.setTweetID(tweet.getTweetID());
        RestClientApp.getTwitterClient().postDestroyFavoriteTweet(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONObject response) {
                //super.onSuccess(response);
                tweet.setFavorited(false);
            }
        }, request);
    }

    @Override
    public void OnUserProfileRequested(User user) {

        QueryParameters parameters = new QueryParameters(null, null);
        parameters.setUserID(String.valueOf(user.getUserID()));
        //RestClientApp.getTwitterClient().sendRequest(new TimelineResponseHandler(adapter, swipeRefreshLayout), APIRequest.USER_TIMELINE, parameters);
        showUserProfile(user);
    }

    private void showUserProfile(final User user) {

        QueryParameters qp = new QueryParameters(null, null);
        qp.setUserID(String.valueOf(user.getUserID()));

        RestClientApp.getTwitterClient().sendRequest(new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(JSONArray response) {

                System.out.println("User Profile:\r\n" + response);
                try {
                    UserProfile userProfile = UserProfile.fromJSON(response.getJSONObject(0));
                    Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                    intent.putExtra("UID", userProfile);
                    startActivity(intent);
                }
                catch (JSONException ex){
                    Log.d("TWEETS", ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }, APIRequest.USER_PROFILE, qp);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == AppConstants.RequestCodes.COMPOSE_FROM_HOME) {
            if (resultCode == Activity.RESULT_OK) {
                TweetRequest request = data.getParcelableExtra(AppConstants.KEY_TWEET_REQUEST);
                RestClientApp.getTwitterClient().postTweet(new TweetResponseHandler(adapter), request);
            }
        }

        if (requestCode == AppConstants.RequestCodes.TWEET_REPLY_FROM_HOME) {
            if (resultCode == Activity.RESULT_OK) {
                TweetRequest request = data.getParcelableExtra(AppConstants.KEY_TWEET_REQUEST);

                RestClientApp.getTwitterClient().postTweet(new TweetResponseHandler(adapter), request);
            }
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            listener = (NetworkOperationsListener) activity;
        }
        catch (Exception ex){
            throw new ClassCastException(activity.toString()
                    + " must implement NetworkOperationsListener");
        }
    }
}

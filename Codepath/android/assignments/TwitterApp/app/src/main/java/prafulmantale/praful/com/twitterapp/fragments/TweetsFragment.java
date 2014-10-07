package prafulmantale.praful.com.twitterapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.adapters.TimelineAdapter;
import prafulmantale.praful.com.twitterapp.application.RestClientApp;
import prafulmantale.praful.com.twitterapp.enums.APIRequest;
import prafulmantale.praful.com.twitterapp.handlers.TimelineResponseHandler;
import prafulmantale.praful.com.twitterapp.listeners.EndlessScrollListener;
import prafulmantale.praful.com.twitterapp.models.Tweet;
import prafulmantale.praful.com.twitterapp.networking.TwitterClient;
import prafulmantale.praful.com.twitterapp.query.QueryParameters;

/**
 * Created by prafulmantale on 10/7/14.
 */
public abstract class TweetsFragment extends Fragment {

    protected TimelineAdapter adapter;
    protected List<Tweet> tweetsList;
    protected ListView lvTweets;
    protected TwitterClient restClient ;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected String preMaxId = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Non-view initialization
        restClient = RestClientApp.getTwitterClient();
        tweetsList = new ArrayList<Tweet>();
        adapter = new TimelineAdapter(getActivity(), tweetsList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout
        View view = inflater.inflate(R.layout.fragment_tweets, container, false);

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
}

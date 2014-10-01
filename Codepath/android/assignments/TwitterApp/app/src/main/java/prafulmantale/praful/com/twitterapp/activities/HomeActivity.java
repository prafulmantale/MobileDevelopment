package prafulmantale.praful.com.twitterapp.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.adapters.TimelineAdapter;
import prafulmantale.praful.com.twitterapp.enums.APIRequest;
import prafulmantale.praful.com.twitterapp.handlers.LoggedInUserResponseHandler;
import prafulmantale.praful.com.twitterapp.handlers.TimelineResponseHandler;
import prafulmantale.praful.com.twitterapp.handlers.TweetResponseHandler;
import prafulmantale.praful.com.twitterapp.helpers.AppConstants;
import prafulmantale.praful.com.twitterapp.listeners.EndlessScrollListener;
import prafulmantale.praful.com.twitterapp.models.Tweet;
import prafulmantale.praful.com.twitterapp.models.TweetRequest;
import prafulmantale.praful.com.twitterapp.models.User;
import prafulmantale.praful.com.twitterapp.networking.RestClientApp;
import prafulmantale.praful.com.twitterapp.query.QueryParameters;

public class HomeActivity extends Activity {


    private ListView lvTimeline;
    private TimelineAdapter adapter;
    private List<Tweet> tweetList;
    private ImageView ivComposeTweet;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String preMaxId = null;
    public static User loggedInUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initialize();
        setupListeners();

        if(loggedInUser == null){
            RestClientApp.getTwitterClient().sendRequest(new LoggedInUserResponseHandler(), APIRequest.LOGGEDIN_USER_INFO, new QueryParameters(null, null));
        }

        RestClientApp.getTwitterClient().sendRequest(new TimelineResponseHandler(adapter, swipeRefreshLayout), APIRequest.HOME_TIMELINE, new QueryParameters(null, null));
    }

    private void initialize(){

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String since_id = null;
                if(adapter.getCount() != 0){
                    Tweet tweet = adapter.getItem(0);
                    since_id = String.valueOf(tweet.getUid());
                }

                RestClientApp.getTwitterClient().sendRequest(new TimelineResponseHandler(adapter, swipeRefreshLayout), APIRequest.HOME_TIMELINE, new QueryParameters(null, since_id));
            }
        });

        swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        initializeActionBar();
        lvTimeline = (ListView)findViewById(R.id.lvTimeline);
        tweetList = new ArrayList<Tweet>();
        adapter = new TimelineAdapter(this, tweetList);
        lvTimeline.setAdapter(adapter);
    }


    private void initializeActionBar(){

        final ActionBar actionBar = getActionBar();

        View view = getLayoutInflater().inflate(R.layout.action_bar_home_title, null);
        ivComposeTweet = (ImageView)view.findViewById(R.id.ivCreateTweet_actionbar);


        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        actionBar.setCustomView(view, params);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setHomeButtonEnabled(false);

    }

    private void setupListeners(){
        ivComposeTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CreateTweetActivity.class);
                startActivityForResult(intent, AppConstants.RequestCodes.COMPOSE_FROM_HOME);
            }
        });

        lvTimeline.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalCount) {

                if(totalCount == 0){
                    return;
                }

                String max_id = null;
                if(adapter.getCount() != 0){
                    Tweet tweet = adapter.getItem(adapter.getCount() - 1);
                    max_id = String.valueOf(tweet.getUid());

                    if(preMaxId != null && preMaxId.equalsIgnoreCase(max_id)){
                        return;
                    }

                    preMaxId = max_id;
                }

                RestClientApp.getTwitterClient().sendRequest(new TimelineResponseHandler(adapter, swipeRefreshLayout), APIRequest.HOME_TIMELINE, new QueryParameters(max_id, null));
            }
        });

        lvTimeline.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showTweetDetails(adapter.getItem(position));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == AppConstants.RequestCodes.COMPOSE_FROM_HOME){
            if(resultCode == RESULT_OK){
                TweetRequest request = data.getParcelableExtra(AppConstants.KEY_TWEET_REQUEST);
                RestClientApp.getTwitterClient().postTweet(new TweetResponseHandler(adapter), request);
            }
        }
    }

    private void showTweetDetails(Tweet tweet){
        Intent intent = new Intent(this, TweetDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

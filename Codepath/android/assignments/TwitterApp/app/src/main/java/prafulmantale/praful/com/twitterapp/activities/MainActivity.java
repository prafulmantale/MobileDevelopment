package prafulmantale.praful.com.twitterapp.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.application.RestClientApp;
import prafulmantale.praful.com.twitterapp.enums.APIRequest;
import prafulmantale.praful.com.twitterapp.fragments.HomeTimelineFragment;
import prafulmantale.praful.com.twitterapp.fragments.MentionsTimelineFragment;
import prafulmantale.praful.com.twitterapp.handlers.NetworkResponseHandler;
import prafulmantale.praful.com.twitterapp.helpers.AppConstants;
import prafulmantale.praful.com.twitterapp.interfaces.NetworkOperationsListener;
import prafulmantale.praful.com.twitterapp.interfaces.NetworkResponseListener;
import prafulmantale.praful.com.twitterapp.listeners.FragmentTabListener;
import prafulmantale.praful.com.twitterapp.models.TweetRequest;
import prafulmantale.praful.com.twitterapp.models.User;
import prafulmantale.praful.com.twitterapp.models.UserProfile;
import prafulmantale.praful.com.twitterapp.query.QueryParameters;

public class MainActivity extends FragmentActivity implements NetworkOperationsListener, NetworkResponseListener{

    private static final String TAG = MainActivity.class.getName();

    public static User loggedInUser = null;
    public static UserProfile lp = null;

    private ImageView ivComposeTweet;
    private ImageView ivProfile;
    private TextView tvActionBarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);

        loggedInUser = User.getLoggedInUserDetails(this);

        //if (loggedInUser == null) {
        RestClientApp.getTwitterClient().sendRequest(new NetworkResponseHandler(this, APIRequest.LOGGEDIN_USER_INFO), APIRequest.LOGGEDIN_USER_INFO, new QueryParameters(null, null));
        //}

        initializeActionBar();
        setupListeners();
    }

    @Override
    public void OnNetworkResponseReceived(NetworkResponseHandler.RequestStatus status, APIRequest requestType, Object responseObject) {
        if(requestType == APIRequest.LOGGEDIN_USER_INFO){
            JSONObject response = (JSONObject)responseObject;
            User user = User.fromJSON(response);
            UserProfile userProfile = UserProfile.fromJSON(response);
            loggedInUser = user;
            lp = userProfile;
            User.saveLoggedInUserDetails(getBaseContext(), user);
            Log.d("USER", user.toString());//??
        }
    }

    public void showProgressBar(){
        setProgressBarIndeterminateVisibility(true);
    }

    public void hideProgressBar(){
        setProgressBarIndeterminateVisibility(false);
    }

    private void initializeActionBar() {

        final ActionBar actionBar = getActionBar();

        View view = getLayoutInflater().inflate(R.layout.action_bar_home_title, null);
        ivComposeTweet = (ImageView) view.findViewById(R.id.ivCreateTweet_actionbar);
        ivProfile = (ImageView)view.findViewById(R.id.ivShowProfile_actionbar);
        tvActionBarTitle = (TextView)view.findViewById(R.id.tvTitle_home);


        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);


        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setCustomView(view, params);
        actionBar.setDisplayShowHomeEnabled(true);

        //Hack to hide the home icon -- Otherwise the action bar was getting displayed on top of Tabs
        View homeIcon = findViewById(android.R.id.home);
        ((View) homeIcon.getParent()).setVisibility(View.GONE);

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab tab1 = actionBar.newTab().setText(R.string.tab_home)
                .setIcon(R.drawable.ic_home)
                .setTag("HOME")
                .setTabListener(new FragmentTabListener<HomeTimelineFragment>(R.id.flContainer, this, "HOME", HomeTimelineFragment.class, tvActionBarTitle));

        actionBar.addTab(tab1);
        actionBar.selectTab(tab1);

        ActionBar.Tab tab2 = actionBar.newTab().setText(R.string.tab_mentions)
                .setIcon(R.drawable.ic_mentions)
                .setTag("MENTIONS")
                .setTabListener(new FragmentTabListener<MentionsTimelineFragment>(R.id.flContainer, this, "MENTIONS", MentionsTimelineFragment.class, tvActionBarTitle));

        actionBar.addTab(tab2);

    }

    private void setupListeners() {
        ivComposeTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateTweetActivity.class);
                startActivityForResult(intent, AppConstants.RequestCodes.COMPOSE_FROM_HOME);
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProfile();
            }
        });
    }

    private void showProfile(){
        Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
        intent.putExtra("UID", lp);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == AppConstants.RequestCodes.COMPOSE_FROM_HOME) {
            if (resultCode == Activity.RESULT_OK) {
                TweetRequest request = data.getParcelableExtra(AppConstants.KEY_TWEET_REQUEST);
                //RestClientApp.getTwitterClient().postTweet(new TweetResponseHandler(), request);
            }
        }
    }

    public void onProfileView(MenuItem item){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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


    @Override
    public void OnNetworkOperationStarted() {
        showProgressBar();
    }

    @Override
    public void OnNetworkOperationFinished() {
        hideProgressBar();
    }
}

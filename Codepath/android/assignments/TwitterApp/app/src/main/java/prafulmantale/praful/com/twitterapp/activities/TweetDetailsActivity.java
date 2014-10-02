package prafulmantale.praful.com.twitterapp.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.helpers.AppConstants;
import prafulmantale.praful.com.twitterapp.models.Tweet;

public class TweetDetailsActivity extends Activity {

    private Tweet tweet;
    private ImageView ivBack;

    private ImageView ivProfileImage;
    private TextView tvUserName;
    private TextView tvScreenName;
    private TextView tvBody;
    private EditText etTweetReply;

    private TextView tvReply;
    private TextView tvRetweets;
    private TextView tvFavorites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        tweet = (Tweet)getIntent().getParcelableExtra(AppConstants.KEY_TWEET);
        Log.d("TDA", tweet.toString());
        initialize();
    }

    private void initialize(){

        ivProfileImage = (ImageView)findViewById(R.id.ivProfilePicture_tweetdetails);
        tvUserName = (TextView)findViewById(R.id.tvUserName_tweetdetails);
        tvScreenName = (TextView)findViewById(R.id.tvUserHandle_tweetdetails);
        tvBody = (TextView)findViewById(R.id.tvTweet_tweetdetails);
        etTweetReply = (EditText)findViewById(R.id.etTweetText_tweetdetails);
        tvReply = (TextView)findViewById(R.id.ivReply_tweetdetails);
        tvRetweets = (TextView)findViewById(R.id.tvRetweets_tweetdetails);
        tvFavorites = (TextView)findViewById(R.id.tvFavorites_tweetdetails);


        etTweetReply.setHint("Reply to " + tweet.getUser().getName());

        ImageLoader.getInstance().displayImage(tweet.getUser().getProfileImageUrl(), ivProfileImage);
        tvUserName.setText(tweet.getUser().getName());
        tvScreenName.setText(tweet.getUser().getScreenName());
        tvBody.setText(tweet.getText());

        tvRetweets.setText(tweet.getRetweet_count());
        tvFavorites.setText(tweet.getFavorite_count());

        initializeActionBar();
        setUpListeners();
    }

    private void initializeActionBar(){

        final ActionBar actionBar = getActionBar();
        View view = getLayoutInflater().inflate(R.layout.action_bar_home_title, null);

        TextView tvTitle = (TextView)view.findViewById(R.id.tvTitle_home);
        tvTitle.setText(R.string.button_tweet_text);
        ivBack = (ImageView)view.findViewById(R.id.ivBackbutton);
        ivBack.setVisibility(View.VISIBLE);
        ImageView ivBird = (ImageView)view.findViewById(R.id.ivFindPeopleIcon_home);
        ivBird.setVisibility(View.GONE);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);

        actionBar.setCustomView(view, params);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
    }

    private void setUpListeners(){

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tweet_details, menu);
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

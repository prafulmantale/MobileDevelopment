package prafulmantale.praful.com.twitterapp.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.helpers.AppConstants;
import prafulmantale.praful.com.twitterapp.models.TweetRequest;

public class CreateTweetActivity extends Activity {

    private TextView tvCharCount;
    private EditText etTweetBody;
    private Button btnTweet;

    private ImageView ivProfileImage;
    private TextView tvUserName;
    private TextView tvScreenName;

    private long tweetID = -1;
    private String userHandle = null;
    private boolean isReplyMessage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tweet);

        if(getIntent().hasExtra(AppConstants.KEY_TWEET_ID) && getIntent().hasExtra(AppConstants.KEY_USER_HANDLE)){
            tweetID = (long)getIntent().getLongExtra(AppConstants.KEY_TWEET_ID, -1);
            userHandle = (String)getIntent().getStringExtra(AppConstants.KEY_USER_HANDLE);
            isReplyMessage = true;
        }

        initialize();
    }


    private void initialize(){

        etTweetBody = (EditText)findViewById(R.id.etTweetText_createtweet);

        ivProfileImage = (ImageView)findViewById(R.id.ivProfilePicture_createtweet);
        tvUserName = (TextView)findViewById(R.id.tvUserName_createtweet);
        tvScreenName = (TextView)findViewById(R.id.tvUserHandle_createtweet);

        ImageLoader.getInstance().displayImage(HomeActivity.loggedInUser.getProfileImageUrl(), ivProfileImage);
        tvUserName.setText(HomeActivity.loggedInUser.getName());
        tvScreenName.setText("@" + HomeActivity.loggedInUser.getScreenName());

        initializeActionBar();
        setupListeners();

        if(userHandle != null && !userHandle.isEmpty()){
            etTweetBody.setText("@" + userHandle + " ");
        }

        etTweetBody.requestFocus();
    }

    private void initializeActionBar(){

        ActionBar actionBar = getActionBar();

        View view = getLayoutInflater().inflate(R.layout.action_bar_tweet_title, null);
        tvCharCount = (TextView)view.findViewById(R.id.tvCharacterCount_tweet_title);
        btnTweet = (Button)view.findViewById(R.id.btnTweet_tweet_bar);

        btnTweet.setEnabled(false);
        tvCharCount.setText("140");

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        actionBar.setCustomView(view, params);

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void setupListeners(){

        etTweetBody.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(etTweetBody.getText().toString().length() == 0){
                    btnTweet.setEnabled(false);
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        btnTweet.setBackground(getResources().getDrawable(R.drawable.tweet_button_disabled_style));
                    }
                    else{
                        btnTweet.setBackgroundResource(R.drawable.tweet_button_disabled_style);
                    }
                    tvCharCount.setText("140");
                }
                else{
                    btnTweet.setEnabled(true);
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        btnTweet.setBackground(getResources().getDrawable(R.drawable.tweet_button_enabled_style));
                    }
                    else{
                        btnTweet.setBackgroundResource(R.drawable.signup_button_style);
                    }
                    tvCharCount.setText(String.valueOf((140 - etTweetBody.getText().length())));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = etTweetBody.getText().toString();
                if(status.length() == 0){
                    setResult(RESULT_CANCELED);
                }
                else{
                    Intent intent = new Intent();
                    TweetRequest tweetReq = new TweetRequest();
                    tweetReq.setBody(status);

                    if(isReplyMessage){
                        tweetReq.setTweetID(tweetID);
                    }

                    intent.putExtra(AppConstants.KEY_TWEET_REQUEST, tweetReq);

                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_tweet, menu);
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

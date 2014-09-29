package prafulmantale.praful.com.twitterapp.activities;

import android.app.ActionBar;
import android.app.Activity;
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
import android.widget.TextView;

import prafulmantale.praful.com.twitterapp.R;

public class CreateTweetActivity extends Activity {

    private TextView tvCharCount;
    private EditText etTweetBody;
    private Button btnTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tweet);

        initialize();
    }


    private void initialize(){

        etTweetBody = (EditText)findViewById(R.id.etTweetText_createtweet);
        etTweetBody.requestFocus();

        initializeActionBar();
        setupListeners();
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

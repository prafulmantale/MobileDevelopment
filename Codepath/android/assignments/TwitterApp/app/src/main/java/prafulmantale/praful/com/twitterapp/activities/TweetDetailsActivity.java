package prafulmantale.praful.com.twitterapp.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.models.Tweet;

public class TweetDetailsActivity extends Activity {

    private Tweet tweet;
    private ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        initialize();
    }

    private void initialize(){

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

package prafulmantale.praful.com.instagramviewer.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import prafulmantale.praful.com.instagramviewer.R;
import prafulmantale.praful.com.instagramviewer.Utils.AppConstants;
import prafulmantale.praful.com.instagramviewer.Utils.RoundedTransformation;
import prafulmantale.praful.com.instagramviewer.models.UserDetails;

public class UserDetailsActivity extends Activity {

    private UserDetails userDetails;
    private ImageView ivProfilePicture;
    private TextView tvPosts;
    private TextView tvFollowedBy;
    private TextView tvFollowing;
    private TextView tvFullName;
    private TextView tvBio;
    private TextView tvWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        userDetails = (UserDetails)getIntent().getSerializableExtra(AppConstants.USER_DETAILS_KEY);

        initializeActionBar();

        initialize();

        updateViewData();
    }

    private void initializeActionBar(){

        final ActionBar actionBar = getActionBar();

        View viewActionBar = getLayoutInflater().inflate(R.layout.activity_action_bar_title, null);


        //Define layout params
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);

        //Set text - displayed as title
        TextView textView = (TextView) viewActionBar.findViewById(R.id.tvActionBarTitle);
        textView.setText(userDetails.getUsername());

        //Set custom view
        actionBar.setCustomView(viewActionBar, layoutParams);
        actionBar.setDisplayShowCustomEnabled(true);

        //Show home button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //Hide title
        actionBar.setDisplayShowTitleEnabled(false);
    }

    private void initialize(){


        ivProfilePicture = (ImageView)findViewById(R.id.ivUserDetailsProfilePicture);
        tvPosts = (TextView)findViewById(R.id.tvPostsCount);
        tvFollowedBy = (TextView)findViewById(R.id.tvFollowersCount);
        tvFollowing = (TextView)findViewById(R.id.tvFollowingCount);
        tvFullName = (TextView)findViewById(R.id.tvUserFullName);
        tvBio = (TextView)findViewById(R.id.tvUserBio);
        tvWebsite = (TextView)findViewById(R.id.tvWebsite);
    }

    private void updateViewData(){

        Picasso.with(getBaseContext()).load(userDetails.getProfilePictureUrl()).transform(new RoundedTransformation(100, 3)).into(ivProfilePicture);

        tvPosts.setText(userDetails.getSocialDetails().getPosts() + "\r\nposts");
        tvFollowedBy.setText(userDetails.getSocialDetails().getFollowedBy() + "\r\nfollowers");
        tvFollowing.setText(userDetails.getSocialDetails().getFollows() + "\r\nfollowing");

        tvFullName.setText(userDetails.getFullname());
        tvBio.setText(userDetails.getBio());
        tvWebsite.setText(userDetails.getWebsite());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_details, menu);
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

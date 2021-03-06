package prafulmantale.praful.com.twitterapp.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.adapters.ProfileFragmentsPageAdapter;
import prafulmantale.praful.com.twitterapp.fragments.FollowersFragment;
import prafulmantale.praful.com.twitterapp.fragments.FollowingFragment;
import prafulmantale.praful.com.twitterapp.fragments.UserTimelineFragment;
import prafulmantale.praful.com.twitterapp.fragments.UsersListFragment;
import prafulmantale.praful.com.twitterapp.helpers.AppConstants;
import prafulmantale.praful.com.twitterapp.interfaces.NetworkOperationsListener;
import prafulmantale.praful.com.twitterapp.models.UserProfile;

public class UserProfileActivity extends FragmentActivity implements NetworkOperationsListener{

    private static final String TAG = UserProfileActivity.class.getName();
    private UserProfile userProfile;
    private Drawable selectedDrawable;
    private Drawable tabUnselectedDrawable;
    private UserTimelineFragment userTimelineFragment;
    private UsersListFragment followingFragment;
    private UsersListFragment followersFragment;
    private ViewPager viewPager;

    private ProfileFragmentsPageAdapter fragmentsPageAdapter;
    private int selectedPageIndex = 0;

    private Button btnTweets;
    private Button btnFollowers;
    private Button btnFollowing;

    private ImageView ivComposeTweet;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userProfile = getIntent().getParcelableExtra(AppConstants.KEY_USER_PROFILE);

        initialize();

        setupListeners();

    }

    private void initialize(){

        initializeActionBar();

        final LinearLayout layout = (LinearLayout)findViewById(R.id.llUserProfile);
        ImageView ivProfileImage = (ImageView)findViewById(R.id.ivProfileImage);
        TextView tvName = (TextView)findViewById(R.id.tvProfileUserName);
        TextView tvSceenName = (TextView)findViewById(R.id.tvProfileScreenName);
        TextView tvDescription = (TextView)findViewById(R.id.tvProfileDescription);
        TextView tvFollowsYou = (TextView)findViewById(R.id.tvProfileFollowsYou);
        TextView tvLocation = (TextView)findViewById(R.id.tvProfileLocation);
        btnTweets = (Button)findViewById(R.id.btnTweetsHeader);
        btnFollowers = (Button)findViewById(R.id.btnFollowersHeader);
        btnFollowing = (Button)findViewById(R.id.btnFollowingHeader);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        if(userProfile.getUserId() == MainActivity.loggedInUser.getUserId()){
            tvFollowsYou.setVisibility(View.GONE);
        }

        if(userProfile.getProfileBannerUrl() != null && !userProfile.getProfileBannerUrl().isEmpty()) {
            ImageLoader.getInstance().loadImage(userProfile.getProfileBannerUrl(), new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String s, View view) {
                }

                @Override
                public void onLoadingFailed(String s, View view, FailReason failReason) {
                    Log.d(TAG, "onLoadingFailed: " + failReason.getCause());
                }

                @Override
                public void onLoadingComplete(String s, View view, Bitmap bitmap) {

                    BitmapDrawable drawable = new BitmapDrawable(bitmap);
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                        layout.setBackground(drawable);
                    } else {
                        layout.setBackgroundDrawable(drawable);
                    }
                }

                @Override
                public void onLoadingCancelled(String s, View view) {
                    Log.d(TAG, "onLoadingCancelled");
                }
            });
        }

        if(userProfile.getProfileImageUrl() != null && !userProfile.getProfileImageUrl().isEmpty()) {
            ImageLoader.getInstance().displayImage(userProfile.getProfileImageUrl(), ivProfileImage);
        }

        tvName.setText(userProfile.getName());
        tvSceenName.setText(userProfile.getDisplayScreenName());
        tvDescription.setText(userProfile.getDescription());
        tvLocation.setText(userProfile.getLocation());

        btnTweets.setText(Html.fromHtml(userProfile.getHTMLDisplayStatusCount()));
        btnFollowers.setText(Html.fromHtml(userProfile.getHTMLDisplayFollowersCount()));
        btnFollowing.setText(Html.fromHtml(userProfile.getHTMLDisplayFriendsCount()));


        userTimelineFragment = UserTimelineFragment.newInstance(userProfile.getUserIdStr());
        followersFragment = FollowersFragment.newInstance(userProfile.getUserIdStr());
        followingFragment = FollowingFragment.newInstance(userProfile.getUserIdStr());

        final List<Fragment> list = new ArrayList<Fragment>(3);
        list.add(userTimelineFragment);
        list.add(followingFragment);
        list.add(followersFragment);

        fragmentsPageAdapter = new ProfileFragmentsPageAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(fragmentsPageAdapter);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position < 0 || position > 2
                        || selectedPageIndex == position){
                    return;
                }

                setSelectedButton(getTabButton(position));
                unSelectButton(getTabButton(selectedPageIndex));
                selectedPageIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        selectedDrawable = getResources().getDrawable(R.drawable.tab_button_style_selected);
        tabUnselectedDrawable = getResources().getDrawable(R.drawable.tab_button_style_unselected);
        setSelectedButton(btnTweets);
        unSelectButton(btnFollowers);
        unSelectButton(btnFollowing);
    }

    private void initializeActionBar() {

        final ActionBar actionBar = getActionBar();

        View view = getLayoutInflater().inflate(R.layout.action_bar_home_title, null);
        ivComposeTweet = (ImageView) view.findViewById(R.id.ivCreateTweet_actionbar);

        ivBack = (ImageView)view.findViewById(R.id.ivBackbutton);
        ivBack.setVisibility(View.VISIBLE);
        ImageView ivBird = (ImageView)view.findViewById(R.id.ivFindPeopleIcon_home);
        ivBird.setVisibility(View.GONE);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);


        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setCustomView(view, params);
        actionBar.setDisplayShowHomeEnabled(false);
    }

    private void setupListeners() {
        ivComposeTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, CreateTweetActivity.class);
                intent.putExtra(AppConstants.KEY_USER_HANDLE, userProfile.getScreenName());
                startActivityForResult(intent, AppConstants.RequestCodes.COMPOSE_FROM_HOME);
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onTabChanged(View view){

        int currentItem = selectedPageIndex;

        if(view.getId() == R.id.btnTweetsHeader){
            currentItem = 0;
        }

        if(view.getId() == R.id.btnFollowingHeader){
            currentItem = 1;
        }

        if(view.getId() == R.id.btnFollowersHeader){
            currentItem = 2;
        }

        viewPager.setCurrentItem(currentItem);

    }


    private Button getTabButton(int position){

        if(position == 0){
            return btnTweets;
        }

        if(position == 1){
            return btnFollowing;
        }

        if(position == 2) {
            return btnFollowers;
        }
        Log.d(TAG, "Invalid button position received: " + position + "");

        return null;
    }

    private void setSelectedButton(Button newSelect){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            newSelect.setBackground(selectedDrawable);
        }
        else{
            newSelect.setBackgroundDrawable(selectedDrawable);
        }
    }

    private void unSelectButton(Button unSelect){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            unSelect.setBackground(tabUnselectedDrawable);
        }
        else{
            unSelect.setBackgroundDrawable(tabUnselectedDrawable);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile, menu);
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

    }

    @Override
    public void OnNetworkOperationFinished() {

    }
}

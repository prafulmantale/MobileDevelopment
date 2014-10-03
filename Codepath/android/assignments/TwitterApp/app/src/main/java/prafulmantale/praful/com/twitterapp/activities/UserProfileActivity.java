package prafulmantale.praful.com.twitterapp.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.application.RestClientApp;
import prafulmantale.praful.com.twitterapp.enums.APIRequest;
import prafulmantale.praful.com.twitterapp.handlers.TimelineResponseHandler;
import prafulmantale.praful.com.twitterapp.models.UserProfile;
import prafulmantale.praful.com.twitterapp.query.QueryParameters;

public class UserProfileActivity extends Activity {

    private static final String TAG = UserProfileActivity.class.getName();
    private UserProfile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userProfile = (UserProfile)getIntent().getSerializableExtra("UID");

        populateTestData();

        initialize();

    }

    private void populateTestData(){
//        userProfile = new UserProfile();
//        userProfile.setProfileBannerUrl("https://pbs.twimg.com/profile_banners/135511832/1403063877");
//        userProfile.setProfileImageUrl("http://pbs.twimg.com/profile_images/512834721970024449/Vq_gAqV6_normal.jpeg");
//        userProfile.setName("Vikas");
//        userProfile.setScreenName("vikaschhatwal");
//        userProfile.setDescription("It doesn't matter. Who is without a flaw?");
//        userProfile.setLocation("San Fransisco, California");
//        userProfile.setFollowsYou(false);
    }


    private void initialize(){

        final LinearLayout layout = (LinearLayout)findViewById(R.id.llUserProfile);
        ImageView ivProfileImage = (ImageView)findViewById(R.id.ivProfileImage);
        TextView tvName = (TextView)findViewById(R.id.tvProfileUserName);
        TextView tvSceenName = (TextView)findViewById(R.id.tvProfileScreenName);
        TextView tvDescription = (TextView)findViewById(R.id.tvProfileDescription);
        TextView tvFollowsYou = (TextView)findViewById(R.id.tvProfileFollowsYou);
        TextView tvLocation = (TextView)findViewById(R.id.tvProfileLocation);

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
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    layout.setBackground(drawable);
                }
                else {
                    layout.setBackgroundDrawable(drawable);
                }
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
                Log.d(TAG, "onLoadingCancelled");
            }


        });

        ImageLoader.getInstance().displayImage(userProfile.getProfileImageUrl(), ivProfileImage);

        tvName.setText(userProfile.getName());
        tvSceenName.setText(userProfile.getDisplayScreenName());
        if(!userProfile.isFollowsYou()){
            tvFollowsYou.setVisibility(View.GONE);
        }
        tvDescription.setText(userProfile.getDescription());
        tvLocation.setText(userProfile.getLocation());
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
}

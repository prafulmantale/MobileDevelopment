package prafulmantale.praful.com.twitterapp.activities;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.codepath.oauth.OAuthBaseClient;
import com.codepath.oauth.OAuthLoginActivity;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.enums.AnimationType;
import prafulmantale.praful.com.twitterapp.listeners.AppAnimationListener;
import prafulmantale.praful.com.twitterapp.networking.TwitterClient;


public class Welcome extends OAuthLoginActivity<TwitterClient> {

    //Animations
    private Animation slideOutAnimation;
    private Animation fadeOutAnimation;
    private Animation fadeInAnimation;
    private Animation loginFadeInAnimation;

    //Animation Listners
    private AppAnimationListener loginSlideOutListener;
    private AppAnimationListener loginFadeInListener;
    private AppAnimationListener footerFadeInListener;
    private AppAnimationListener footerFadeOutLisetner;


    //Views
    private LinearLayout footerRow;
    private RelativeLayout loginLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getActionBar().hide();

        initialize();
    }

    private void initialize() {

        initializeViews();
        initializeAnimations();
    }

    private void initializeViews(){
        footerRow = (LinearLayout)findViewById(R.id.footerRow_welcome);
        loginLayout = (RelativeLayout)findViewById(R.id.loginLayout);
    }

    private void initializeAnimations(){
        slideOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out);
        fadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        loginFadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        loginFadeInListener = new AppAnimationListener(loginLayout, AnimationType.FadeIn);
        loginSlideOutListener = new AppAnimationListener(loginLayout, AnimationType.SlideOut);
        footerFadeInListener = new AppAnimationListener(footerRow, AnimationType.FadeIn);
        footerFadeOutLisetner = new AppAnimationListener(footerRow, AnimationType.FadeOut);

        setupAnimationListeners();
    }

    private void setupAnimationListeners(){

        fadeOutAnimation.setAnimationListener(footerFadeOutLisetner);
        fadeInAnimation.setAnimationListener(footerFadeInListener);
        slideOutAnimation.setAnimationListener(loginSlideOutListener);
        loginFadeInAnimation.setAnimationListener(loginFadeInListener);

    }

    public void onSignButtonClick(View view){

        if(view.getId() == R.id.btnSignIn) {

            footerRow.startAnimation(fadeOutAnimation);
            loginLayout.startAnimation(loginFadeInAnimation);
        }

        if(view.getId() == R.id.btnCancel){
            loginLayout.startAnimation(slideOutAnimation);
            footerRow.startAnimation(fadeInAnimation);
        }

        if(view.getId() == R.id.btnLogin){

        }

    }

    private void showHomeActivity() {

        //Intent intent = new Intent(this, HomeActivity.class);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
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

    public Welcome() {
        super();
    }

    @Override
    public void onLoginSuccess() {
        showHomeActivity();
    }

    @Override
    public void onLoginFailure(Exception e) {
        e.printStackTrace();
    }


    public void onLoginRequested(View view){
        getClient().connect();
    }

}

package prafulmantale.praful.com.twitterapp.listeners;

import android.view.View;
import android.view.animation.Animation;

import java.util.LinkedList;
import java.util.Queue;

import prafulmantale.praful.com.twitterapp.enums.AnimationType;

/**
 * Created by prafulmantale on 9/25/14.
 */
public class AppAnimationListener implements Animation.AnimationListener {

    private View view;
    private AnimationType animationType;


    public AppAnimationListener(View view, AnimationType animationType){

        this.view = view;
        this.animationType = animationType;
    }


    @Override
    public void onAnimationStart(Animation animation) {

        if(animationType == AnimationType.FadeIn || animationType == AnimationType.SlideIn){
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {

        if(animationType == AnimationType.FadeOut || animationType == AnimationType.SlideOut){
            view.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}

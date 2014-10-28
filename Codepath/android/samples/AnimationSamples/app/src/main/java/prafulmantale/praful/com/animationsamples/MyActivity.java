package prafulmantale.praful.com.animationsamples;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationSet;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    public void onAnimate(View view){

        //Property Animations

//        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0f);
//
//        animator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//
//        animator.setDuration(3000);
//        animator.setRepeatCount(3);
//        animator.setRepeatMode(ValueAnimator.REVERSE);
//
//
//
//        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "x", view.getX(), 0f);
//        animator2.setDuration(2000);
//
//
//        AnimatorSet set = new AnimatorSet();
//        //set.playTogether(animator, animator2);
//        set.play(animator).with(animator2);
//        set.start();

        view.animate().alpha(0).x(0);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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

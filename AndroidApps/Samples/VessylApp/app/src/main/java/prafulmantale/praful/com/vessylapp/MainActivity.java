package prafulmantale.praful.com.vessylapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;


public class MainActivity extends ActionBarActivity {

    private Animation animation;
    private DecelerateInterpolator interpolator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llView = findViewById(R.id.llInfo);


    }

    private void initialize(){
        animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(3000);

//        View view = findViewById(R.id.tvRecognized);
//
//        view.clearAnimation();
//        view.startAnimation(animation);

//        TextView tvRec  = (TextView)findViewById(R.id.tvRecognized);
//        TextView tvBrand  = (TextView)findViewById(R.id.tvDrinkbrand);
//        TextView tvType  = (TextView)findViewById(R.id.tvDrinkType);
//        ObjectAnimator animRec = ObjectAnimator.ofFloat(tvRec, View.ALPHA, 0f, 1f);
//        animRec.setDuration(5000);
//
//        ObjectAnimator animBrand = ObjectAnimator.ofFloat(tvBrand, View.ALPHA, 0f, 1f);
//        animBrand.setDuration(5000);
//
//        ObjectAnimator animType = ObjectAnimator.ofFloat(tvType, View.ALPHA, 0f, 1f);
//        animType.setDuration(5000);
//
//        AnimatorSet animatorSet = new AnimatorSet();
//
//        animatorSet.playTogether(animRec, animBrand, animType);
//        animatorSet.start();

        interpolator = new DecelerateInterpolator();

        llView.setVisibility(View.VISIBLE);

        llView.setTranslationX(0.0F);
        llView.setTranslationY(0.5f);
        llView.setRotationX(45.0F);
        llView.setScaleX(0.7F);
        llView.setScaleY(0.85F);

        ViewPropertyAnimator localViewPropertyAnimator =
                llView.animate().rotationX(0.0F).rotationY(0.0F).translationX(0).translationY(0).setDuration(2000).scaleX(
                        1.0F).scaleY(1.0F).setInterpolator(interpolator);

        localViewPropertyAnimator.setStartDelay(0).start();
    }

    View llView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        final Handler handler = new Handler();
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                initialize();
//            }
//        }, 5000);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            if(llView == null){
                llView = findViewById(R.id.llInfo);
            }

            llView.setVisibility(View.INVISIBLE);
            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    initialize();
                }
            }, 1000);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

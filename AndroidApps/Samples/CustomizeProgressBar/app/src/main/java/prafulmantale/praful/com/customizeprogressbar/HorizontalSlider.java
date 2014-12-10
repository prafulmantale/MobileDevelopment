package prafulmantale.praful.com.customizeprogressbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by prafulmantale on 11/6/14.
 */
public class HorizontalSlider extends ProgressBar {

    private OnProgressChangeListener listener;
    private static int padding = 2;


    public interface OnProgressChangeListener {
        void onProgressChanged(View v, int progress);
    }



    public HorizontalSlider(Context context) {
        super(context);

    }



    public HorizontalSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, android.R.attr.progressBarStyleHorizontal);
    }

    @TargetApi(Build.VERSION_CODES.L)
    public HorizontalSlider(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, android.R.attr.progressBarStyleHorizontal);
    }

    public void setOnProgressChangeListener(OnProgressChangeListener l) {
        listener = l;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN
                || action == MotionEvent.ACTION_MOVE) {
            float x_mouse = event.getX() - padding;
            float width = getWidth() - 2*padding;
            int progress = Math.round((float) getMax() * (x_mouse / width));

            if (progress < 0)
                progress = 0;

            this.setProgress(progress);

            if (listener != null)
                listener.onProgressChanged(this, progress);

        }

        return true;
    }
}



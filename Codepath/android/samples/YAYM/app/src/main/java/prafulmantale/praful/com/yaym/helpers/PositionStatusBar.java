package prafulmantale.praful.com.yaym.helpers;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.SeekBar;

/**
 * Created by prafulmantale on 10/10/14.
 */
public class PositionStatusBar extends SeekBar {

    public PositionStatusBar(Context context) {
        super(context);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}

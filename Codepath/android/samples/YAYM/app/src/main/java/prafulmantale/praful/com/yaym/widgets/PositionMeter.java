package prafulmantale.praful.com.yaym.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by prafulmantale on 10/30/14.
 */
public class PositionMeter extends View {

    private Path path;
    private Paint paint;

    public PositionMeter(Context context) {
        super(context);
    }

    public PositionMeter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PositionMeter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

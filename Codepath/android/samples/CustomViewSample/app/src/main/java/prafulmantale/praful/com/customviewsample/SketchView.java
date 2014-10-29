package prafulmantale.praful.com.customviewsample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by prafulmantale on 10/28/14.
 */
public class SketchView extends View {

    private Paint paint;
    private Path path;

    public SketchView(Context context) {
        super(context);
        initialize();
    }

    public SketchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public SketchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize(){
        paint = new Paint();
        path = new Path();

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.SQUARE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawRect(30, 30, 150,150, paint);
//        paint.setColor(Color.GREEN);
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(200, 200, 50, paint);

        canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            //Path begin paint
            path.moveTo(event.getX(), event.getY());
            postInvalidate();
            return true;
        }
        else if(event.getAction() == MotionEvent.ACTION_MOVE){
            //From previous location to current location
            path.lineTo(event.getX(), event.getY());
            postInvalidate();
            return true;
        }
        else{
            return false;
        }
    }
}

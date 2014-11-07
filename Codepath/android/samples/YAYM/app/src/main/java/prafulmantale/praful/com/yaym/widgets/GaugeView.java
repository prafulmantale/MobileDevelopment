package prafulmantale.praful.com.yaym.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.enums.PositionType;

/**
 * Created by prafulmantale on 11/2/14.
 */
public class GaugeView extends View {

    private PositionType positionType = PositionType.NONE;
    private Bitmap bgImageBlue = null;
    private Bitmap bgImageRed = null;

    float w;
    float h;

    private Paint arcPaint;
    private RectF mOval;
    private int startAngle = 180;

    private Paint needlePaint;

    private Matrix matrix;
    private int framePerSeconds = 100;
    private long animationDuration = 10000;
    private long startTime;

    private float needlePosition = 60;

    private int max;
    private int min;
    private int current;


    public GaugeView(Context context) {
        super(context);
        init();
    }

    public GaugeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GaugeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    private void init(){

        matrix = new Matrix();
        this.startTime = System.currentTimeMillis();
        this.postInvalidate();

        bgImageBlue = BitmapFactory.decodeResource(getResources(), R.drawable.blue_guage);
        bgImageRed = BitmapFactory.decodeResource(getResources(), R.drawable.red_guage);

        w = bgImageBlue.getWidth();
        h = bgImageBlue.getHeight();

        arcPaint = new Paint();
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(2.5f * getResources().getDisplayMetrics().density);
        arcPaint.setColor(getResources().getColor(R.color.arcsecondarycolor));
        arcPaint.setAntiAlias(true);
        arcPaint.setAlpha(30);

        mOval = new RectF(w/2 -w/3.4f, h/2.5f, w/2 + w/3.4f, h + h/2);


        needlePaint = new Paint();
        needlePaint.setColor(getResources().getColor(R.color.needle_color));
        needlePaint.setAntiAlias(true);
        needlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        needlePaint.setStrokeWidth(1f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        if(current >= 0) {
            canvas.drawBitmap(bgImageBlue, 0, 0, null);
            w = bgImageBlue.getWidth();
            h = bgImageBlue.getHeight();
        }
        else{
            canvas.drawBitmap(bgImageRed, 0, 0, null);
            w = bgImageRed.getWidth();
            h = bgImageRed.getHeight();
        }
        canvas.drawArc(mOval, startAngle, 180, false, arcPaint);

        drawNeedle(canvas);
    }

    private void drawNeedle(Canvas canvas) {

        float radius = mOval.width() * 0.35f + 10;
        float centerX = w/2;
        float centerY = h;

        float angle = getAngle();
        canvas.drawLine(
                centerX,
                centerY,
                (float) (centerX + Math.cos((180 - angle) / 180 * Math.PI) * (radius)),
                (float) (centerY - Math.sin(angle / 180 * Math.PI) * (radius)),
                needlePaint
        );
        canvas.drawCircle(w/2-1, h, 3, needlePaint);

    }

    private float getAngle(){
        float angle = 60;

        int total = min + max;
        if(total == 0 || current == 0){
            return angle;
        }

        angle = angle + (float) (current* 180/ total );

        return angle;
    }
    public void setMax(int max){
        this.max = max;
    }

    public void setMin(int min){
        if(min < 0){
            min = -1 * min;
        }
        this.min = min;
    }

    public void setCurrent(int current){
        this.current = current;
        invalidate();
    }

    private void drawText(Canvas canvas){

    }
}

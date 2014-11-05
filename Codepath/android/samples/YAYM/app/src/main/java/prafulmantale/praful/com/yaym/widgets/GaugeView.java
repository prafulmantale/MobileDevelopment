package prafulmantale.praful.com.yaym.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
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
    private Bitmap bgImage = null;

    float w;
    float h;

    private Paint arcPaint;
    private RectF mOval;
    private int startAngle = 180;

    private Paint needlePaint;
    private Path needlePath;


    private Matrix matrix;
    private int framePerSeconds = 100;
    private long animationDuration = 10000;
    private long startTime;

    private float needlePosition = 60;



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

        if(positionType == PositionType.SHORT) {
            bgImage = BitmapFactory.decodeResource(getResources(), R.drawable.red_guage);
        }
        else {
            bgImage = BitmapFactory.decodeResource(getResources(), R.drawable.blue_guage);
        }

        w = bgImage.getWidth();
        h = bgImage.getHeight();

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

        needlePath = new Path();
        needlePath.moveTo(w/2, h);
        needlePath.lineTo(w/3,h/1.7f);
        needlePath.lineTo(w/3,h/1.8f);
        needlePath.lineTo(w/2 + 1, h);
        needlePath.addCircle(w/2+1, h + 1, 3, Path.Direction.CW);
        needlePath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bgImage, 0, 0, null);
        canvas.drawArc(mOval, startAngle, 180, false, arcPaint);

       canvas.drawPath(needlePath, needlePaint);
    }
}

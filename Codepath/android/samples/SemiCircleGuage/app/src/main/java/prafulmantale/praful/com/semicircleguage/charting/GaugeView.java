package prafulmantale.praful.com.semicircleguage.charting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

import prafulmantale.praful.com.semicircleguage.R;

/**
 * Created by prafulmantale on 11/2/14.
 */
public class GaugeView extends View {
    private Bitmap bgImage = null;
    private Bitmap bgImage2 = null;
    private Bitmap posNone = null;
    private Bitmap posShort = null;
    private Bitmap posLong = null;


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

        bgImage = BitmapFactory.decodeResource(getResources(), R.drawable.red_guage);
        bgImage2 = BitmapFactory.decodeResource(getResources(), R.drawable.blue_guage);

        posNone = BitmapFactory.decodeResource(getResources(), R.drawable.position_none172);
        posShort = BitmapFactory.decodeResource(getResources(), R.drawable.position_short172);
        posLong = BitmapFactory.decodeResource(getResources(), R.drawable.position_long172);

        w = bgImage.getWidth();
        h = bgImage.getHeight();

        arcPaint = new Paint();
        arcPaint.setStyle(Paint.Style.FILL);
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

//        canvas.drawBitmap(bgImage, 0, 0, null);
//        canvas.drawBitmap(bgImage2, w, w, null);
//        canvas.drawArc(mOval, startAngle, 180, false, arcPaint);
//
//       canvas.drawPath(needlePath, needlePaint);


        //
        //canvas.drawBitmap(posLong, posLong.getWidth(), 0, null);

        canvas.drawBitmap(posNone, 0, 0, null);
//        //canvas.drawBitmap(posShort, 0, 0, null);
        canvas.drawBitmap(posNone, posNone.getWidth(), 0, null);
//
//        Rect source = new Rect(0, 0, posNone.getWidth(), posNone.getHeight());
//        RectF bitmapRect = new RectF(0, 0,  posNone.getWidth() - 20,posNone.getHeight());
//        canvas.drawBitmap(cropBitmap1(), source, bitmapRect, null);
//        //canvas.drawRect(bitmapRect, arcPaint);

        canvas.clipRect(0, 0, canvas.getWidth(), canvas.getHeight(), Region.Op.REPLACE);
        arcPaint.setColor(Color.WHITE);
        arcPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), arcPaint);

        canvas.clipRect(0, 0, 0 + 100, 0, Region.Op.REPLACE);
        canvas.drawBitmap(posShort, 0 , 0, arcPaint);
    }

    private Bitmap cropBitmap1()
    {
        Bitmap bmp2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.position_short172);
        Bitmap bmOverlay = Bitmap.createBitmap(320, 480, Bitmap.Config.ARGB_8888);

        Paint p = new Paint();
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Canvas c = new Canvas(bmOverlay);
        c.drawBitmap(bmp2, 0, 0, null);
        c.drawRect(30, 30, 100, 100, p);

        return bmOverlay;
    }
}

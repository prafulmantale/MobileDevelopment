package prafulmantale.praful.com.semicircleguage.charting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import prafulmantale.praful.com.semicircleguage.R;

/**
 * Created by prafulmantale on 11/2/14.
 */
public class GaugeView extends View {
    private Bitmap bgImage = null;
    Paint paint = new Paint();
    float w;
    float h;


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
        bgImage = BitmapFactory.decodeResource(getResources(), R.drawable.blue_guage);
        w = bgImage.getWidth();
        h = bgImage.getHeight();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bgImage, 0, 0, null);

//        paint.setColor(Color.BLACK);
//        canvas.drawLine(bgImage.getWidth()/2, bgImage.getHeight(), 60, 150, paint);
//
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawArc(new RectF(10,10, 30 + bgImage.getWidth(), bgImage.getHeight()), 180, 30, true, paint );

//        PointF mPoint1 = new PointF(w/2, h);
//        PointF mPoint2 = new PointF(w/3, h/1.7f);
//        Path myPath1 = new Path();
//        paint.setAntiAlias(true);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(2);
//        paint.setColor(Color.BLACK);
//
//        myPath1 = drawCurve(canvas, paint, mPoint1, mPoint2);
//        canvas.drawPath(myPath1, paint);
    }

    private Path drawCurve(Canvas canvas, Paint paint, PointF mPointa, PointF mPointb) {

        Path myPath = new Path();
        myPath.moveTo(w/2, h);
        myPath.quadTo(mPointa.x, mPointa.y, mPointb.x, mPointb.y);
        return myPath;
    }

}

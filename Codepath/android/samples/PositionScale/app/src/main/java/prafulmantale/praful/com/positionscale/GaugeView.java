package prafulmantale.praful.com.positionscale;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by prafulmantale on 11/18/14.
 */
public class GaugeView extends View {

    private Paint outerCirclePaintBlank;
    private Paint outerCirclePaintProfit;
    private Paint outerCirclePaintLoss;
    private Paint innerCirclePaint;
    private Paint meterLinePaint;

    private RectF outerRect;
    private RectF innerRect;
    private RectF meterRect;

    private Paint needlePaint;

    private int width;
    private int height;

    private int center;

    private int meterWidth = 16;
    private int innerWidth = 24;

    private Paint textPaint;



    public GaugeView(Context context) {
        super(context);

    }

    public GaugeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GaugeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float newangle = (float)(angle * Math.PI / 180);
        float startX = center;
        float startY = 0.79f* height - 1;
        float endX   = startX + (float)(40 * Math.sin(newangle));
        float endY   = startY + (float)(40 * Math.cos(newangle));


        canvas.drawArc(outerRect, 180, 60, true, outerCirclePaintBlank);
        canvas.drawArc(outerRect, 240, 120, true, outerCirclePaintProfit);
        canvas.drawArc(innerRect, 180, 180, true, innerCirclePaint);
        canvas.drawLine(width/4, startY, width/4 + (float)getWidth() - (width/2), startY, innerCirclePaint);

        canvas.drawArc(meterRect, 180, 180, false, meterLinePaint);
        canvas.drawCircle(startX, startY, 4, needlePaint);
        canvas.drawLine(startX, startY, endX, endY, needlePaint);


        textPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("(170)", width/12, (float)getHeight() - height/6, textPaint);
        //textPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("340", outerRect.right + 5, (float)getHeight() - height/6, textPaint);

        canvas.drawText("34.8", startX - 16, startY + 14, textPaint);


    }

    private void init(){

        width = getWidth();
        height = getHeight();


        outerCirclePaintBlank = new Paint(Paint.ANTI_ALIAS_FLAG);
        outerCirclePaintBlank.setColor(Color.GREEN);
        outerCirclePaintBlank.setStyle(Paint.Style.FILL);

        outerCirclePaintProfit = new Paint(Paint.ANTI_ALIAS_FLAG);
        outerCirclePaintProfit.setColor(Color.BLUE);
        outerCirclePaintProfit.setStyle(Paint.Style.FILL);

        outerCirclePaintLoss = new Paint(Paint.ANTI_ALIAS_FLAG);
        outerCirclePaintLoss.setColor(Color.RED);
        outerCirclePaintLoss.setStyle(Paint.Style.FILL);

        innerCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        innerCirclePaint.setColor(Color.WHITE);
        innerCirclePaint.setStyle(Paint.Style.FILL);

        meterLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        meterLinePaint.setColor(Color.MAGENTA);
        meterLinePaint.setStyle(Paint.Style.STROKE);

        float outerRectLeft = width/4;
        float outerRectTop = height/16;
        float outerRectRight = outerRectLeft + (float)getWidth() - (outerRectLeft * 2);
        float outerRectHeight = (float)getHeight()*1.5f;
        center = width/2;

        outerRect = new RectF(outerRectLeft, outerRectTop, outerRectRight, outerRectHeight);

        System.out.println(outerRect);

        innerRect = new RectF(outerRectLeft + meterWidth, outerRectTop + meterWidth, outerRectRight - meterWidth, outerRectHeight - meterWidth);

        meterRect = new RectF(outerRectLeft +innerWidth, outerRectTop + innerWidth, outerRectRight - innerWidth, outerRectHeight - innerWidth);

        needlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        needlePaint.setColor(Color.BLACK);
        needlePaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measuredWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int measuredHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        System.out.println("####Height:" + getHeight() + "|" + getSuggestedMinimumHeight() + "|" + getMeasuredHeight() + " | " + measuredHeight);

        setMeasuredDimension(measuredWidth, measuredHeight);

        init();
    }

    private float angle = 210;
    public void setAngle(float newAngle){
        this.angle = newAngle;
        invalidate();
    }
}

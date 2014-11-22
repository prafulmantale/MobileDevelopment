package prafulmantale.praful.com.yaym.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import prafulmantale.praful.com.yaym.R;

/**
 * Created by prafulmantale on 11/18/14.
 */
public class YieldPercentageView extends View {

    private static final String TAG = YieldPercentageView.class.getSimpleName();

    private Paint arcPaint;
    private Paint progressPaint;
    private Paint textPaint;

    private RectF arcRect = new RectF();;
    private int arcRadius;

    private float progressSweep;
    private int startAngle = 135;
    private int sweepAngle = 270;
    private int max = 100;

    private int progressWidth = 8;

    private int height;
    private int width;

    private String percentageDisplay = "0%";

    public YieldPercentageView(Context context) {
        super(context);
        init();
    }

    public YieldPercentageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public YieldPercentageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        if(isInEditMode()){
            return;
        }

        final Resources resources = getResources();
        float density = resources.getDisplayMetrics().density;

        int arcColor = getResources().getColor(R.color.risk_capacity_arc_blank);
        int progressColor = Color.BLUE;

        progressWidth = (int)(progressWidth * density);

        arcPaint = new Paint();
        arcPaint.setColor(arcColor);
        arcPaint.setAntiAlias(true);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(progressWidth);

        progressPaint = new Paint();
        progressPaint.setColor(progressColor);
        progressPaint.setAntiAlias(true);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(progressWidth);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(getResources().getColor(R.color.risk_capacity_title));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(12 * density);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(isInEditMode()){
            return;
        }
        // Draw the arcs
        canvas.drawArc(arcRect, startAngle, sweepAngle, false, arcPaint);
        canvas.drawArc(arcRect, startAngle, progressSweep, false,progressPaint);
        canvas.drawText(percentageDisplay, arcRect.centerX(), arcRect.centerY() + 4, textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int min = Math.min(height, width);
        final int maxPadding = Math.max(Math.max(getPaddingBottom(), getPaddingTop()), Math.max(getPaddingLeft(), getPaddingRight()));

        float top = 0;
        float left = 0;
        int arcDiameter = 0;

        arcDiameter = min - maxPadding - 12;

        arcRadius = (arcDiameter/2);
        top = (height / 2) - arcRadius;
        left = (width/2) - arcRadius;

        arcRect.set(left, top, left + arcDiameter, top + arcDiameter);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setYieldPercentage(double percentage, String percentageDisplay){
        if(percentage > 100){
            percentage = 100;
            percentageDisplay = "100";
        }
        if(percentage < 0){
            percentage = 0;
            percentageDisplay = "0";
        }

        this.percentageDisplay = percentageDisplay + "%";
        progressSweep = (float) (270 * percentage / max);

        invalidate();
    }
}

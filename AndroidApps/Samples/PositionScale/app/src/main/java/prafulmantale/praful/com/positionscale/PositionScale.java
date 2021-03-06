package prafulmantale.praful.com.positionscale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by prafulmantale on 11/18/14.
 */
public class PositionScale extends View {

    private Paint blankPaint;
    private Paint normalPaint;
    private Paint warningPaint;
    private Paint dangerPaint;
    private int width;

    private int barsCount = 0;
    private int blankCount = 34;
    private int normalCount = 0;
    private int warningCount = 0;
    private int dangerCount = 0;

    private Paint textPaint;
    private Paint centerCircleColor;

    private Bitmap pointerImage;

    private float topMargin = 30f;
    private float barWidth = 3f;
    private float barHeight = 16;
    private float centerBarHeight = 1.5f * barHeight;

    private Paint dashPaint;

    private float rightCenterBarStartPoint;
    private float leftCenterBarStartPoint;

    private Path dashPath;

    private int maxLongPosition;
    private String maxLongPosText;

    private int maxShortPosition;
    private String maxShortPosText;

    private double currentPosition;
    private String currentPosText;


    public PositionScale(Context context) {
        super(context);
    }

    public PositionScale(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PositionScale(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){

        width = getMeasuredWidth();
        blankPaint = new Paint();
        blankPaint.setColor(Color.GRAY);

        normalPaint = new Paint();
        normalPaint.setColor(Color.BLUE);

        warningPaint = new Paint();
        warningPaint.setColor(Color.MAGENTA);

        dangerPaint = new Paint();
        dangerPaint.setColor(Color.RED);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);

        centerCircleColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        centerCircleColor.setColor(Color.GRAY);
        centerCircleColor.setStyle(Paint.Style.FILL);

        pointerImage = BitmapFactory.decodeResource(getResources(), R.drawable.pointer);

        dashPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dashPaint.setColor(Color.GRAY);
        dashPaint.setStrokeWidth(1);
        dashPaint.setStyle(Paint.Style.STROKE);
        dashPaint.setPathEffect(new DashPathEffect(new float[]{2,2}, 0));

        dashPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float left = width/2;
        drawCenterBar(canvas, left);
        canvas.drawCircle(left + 1, 33 + topMargin, 5, centerCircleColor);
        drawShortBars(canvas);
        drawLongBars(canvas);
        drawDots(canvas,leftCenterBarStartPoint,  rightCenterBarStartPoint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int measuredWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int measuredHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(measuredWidth, measuredHeight);

        init();
    }

    private void drawDots(Canvas canvas, float left, float right){


        dashPath.moveTo(left, topMargin + barHeight + 4);
        dashPath.lineTo(right, topMargin + barHeight + 4);
        dashPath.close();
        canvas.drawPath(dashPath, dashPaint);
    }

    private void drawCenterBar(Canvas canvas, float left) {

        canvas.drawRect( left, topMargin, left + 0.75f, topMargin + centerBarHeight, blankPaint);
    }

    private void drawLongBars(Canvas canvas){

        float left = width /2 + 2f;
        float right = 3;
        for(int i = 0; i < normalCount; i ++){

            canvas.drawRect( left, topMargin, left + right, topMargin + barHeight, normalPaint);
            left += 5f;
        }

        for(int i = 0; i < warningCount; i ++){

            canvas.drawRect( left, topMargin, left + right, topMargin + barHeight, warningPaint);
            left += 5f;
        }

        for(int i = 0; i < dangerCount; i ++){

            canvas.drawRect( left, topMargin, left + right, topMargin + barHeight, dangerPaint);
            left += 5f;
        }

        for(int i = 0; i < blankCount; i ++){

            canvas.drawRect( left, topMargin, left + right, topMargin + barHeight, blankPaint);
            left += 5f;
        }

        drawCenterBar(canvas, width /2 + 2f + 17 * 5 -2f);
        drawCenterBar(canvas, left + 1);
        rightCenterBarStartPoint = left - 2;

        canvas.drawText("200", left - 10, 35 + topMargin, textPaint);
        canvas.drawBitmap(pointerImage, width/2 + barsCount*5 - pointerImage.getWidth()/2 - (barsCount == 0 ? 0 : 2), topMargin - pointerImage.getHeight() - 2, null);
        canvas.drawText("(223.1)", width/2 + barsCount*5 - pointerImage.getWidth()/2 - 2 - 10, topMargin - pointerImage.getHeight() - 5, textPaint);
    }

    private void drawShortBars(Canvas canvas){

        float right = width /2 - 1f;
        float left = 3;
        for(int i = 0; i < normalCount; i ++){

            canvas.drawRect( right - left, topMargin, right, topMargin + barHeight, normalPaint);
            right -= 5f;
        }

        for(int i = 0; i < warningCount; i ++){

            canvas.drawRect( right - left, topMargin, right, topMargin + barHeight, warningPaint);
            right -= 5f;
        }

        for(int i = 0; i < dangerCount; i ++){

            canvas.drawRect( right - left, topMargin,  right, topMargin + barHeight, dangerPaint);
            right -= 5f;
        }

        for(int i = 0; i < blankCount; i ++){

            canvas.drawRect( right - left, topMargin, right, topMargin + barHeight, blankPaint);
            right -= 5f;
        }

        drawCenterBar(canvas, width /2 - 1f - 17*5 - left + 3f);
        drawCenterBar(canvas, right - left + 1);

        leftCenterBarStartPoint = right - left + 3;

        canvas.drawText("(200)", right - left -9, 35 + topMargin, textPaint);

    }

    public void setBars(int barsCount){

        if(barsCount < 0){
            barsCount = 0;
        }

        if(barsCount > 34){
            barsCount = 34;
        }

        if(this.barsCount != barsCount){
            this.barsCount = barsCount;

            if(barsCount == 0){
                blankCount = 34;
                normalCount = 0;
                warningCount = 0;
                dangerCount = 0;
            }
            else if(barsCount <= 17){
                blankCount = 34 - barsCount;
                normalCount = barsCount;
                warningCount = 0;
                dangerCount = 0;
            }
            else if(barsCount <=32){
                blankCount = 34 - barsCount;
                normalCount = 17;
                warningCount = 34 - blankCount - normalCount;
                dangerCount = 0;
            }
            else{
                blankCount = 34 - barsCount;
                normalCount = 17;
                warningCount = 15;
                dangerCount = 34 - blankCount - normalCount - warningCount;
            }

            invalidate();
        }
    }

    public void setMaxLongPosition(int maxLong, String displayMaxLong){

        if(this.maxLongPosition != maxLong) {
            this.maxLongPosition = maxLong;
            this.maxLongPosText = displayMaxLong;
            invalidate();
        }
    }

    public void setMaxShortPosition(int maxShort, String displayMaxShort){
        if(this.maxShortPosition != maxShort) {
            this.maxShortPosition = maxShort;
            this.maxShortPosText = displayMaxShort;
            invalidate();
        }
    }

    public void setCurrentPosition(double currentPosition, String displayCurrentPosition){

        if(this.currentPosition != currentPosition) {
            this.currentPosition = currentPosition;
            this.currentPosText = displayCurrentPosition;
            invalidate();
        }
    }
}

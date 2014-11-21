package prafulmantale.praful.com.yaym.widgets;

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

import prafulmantale.praful.com.yaym.R;

/**
 * Created by prafulmantale on 11/19/14.
 */
public class PositionStatusView extends View {

    private static final String TAG = PositionStatusView.class.getSimpleName();

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
    private Paint textPaintMaxShort;
    private Paint textPaintMaxLong;
    private Paint centerCircleColor;
    private Paint centerBarPaint;

    private Bitmap pointerImage;

    private float topMargin = 30f;
    private float barWidth = getResources().getDimensionPixelSize(R.dimen.position_view_bar_width);
    private float barHeight = getResources().getDimensionPixelSize(R.dimen.position_view_bar_height);
    private float centerBarHeight = getResources().getDimensionPixelSize(R.dimen.position_view_center_bar_height);
    private float barMargin = getResources().getDimensionPixelSize(R.dimen.position_view_bar_margin);

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

    private boolean isPositionLong = true;

    public PositionStatusView(Context context) {
        super(context);
    }

    public PositionStatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PositionStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){

        width = getMeasuredWidth();
        blankPaint = new Paint();
        blankPaint.setColor(getResources().getColor(R.color.pos_blank));

        normalPaint = new Paint();
        normalPaint.setColor(getResources().getColor(R.color.pos_normal));

        warningPaint = new Paint();
        warningPaint.setColor(getResources().getColor(R.color.pos_warning));

        dangerPaint = new Paint();
        dangerPaint.setColor(getResources().getColor(R.color.pos_danger));

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.position_view_current_value_size));

        textPaintMaxShort = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaintMaxShort.setColor(Color.BLACK);
        textPaintMaxShort.setTextAlign(Paint.Align.LEFT);
        textPaintMaxShort.setTextSize(getResources().getDimensionPixelSize(R.dimen.position_view_current_value_size));

        textPaintMaxLong = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaintMaxLong.setColor(Color.BLACK);
        textPaintMaxLong.setTextAlign(Paint.Align.RIGHT);
        textPaintMaxLong.setTextSize(getResources().getDimensionPixelSize(R.dimen.position_view_current_value_size));

        centerCircleColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        centerCircleColor.setColor(getResources().getColor(R.color.pos_blank));
        centerCircleColor.setStyle(Paint.Style.FILL);

        centerBarPaint = new Paint();
        centerBarPaint.setColor(getResources().getColor(R.color.needle_color));

        pointerImage = BitmapFactory.decodeResource(getResources(), R.drawable.pointer);

        dashPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dashPaint.setColor(getResources().getColor(R.color.pos_blank));
        dashPaint.setStrokeWidth(1);
        dashPaint.setStyle(Paint.Style.STROKE);
        dashPaint.setPathEffect(new DashPathEffect(new float[]{5,5}, 0));

        dashPath = new Path();

        maxLongPosText = "0.0";
        maxShortPosText = "0.0";
        currentPosText = "0.0";
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

        canvas.drawRect( left, topMargin, left + 1.5f, topMargin + centerBarHeight, centerBarPaint);
    }

    private void drawLongBars(Canvas canvas){

        float left = width /2 + 2f;
        float right = barWidth;
        float xJump = barWidth + barMargin;

        if(!isPositionLong){
            for (int i = 0; i < 34; i++) {

                canvas.drawRect(left, topMargin, left + right, topMargin + barHeight, blankPaint);
                left += xJump;
            }
        }
        else {
            for (int i = 0; i < normalCount; i++) {

                canvas.drawRect(left, topMargin, left + right, topMargin + barHeight, normalPaint);
                left += xJump;
            }

            for (int i = 0; i < warningCount; i++) {

                canvas.drawRect(left, topMargin, left + right, topMargin + barHeight, warningPaint);
                left += xJump;
            }

            for (int i = 0; i < dangerCount; i++) {

                canvas.drawRect(left, topMargin, left + right, topMargin + barHeight, dangerPaint);
                left += xJump;
            }

            for (int i = 0; i < blankCount; i++) {

                canvas.drawRect(left, topMargin, left + right, topMargin + barHeight, blankPaint);
                left += xJump;
            }
        }

        drawCenterBar(canvas, width /2 + 2f + 17 * xJump -2f);
        drawCenterBar(canvas, left + 1);
        rightCenterBarStartPoint = left - 2;

        canvas.drawText(maxLongPosText, left, centerBarHeight + 1.35f*topMargin, textPaintMaxLong);

        if(isPositionLong) {
            canvas.drawBitmap(pointerImage, width / 2 + barsCount * xJump - pointerImage.getWidth() / 2 - (barsCount == 0 ? 0 : 2), topMargin - pointerImage.getHeight() - 2, null);
            canvas.drawText(currentPosText, pointerImage.getWidth()/2 + width / 2 + barsCount * xJump - pointerImage.getWidth() / 2 - (barsCount == 0 ? 0 : 2), topMargin - pointerImage.getHeight() - 5, textPaint);
        }
    }

    private void drawShortBars(Canvas canvas){

        float right = width /2 - 1f;
        float left = barWidth;
        float xJump = barWidth + barMargin;
        if(isPositionLong){
            for (int i = 0; i < 34; i++) {

                canvas.drawRect(right - left, topMargin, right, topMargin + barHeight, blankPaint);
                right -= xJump;
            }
        }
        else {
            for (int i = 0; i < normalCount; i++) {

                canvas.drawRect(right - left, topMargin, right, topMargin + barHeight, normalPaint);
                right -= xJump;
            }

            for (int i = 0; i < warningCount; i++) {

                canvas.drawRect(right - left, topMargin, right, topMargin + barHeight, warningPaint);
                right -= xJump;
            }

            for (int i = 0; i < dangerCount; i++) {

                canvas.drawRect(right - left, topMargin, right, topMargin + barHeight, dangerPaint);
                right -= 5f;
            }

            for (int i = 0; i < blankCount; i++) {

                canvas.drawRect(right - left, topMargin, right, topMargin + barHeight, blankPaint);
                right -= xJump;
            }
        }

        drawCenterBar(canvas, width /2 - 1f - 17*xJump - left + 3f);
        drawCenterBar(canvas, right - left + 1);

        leftCenterBarStartPoint = right - left + 3;

        canvas.drawText("(" + maxShortPosText + ")", right - left + 2, centerBarHeight + 1.4f*topMargin, textPaintMaxShort);

        if(!isPositionLong) {
            canvas.drawBitmap(pointerImage, width / 2 - ((barsCount - 1) * xJump) - pointerImage.getWidth() / 2 - (barsCount == 0 ? 0 : 2), topMargin - pointerImage.getHeight() - 2, null);
            canvas.drawText(currentPosText, pointerImage.getWidth()/2 +  width / 2 - ((barsCount - 1) * xJump) - pointerImage.getWidth() / 2 - (barsCount == 0 ? 0 : 2), topMargin - pointerImage.getHeight() - 5, textPaint);
        }
    }

    private void setBars(){

        double percentage = 34 * currentPosition/maxLongPosition;
        if(!isPositionLong){
            percentage = -1 * percentage;
        }

        int barsCount =  (int)Math.ceil(percentage);


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

//        if(this.maxLongPosition != maxLong) {
            this.maxLongPosition = maxLong;
            this.maxLongPosText = displayMaxLong;
//            invalidate();
//        }
    }

    public void setMaxShortPosition(int maxShort, String displayMaxShort){
//        if(this.maxShortPosition != maxShort) {
            this.maxShortPosition = maxShort;
            this.maxShortPosText = displayMaxShort;
//            invalidate();
//        }
    }

    public void setCurrentPosition(double currentPosition, String displayCurrentPosition){

//        if(this.currentPosition != currentPosition) {
            this.currentPosition = currentPosition;
            this.currentPosText = displayCurrentPosition;
            isPositionLong = !(currentPosition < 0);
            if(!isPositionLong){
                this.currentPosText = "(" + displayCurrentPosition + ")";
            }
            setBars();

            invalidate();
//        }
    }
}

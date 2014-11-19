package prafulmantale.praful.com.positionscale;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
        System.out.println("### Width:" + getWidth());

        blankPaint = new Paint();
        blankPaint.setColor(Color.GRAY);

        normalPaint = new Paint();
        normalPaint.setColor(Color.BLUE);

        warningPaint = new Paint();
        warningPaint.setColor(Color.MAGENTA);

        dangerPaint = new Paint();
        dangerPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float left = width/2;
        drawCenterBar(canvas, left);
        drawShortBars(canvas);
        drawLongBars(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int measuredWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int measuredHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(measuredWidth, measuredHeight);

        init();
    }

    private void drawCenterBar(Canvas canvas, float left) {

        canvas.drawRect( left, 0, left + 0.75f, 24, blankPaint);
    }

    private void drawLongBars(Canvas canvas){

        float left = width /2 + 2f;
        float right = 3;
        for(int i = 0; i < normalCount; i ++){

            canvas.drawRect( left, 0, left + right, 16, normalPaint);
            left += 5f;
        }

        for(int i = 0; i < warningCount; i ++){

            canvas.drawRect( left, 0, left + right, 16, warningPaint);
            left += 5f;
        }

        for(int i = 0; i < dangerCount; i ++){

            canvas.drawRect( left, 0, left + right, 16, dangerPaint);
            left += 5f;
        }

        for(int i = 0; i < blankCount; i ++){

            canvas.drawRect( left, 0, left + right, 16, blankPaint);
            left += 5f;
        }

        drawCenterBar(canvas, width /2 + 2f + 17 *5 -2f);
        drawCenterBar(canvas, left + 1);
    }

    private void drawShortBars(Canvas canvas){

        float right = width /2 - 1f;
        float left = 3;
        for(int i = 0; i < normalCount; i ++){

            canvas.drawRect( right - left, 0, right, 16, normalPaint);
            right -= 5f;
        }

        for(int i = 0; i < warningCount; i ++){

            canvas.drawRect( right - left, 0, right, 16, warningPaint);
            right -= 5f;
        }

        for(int i = 0; i < dangerCount; i ++){

            canvas.drawRect( right - left, 0,  right, 16, dangerPaint);
            right -= 5f;
        }

        for(int i = 0; i < blankCount; i ++){

            canvas.drawRect( right - left, 0, right, 16, blankPaint);
            right -= 5f;
        }

        drawCenterBar(canvas, width /2 - 1f - 17*5 - left + 3f);
        drawCenterBar(canvas, right - left + 1);

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
}

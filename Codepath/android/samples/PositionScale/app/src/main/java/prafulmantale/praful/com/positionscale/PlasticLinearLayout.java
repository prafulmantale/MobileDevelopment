package prafulmantale.praful.com.positionscale;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by prafulmantale on 11/18/14.
 */
public class PlasticLinearLayout extends LinearLayout {

    private Path shinyPath;
    private Paint shinyPaint;

    public PlasticLinearLayout(Context context) {
        super(context);
        init();
    }

    public PlasticLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlasticLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setBackgroundResource(R.drawable.plastic_background);
        shinyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        createShinyPath();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        if(shinyPath == null){
            createShinyPath();
        }

        //canvas.drawPath(shinyPath, shinyPaint);

        drawCenterBar(canvas);
        drawLongBars(canvas);
        drawShortBars(canvas);


        super.dispatchDraw(canvas);
    }

    private void drawCenterBar(Canvas canvas) {

        int width = getWidth();
        Paint paint1 = new Paint();
        paint1.setColor(Color.GRAY);
        float left = width/2;
        canvas.drawRect( left, 0, left + 1, 24, paint1);
    }

    private void drawLongBars(Canvas canvas){

        int width = getWidth();
        Paint paint1 = new Paint();
        paint1.setColor(Color.BLUE);
        float left = width /2 + 2f;
        float right = 3;
        for(int i = 0; i < normalCount; i ++){

            canvas.drawRect( left, 0, left + right, 16, paint1);
            left += 5f;
        }

        Paint paint2 = new Paint();
        paint2.setColor(Color.MAGENTA);

        for(int i = 0; i < warningCount; i ++){

            canvas.drawRect( left, 0, left + right, 16, paint2);
            left += 5f;
        }

        Paint paint3 = new Paint();
        paint3.setColor(Color.RED);

        for(int i = 0; i < dangerCount; i ++){

            canvas.drawRect( left, 0, left + right, 16, paint3);
            left += 5f;
        }

        Paint paint4 = new Paint();
        paint4.setColor(Color.GRAY);

        for(int i = 0; i < blankCount; i ++){

            canvas.drawRect( left, 0, left + right, 16, paint4);
            left += 5f;
        }

    }

    private void drawShortBars(Canvas canvas){

        int width = getWidth();
        Paint paint1 = new Paint();
        paint1.setColor(Color.BLUE);
        float right = width /2 - 1f;
        float left = 3;
        for(int i = 0; i < normalCount; i ++){

            canvas.drawRect( right - left, 0, right, 16, paint1);
            right -= 5f;
        }

        Paint paint2 = new Paint();
        paint2.setColor(Color.MAGENTA);

        for(int i = 0; i < warningCount; i ++){

            canvas.drawRect( right - left, 0, right, 16, paint2);
            right -= 5f;
        }

        Paint paint3 = new Paint();
        paint3.setColor(Color.RED);

        for(int i = 0; i < dangerCount; i ++){

            canvas.drawRect( right - left, 0,  right, 16, paint3);
            right -= 5f;
        }

        Paint paint4 = new Paint();
        paint4.setColor(Color.GRAY);

        for(int i = 0; i < blankCount; i ++){

            canvas.drawRect( right - left, 0, right, 16, paint4);
            right -= 5f;
        }

    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        shinyPath = null;
    }

    private void createShinyPath(){
        shinyPath = new Path();
        int width = getWidth();
        int height = (int) (0.85 * getHeight());

        shinyPath.moveTo(0, 0);
        shinyPath.lineTo(0, width);
        shinyPath.lineTo(width, height);
        shinyPath.close();

        shinyPaint.setShader(new LinearGradient(0, 0, 0, height, 0x66ffffff, 0x10ffffff, Shader.TileMode.CLAMP));
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

    private int barsCount = 0;
    private int blankCount = 34;
    private int normalCount = 0;
    private int warningCount = 0;
    private int dangerCount = 0;

}

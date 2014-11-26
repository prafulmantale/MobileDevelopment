package prafulmantale.praful.com.positionscale;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by prafulmantale on 11/26/14.
 */
public class LineChart extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int [] inputs = new int[]{
            5, 7, 11, 30, 7, 8, 10, 11, 17, 28, 21, 2, 9, 15, 14, 6, 13, 12, 22, 26, 28,20, 19, 4
    };

    int topMargin = 2;
    int volMax = 30;

    public LineChart(Context context) {
        super(context);
    }

    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.MAGENTA);
        borderPaint.setColor(Color.GREEN);
        borderPaint.setStyle(Paint.Style.STROKE);

        float left = 50;
        float top = getMeasuredHeight() - 1;
        int barwidth = getBarWidth();

        float perUnit = ((float) getMeasuredHeight() - ((float) getMeasuredHeight() / 20)) / (float) volMax;

        float prevX = -1;
        float prevY = -1;
        for (int i = 0; i < 24; i++) {

            canvas.drawCircle(left + barwidth/2, top - getBarHeight(i, perUnit), barwidth/2,  paint);
            if(i > 0){
                canvas.drawLine(prevX, prevY, left + barwidth/2, top - getBarHeight(i, perUnit), paint);
            }
            prevX = left + barwidth/2;
            prevY = top - getBarHeight(i, perUnit);

            left += margin + barwidth;
        }

        canvas.drawLine(40, 0, left, 0, borderPaint);
        canvas.drawLine(left, 0, left, getMeasuredHeight(), borderPaint);
        canvas.drawLine(left, getMeasuredHeight(), 40, getMeasuredHeight(), borderPaint);
        canvas.drawLine(40, getMeasuredHeight(), 40, 0, borderPaint);

        canvas.drawText("Yield", left + 3, 10, textPaint);
        canvas.drawText("0M", left + 3, getMeasuredHeight() - 2, textPaint);
    }

    private int getBarWidth(){
        int width = getMeasuredWidth() - 100;
        int barwidth = width - (22 * margin);
        barwidth = barwidth/(24);

        return barwidth;
    }

    private float getBarHeight(int index, float perUnit){

        int val = inputs[index];
        return perUnit * val;
    }

    private int margin = 6;
}

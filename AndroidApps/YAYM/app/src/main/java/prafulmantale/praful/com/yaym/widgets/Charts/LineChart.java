package prafulmantale.praful.com.yaym.widgets.Charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import prafulmantale.praful.com.yaym.R;

/**
 * Created by prafulmantale on 11/25/14.
 */
public class LineChart extends View {

    private Paint circlePaint;
    private Paint linePaint;
    private Paint verticalBorderPaint;
    private Paint horizontalBorderPaint;
    private Paint labelPaint;

    private int leftMargin;
    private int rightMargin;
    private int topMargin;
    private int bottomMargin;

    private int marginBetweenBars;

    private double []dataSource;
    private YieldChartProperties props;
    private Typeface textTypeface;

    public LineChart(Context context) {
        super(context);

        init();
    }

    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public LineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){

        if(isInEditMode()){
            return;
        }

        textTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(getResources().getColor(R.color.chart_yield_circle));
        circlePaint.setStyle(Paint.Style.FILL);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(getResources().getColor(R.color.chart_yield_line));
        linePaint.setStyle(Paint.Style.STROKE);

        verticalBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        verticalBorderPaint.setColor(getResources().getColor(R.color.chart_vertical_line));
        verticalBorderPaint.setStyle(Paint.Style.STROKE);
        verticalBorderPaint.setStrokeWidth(1);

        horizontalBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        horizontalBorderPaint.setColor(getResources().getColor(R.color.char_horizontal_line));
        horizontalBorderPaint.setStyle(Paint.Style.STROKE);
        horizontalBorderPaint.setStrokeWidth(1);

        labelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        labelPaint.setColor(getResources().getColor(R.color.chart_label_text));
        labelPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        labelPaint.setStrokeWidth(0.5f);
        labelPaint.setTypeface(textTypeface);
        labelPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.chart_scale_label_text_size));

        marginBetweenBars = getResources().getDimensionPixelSize(R.dimen.chart_bar_margin);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(isInEditMode()){
            return;
        }

        if(dataSource == null || dataSource.length == 0){
            return;
        }

        leftMargin = getMeasuredWidth()/6;
        rightMargin = getMeasuredWidth()/6;
        topMargin = getMeasuredHeight()/8;
        bottomMargin = getMeasuredHeight()/8;

        float left = marginBetweenBars + leftMargin;
        float top = getMeasuredHeight() - topMargin;
        int barwidth = getBarWidth();

        float perUnit = ((float)getMeasuredHeight() - topMargin - bottomMargin)/(float)(props.maxYield - props.minYield);

        float prevX = 0;
        float prevY = 0;

        for(int i = 0; i < 24; i++){

            float cx = left + barwidth/2;
            float cy = topMargin + getBarHeight(i, perUnit) + barwidth/2;
            float  radius = barwidth/4;

            if(i > 0){
                canvas.drawLine(cx, cy, prevX, prevY, linePaint);
            }

            canvas.drawCircle(cx, cy, radius, circlePaint);

            prevX = cx;
            prevY = cy;

            left+= marginBetweenBars + barwidth;
        }

        canvas.drawLine(leftMargin, topMargin, left, topMargin, horizontalBorderPaint);
        canvas.drawLine(left, topMargin, left, getMeasuredHeight() - bottomMargin, verticalBorderPaint);
        canvas.drawLine(left, getMeasuredHeight() - bottomMargin, leftMargin, getMeasuredHeight() - bottomMargin, horizontalBorderPaint);
        canvas.drawLine(leftMargin, getMeasuredHeight() - bottomMargin, leftMargin, topMargin, verticalBorderPaint);

        float y = (top - bottomMargin) / 3;

        for(int i = 1; i <= 2; i++) {
            float yLoc = top -  (y * i);
            canvas.drawLine(leftMargin, yLoc, left, yLoc, horizontalBorderPaint);
            canvas.drawText(props.scale[i], left + 10, yLoc, labelPaint);
        }

        canvas.drawText(props.maxYieldDisplay, left + 10, topMargin, labelPaint);
        canvas.drawText(props.minYieldDisplay, left + 10, getMeasuredHeight() - bottomMargin, labelPaint);
    }

    public void setDataSource(double [] dataSource){

        if(dataSource == null || dataSource.length == 0){
            return;
        }

        if(dataSource.length < 24){
            System.out.println("Needs to handle properly");

            return;
        }

        this.dataSource = dataSource;
        double max = dataSource[0];
        double min = dataSource[0];
        for(int i = 1; i < dataSource.length; i ++){

            if(dataSource[i] > max){
                max = dataSource[i];
            }

            if(dataSource[i] < min){
                min = dataSource[i];
            }
        }

        props = new YieldChartProperties(min, max);
        invalidate();
    }

    private int getBarWidth(){
        int width = getMeasuredWidth() - (leftMargin + rightMargin);
        int barwidth = width - (24 * marginBetweenBars);
        barwidth = barwidth/(dataSource.length);

        return barwidth;
    }

    private float getBarHeight(int index, float perUnit){

        double val = dataSource[index];
        return (float)(perUnit *  (props.maxYield - val));
    }

    private class YieldChartProperties {

        private double minYield;
        private double maxYield;
        private String minYieldDisplay;
        private String maxYieldDisplay;
        private String []scale;


        public YieldChartProperties (double inputMinYield, double inputMaxYield){

            int newMin = (int)inputMinYield + 1;
            newMin = newMin / 10;
            newMin = newMin * 10;

            while (newMin > inputMinYield) {
                newMin = newMin - 10;
            }

            if(newMin == (int)inputMinYield){
                newMin = newMin - 5;
            }
            minYield = newMin;

            minYieldDisplay = String.valueOf(newMin);

            int newMax = (int)minYield;

            int counter = 1;
            int multiplier = 0;
            while (newMax < inputMaxYield) {
                multiplier = 5 * counter;
                newMax = newMin + multiplier * 3;
                counter++;
            }

            maxYield = newMax;
            maxYieldDisplay = String.valueOf(maxYield);

            scale = new String[4];

            scale[0] = minYieldDisplay;
            scale[1] = String.valueOf(minYield + multiplier);
            scale[2] = String.valueOf(minYield + (2 * multiplier));
            scale[3] = maxYieldDisplay;
        }
    }
}

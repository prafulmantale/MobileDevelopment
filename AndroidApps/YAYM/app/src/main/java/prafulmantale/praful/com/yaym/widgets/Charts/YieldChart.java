package prafulmantale.praful.com.yaym.widgets.Charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.caches.HistoricalDataCache;

/**
 * Created by prafulmantale on 11/25/14.
 */
public class YieldChart extends ChartView {

    private Paint circlePaint;
    private Paint linePaint;

    private double []dataSource;
    private YieldChartProperties props;

    public YieldChart(Context context) {
        super(context);
    }

    public YieldChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public YieldChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(){

        if(isInEditMode()){
            return;
        }

        super.init();

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(getResources().getColor(R.color.chart_yield_circle));
        circlePaint.setStyle(Paint.Style.FILL);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(getResources().getColor(R.color.chart_yield_line));
        linePaint.setStyle(Paint.Style.STROKE);


        title = getResources().getString(R.string.yield_chart_title);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(isInEditMode()){
            return;
        }

        if(dataSource == null || dataSource.length == 0){
            return;
        }

        leftMargin = 0;//getMeasuredWidth()/6;
        rightMargin = getMeasuredWidth()/6;
        topMargin = getMeasuredHeight()/8;
        bottomMargin = getMeasuredHeight()/8;

        float left = marginBetweenBars + leftMargin;
        float top = getMeasuredHeight() - topMargin;
        int barwidth = getBarWidth();

        float perUnit = ((float)getMeasuredHeight() - topMargin - bottomMargin)/(float)(props.maxYield - props.minYield);

        float prevX = 0;
        float prevY = 0;

        int hrCounter = 1;
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

            if(i == hrCounter) {

                canvas.drawLine(left + barwidth/2, getMeasuredHeight() - bottomMargin, left + barwidth/2, getMeasuredHeight() - bottomMargin + 20, linePaint);
                canvas.drawText(HistoricalDataCache.getInstance().getTimeStamps()[i], left - (labelPaint.getTextSize()/2) , 20 + getMeasuredHeight() - bottomMargin + labelPaint.getTextSize(), labelPaint);
                hrCounter += 4;
            }

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

        canvas.drawText(title, (getMeasuredWidth() - leftMargin - rightMargin)/2 - titlePaint.getTextSize(), topMargin - 15, titlePaint);
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

package prafulmantale.praful.com.yaym.widgets.Charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.helpers.UOMNumber;

/**
 * Created by prafulmantale on 11/25/14.
 */
public class LineChart extends View {

    private Paint circlePaint;
    private Paint linePaint;
    private Paint verticalBorderPaint;
    private Paint horizontalBorderPaint;
    private Paint labelPaint;

    private int sideMargin;
    private int marginBetweenBars;
    private int topMargin;
    private double maxVolume;
    private double minVolume = 0;

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
        labelPaint.setStyle(Paint.Style.STROKE);
        labelPaint.setStrokeWidth(1);
        labelPaint.setTypeface(textTypeface);

        sideMargin = getResources().getDimensionPixelSize(R.dimen.chart_side_margin);
        marginBetweenBars = getResources().getDimensionPixelSize(R.dimen.chart_bar_margin);
        topMargin = getResources().getDimensionPixelSize(R.dimen.chart_bar_top_margin);
        minVolume = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(isInEditMode()){
            return;
        }

        if(dataSource == null || dataSource.length == 0){
            return;
        }

        float left = marginBetweenBars + sideMargin;
        float top = getMeasuredHeight() - 1;
        int barwidth = getBarWidth();

        float perUnit = ((float)getMeasuredHeight() - ((float)getMeasuredHeight()/20))/(float)maxVolume;

        float prevX = 0;
        float prevY = 0;

        for(int i = 0; i < 24; i++){

            float cx = left + barwidth/2;
            float cy = top - getBarHeight(i, perUnit) + barwidth/2;
            float  radius = barwidth/4;

            if(i > 0){
                canvas.drawLine(cx, cy, prevX, prevY, linePaint);
            }

            canvas.drawCircle(cx, cy, radius, circlePaint);

            prevX = cx;
            prevY = cy;

            left+= marginBetweenBars + barwidth;
        }

        canvas.drawLine(sideMargin, 0, left, 0, horizontalBorderPaint);
        canvas.drawLine(left, 0, left, getMeasuredHeight(), verticalBorderPaint);
        canvas.drawLine(left, getMeasuredHeight(), sideMargin, getMeasuredHeight(), horizontalBorderPaint);
        canvas.drawLine(sideMargin, getMeasuredHeight(), sideMargin, 0, verticalBorderPaint);

        float y = top / 3;

        for(int i = 1; i <= 2; i++) {
            canvas.drawLine(sideMargin, y * i, left, y * i, horizontalBorderPaint);
        }

        canvas.drawText("Yield", left + 3, 10, labelPaint);
        canvas.drawText("0M", left + 3, getMeasuredHeight() - 2, labelPaint);
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
        maxVolume = dataSource[0];
        for(int i = 1; i < dataSource.length; i ++){

            if(dataSource[i] > maxVolume){
                maxVolume = dataSource[i];
            }

            if(dataSource[i] < minVolume){
                minVolume = dataSource[i];
            }
        }

//        props = YieldChartProperties.newInstance(minVolume, maxVolume);
//
//        maxVolume = props.getMaxYield().getAbsoluteValue();
//        minVolume = props.getMinYield().getAbsoluteValue();

        invalidate();
    }

    private int getBarWidth(){
        int width = getMeasuredWidth() - (2 * sideMargin) - (2 * marginBetweenBars);
        int barwidth = width - (22 * marginBetweenBars);
        barwidth = barwidth/(dataSource.length);

        return barwidth;
    }

    private float getBarHeight(int index, float perUnit){

        double val = dataSource[index];
        return (float)(perUnit * val);
    }

    private class YieldChartProperties {

        private UOMNumber minYield;
        private UOMNumber maxYield;


        public YieldChartProperties (double inputMinYield, double inputMaxYield){

            UOMNumber minUN = new UOMNumber(inputMinYield);

            int min = (int)minUN.getFormattedValue();
            if(min % 2 != 0){
                min = min - 1;
            }

            props.minYield = new UOMNumber(min);

            UOMNumber maxUN = new UOMNumber(inputMaxYield);
            int max = (int)maxUN.getFormattedValue() + 1;

            if(max % 2 != 0){
                max = max + 1;
            }

            props.maxYield = new UOMNumber(max);
        }
    }
}

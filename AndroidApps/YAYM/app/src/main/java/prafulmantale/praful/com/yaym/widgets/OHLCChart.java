package prafulmantale.praful.com.yaym.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.models.OHLCData;
import prafulmantale.praful.com.yaym.models.RateChartProperties;

/**
 * Created by prafulmantale on 11/25/14.
 */
public class OHLCChart extends View {

    private Paint barPaintUp;
    private Paint barPaintDown;
    private Paint verticalBorderPaint;
    private Paint horizontalBorderPaint;
    private Paint labelPaint;

    private int rightMargin;
    private int leftMargin;
    private int topMargin;
    private int bottomMargin;
    private int marginBetweenBars;

    private OHLCData []dataSource;
    private Typeface textTypeface;
    private RateChartProperties rateChartProperties = null;

    public OHLCChart(Context context) {
        super(context);

        init();
    }

    public OHLCChart(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public OHLCChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){

        if(isInEditMode()){
            return;
        }

        textTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        barPaintUp = new Paint(Paint.ANTI_ALIAS_FLAG);
        barPaintUp.setColor(getResources().getColor(R.color.chart_rate_border));
        barPaintUp.setStyle(Paint.Style.STROKE);

        barPaintDown = new Paint(Paint.ANTI_ALIAS_FLAG);
        barPaintDown.setColor(getResources().getColor(R.color.chart_rate_bar));
        barPaintDown.setStyle(Paint.Style.FILL);

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

        float perUnit = ((float)getMeasuredHeight() - topMargin - bottomMargin)/(10000 * (float)(rateChartProperties.getMaxRate() - rateChartProperties.getMinRate()));

        for(int i = 0; i < 24; i++){
            float bottom =  bottomMargin + getRectBottom(i, perUnit);
            float rectTop = topMargin + getRectTop(i, perUnit);


            if(dataSource[i].getOpen()> dataSource[i].getClose()) {
                canvas.drawRect(left, rectTop, left + barwidth, bottom, barPaintDown);
            }
            canvas.drawRect(left, rectTop, left + barwidth, bottom, barPaintUp);

            canvas.drawLine(left + barwidth/2, rectTop, left + barwidth/2, topMargin + getHighY(i, perUnit), barPaintUp);
            canvas.drawLine(left + barwidth/2, bottom, left + barwidth/2, bottomMargin + getLowY(i, perUnit), barPaintUp);

            left+= marginBetweenBars + barwidth;
        }

        canvas.drawLine(leftMargin, topMargin, left, topMargin, horizontalBorderPaint);
        canvas.drawLine(left, topMargin, left, getMeasuredHeight() - bottomMargin, verticalBorderPaint);
        canvas.drawLine(left, getMeasuredHeight() - bottomMargin, leftMargin, getMeasuredHeight() - bottomMargin, horizontalBorderPaint);
        canvas.drawLine(leftMargin, getMeasuredHeight() - bottomMargin, leftMargin, topMargin, verticalBorderPaint);

        canvas.drawText(rateChartProperties.getScale()[3], left + 10, topMargin, labelPaint);
        canvas.drawText(rateChartProperties.getScale()[0], left + 10, getMeasuredHeight() - bottomMargin, labelPaint);

        float y = (top - bottomMargin) / 3;

        for(int i = 1; i <= 2; i++) {
            float yLoc = top - (y * i);
            canvas.drawLine(leftMargin, yLoc, left, yLoc, horizontalBorderPaint);
            canvas.drawText(rateChartProperties.getScale()[i], left + 10, yLoc, labelPaint);
        }
    }

    public void setDataSource(String ccyPair, OHLCData[] dataSource){

        if(dataSource == null || dataSource.length == 0){
            return;
        }

        if(dataSource.length < 24){
            System.out.println("Needs to handle properly");

            return;
        }

        this.dataSource = dataSource;
        double minRate = dataSource[0].getLow();
        double maxRate = dataSource[0].getHigh();
        for(int i = 1; i < dataSource.length; i ++){

            if(dataSource[i].getHigh() > maxRate){
                maxRate = dataSource[i].getHigh();
            }

            if(dataSource[i].getLow() < minRate){
                minRate = dataSource[i].getLow();
            }
        }

        rateChartProperties = RateChartProperties.newInstance(ccyPair, minRate, maxRate);


        System.out.println("Min Rate: " + rateChartProperties.getMinRate() + "   Max Rate: " + rateChartProperties.getMaxRate());

        invalidate();
    }

    private int getBarWidth(){
        int width = getMeasuredWidth() - (leftMargin + rightMargin) - (2 * marginBetweenBars);
        int barwidth = width - (22 * marginBetweenBars);
        barwidth = barwidth/(dataSource.length);

        return barwidth;
    }

    private float getRectBottom(int index, float perUnit){
        double open = dataSource[index].getOpen();
        double close = dataSource[index].getClose();

        if(open > close){
            return (float)(perUnit * 10000 * (rateChartProperties.getMaxRate() - close));
        }
        else{
            return (float)(perUnit * 10000 * (rateChartProperties.getMaxRate() - open));
        }

    }

    private float getRectTop(int index, float perUnit){
        double open = dataSource[index].getOpen() ;
        double close = dataSource[index].getClose();
        if(open > close){
            return  (float)(perUnit * 10000 * (rateChartProperties.getMaxRate()-open));
        }
        else{
            return (float)(perUnit * 10000 * (rateChartProperties.getMaxRate() - close));
        }

    }

    private float getHighY(int index, float perUnit){

        double high = dataSource[index].getHigh() ;
        return  (float)(perUnit * 10000 * (rateChartProperties.getMaxRate() - high));
    }

    private float getLowY(int index, float perUnit){

        double low = dataSource[index].getLow() ;
        return  (float)(perUnit * 10000 * (rateChartProperties.getMaxRate() - low));
    }
}

package prafulmantale.praful.com.yaym.widgets.Charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.math.BigDecimal;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.caches.RefDataCache;
import prafulmantale.praful.com.yaym.models.OHLCData;
import prafulmantale.praful.com.yaym.models.RateProperties;
import prafulmantale.praful.com.yaym.models.ReferenceData;

/**
 * Created by prafulmantale on 11/25/14.
 */
public class OHLCChart extends ChartView {

    private Paint barPaintUp;
    private Paint barPaintDown;

    private OHLCData []dataSource;
    private RateChartProperties rateChartProperties = null;

    public OHLCChart(Context context) {
        super(context);
    }

    public OHLCChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OHLCChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(){

        if(isInEditMode()){
            return;
        }

        super.init();

        barPaintUp = new Paint(Paint.ANTI_ALIAS_FLAG);
        barPaintUp.setColor(getResources().getColor(R.color.chart_rate_border));
        barPaintUp.setStyle(Paint.Style.STROKE);

        barPaintDown = new Paint(Paint.ANTI_ALIAS_FLAG);
        barPaintDown.setColor(getResources().getColor(R.color.chart_rate_bar));
        barPaintDown.setStyle(Paint.Style.FILL);

        title = getResources().getString(R.string.rate_chart_title);
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

        float perUnit = ((float)getMeasuredHeight() - topMargin - bottomMargin)/(10000 * (float)(rateChartProperties.maxRate - rateChartProperties.minRate));

        int hrCounter = 1;
        for(int i = 0; i < 24; i++){
            float bottom =  bottomMargin + getRectBottom(i, perUnit);
            float rectTop = topMargin + getRectTop(i, perUnit);


            if(dataSource[i].getOpen()> dataSource[i].getClose()) {
                canvas.drawRect(left, rectTop, left + barwidth, bottom, barPaintDown);
            }
            canvas.drawRect(left, rectTop, left + barwidth, bottom, barPaintUp);

            canvas.drawLine(left + barwidth/2, rectTop, left + barwidth/2, topMargin + getHighY(i, perUnit), barPaintUp);
            canvas.drawLine(left + barwidth/2, bottom, left + barwidth/2, bottomMargin + getLowY(i, perUnit), barPaintUp);

            if(i == hrCounter) {

                canvas.drawLine(left + barwidth/2, getMeasuredHeight() - bottomMargin, left + barwidth/2, getMeasuredHeight() - bottomMargin + 20, barPaintUp);
                canvas.drawText(dataSource[i].getDisplayTimestamp(), left - (labelPaint.getTextSize()/2) , 20 + getMeasuredHeight() - bottomMargin + labelPaint.getTextSize(), labelPaint);
                hrCounter += 4;
            }

            left+= marginBetweenBars + barwidth;
        }

        canvas.drawLine(leftMargin, topMargin, left, topMargin, horizontalBorderPaint);
        canvas.drawLine(left, topMargin, left, getMeasuredHeight() - bottomMargin, verticalBorderPaint);
        canvas.drawLine(left, getMeasuredHeight() - bottomMargin, leftMargin, getMeasuredHeight() - bottomMargin, horizontalBorderPaint);
        canvas.drawLine(leftMargin, getMeasuredHeight() - bottomMargin, leftMargin, topMargin, verticalBorderPaint);

        canvas.drawText(rateChartProperties.scale[3], left + 10, topMargin, labelPaint);
        canvas.drawText(rateChartProperties.scale[0], left + 10, getMeasuredHeight() - bottomMargin, labelPaint);

        float y = (top - bottomMargin) / 3;

        for(int i = 1; i <= 2; i++) {
            float yLoc = top - (y * i);
            canvas.drawLine(leftMargin, yLoc, left, yLoc, horizontalBorderPaint);
            canvas.drawText(rateChartProperties.scale[i], left + 10, yLoc, labelPaint);
        }

        canvas.drawText(title, (getMeasuredWidth() - leftMargin - rightMargin)/2 - titlePaint.getTextSize(), topMargin - 15, titlePaint);
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

        rateChartProperties = new RateChartProperties(ccyPair, minRate, maxRate);

        invalidate();
    }

    private int getBarWidth(){
        int width = getMeasuredWidth() - (leftMargin + rightMargin);
        int barwidth = width - (24 * marginBetweenBars);
        barwidth = barwidth/(dataSource.length);

        return barwidth;
    }

    private float getRectBottom(int index, float perUnit){
        double open = dataSource[index].getOpen();
        double close = dataSource[index].getClose();

        if(open > close){
            return (float)(perUnit * 10000 * (rateChartProperties.maxRate - close));
        }
        else{
            return (float)(perUnit * 10000 * (rateChartProperties.maxRate - open));
        }
    }

    private float getRectTop(int index, float perUnit){
        double open = dataSource[index].getOpen() ;
        double close = dataSource[index].getClose();
        if(open > close){
            return  (float)(perUnit * 10000 * (rateChartProperties.maxRate - open));
        }
        else{
            return (float)(perUnit * 10000 * (rateChartProperties.maxRate - close));
        }
    }

    private float getHighY(int index, float perUnit){

        double high = dataSource[index].getHigh() ;
        return  (float)(perUnit * 10000 * (rateChartProperties.maxRate - high));
    }

    private float getLowY(int index, float perUnit){

        double low = dataSource[index].getLow() ;
        return  (float)(perUnit * 10000 * (rateChartProperties.maxRate - low));
    }

    private class RateChartProperties {

        private double minRate;
        private double maxRate;
        private final int pipStep = 5;
        private String[] scale;
        private String ccyPair;

        public RateChartProperties(String ccyPair, double minRate, double maxRate) {
            this.minRate = minRate;
            this.maxRate = maxRate;
            scale = new String[4];

            update(ccyPair);
        }

        private void update(String ccyPair) {

            RateProperties rpMin = RateProperties.newInstance(ccyPair, minRate);

            double newMinRate = rpMin.getBigFigure();
            while (newMinRate < minRate) {
                newMinRate += 25 * rpMin.getOnePipValue();
            }

            while (newMinRate > minRate) {
                newMinRate = newMinRate - (pipStep * rpMin.getOnePipValue());
            }
            if (newMinRate == minRate) {
                newMinRate = newMinRate - (5 * rpMin.getOnePipValue());
            }
            minRate = newMinRate;

            double newMaxRate = minRate;

            int counter = 0;
            double pipMultiplier = 0;
            while (newMaxRate < maxRate) {
                pipMultiplier = ((25 + (pipStep * counter)) * rpMin.getOnePipValue());
                newMaxRate = minRate + pipMultiplier * 3;
                counter++;
            }

            if (newMaxRate == maxRate) {
                newMaxRate = newMaxRate + (5 * rpMin.getOnePipValue());
            }
            maxRate = newMaxRate;

            ReferenceData refData = RefDataCache.getInstance().getReferenceData(ccyPair);
            BigDecimal bmin = new BigDecimal(minRate).setScale(refData.getSpotPrecision(), BigDecimal.ROUND_HALF_UP);
            BigDecimal bmax = new BigDecimal(maxRate).setScale(refData.getSpotPrecision(), BigDecimal.ROUND_HALF_UP);

            minRate = bmin.doubleValue();
            maxRate = bmax.doubleValue();

            String dispRate = RateProperties.newInstance(ccyPair, minRate).getRateDisplay();
            scale[0] = dispRate.substring(0, dispRate.length() - 1);
            dispRate = RateProperties.newInstance(ccyPair, minRate + pipMultiplier).getRateDisplay();
            scale[1] = dispRate.substring(0, dispRate.length() - 1);
            dispRate = RateProperties.newInstance(ccyPair, minRate + (2 * pipMultiplier)).getRateDisplay();
            scale[2] = dispRate.substring(0, dispRate.length() - 1);
            dispRate = RateProperties.newInstance(ccyPair, maxRate).getRateDisplay();
            scale[3] = dispRate.substring(0, dispRate.length() - 1);
        }
    }
}

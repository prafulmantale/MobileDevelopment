package prafulmantale.praful.com.yaym.widgets.Charts;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.caches.HistoricalDataCache;
import prafulmantale.praful.com.yaym.enums.UOMSymbol;
import prafulmantale.praful.com.yaym.helpers.AppConstants;
import prafulmantale.praful.com.yaym.helpers.UOMNumber;

/**
 * Created by prafulmantale on 11/25/14.
 */
public class BarChart extends ChartView {

    private Paint barPaint;
    private double []dataSource;
    private VolumeChartProperties properties;

    public BarChart(Context context) {
        super(context);
    }

    public BarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(){

        if(isInEditMode()){
            return;
        }

        super.init();

        Resources resources = getResources();


        barPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        barPaint.setColor(getResources().getColor(R.color.chart_volume_bar));
        barPaint.setStyle(Paint.Style.FILL);

        title = resources.getString(R.string.volume_chart_title);
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

        float perUnit = ((float)getMeasuredHeight() - topMargin - bottomMargin)/(float)properties.maxVolume;

        int hrCounter = 1;
        for(int i = 0; i < 24; i++){

            canvas.drawRect(left, top - getBarHeight(i, perUnit), left + barwidth, top, barPaint);

            if(i == hrCounter) {

                canvas.drawLine(left + barwidth/2, getMeasuredHeight() - bottomMargin, left + barwidth/2, getMeasuredHeight() - bottomMargin + 20, barPaint);
                canvas.drawText(HistoricalDataCache.getInstance().getTimeStamps()[i], left - (labelPaint.getTextSize()/2) , 20 + getMeasuredHeight() - bottomMargin + labelPaint.getTextSize(), labelPaint);
                hrCounter += 4;
            }

            left+= marginBetweenBars + barwidth;
        }

        canvas.drawLine(leftMargin, topMargin, left, topMargin, horizontalBorderPaint);
        canvas.drawLine(left, topMargin, left, getMeasuredHeight() - bottomMargin, verticalBorderPaint);
        canvas.drawLine(left, getMeasuredHeight() - bottomMargin, leftMargin, getMeasuredHeight() - bottomMargin, horizontalBorderPaint);
        canvas.drawLine(leftMargin, getMeasuredHeight() - bottomMargin, leftMargin, topMargin, verticalBorderPaint);

        float y = (top - bottomMargin) / 2;

        for(int i = 1; i <= 1; i++) {
            float yLoc = bottomMargin +  (y * i);
            canvas.drawLine(leftMargin, yLoc, left, yLoc, horizontalBorderPaint);
            canvas.drawText(properties.midDisplayValue, left + 10, yLoc, labelPaint);

        }

        canvas.drawText(properties.displayMaxVolume, left + 10, topMargin, labelPaint);
        canvas.drawText(properties.displayMinVolume, left + 10, getMeasuredHeight() - bottomMargin, labelPaint);

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
        double maxVolume = dataSource[0];
        for(int i = 1; i < dataSource.length; i ++){
            if(dataSource[i] > maxVolume){
                maxVolume = dataSource[i];
            }
        }

        properties = new VolumeChartProperties(maxVolume);

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
        return (float)(perUnit * val);
    }

    private class VolumeChartProperties {

        private double maxVolume;
        private String displayMaxVolume;
        private String midDisplayValue;
        private String displayMinVolume;

        public VolumeChartProperties (double inputMaxVolume){

            UOMNumber uomn = new UOMNumber(inputMaxVolume);
            int vol = (int)uomn.getFormattedValue() + 1;

            if(vol % 2 != 0){
                vol = vol + 1;
            }

            if(uomn.getUomSymbol() == UOMSymbol.K){
                vol = vol * AppConstants.ONE_THOUSAND;
            }
            else{
                vol = vol * AppConstants.ONE_MILLION;
            }

            maxVolume = vol;
            UOMNumber mid = new UOMNumber(vol/2);
            displayMaxVolume = new UOMNumber(vol).getDisplayValue();
            midDisplayValue = mid.getDisplayValue();
            displayMinVolume = "0" + mid.getUomSymbol();

        }
    }
}

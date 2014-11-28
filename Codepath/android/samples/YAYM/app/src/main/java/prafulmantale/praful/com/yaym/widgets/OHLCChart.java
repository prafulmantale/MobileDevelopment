package prafulmantale.praful.com.yaym.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.models.OHLCData;

/**
 * Created by prafulmantale on 11/25/14.
 */
public class OHLCChart extends View {

    private Paint barPaintUp;
    private Paint barPaintDown;
    private Paint verticalBorderPaint;
    private Paint horizontalBorderPaint;
    private Paint labelPaint;

    private int sideMargin;
    private int marginBetweenBars;
    private int topMargin;
    private double maxRate;
    private double minRate = 0;

    private OHLCData []dataSource;

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
        labelPaint.setStyle(Paint.Style.STROKE);
        labelPaint.setStrokeWidth(1);

        sideMargin = getResources().getDimensionPixelSize(R.dimen.chart_side_margin);
        marginBetweenBars = getResources().getDimensionPixelSize(R.dimen.chart_bar_margin);
        topMargin = getResources().getDimensionPixelSize(R.dimen.chart_bar_top_margin);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(dataSource == null || dataSource.length == 0){
            return;
        }

        float left = marginBetweenBars + sideMargin;
        float top = getMeasuredHeight() - 1;
        int barwidth = getBarWidth();

        float perUnit = ((float)getMeasuredHeight() - ((float)getMeasuredHeight()/20))/(10000 * (float)(maxRate - minRate));

        for(int i = 0; i < 24; i++){
            float bottom = getRectBottom(i, perUnit);
            float rectTop = getRectTop(i, perUnit);


            if(dataSource[i].getOpen()> dataSource[i].getClose()) {
                canvas.drawRect(left, rectTop, left + barwidth, bottom, barPaintDown);
            }
            canvas.drawRect(left, rectTop, left + barwidth, bottom, barPaintUp);

            canvas.drawLine(left + barwidth/2, rectTop, left + barwidth/2, getHighY(i, perUnit), barPaintUp);
            canvas.drawLine(left + barwidth/2, bottom, left + barwidth/2, getLowY(i, perUnit), barPaintUp);

            left+= marginBetweenBars + barwidth;
        }

        canvas.drawLine(sideMargin, 0, left, 0, horizontalBorderPaint);
        canvas.drawLine(left, 0, left, getMeasuredHeight(), verticalBorderPaint);
        canvas.drawLine(left, getMeasuredHeight(), sideMargin, getMeasuredHeight(), horizontalBorderPaint);
        canvas.drawLine(sideMargin, getMeasuredHeight(), sideMargin, 0, verticalBorderPaint);

        canvas.drawText("Rate", left + 3, 10, labelPaint);
        canvas.drawText("0", left + 3, getMeasuredHeight() - 2, labelPaint);
    }

    public void setDataSource(OHLCData[] dataSource){

        if(dataSource == null || dataSource.length == 0){
            return;
        }

        this.dataSource = dataSource;
        minRate = dataSource[0].getLow();
        maxRate = dataSource[0].getHigh();
        for(int i = 1; i < dataSource.length; i ++){

            if(dataSource[i].getHigh() > maxRate){
                maxRate = dataSource[i].getHigh();
            }

            if(dataSource[i].getLow() < minRate){
                minRate = dataSource[i].getLow();
            }
        }

        invalidate();
    }

    private int getBarWidth(){
        int width = getMeasuredWidth() - (2 * sideMargin) - (2 * marginBetweenBars);
        int barwidth = width - (22 * marginBetweenBars);
        barwidth = barwidth/(dataSource.length);

        return barwidth;
    }

    private float getRectBottom(int index, float perUnit){
        double open = dataSource[index].getOpen();
        double close = dataSource[index].getClose();

        if(open > close){
            return (float)(perUnit * 10000 * (maxRate - close));
        }
        else{
            return (float)(perUnit * 10000 * (maxRate - open));
        }

    }

    private float getRectTop(int index, float perUnit){
        double open = dataSource[index].getOpen() ;
        double close = dataSource[index].getClose();
        if(open > close){
            return  (float)(perUnit * 10000 * (maxRate-open));
        }
        else{
            return (float)(perUnit * 10000 * (maxRate - close));
        }

    }

    private float getHighY(int index, float perUnit){

        double high = dataSource[index].getHigh() ;
        return  (float)(perUnit * 10000 * (maxRate - high));
    }

    private float getLowY(int index, float perUnit){

        double low = dataSource[index].getLow() ;
        return  (float)(perUnit * 10000 * (maxRate - low));
    }
}

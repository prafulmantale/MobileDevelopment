package prafulmantale.praful.com.yaym.widgets;

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
public class BarChart extends View {

    private Paint barPaint;
    private Paint verticalBorderPaint;
    private Paint horizontalBorderPaint;
    private Paint labelPaint;

    private int sideMargin;
    private int marginBetweenBars;
    private int topMargin;
    private double maxVolume;
    private double minVolume = 0;

    private double []dataSource;
    private Typeface textTypeface;


    public BarChart(Context context) {
        super(context);

        init();
    }

    public BarChart(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public BarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){

        textTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        barPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        barPaint.setColor(getResources().getColor(R.color.chart_volume_bar));
        barPaint.setStyle(Paint.Style.FILL);

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

        if(dataSource == null || dataSource.length == 0){
            return;
        }

        float left = marginBetweenBars + sideMargin;
        float top = getMeasuredHeight() - 1;
        int barwidth = getBarWidth();

        float perUnit = ((float)getMeasuredHeight() - ((float)getMeasuredHeight()/20))/(float)maxVolume;



        for(int i = 0; i < 24; i++){

            canvas.drawRect(left, top - getBarHeight(i, perUnit), left + barwidth, top, barPaint);
            left+= marginBetweenBars + barwidth;
        }

        canvas.drawLine(sideMargin, 0, left, 0, horizontalBorderPaint);
        canvas.drawLine(left, 0, left, getMeasuredHeight(), verticalBorderPaint);
        canvas.drawLine(left, getMeasuredHeight(), sideMargin, getMeasuredHeight(), horizontalBorderPaint);
        canvas.drawLine(sideMargin, getMeasuredHeight(), sideMargin, 0, verticalBorderPaint);

        float y = top / 2;

        for(int i = 1; i <= 1; i++) {
            canvas.drawLine(sideMargin, y * i, left, y * i, horizontalBorderPaint);
        }

        canvas.drawText("Volume", left + 3, 10, labelPaint);
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
        minVolume = dataSource[0];
        for(int i = 1; i < dataSource.length; i ++){
            System.out.println("Volume i" + dataSource[i]);
            if(dataSource[i] > maxVolume){
                maxVolume = dataSource[i];
            }

            if(dataSource[i] < minVolume){
                minVolume = dataSource[i];
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

    private float getBarHeight(int index, float perUnit){

        double val = dataSource[index];
        return (float)(perUnit * val);
    }
}

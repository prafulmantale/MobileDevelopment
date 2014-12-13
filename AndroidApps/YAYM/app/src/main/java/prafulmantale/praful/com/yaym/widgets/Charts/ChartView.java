package prafulmantale.praful.com.yaym.widgets.Charts;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import prafulmantale.praful.com.yaym.R;

/**
 * Created by prafulmantale on 12/12/14.
 */
public abstract class ChartView extends View{

    protected Paint verticalBorderPaint;
    protected Paint horizontalBorderPaint;

    protected Paint labelPaint;
    protected Paint titlePaint;
    protected String title;

    protected Typeface textTypeface;


    protected int rightMargin;
    protected int leftMargin;
    protected int topMargin;
    protected int bottomMargin;
    protected int marginBetweenBars;

    protected ChartView(Context context) {
        super(context);
        init();
    }

    protected ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    protected ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init(){

        Resources resources = getResources();

        textTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

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
        labelPaint.setTextSize(resources.getDimensionPixelSize(R.dimen.chart_scale_label_text_size));

        titlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        titlePaint.setColor(getResources().getColor(R.color.chart_label_text));
        titlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        titlePaint.setStrokeWidth(1f);
        titlePaint.setTypeface(textTypeface);
        titlePaint.setTextSize(resources.getDimensionPixelSize(R.dimen.chart_title_label_text_size));

        marginBetweenBars = getResources().getDimensionPixelSize(R.dimen.chart_bar_margin);
    }
}

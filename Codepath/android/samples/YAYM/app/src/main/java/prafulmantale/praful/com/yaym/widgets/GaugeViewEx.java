package prafulmantale.praful.com.yaym.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import prafulmantale.praful.com.yaym.R;

/**
 * Created by prafulmantale on 11/18/14.
 */
public class GaugeViewEx extends View {

    private static final String TAG = GaugeViewEx.class.getSimpleName();

    private Paint outerCirclePaintBlank;
    private Paint outerCirclePaintProfit;
    private Paint outerCirclePaintLoss;
    private Paint meterLinePaint;

    private RectF outerRect = new RectF();
    private RectF meterRect = new RectF();

    private Paint needlePaint;

    private int width;
    private int height;

    private int meterWidth;// = getResources().getDimensionPixelSize(R.dimen.gauge_view_meter_width);
    private int innerWidth;// = getResources().getDimensionPixelSize(R.dimen.gause_view_inner_gap) + meterWidth;

    private Paint textPaint;
    private Paint textPaintProfit;
    private Paint textPaintLoss;


    private int lossThreshold;
    private String lossThresholdText;

    private int profitThreshold;
    private String profitThresholdText;

    private double currentPnL;
    private String currentPnLText;

    private float angle = 210;

    public GaugeViewEx(Context context) {
        super(context);
        init();
    }

    public GaugeViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GaugeViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(isInEditMode()){
            return;
        }

        float newangle = (float)(angle * Math.PI / 180);
        float startX = outerRect.centerX();
        float startY = outerRect.centerY();

        float endX   = startX + (float)(meterRect.height()/2 * Math.sin(newangle));
        float endY   = startY + (float)(meterRect.height()/2 * Math.cos(newangle));

        //Outer rect
        if(currentPnL >= 0) {
            canvas.drawArc(outerRect, 180, 60, false, outerCirclePaintBlank);
            canvas.drawArc(outerRect, 240, 120, false, outerCirclePaintProfit);
        }
        else{
            canvas.drawArc(outerRect, 180, 60, false, outerCirclePaintLoss);
            canvas.drawArc(outerRect, 240, 120, false, outerCirclePaintBlank);
        }

        //Meter line
        canvas.drawArc(meterRect, 180, 180, false, meterLinePaint);

        //Needle
        canvas.drawCircle(startX, startY, 4, needlePaint);
        canvas.drawLine(startX, startY, endX, endY, needlePaint);

        canvas.drawText(lossThresholdText, outerRect.left - 20, startY, textPaintLoss);
        canvas.drawText(profitThresholdText, outerRect.right + 20, startY, textPaintProfit);
        canvas.drawText(currentPnLText, startX, startY + textPaint.getTextSize(), textPaint);
    }

    private void init(){

        if(isInEditMode()){
            return;
        }
        meterWidth = getResources().getDimensionPixelSize(R.dimen.gauge_view_meter_width);
        innerWidth = getResources().getDimensionPixelSize(R.dimen.gause_view_inner_gap) + meterWidth;

        outerCirclePaintBlank = new Paint(Paint.ANTI_ALIAS_FLAG);
        outerCirclePaintBlank.setColor(getResources().getColor(R.color.pnl_blank));
        outerCirclePaintBlank.setStyle(Paint.Style.STROKE);
        outerCirclePaintBlank.setStrokeWidth(meterWidth);

        outerCirclePaintProfit = new Paint(Paint.ANTI_ALIAS_FLAG);
        outerCirclePaintProfit.setColor(getResources().getColor(R.color.pnl_profit));
        outerCirclePaintProfit.setStyle(Paint.Style.STROKE);
        outerCirclePaintProfit.setStrokeWidth(meterWidth);

        outerCirclePaintLoss = new Paint(Paint.ANTI_ALIAS_FLAG);
        outerCirclePaintLoss.setColor(getResources().getColor(R.color.pnl_loss));
        outerCirclePaintLoss.setStyle(Paint.Style.STROKE);
        outerCirclePaintLoss.setStrokeWidth(meterWidth);

        meterLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        meterLinePaint.setColor(getResources().getColor(R.color.pnl_meter_line));
        meterLinePaint.setStyle(Paint.Style.STROKE);

        needlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        needlePaint.setColor(getResources().getColor(R.color.needle_color));
        needlePaint.setStyle(Paint.Style.FILL);
        needlePaint.setStrokeWidth(1f);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(getResources().getColor(R.color.pnl_text_color));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.gauge_view_currentvalue_fontsize));


        textPaintProfit = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaintProfit.setColor(getResources().getColor(R.color.pnl_text_color));
        textPaintProfit.setTextSize(getResources().getDimensionPixelSize(R.dimen.gauge_view_profitvalue_fontsize));

        textPaintLoss = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaintLoss.setColor(getResources().getColor(R.color.pnl_text_color));
        textPaintLoss.setTextAlign(Paint.Align.RIGHT);
        textPaintLoss.setTextSize(getResources().getDimensionPixelSize(R.dimen.gauge_view_lossvalue_fontsize));

        currentPnLText = "0.0";
        profitThresholdText = "00";
        lossThresholdText = "00";

        setAngle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        final int min = Math.min(width, height);
        final int maxPadding = Math.max(Math.max(getPaddingBottom(), getPaddingTop()), Math.max(getPaddingLeft(), getPaddingRight()));

        float top = 0;
        float left = 0;
        int arcDiameter = 0;

        arcDiameter = min - maxPadding - 20;
        float arcRadius = (arcDiameter/2);
        top = (height / 2) - arcRadius;
        left = (width/2) - arcRadius;

        outerRect.set(left, top + top, left + arcDiameter, top + arcDiameter);
        meterRect = new RectF(left +innerWidth, top + innerWidth, outerRect.right - innerWidth, outerRect.bottom - innerWidth);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setLossThreshold(int lossThreshold, String lossThresholdText){

        this.lossThreshold = lossThreshold;
        this.lossThresholdText = "(" + lossThresholdText + ")";
    }

    public void setProfitThreshold(int profitThreshold, String profitThresholdText) {
        this.profitThreshold = profitThreshold;
        this.profitThresholdText = profitThresholdText;
    }

    public void setCurrentPnL(double currentPnL, String currentPnLText) {
            this.currentPnL = currentPnL;
            if(this.currentPnL >= 0) {
                this.currentPnLText = currentPnLText;
            }
            else{
                this.currentPnLText = "(" + currentPnLText + ")";
            }

            setAngle();
            invalidate();
    }

    private void setAngle(){

        if(currentPnL == 0 || profitThreshold == 0 || lossThreshold == 0){
            angle = 210;
            return;
        }

        if(currentPnL >= 0) {
            angle = 210 - (float) (120 * currentPnL / profitThreshold);
            if(angle < 90){
                angle = 90;
            }
        }
        else{
            angle = 210 + (float) (60 * currentPnL / lossThreshold);
            if(angle > 270){
                angle = 270;
            }
        }
    }
}

package prafulmantale.praful.com.yaym.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
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
    private Paint innerCirclePaint;
    private Paint meterLinePaint;

    private RectF outerRect;
    private RectF innerRect;
    private RectF meterRect;

    private Paint needlePaint;

    private int width;
    private int height;

    private int center;

    private int meterWidth = getResources().getDimensionPixelSize(R.dimen.gauge_view_meter_width);
    private int innerWidth = getResources().getDimensionPixelSize(R.dimen.gause_view_inner_gap) + meterWidth;

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

    }

    public GaugeViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GaugeViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float newangle = (float)(angle * Math.PI / 180);
        float startX = center;
        float startY = 0.79f* height - 1;
        float endX   = startX + (float)(40 * Math.sin(newangle));
        float endY   = startY + (float)(40 * Math.cos(newangle));


        //Outer rect
        if(currentPnL>= 0) {
            canvas.drawArc(outerRect, 180, 60, true, outerCirclePaintBlank);
            canvas.drawArc(outerRect, 240, 120, true, outerCirclePaintProfit);
        }
        else{
            canvas.drawArc(outerRect, 180, 60, true, outerCirclePaintLoss);
            canvas.drawArc(outerRect, 240, 120, true, outerCirclePaintBlank);
        }

        //Inner Rect
        canvas.drawArc(innerRect, 180, 180, true, innerCirclePaint);

        //Meter line
        canvas.drawArc(meterRect, 180, 180, false, meterLinePaint);

//        canvas.drawLine(innerRect.left, innerRect.bottom, innerRect.right, innerRect.bottom, outerCirclePaintLoss);

        //Needle
        canvas.drawCircle(startX, startY, 4, needlePaint);
        canvas.drawLine(startX, startY, endX, endY, needlePaint);


        canvas.drawText(lossThresholdText, outerRect.left - 5, height - height/6, textPaintLoss);
        canvas.drawText(profitThresholdText, outerRect.right + 5, height - height/6, textPaintProfit);
        canvas.drawText(currentPnLText, startX, startY + 20, textPaint);
    }

    private void init(){

        outerCirclePaintBlank = new Paint(Paint.ANTI_ALIAS_FLAG);
        outerCirclePaintBlank.setColor(getResources().getColor(R.color.pnl_blank));
        outerCirclePaintBlank.setStyle(Paint.Style.FILL);

        outerCirclePaintProfit = new Paint(Paint.ANTI_ALIAS_FLAG);
        outerCirclePaintProfit.setColor(getResources().getColor(R.color.pnl_profit));
        outerCirclePaintProfit.setStyle(Paint.Style.FILL);

        outerCirclePaintLoss = new Paint(Paint.ANTI_ALIAS_FLAG);
        outerCirclePaintLoss.setColor(getResources().getColor(R.color.pnl_loss));
        outerCirclePaintLoss.setStyle(Paint.Style.FILL);

        innerCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        innerCirclePaint.setColor(Color.WHITE);
        innerCirclePaint.setShader(new LinearGradient(0, 0, 0, getHeight(), Color.parseColor("#f6f6f6"), Color.parseColor("#ededed"), Shader.TileMode.REPEAT));
        innerCirclePaint.setStyle(Paint.Style.FILL);

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


        float outerRectLeft = width/4;
        float outerRectTop = height/16;
        float outerRectRight = outerRectLeft + width - (outerRectLeft * 2);
        float outerRectHeight = (float)height*1.5f;
        center = width/2;

        outerRect = new RectF(outerRectLeft, outerRectTop, outerRectRight, outerRectHeight);
        innerRect = new RectF(outerRectLeft + meterWidth, outerRectTop + meterWidth, outerRectRight - meterWidth, outerRectHeight - meterWidth);
        meterRect = new RectF(outerRectLeft +innerWidth, outerRectTop + innerWidth, outerRectRight - innerWidth, outerRectHeight - innerWidth);

        currentPnLText = "0.0";
        profitThresholdText = "00";
        lossThresholdText = "00";

        setAngle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(width, height);


        init();
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
//        if(this.currentPnL != currentPnL) {
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

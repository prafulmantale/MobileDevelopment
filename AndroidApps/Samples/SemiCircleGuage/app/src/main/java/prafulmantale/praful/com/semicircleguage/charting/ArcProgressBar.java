package prafulmantale.praful.com.semicircleguage.charting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.SeekBar;

import java.util.ArrayList;

import prafulmantale.praful.com.semicircleguage.R;

public class ArcProgressBar extends SeekBar {

    Context mcontext;

    public ArcProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;
        intialize(mcontext);

    }

    public ArcProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mcontext = context;
        intialize(mcontext);

    }

    @Override
    public synchronized void setProgress(int progress) {
        // TODO Auto-generated method stub
        super.setProgress(progress);
    }

    private Paint mBasePaint;
    private Paint mSectionPaint1;
    private Paint mSectionPaint2;
    private Paint mSectionPaint3;
    private Paint mSectionPaint4;
    private Paint mselectSectionPaint;
    private Paint mselectLinePaint;
    private Paint mtextLargePaint;
    private Paint mtextSmallPaint;

    private int sectionGap = 1;

    private Paint mProgressPaint;
    private Paint distanceInMeters;
    private Paint distanceValueIndicator;

    private RectF mOval = null;
    private RectF newOval = null;
    private int defaultmax = 180;
    private int startAngle = 180;
    private int strokeWidth = 10;
    private SectionSelectedListner sectionSelctedlistner;

    private int trackColor = 0x2E9AFE;
    private int trackStrokeColor = 0x39B54A;
    private int trackopacityPercent = 40;
    private int trackStrokeWidth = 6;

    private int progressStrokeWidth = 6;
    private int progressStrokeColor = 0x35bf4c;
    private int progressColor = 0x35bf4c;

    private int widgetHeight;
    private int widgetWdith;

    private int arcprogress;
    private ArrayList<String> mainTextArray;
    private ArrayList<String> smallTextArray;

    public void setOval(RectF mOval) {
        this.mOval = mOval;
    }

    public void setStartAngle(int startAngle) {
        this.startAngle = startAngle;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void setTrackColor(int trackColor) {
        this.trackColor = trackColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public ArcProgressBar(Context context) {
        super(context);
        intialize(context);
    }

    public void intialize(Context context) {

        mBasePaint = new Paint();
        mBasePaint.setStyle(Paint.Style.STROKE);
        mBasePaint.setColor(getResources().getColor(R.color.arcsecondarycolor));
        mBasePaint.setStrokeWidth((float) 2.5
                * getResources().getDisplayMetrics().density);
        mBasePaint.setAlpha(30);
        mBasePaint.setAntiAlias(true);

        mProgressPaint = new Paint();
        mProgressPaint.setStyle(Paint.Style.FILL);
        mProgressPaint.setColor(getResources()
                .getColor(R.color.arcprimarycolor));
        mProgressPaint
                .setStrokeWidth(5 * getResources().getDisplayMetrics().density);
        mProgressPaint.setAntiAlias(true);

        setMax(defaultmax);// degrees

		/* Text initalize */
        distanceInMeters = new Paint();
        distanceInMeters.setStyle(Style.FILL);
        distanceInMeters.setColor(getResources().getColor(
                R.color.arcdistancemeters));
        distanceInMeters.setTextSize(18 * this.getContext().getResources()
                .getDisplayMetrics().density);
        distanceInMeters.setTextAlign(Align.CENTER);

        distanceValueIndicator = new Paint();
        distanceValueIndicator.setStyle(Style.FILL);
        distanceValueIndicator.setColor(getResources().getColor(
                R.color.arcprimarycolor));
        distanceValueIndicator.setTextSize(36 * this.getContext()
                .getResources().getDisplayMetrics().density);
        distanceValueIndicator.setTextAlign(Align.CENTER);

        setMax(defaultmax);
        setProgress(10);

		/* initialize section points */
        mSectionPaint1 = new Paint();
        mSectionPaint1.setStyle(Paint.Style.FILL);
        mSectionPaint1.setColor(getResources().getColor(R.color.firstcolor));
        mSectionPaint1.setStrokeWidth((float) 2.5
                * getResources().getDisplayMetrics().density);
        // mSectionPaint1.setAlpha(30);
        mSectionPaint1.setAntiAlias(true);

        mSectionPaint2 = new Paint();
        mSectionPaint2.setStyle(Paint.Style.FILL);
        mSectionPaint2.setColor(getResources().getColor(R.color.secondcolor));
        mSectionPaint2.setStrokeWidth((float) 2.5
                * getResources().getDisplayMetrics().density);
        // mSectionPaint2.setAlpha(30);
        mSectionPaint2.setAntiAlias(true);

        mSectionPaint3 = new Paint();
        mSectionPaint3.setStyle(Paint.Style.FILL);
        mSectionPaint3.setColor(getResources().getColor(R.color.thirdcolor));
        mSectionPaint3.setStrokeWidth((float) 2.5
                * getResources().getDisplayMetrics().density);
        // mSectionPaint3.setAlpha(30);
        mSectionPaint3.setAntiAlias(true);

        mSectionPaint4 = new Paint();
        mSectionPaint4.setStyle(Paint.Style.FILL);
        mSectionPaint4.setColor(getResources().getColor(R.color.fourcolor));
        mSectionPaint3.setStrokeWidth((float) 2.5
                * getResources().getDisplayMetrics().density);
        // mSectionPaint4.setAlpha(30);
        mSectionPaint4.setAntiAlias(true);

        mselectSectionPaint = new Paint();
        mselectSectionPaint.setStyle(Paint.Style.STROKE);
        mselectSectionPaint.setColor(getResources().getColor(R.color.gray));
        mselectSectionPaint.setStrokeWidth((float) 2.5
                * getResources().getDisplayMetrics().density);
        mselectSectionPaint.setAntiAlias(true);
        mselectSectionPaint.setShadowLayer(3.0f, 0.0f, 2.0f, 0xFF000000);

		/* section seperator Paint */
        mselectLinePaint = new Paint();
        mselectLinePaint.setStyle(Paint.Style.FILL);
        mselectLinePaint.setColor(getResources().getColor(R.color.white));
        mselectLinePaint.setStrokeWidth((float) 2.5 * getResources().getDisplayMetrics().density);
        mselectLinePaint.setAntiAlias(true);
        mselectLinePaint.setShadowLayer(10.0f, 0.0f, 2.0f, 0xFF000000);





		/* Paint for drawing text */
        mtextLargePaint = new Paint();
        mtextLargePaint.setStyle(Paint.Style.FILL);
        mtextLargePaint.setColor(getResources().getColor(R.color.white));
        mtextLargePaint.setStrokeWidth((float) 2.5
                * getResources().getDisplayMetrics().density);
        mtextLargePaint.setAntiAlias(true);
        mtextLargePaint.setTextSize(40);

        mtextSmallPaint = new Paint();
        mtextSmallPaint.setStyle(Paint.Style.FILL);
        mtextSmallPaint.setColor(getResources().getColor(R.color.white));
        mtextSmallPaint.setStrokeWidth((float) 2.5
                * getResources().getDisplayMetrics().density);
        mtextSmallPaint.setAntiAlias(true);
        mtextSmallPaint.setTextSize(20);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);

        strokeWidth = parentWidth * 8 / 100;
        this.setMeasuredDimension(parentWidth, parentWidth * 5 / 10);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int height = getHeight() * 2;
        int width = getWidth();



        setArcprogress(getProgress());

        mOval = new RectF(strokeWidth, strokeWidth, width - strokeWidth, height
                - strokeWidth);

        canvas.drawArc(mOval, startAngle, getMax(), false, mBasePaint);

		/*
		 * /canvas.drawArc(mOval, startAngle, getArcprogress(), false,
		 * mProgressPaint);
		 */

		/* caluclate the center of rectangle */
        int circlx = (width) / 2;
        int circly = (height) / 2;

        int percentageinDegress = getArcprogress();
        float plainPercentage = percentageinDegress * 100 / 180;

		/* draw the sections */
        //drawtheSectionColor(canvas, mOval, startAngle, circlx, circly);

		/* draw the image indicator */
        //	drawImageIndicator(canvas, getProgress(), circlx, circly - 100);

        if (getMainTextArray() != null)
            drawText(canvas, circlx, circly, height / 2);

    }

    private void drawText(Canvas canvas, int centerx, int centery, int radius) {

        ArrayList<String> mainArry = getMainTextArray();
        ArrayList<String> smallArry = getSmallTextArray();

        canvas.drawText(mainArry.get(0), (int) (centerx - radius / 2),
                (int) (centery - radius / 2 * Math.tan(3.14 / 12)),
                mtextLargePaint);
        canvas.drawText(smallArry.get(0), (int) (centerx - radius / 2),
                (int) (centery - (radius / 2 * Math.tan(3.14 / 12)) / 2),
                mtextSmallPaint);

        canvas.drawText(mainArry.get(1), (int) (centerx - radius / 4),
                (int) (centery - radius / 2), mtextLargePaint);
        canvas.drawText(smallArry.get(1), (int) (centerx - radius / 4),
                (int) (centery - 1.75 * radius / 4), mtextSmallPaint);

        canvas.drawText(mainArry.get(2), (int) (centerx + radius / 4),
                (int) (centery - radius / 2), mtextLargePaint);
        canvas.drawText(smallArry.get(2), (int) (centerx + radius / 4),
                (int) (centery - 1.75 * radius / 4), mtextSmallPaint);

        canvas.drawText(mainArry.get(3), (int) (centerx + radius / 2),
                (int) (centery - radius / 2 * Math.tan(3.14 / 12)),
                mtextLargePaint);
        canvas.drawText(smallArry.get(3), (int) (centerx + radius / 2),
                (int) (centery - (radius / 2 * Math.tan(3.14 / 12)) / 2),
                mtextSmallPaint);

    }

    private void drawtheSectionColor(Canvas canvas, RectF rect,
                                     float startAngle, int centerX, int centerY) {
        // TODO Auto-generated method stub
        int option = 0;
        int progress = getProgress();

        if (progress < 45)
            option = 1;
        else if (progress < 90)
            option = 2;
        else if (progress < 135)
            option = 3;
        else
            option = 4;
        switch (option) {
            case 1:
                canvas.drawArc(rect, 180, 180, false, mSectionPaint4);
                canvas.drawArc(rect, 135, startAngle, false, mSectionPaint3);
                canvas.drawArc(rect, 90, startAngle, false, mSectionPaint2);
                canvas.drawArc(rect, 45, startAngle, false, mselectLinePaint);
                canvas.drawArc(rect, 45, startAngle - sectionGap, false,
                        mSectionPaint1);

                //	canvas.drawArc(rect, 180, 45-sectionGap, false, mselectSectionPaint);


                break;
            case 2:
                canvas.drawArc(rect, 180, 180, false, mSectionPaint4);
                canvas.drawArc(rect, 135, startAngle, false, mSectionPaint3);
			/* drawLines */
                canvas.drawArc(rect, 90, startAngle, false, mselectLinePaint);
                canvas.drawArc(rect, 90, startAngle - sectionGap, false,
                        mSectionPaint2);
                canvas.drawArc(rect, 45, startAngle + sectionGap, false,
                        mselectLinePaint);
                canvas.drawArc(rect, 45, startAngle, false, mSectionPaint1);

                //canvas.drawArc(rect, 225+sectionGap, 45-2*sectionGap, false, mselectSectionPaint);
                break;
            case 3:
                canvas.drawArc(rect, 180, 180, false, mSectionPaint4);
			/* drawLines */
                canvas.drawArc(rect, 135, startAngle, false, mselectLinePaint);
                canvas.drawArc(rect, 135, startAngle - sectionGap, false,
                        mSectionPaint3);
                canvas.drawArc(rect, 90, startAngle + sectionGap, false,
                        mselectLinePaint);
                canvas.drawArc(rect, 90, startAngle, false, mSectionPaint2);
                canvas.drawArc(rect, 45, startAngle, false, mSectionPaint1);

                //	canvas.drawArc(rect, 270+sectionGap, 45-2*sectionGap, false, mselectSectionPaint);
                break;
            case 4:
			/* drawLines */
                canvas.drawArc(rect, 180, startAngle - sectionGap, false,
                        mselectLinePaint);
                canvas.drawArc(rect, 180, startAngle, false, mSectionPaint4);
                canvas.drawArc(rect, 135, startAngle + sectionGap, false,
                        mselectLinePaint);
                canvas.drawArc(rect, 135, startAngle, false, mSectionPaint3);
                canvas.drawArc(rect, 90, startAngle, false, mSectionPaint2);
                canvas.drawArc(rect, 45, startAngle, false, mSectionPaint1);

                //canvas.drawArc(rect, 315+sectionGap, 45-sectionGap, false, mselectSectionPaint);
                break;
        }

        if (sectionSelctedlistner != null)
            sectionSelctedlistner.sectionSelected(option);
    }

    public void drawImageIndicator(Canvas canvas, int progress, int left,
                                   int top) {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.arrow);

        Rect rect = new Rect(left, 100, bitmap.getWidth(), bitmap.getHeight());
        Matrix matrix = new Matrix();
        float px = rect.exactCenterX();
        float py = rect.exactCenterY();
        matrix.postTranslate(-bitmap.getWidth() / 2, -bitmap.getHeight() / 2);
        matrix.postRotate(progress);
        matrix.postTranslate(left, top);
        canvas.drawBitmap(bitmap, matrix, null);
        matrix.reset();
    }

    public int getArcprogress() {

        return arcprogress;

    }

    public void setArcprogress(int arcprogress) {
        this.arcprogress = arcprogress;
    }

    public SectionSelectedListner getSectionSelctedlistner() {
        return sectionSelctedlistner;
    }

    public void setSectionSelctedlistner(
            SectionSelectedListner sectionSelctedlistner) {
        this.sectionSelctedlistner = sectionSelctedlistner;
    }

    public ArrayList<String> getSmallTextArray() {
        return smallTextArray;
    }

    public void setSmallTextArray(ArrayList<String> smallTextArray) {
        this.smallTextArray = smallTextArray;
    }

    public ArrayList<String> getMainTextArray() {
        return mainTextArray;
    }

    public void setMainTextArray(ArrayList<String> mainTextArray) {
        this.mainTextArray = mainTextArray;
    }

}



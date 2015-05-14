package praful.com.kidsonwheels.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import praful.com.kidsonwheels.R;

/**
 * Created by prafulmantale on 5/13/15.
 */
public class EditablePhoto  extends View {

    private Bitmap image;
    private Drawable placeHolder;
    private Bitmap framedPhoto;
    private Paint paint;

    public EditablePhoto(Context context) {
        super(context);
        init();
    }

    public EditablePhoto(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditablePhoto(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        placeHolder = getResources().getDrawable(R.drawable.three);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(placeHolder == null && image == null){
            return;
        }
        if(framedPhoto == null){
            createFramedPhoto(Math.min(getWidth(), getHeight()));
        }
        canvas.drawBitmap(framedPhoto, 0, 0, null);
    }

    private void createFramedPhoto(int size) {
        Drawable imageDrawable = (image != null) ? new BitmapDrawable(image) : placeHolder;
        Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        RectF outerRect = new RectF(0, 0, size, size);
        float outerRadius = size / 18;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        canvas.drawRoundRect(outerRect, outerRadius, outerRadius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        imageDrawable.setBounds(0, 0, size, size);
        canvas.saveLayer(outerRect, paint, Canvas.ALL_SAVE_FLAG);
        imageDrawable.draw(canvas);
        canvas.restore();
        framedPhoto = output;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int measuredHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        int min = Math.min(measuredHeight, measuredWidth);
        setMeasuredDimension(min, min);
    }
}
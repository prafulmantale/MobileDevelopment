package prafulmantale.praful.com.positionscale;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by prafulmantale on 11/18/14.
 */
public class PlasticLinearLayout extends LinearLayout {

    private Path shinyPath;
    private Paint shinyPaint;

    public PlasticLinearLayout(Context context) {
        super(context);
        init();
    }

    public PlasticLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlasticLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setBackgroundResource(R.drawable.plastic_background);
        shinyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        createShinyPath();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        if(shinyPath == null){
            createShinyPath();
        }

        canvas.drawPath(shinyPath, shinyPaint);

        super.dispatchDraw(canvas);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        shinyPath = null;
    }

    private void createShinyPath(){
        shinyPath = new Path();
        int width = getWidth();
        int height = (int) (0.85 * getHeight());

        shinyPath.moveTo(0, 0);
        shinyPath.lineTo(0, width);
        shinyPath.lineTo(width, height);
        shinyPath.close();

        shinyPaint.setShader(new LinearGradient(0, 0, 0, height, 0x66ffffff, 0x10ffffff, Shader.TileMode.CLAMP));
    }

}

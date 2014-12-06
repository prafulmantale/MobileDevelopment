package prafulmantale.praful.com.yaym.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import prafulmantale.praful.com.yaym.R;

/**
 * Created by prafulmantale on 12/5/14.
 */
public class TypefacedButton extends Button {

    public TypefacedButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        if(isInEditMode()){
            return;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TypefacedTextView);

        if(typedArray != null) {

            String fontname = typedArray.getString(R.styleable.TypefacedTextView_typeface);
            typedArray.recycle();

            setTypeface(Typeface.createFromAsset(context.getAssets(), fontname));
        }
    }
}

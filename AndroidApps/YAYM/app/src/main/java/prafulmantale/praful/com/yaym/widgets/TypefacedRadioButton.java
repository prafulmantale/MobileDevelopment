package prafulmantale.praful.com.yaym.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;

import prafulmantale.praful.com.yaym.R;

/**
 * Created by prafulmantale on 12/5/14.
 */
public class TypefacedRadioButton extends RadioButton {

    public TypefacedRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        if(isInEditMode()){
            return;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TypefacedTextView);

        String fontName = typedArray.getString(R.styleable.TypefacedTextView_typeface);

        if(typedArray != null) {
            typedArray.recycle();
        }

        if(fontName != null){
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontName);
            setTypeface(typeface);
        }
    }
}

package prafulmantale.praful.com.yaym.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import prafulmantale.praful.com.yaym.R;

/**
 * Created by prafulmantale on 12/5/14.
 */
public class TypefacedEditText extends EditText{

    public TypefacedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        if(isInEditMode()){
            return;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TypefacedTextView);
        String fontname = typedArray.getString(R.styleable.TypefacedTextView_typeface);

        if(typedArray != null){
            typedArray.recycle();
        }

        if(fontname != null){
            setTypeface(Typeface.createFromAsset(context.getAssets(), fontname));
        }
    }
}

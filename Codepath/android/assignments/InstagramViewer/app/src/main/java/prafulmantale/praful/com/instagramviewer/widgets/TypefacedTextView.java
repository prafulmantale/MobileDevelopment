package prafulmantale.praful.com.instagramviewer.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import prafulmantale.praful.com.instagramviewer.R;

/**
 * Created by prafulmantale on 9/17/14.
 */
public class TypefacedTextView extends TextView {

    public TypefacedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if(isInEditMode()){
            return;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TypefacedTextView);
        String fontName = typedArray.getString(R.styleable.TypefacedTextView_typeface);
        typedArray.recycle();

        if(fontName != null){
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontName);
            setTypeface(typeface);
        }
    }
}

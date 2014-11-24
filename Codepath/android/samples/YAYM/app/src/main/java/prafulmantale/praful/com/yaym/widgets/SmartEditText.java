package prafulmantale.praful.com.yaym.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import prafulmantale.praful.com.yaym.R;

/**
 * Created by prafulmantale on 11/24/14.
 */
public class SmartEditText extends RelativeLayout {

    private EditText editText;
    private Button button;

    public SmartEditText(Context context) {
        super(context);
        initViews();
    }

    public SmartEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public SmartEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }

    private void initViews(){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.smart_edit_text, this, true);

        editText = (EditText)findViewById(R.id.etSmartEditText);
        button = (Button)findViewById(R.id.btnClearContent);
        button.setVisibility(INVISIBLE);

        setupListeners();
    }

    private void setupListeners(){

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    button.setVisibility(VISIBLE);
                }
                else{
                    button.setVisibility(INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public Editable getText(){
        return editText.getText();
    }

    public void setText(String text){
        editText.setText(text);
    }

    public void setTypeface(Typeface tf){
        editText.setTypeface(tf);
    }
}

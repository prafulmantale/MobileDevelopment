package prafulmantale.simpletodolist.dialogs;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import prafulmantale.simpletodolist.R;


public class EditItemDialog extends DialogFragment implements TextView.OnEditorActionListener{

    private EditText editText;
    private int position;
    private String itemText;
    private Button saveButton;
    private Button cancelButton;

    public EditItemDialog(){

    }

    public static EditItemDialog newInstance(String title, String itemText, int position){
        EditItemDialog frag = new EditItemDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("item", itemText);
        args.putInt("position", position);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_item, container);

        editText = (EditText)view.findViewById(R.id.eEditedItem);
        saveButton = (Button)view.findViewById(R.id.saveButton);
        cancelButton = (Button)view.findViewById(R.id.cancelButton);

        String title = getArguments().getString("title", "Enter new value");
        getDialog().setTitle(title);

        itemText = getArguments().getString("item");
        editText.setText(itemText);

        position = getArguments().getInt("position");

        editText.requestFocus();

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        editText.setOnEditorActionListener(this);

        setUpListeners();

        return view;
    }


    private void setUpListeners(){

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getEditedText() == null || getEditedText().trim().isEmpty()){

                }
                onEditorAction(editText, EditorInfo.IME_ACTION_DONE, null);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditorAction(null, EditorInfo.IME_ACTION_NONE, null);
            }
        });
    }


    private String getEditedText(){
        return editText.getText().toString();
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

        EditItemDialogListener listener = (EditItemDialogListener)getActivity();

        if(EditorInfo.IME_ACTION_DONE == actionId && getEditedText() != null && getEditedText().trim().isEmpty() == false){
            listener.onFinishEditDialog(getEditedText(), position);
            dismiss();
            return true;
        }
        else{
            listener.onFinishEditDialog(null, -1);
            dismiss();
        }
        return false;
    }

    public interface EditItemDialogListener{
        void onFinishEditDialog(String itemText, int position);
    }
}

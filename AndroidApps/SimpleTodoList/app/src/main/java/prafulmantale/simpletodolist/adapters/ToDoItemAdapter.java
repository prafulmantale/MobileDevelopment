package prafulmantale.simpletodolist.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.AvoidXfermode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import prafulmantale.simpletodolist.R;
import prafulmantale.simpletodolist.dialogs.EditItemDialog;
import prafulmantale.simpletodolist.models.ToDoItem;

/**
 * Created by prafulmantale on 7/31/14.
 */
public class ToDoItemAdapter extends ArrayAdapter<ToDoItem> implements CheckBox.OnEditorActionListener{

    private final List<ToDoItem> list;
    private final Activity context;

    public ToDoItemAdapter(Activity context, List<ToDoItem> list) {
        super(context, R.layout.activity_to_do_item_row, list);

        this.list = list;
        this.context = context;
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        EditItemDialog.EditItemDialogListener listener = (EditItemDialog.EditItemDialogListener)context;
        listener.onFinishEditDialog(textView.getText().toString(), i);
        return false;
    }

    static class ViewHolder{
        protected EditText textView;
        protected CheckBox checkBox;
        protected ImageView imageView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;

        if(convertView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.activity_to_do_item_row, null);

            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.textView = (EditText)view.findViewById(R.id.itemLabel);
            viewHolder.checkBox = (CheckBox)view.findViewById(R.id.itemCheck);
            viewHolder.imageView = (ImageView)view.findViewById(R.id.imageView);


            view.setTag(viewHolder);
            viewHolder.checkBox.setTag(list.get(position));
            viewHolder.imageView.setTag(list.get(position));

            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    ToDoItem toDoItem = (ToDoItem)viewHolder.checkBox.getTag();
                    toDoItem.setCompleted(compoundButton.isChecked());
                    onEditorAction(viewHolder.textView, position, null);
                }
            });

            viewHolder.textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {

                    if(!hasFocus){
                        viewHolder.imageView.setVisibility(View.INVISIBLE);
                        ToDoItem toDoItem = (ToDoItem)viewHolder.checkBox.getTag();
                        String newVal = viewHolder.textView.getText().toString();
                         if(newVal.equals(toDoItem.getItem()) == false) {
                             toDoItem.setItem(newVal);
                             onEditorAction(viewHolder.textView, position, null);
                         }
                    }
                    else{
                        viewHolder.imageView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        else{
            view = convertView;

            ((ViewHolder)view.getTag()).checkBox.setTag(list.get(position));
        }

        ViewHolder holder = (ViewHolder)view.getTag();
        holder.textView.setText(list.get(position).getItem());
        holder.checkBox.setChecked(list.get(position).isCompleted());

        return view;
    }
}

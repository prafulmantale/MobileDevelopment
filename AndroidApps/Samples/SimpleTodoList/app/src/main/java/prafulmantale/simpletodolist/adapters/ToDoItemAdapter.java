package prafulmantale.simpletodolist.adapters;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.graphics.AvoidXfermode;
import android.graphics.Color;
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
        protected TextView duedate;
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
            viewHolder.duedate = (TextView)view.findViewById(R.id.duedate);


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

                    ToDoItem toDoItem = (ToDoItem)viewHolder.checkBox.getTag();

                    if(!hasFocus){
                        viewHolder.imageView.setVisibility(View.INVISIBLE);

                        String newVal = viewHolder.textView.getText().toString();
                         if(newVal.equals(toDoItem.getItem()) == false) {
                             toDoItem.setItem(newVal);
                             onEditorAction(viewHolder.textView, position, null);
                         }
                    }
                    else{
                        if(!toDoItem.isCompleted()) {
                            viewHolder.imageView.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });

            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ItemDetailsListener listener = (ItemDetailsListener)context;
                    listener.OnItemDetailsRequested(position);
                }
            });
        }
        else{
            view = convertView;

            ((ViewHolder)view.getTag()).checkBox.setTag(list.get(position));
        }

        ViewHolder holder = (ViewHolder)view.getTag();
        ToDoItem toDoItem = list.get(position);
        holder.textView.setText(toDoItem.getItem());
        holder.checkBox.setChecked(toDoItem.isCompleted());
        if(toDoItem.isDueDateConfigured() == false) {
            holder.duedate.setText(R.string.no_due_date_configured);
        }
        else{
            holder.duedate.setText(toDoItem.getDateTime());
        }

        holder.textView.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        if(toDoItem.getPriority() == ToDoItem.ItemPriority.First){

            view.setBackgroundColor( context.getResources().getColor(android.R.color.holo_blue_light));
        }

        if(toDoItem.getPriority() == ToDoItem.ItemPriority.Second){
            view.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
        }

        if(toDoItem.getPriority() == ToDoItem.ItemPriority.Third){
            view.setBackgroundColor(context.getResources().getColor(android.R.color.holo_orange_light));
        }

        if(toDoItem.getPriority() == ToDoItem.ItemPriority.None){
            view.setBackgroundColor(context.getResources().getColor(android.R.color.white));
        }

        return view;
    }

    public interface ItemDetailsListener{
        public void OnItemDetailsRequested(int position);
    }
}

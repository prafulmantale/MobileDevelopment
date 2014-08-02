package prafulmantale.simpletodolist.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.AvoidXfermode;
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
import prafulmantale.simpletodolist.models.ToDoItem;

/**
 * Created by prafulmantale on 7/31/14.
 */
public class ToDoItemAdapter extends ArrayAdapter<ToDoItem> {

    private final List<ToDoItem> list;
    private final Activity context;

    public ToDoItemAdapter(Activity context, List<ToDoItem> list) {
        super(context, R.layout.activity_to_do_item_row, list);

        this.list = list;
        this.context = context;
    }

    static class ViewHolder{
        protected EditText textView;
        protected CheckBox checkBox;
        protected ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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

//            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                    ToDoItem toDoItem = (ToDoItem)viewHolder.checkBox.getTag();
//                    toDoItem.setSelected(compoundButton.isChecked());
//                }
//            });
        }
        else{
            view = convertView;

            ((ViewHolder)view.getTag()).checkBox.setTag(list.get(position));
        }

        ViewHolder holder = (ViewHolder)view.getTag();
        holder.textView.setText(list.get(position).getItem());
        holder.checkBox.setChecked(list.get(position).isSelected());

        return view;
    }
}

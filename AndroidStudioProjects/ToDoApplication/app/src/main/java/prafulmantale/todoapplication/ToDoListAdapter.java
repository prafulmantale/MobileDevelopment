package prafulmantale.todoapplication;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prafulmantale on 7/29/14.
 */
public class ToDoListAdapter extends ArrayAdapter<ToDoListItem>{

    public ToDoListAdapter(Context context, List<ToDoListItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ToDoListItem item = getItem(position);

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_list_item, parent, false);
        }

        EditText itemview = (EditText)convertView.findViewById(R.id.todoitem);
        itemview.setText(item.getItem());
        ImageView imageView = (ImageView)convertView.findViewById(R.id.deletebutton);
        ImageView ediview = (ImageView)convertView.findViewById(R.id.savebutton);
        convertView.setTag(item);
        imageView.setTag(item);
        ediview.setTag(itemview);
        itemview.setTag(item);

        //imageView.setBackground();

        return convertView;
    }
}
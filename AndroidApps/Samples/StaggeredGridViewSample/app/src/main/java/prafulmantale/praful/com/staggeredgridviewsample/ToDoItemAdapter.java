package prafulmantale.praful.com.staggeredgridviewsample;

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



/**
 * Created by prafulmantale on 7/31/14.
 */
public class ToDoItemAdapter extends ArrayAdapter<ToDoItem>{

    private final List<ToDoItem> list;
    private final Activity context;

    public ToDoItemAdapter(Activity context, List<ToDoItem> list) {
        super(context, R.layout.activity_to_do_item_row, list);

        this.list = list;
        this.context = context;
    }



    static class ViewHolder{
        protected EditText textView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;

        if(convertView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.activity_to_do_item_row, null);

            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.textView = (EditText)view.findViewById(R.id.itemLabel);

            view.setTag(viewHolder);
        }
        else{
            view = convertView;
        }

        ViewHolder holder = (ViewHolder)view.getTag();
        ToDoItem toDoItem = list.get(position);
        holder.textView.setText(toDoItem.getItem());

        return view;
    }


}

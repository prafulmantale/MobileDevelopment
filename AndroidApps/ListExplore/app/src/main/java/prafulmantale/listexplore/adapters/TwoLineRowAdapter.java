package prafulmantale.listexplore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.listexplore.R;
import prafulmantale.listexplore.models.TwoLineRowModel;

/**
 * Created by prafulmantale on 8/9/14.
 */
public class TwoLineRowAdapter extends ArrayAdapter<TwoLineRowModel> {

    private Context context;
    private List<TwoLineRowModel> list;

    public TwoLineRowAdapter(Context context, List<TwoLineRowModel> objects) {
        super(context, R.layout.activity_two_line_list_row, objects);

        this.context = context;
        this.list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_two_line_list_row, parent, false);

        TextView textView1 = (TextView)rowView.findViewById(R.id.firstline);
        TextView textView2 = (TextView)rowView.findViewById(R.id.secondline);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.imageView);

        textView1.setText(list.get(position).getLine1());
        textView2.setText(list.get(position).getLine2());
        imageView.setImageResource(R.drawable.ic_launcher);

        return rowView;
    }
}

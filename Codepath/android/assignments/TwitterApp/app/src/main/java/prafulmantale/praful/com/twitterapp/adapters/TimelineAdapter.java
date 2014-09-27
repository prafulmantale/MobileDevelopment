package prafulmantale.praful.com.twitterapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import prafulmantale.praful.com.twitterapp.R;

/**
 * Created by prafulmantale on 9/26/14.
 */
public class TimelineAdapter extends ArrayAdapter<String> {

    public TimelineAdapter(Context context, List<String> objects) {

        super(context, R.layout.item_timeline_row, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_timeline_row, parent, false);
        }


        return convertView;
    }
}

package prafulmantale.praful.com.imagefinder.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import prafulmantale.praful.com.imagefinder.R;
import prafulmantale.praful.com.imagefinder.enums.ImageColor;

/**
 * Created by prafulmantale on 9/24/14.
 */
public class SpinnerColorAdapter extends ArrayAdapter<String> {

    private static class ViewHolder{
        Button button;
        TextView tvText;
    }

    public SpinnerColorAdapter(Context context, List<String> objects) {
        super(context, R.layout.item_spinner_dropdown_ex, objects);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent){

        String color = getItem(position);
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_spinner_dropdown_ex, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.button = (Button)convertView.findViewById(R.id.tvSpinnerButton);
            viewHolder.tvText = (TextView)convertView.findViewById(R.id.tvSpinnerItem);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.tvText.setText(ImageColor.values()[position].name());

        if(position == 0){
             viewHolder.button.setBackgroundColor(Color.TRANSPARENT);
        }


        if(position == 1){
            viewHolder.button.setBackgroundColor(Color.BLACK);
        }

        if(position == 2){
            viewHolder.button.setBackgroundColor(Color.BLUE);
        }

        return convertView;
    }
}

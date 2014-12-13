package prafulmantale.praful.com.yaym.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import prafulmantale.praful.com.yaym.R;

/**
 * Created by prafulmantale on 12/12/14.
 */
public class CcyPairsSettingsAdapter extends ArrayAdapter<String>{

    private final boolean forSelected;
    private Context context;

    private  class ViewHolder{
        ImageView ivAddRemove;
        TextView tvCurrencyPair;

        void init(View view){

            ivAddRemove = (ImageView)view.findViewById(R.id.ivAddRemove);
            tvCurrencyPair = (TextView)view.findViewById(R.id.tvCcyPair);

            if(forSelected){
                ivAddRemove.setImageResource(R.drawable.ic_action_content_remove_circle_outline);
            }


        }

    }

    public CcyPairsSettingsAdapter(Context context, String[] objects, boolean forSelected) {
        super(context, R.layout.item_ccy_pair_settings_row, objects);
        this.context = context;
        this.forSelected = forSelected;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_ccy_pair_settings_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(convertView);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
            viewHolder.tvCurrencyPair.setText(getItem(position));
        }

        viewHolder.ivAddRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(getItem(position));
                //Remove item from the selected/available
            }
        });

        return convertView;
    }
}

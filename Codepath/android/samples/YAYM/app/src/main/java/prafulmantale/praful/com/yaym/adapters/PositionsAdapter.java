package prafulmantale.praful.com.yaym.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class PositionsAdapter extends ArrayAdapter<RWPositionSnapshot> {

    public PositionsAdapter(Context context, List<RWPositionSnapshot> objects) {
        super(context, R.layout.item_positions_row, objects);
    }

    private class ViewHolder{
        TextView tvCcyPair;
        TextView tvRate;
        TextView tvPosition;
        TextView tvUnrealizedPnL;

        protected void init(View convertView){
            tvCcyPair = (TextView)convertView.findViewById(R.id.tvCurrencyPair);
            tvRate = (TextView)convertView.findViewById(R.id.tvRate);
            tvPosition = (TextView)convertView.findViewById(R.id.tvPosition);
            tvUnrealizedPnL = (TextView)convertView.findViewById(R.id.tvUnrealizedPnl);
        }

        protected void populateData(RWPositionSnapshot snapshot){
            tvCcyPair.setText(snapshot.getCurrencyPair());
            tvRate.setText(String.valueOf(snapshot.getMidRate()));
            tvPosition.setText(String.valueOf(snapshot.getAccumulation()));
            tvUnrealizedPnL.setText(String.valueOf(snapshot.getUnrealizedPnL()));
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RWPositionSnapshot snapshot = getItem(position);
        ViewHolder viewHolder = null;

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_positions_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.populateData(snapshot);

        return convertView;
    }
}

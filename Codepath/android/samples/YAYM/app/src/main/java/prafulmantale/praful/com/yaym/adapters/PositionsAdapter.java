package prafulmantale.praful.com.yaym.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class PositionsAdapter extends ArrayAdapter<RWPositionSnapshot> {

    private int mLastPosition;
    private boolean disableAnimation = true;

    public PositionsAdapter(Context context, List<RWPositionSnapshot> objects) {
        super(context, R.layout.item_positions_row, objects);

    }

    private class ViewHolder{
        TextView tvCcyPair;
        TextView tvBigFigure;
        TextView tvPips;
        TextView tvHalfPips;
        View ccyPairRate;
        TextView tvUnrealizedPnL;



        protected void init(View convertView){
            ccyPairRate = convertView.findViewById(R.id.itemCcyPairRate);

            tvCcyPair = (TextView)ccyPairRate.findViewById(R.id.tvCcyPair_ratecontrol);
            tvCcyPair.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf"));
            tvBigFigure = (TextView)ccyPairRate.findViewById(R.id.tvRate_BigFigure);
            tvBigFigure.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Medium.ttf"));
            tvPips = (TextView)ccyPairRate.findViewById(R.id.tvRate_Pips);
            tvPips.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Medium.ttf"));
            tvHalfPips = (TextView)ccyPairRate.findViewById(R.id.tvRate_halfPips);
            tvHalfPips.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Medium.ttf"));

            tvUnrealizedPnL = (TextView)convertView.findViewById(R.id.tvUnrealizedPnl);
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        protected void populateData(RWPositionSnapshot snapshot){
            tvCcyPair.setText(snapshot.getCurrencyPair());
            tvBigFigure.setText(String.valueOf(snapshot.getBigFigure()));
            tvPips.setText(snapshot.getPips());
            tvHalfPips.setText(snapshot.getHalfPips());

            tvUnrealizedPnL.setText(String.valueOf(snapshot.getUnrealizedPnL()));

           // sbPositionsStatusLong.setThumb(writeOnDrawable(R.drawable.pointer, "0.0"));

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
        float initialTranslation = (mLastPosition <= position ? 500f : -500f);

        if(disableAnimation == false) {
            convertView.setTranslationY(initialTranslation);
            convertView.animate()
                    .setInterpolator(new DecelerateInterpolator(1.0f))
                    .translationY(0f)
                    .setDuration(300l)
                    .setListener(null);
        }

        // Keep track of the last position we loaded
        mLastPosition = position;

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        try {
            disableAnimation = true;
            mLastPosition = 0;
            super.notifyDataSetChanged();

        }
        finally {
            disableAnimation = false;
        }

    }

}

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
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.caches.RulesCache;
import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;
import prafulmantale.praful.com.yaym.models.RiskRules;
import prafulmantale.praful.com.yaym.widgets.GaugeViewEx;
import prafulmantale.praful.com.yaym.widgets.PositionStatusView;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class PositionsAdapter extends ArrayAdapter<RWPositionSnapshot> {

    private int mLastPosition;

    public PositionsAdapter(Context context, List<RWPositionSnapshot> objects) {
        super(context, R.layout.item_positions_row, objects);

    }

    private class ViewHolder{
        TextView tvCcyPair;
        TextView tvBigFigure;
        TextView tvPips;
        TextView tvHalfPips;
        View ccyPairRate;

        GaugeViewEx gaugeView;
        PositionStatusView positionStatusView;


        protected void init(View convertView){
            ccyPairRate = convertView.findViewById(R.id.itemCcyPairRate);

            tvCcyPair = (TextView)ccyPairRate.findViewById(R.id.tvCcyPair_ratecontrol);
            tvCcyPair.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf"));
            tvBigFigure = (TextView)ccyPairRate.findViewById(R.id.tvRate_BigFigure);
            tvBigFigure.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf"));
            tvPips = (TextView)ccyPairRate.findViewById(R.id.tvRate_Pips);
            tvPips.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf"));
            tvHalfPips = (TextView)ccyPairRate.findViewById(R.id.tvRate_halfPips);
            tvHalfPips.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf"));

            gaugeView = (GaugeViewEx)convertView.findViewById(R.id.gvPnLGauge);

            positionStatusView = (PositionStatusView)convertView.findViewById(R.id.psView);
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        protected void populateData(RWPositionSnapshot snapshot){

            if(snapshot == null)
                return;

            tvCcyPair.setText(snapshot.getCurrencyPair());
            tvBigFigure.setText(String.valueOf(snapshot.getBigFigure()));
            tvPips.setText(snapshot.getPips());
            tvHalfPips.setText(snapshot.getHalfPips());

            RiskRules rule = RulesCache.getInstance().getRule(snapshot.getCurrencyPair());

            if(rule != null) {
                positionStatusView.setMaxLongPosition((int) rule.getMaxLimitLong(), rule.getMaxLongInThousandsStr());
                positionStatusView.setMaxShortPosition((int) rule.getMaxLimitShort(), rule.getMaxShortInThousandsStr());
                positionStatusView.setCurrentPosition(snapshot.getAccumulation(), snapshot.getAccumulationDisplay());

                gaugeView.setProfitThreshold((int) rule.getProfitThreshold(), rule.getProfitThresholdStr());
                gaugeView.setLossThreshold((int) rule.getLossThreshold(), rule.getLossThresholdStr());
                gaugeView.setCurrentPnL((int) snapshot.getUnrealizedPnL(), snapshot.getUnrealizedPnLDisplay());
            }
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

        convertView.setTranslationY(initialTranslation);
        convertView.animate()
                .setInterpolator(new DecelerateInterpolator(1.0f))
                .translationY(0f)
                .setDuration(300l)
                .setListener(null);

        // Keep track of the last position we loaded
        mLastPosition = position;

        return convertView;
    }


    public void updateViews(ListView lvPositions, List<RWPositionSnapshot> snapshots){

        if(lvPositions == null || snapshots == null){
            return;
        }

        for(RWPositionSnapshot snapshot : snapshots){
            View convertView = getViewForCurrencyPair(lvPositions, snapshot.getCurrencyPair());
            updateViewForCurrencyPair(snapshot, convertView);
        }

        //notifyDataSetChanged();
    }

    private void updateViewForCurrencyPair(RWPositionSnapshot snapshot, View convertView){

        if(convertView == null){
            return;
        }

        ViewHolder viewHolder = (ViewHolder)convertView.getTag();

        viewHolder.populateData(snapshot);

    }

    private View getViewForCurrencyPair(ListView lvPositions, String currencyPair){

        int count = lvPositions.getChildCount();

        for(int i = 0; i < count; i ++){
            View view = lvPositions.getChildAt(i);

            if(view == null){
                continue;
            }

            View ccyPairRate = view.findViewById(R.id.itemCcyPairRate);

            if(ccyPairRate == null){
                continue;
            }

            TextView tvCcyPair = (TextView)ccyPairRate.findViewById(R.id.tvCcyPair_ratecontrol);

            if(tvCcyPair == null){
                continue;
            }

            if(currencyPair.equals(tvCcyPair.getText())){
                return view;
            }
        }

        return null;
    }

}

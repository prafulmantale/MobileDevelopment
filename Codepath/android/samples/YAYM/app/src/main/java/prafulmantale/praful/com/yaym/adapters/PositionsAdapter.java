package prafulmantale.praful.com.yaym.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ClipDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.caches.RulesCache;
import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;
import prafulmantale.praful.com.yaym.models.RiskRules;
import prafulmantale.praful.com.yaym.widgets.GaugeView;

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

        View itemPosition;
        ImageView ivShort;
        ImageView ivLong;

        ClipDrawable ivLongDrawable;
        ClipDrawable ivShortDrawable;

        TextView tvMaxShort;
        TextView tvMaxLong;

        ImageView ivPointer;
        TextView tvPosition;

        FrameLayout frameLong;

        GaugeView gaugeView;
        TextView tvUnPnL;


        protected void init(View convertView){
            ccyPairRate = convertView.findViewById(R.id.itemCcyPairRate);
            itemPosition = convertView.findViewById(R.id.itemPosition);

            tvCcyPair = (TextView)ccyPairRate.findViewById(R.id.tvCcyPair_ratecontrol);
            tvCcyPair.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf"));
            tvBigFigure = (TextView)ccyPairRate.findViewById(R.id.tvRate_BigFigure);
            tvBigFigure.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf"));
            tvPips = (TextView)ccyPairRate.findViewById(R.id.tvRate_Pips);
            tvPips.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf"));
            tvHalfPips = (TextView)ccyPairRate.findViewById(R.id.tvRate_halfPips);
            tvHalfPips.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf"));

            ivShort = (ImageView)itemPosition.findViewById(R.id.iv_position_short);
            ivLong = (ImageView)itemPosition.findViewById(R.id.iv_position_long);

            ivLongDrawable = (ClipDrawable) ivLong.getDrawable();
            ivShortDrawable = (ClipDrawable) ivShort.getDrawable();

            tvMaxShort = (TextView)itemPosition.findViewById(R.id.tvMaxShort);
            tvMaxLong = (TextView)itemPosition.findViewById(R.id.tvMaxLong);

            frameLong = (FrameLayout)itemPosition.findViewById(R.id.frameLong);
            ivPointer = (ImageView)itemPosition.findViewById(R.id.ivPointer);
            tvPosition = (TextView)itemPosition.findViewById(R.id.tvPosition);

            gaugeView = (GaugeView)convertView.findViewById(R.id.gvPnLGauge);
            tvUnPnL = (TextView)convertView.findViewById(R.id.tvUnPnL);

        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        protected void populateData(RWPositionSnapshot snapshot){
            tvCcyPair.setText(snapshot.getCurrencyPair());
            tvBigFigure.setText(String.valueOf(snapshot.getBigFigure()));
            tvPips.setText(snapshot.getPips());
            tvHalfPips.setText(snapshot.getHalfPips());

            setClipLevel(snapshot);


        }

        private void setClipLevel(RWPositionSnapshot snapshot){

            RiskRules rule = RulesCache.getInstance().getRule(snapshot.getCurrencyPair());
            double pnl = 0;
            int level = 0;
            boolean isShort = false;
            if(snapshot.getAccumulation() < 0){
                pnl =  snapshot.getAccumulation()/1000;
                level = (int)((10000/rule.getMaxShortInThousands()) * pnl);
                ivShortDrawable.setLevel(level);
                ivLongDrawable.setLevel(0);
                isShort = true;
            }
            else{
                pnl =  snapshot.getAccumulation()/1000;
                level = (int)((10000/rule.getMaxLongInThousands()) * pnl);
                ivLongDrawable.setLevel(level);
                ivShortDrawable.setLevel(0);
            }

            tvMaxLong.setText("(" + rule.getMaxLongInThousandsStr() + ")");
            tvMaxShort.setText("(" + rule.getMaxShortInThousandsStr() + ")");

            int addMargin = (frameLong.getWidth() * level/10000);
            if(isShort){
                addMargin = addMargin * -1 + 1 - ivPointer.getWidth()/2;
            }
            LinearLayout.LayoutParams  params = (LinearLayout.LayoutParams)ivPointer.getLayoutParams();
            params.leftMargin = frameLong.getLeft() - (ivPointer.getWidth()/2) - 1 + addMargin;
            ivPointer.setLayoutParams(params);

            tvPosition.setText("(" + snapshot.getAccumulationDisplay() + ")");
            LinearLayout.LayoutParams  params2 = (LinearLayout.LayoutParams)tvPosition.getLayoutParams();
            params2.leftMargin = params.leftMargin - (tvPosition.getWidth()/2);
            tvPosition.setLayoutParams(params2);

            gaugeView.setMax((int)(rule.getProfitThreshold()));
            gaugeView.setMin((int)(rule.getLossThreshold()));
            gaugeView.setCurrent((int)(snapshot.getUnrealizedPnL()));
            tvUnPnL.setText(String.valueOf(rule.getLossThreshold()) + "|" + String.valueOf(snapshot.getUnrealizedPnL()) + "|" + String.valueOf(rule.getProfitThreshold()) );
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
//
//        if(disableAnimation == false) {
//            convertView.setTranslationY(initialTranslation);
//            convertView.animate()
//                    .setInterpolator(new DecelerateInterpolator(1.0f))
//                    .translationY(0f)
//                    .setDuration(300l)
//                    .setListener(null);
//        }
//
//        // Keep track of the last position we loaded
//        mLastPosition = position;

        return convertView;
    }


}

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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.caches.RulesCache;
import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;
import prafulmantale.praful.com.yaym.models.RiskRules;

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
            //ivLongDrawable.setLevel(10000);

            ivShortDrawable = (ClipDrawable) ivShort.getDrawable();
            //ivShortDrawable.setLevel(10000);

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
            if(snapshot.getUnrealizedPnL() < 0){
                pnl =  (-1) * snapshot.getUnrealizedPnL();
                int level = (int)((10000/rule.getMaxLimitShort()) * pnl);
                ivShortDrawable.setLevel(level);
            }
            else{
                pnl =  snapshot.getUnrealizedPnL();
                int level = (int)((10000/rule.getMaxLimitLong()) * pnl);
                ivLongDrawable.setLevel(level);
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

package prafulmantale.praful.com.yaym.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import prafulmantale.praful.com.yaym.R;

/**
 * Created by prafulmantale on 12/12/14.
 */
public class CcyPairsSettingsAdapter extends ArrayAdapter<String>{

    private final boolean forSelected;
    private Context context;
    private DecelerateInterpolator interpolator;

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

    public CcyPairsSettingsAdapter(Context context, List<String> objects, boolean forSelected) {
        super(context, R.layout.item_ccy_pair_settings_row, objects);
        this.context = context;
        this.forSelected = forSelected;
        interpolator = new DecelerateInterpolator();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_ccy_pair_settings_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
            viewHolder.tvCurrencyPair.setText(getItem(position));
        }

        viewHolder.tvCurrencyPair.setText(getItem(position));

        viewHolder.ivAddRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(getItem(position));
                //Remove item from the selected/available
            }
        });

        //Gplus

        convertView.setTranslationX(0.0F);
        convertView.setTranslationY(convertView.getHeight());
        convertView.setRotationX(45.0F);
        convertView.setScaleX(0.7F);
        convertView.setScaleY(0.55F);

        ViewPropertyAnimator localViewPropertyAnimator =
                convertView.animate().rotationX(0.0F).rotationY(0.0F).translationX(0).translationY(0).setDuration(500).scaleX(
                        1.0F).scaleY(1.0F).setInterpolator(interpolator);

        localViewPropertyAnimator.setStartDelay(0).start();

        return convertView;
    }
}

package prafulmantale.praful.com.staggeredgridviewsample;

/**
 * Created by prafulmantale on 10/12/14.
 */
import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightTextView;

import org.w3c.dom.Text;

/***
 * ADAPTER
 */

public class SampleAdapter extends ArrayAdapter<String> {

    private static final String TAG = "SampleAdapter";

    static class ViewHolder {
        DynamicHeightTextView txtLineOne;
        TextView txtLineTwo;
        TextView txtLineThree;
    }

    private final LayoutInflater mLayoutInflater;
    private final Random mRandom;
    private final ArrayList<Integer> mBackgroundColors;

    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();

    public SampleAdapter(final Context context, final int textViewResourceId) {
        super(context, textViewResourceId);
        mLayoutInflater = LayoutInflater.from(context);
        mRandom = new Random();
        mBackgroundColors = new ArrayList<Integer>();
        mBackgroundColors.add(R.color.orange);
        mBackgroundColors.add(R.color.green);
        mBackgroundColors.add(R.color.blue);
        mBackgroundColors.add(R.color.yellow);
        mBackgroundColors.add(R.color.grey);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_sample, parent, false);
            vh = new ViewHolder();
            vh.txtLineOne = (DynamicHeightTextView) convertView.findViewById(R.id.txt_line1);
            vh.txtLineTwo = (TextView) convertView.findViewById(R.id.txt_line2);
            vh.txtLineThree = (TextView) convertView.findViewById(R.id.txt_line3);

            convertView.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }

        double positionHeight = getPositionRatio(position);
        int backgroundIndex = position >= mBackgroundColors.size() ?
                position % mBackgroundColors.size() : position;

        convertView.setBackgroundResource(mBackgroundColors.get(backgroundIndex));

        Log.d(TAG, "getView position:" + position + " h:" + positionHeight);

        //vh.txtLineOne.setHeightRatio(positionHeight);
        vh.txtLineOne.setText("Item 1");
        vh.txtLineOne.setPaintFlags(vh.txtLineOne.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        //someTextView.setPaintFlags(someTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

        //vh.txtLineTwo.setHeightRatio(positionHeight);
        vh.txtLineTwo.setText("Item 2");
        //vh.txtLineThree.setHeightRatio(positionHeight);
        vh.txtLineThree.setText("Item 3");

        if(position%2 == 0){
            vh.txtLineThree.setText("Item 3\r\nItem 4");
        }



        return convertView;
    }

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        // if not yet done generate and stash the columns height
        // in our real world scenario this will be determined by
        // some match based on the known height and width of the image
        // and maybe a helpful way to get the column height!
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
            Log.d(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }

    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5 the width
    }
}

package prafulmantale.praful.com.yaym.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.helpers.ReversedSeekBar;
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
        View ccyPairRate;
        TextView tvPosition;
        TextView tvUnrealizedPnL;
        SeekBar sbPositionsStatusLong;
        ReversedSeekBar sbPositionsStatusShort;
        ImageView dummy;
       View customSeekBar;


        protected void init(View convertView){
            ccyPairRate = convertView.findViewById(R.id.itemCcyPairRate);
            customSeekBar = convertView.findViewById(R.id.itemPositionSeekBar);

            tvCcyPair = (TextView)ccyPairRate.findViewById(R.id.tvCcyPair_ratecontrol);
            tvRate = (TextView)ccyPairRate.findViewById(R.id.tvRate_ratecontrol);
            tvPosition = (TextView)convertView.findViewById(R.id.tvPosition);
            tvUnrealizedPnL = (TextView)convertView.findViewById(R.id.tvUnrealizedPnl);
            sbPositionsStatusLong = (SeekBar)customSeekBar.findViewById(R.id.sbPositionsStatusLong);
            sbPositionsStatusShort = (ReversedSeekBar)customSeekBar.findViewById(R.id.sbPositionsStatusShort);
            dummy = (ImageView)customSeekBar.findViewById(R.id.imageView);
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        protected void populateData(RWPositionSnapshot snapshot){
            tvCcyPair.setText(snapshot.getCurrencyPair());
            tvRate.setText(String.valueOf(snapshot.getMidRate()));
            tvPosition.setText(String.valueOf(snapshot.getAccumulation()));
            tvUnrealizedPnL.setText(String.valueOf(snapshot.getUnrealizedPnL()));
            sbPositionsStatusLong.setProgress(10);
            sbPositionsStatusShort.setProgress(80);

           dummy.setX( 1);

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


        viewHolder.sbPositionsStatusLong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int originalProgress;

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                originalProgress = seekBar.getProgress();
            }

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onProgressChanged(SeekBar seekBar, int arg1, boolean fromUser) {
                if (fromUser == true) {
                    //seekBar.setProgress(originalProgress);
                }


//                viewHolder.dummy.setLeft(seekBar.getThumb().getBounds().left);
//                seekBar.getThumb().setBounds(seekBar.getThumb().getBounds().left, seekBar.getTop() - 16 - seekBar.getThumb().getIntrinsicHeight(), seekBar.getThumb().getBounds().right, seekBar.getTop() - 16);
            }
        });

        viewHolder.sbPositionsStatusShort.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int originalProgress;

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                originalProgress = seekBar.getProgress();
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int arg1, boolean fromUser) {

                if (fromUser == true) {
                    //seekBar.setProgress(originalProgress);

//                    mDistance.setText("" + AppConstants.getDistance() + " miles");
//                    //Get the thumb bound and get its left value
//                    int x = mSeekBar.getThumb().getBounds().left;
//                    //set the left value to textview x value
//                    mDistance.setX(x);
                }
            }
        });

        return convertView;
    }


    public  BitmapDrawable writeOnDrawable(int drawableId, String text){

        Bitmap bm = BitmapFactory.decodeResource( getContext().getResources(), drawableId).copy(Bitmap.Config.ARGB_8888, true);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);

        Canvas canvas = new Canvas(bm);
        canvas.drawText(text, 0, bm.getHeight() + bm.getHeight()/2, paint);

        return new BitmapDrawable(bm);
    }


}

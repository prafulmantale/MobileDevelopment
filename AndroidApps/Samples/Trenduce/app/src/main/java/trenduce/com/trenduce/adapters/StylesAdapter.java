package trenduce.com.trenduce.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import trenduce.com.trenduce.R;
import trenduce.com.trenduce.interfaces.StylesViewClickListener;
import trenduce.com.trenduce.model.Style;

/**
 * Created by prafulmantale on 3/7/15.
 */
public class StylesAdapter extends ArrayAdapter<Style> {

    public StylesAdapter(Context context, List<Style> objects, StylesViewClickListener listener) {

        super(context, R.layout.item_style, objects);


        this.listener = listener;

    }


    private class ViewHolder {

        private ImageView ivStyleImage;
        private TextView tvTitle;
        private TextView tvUserName;
        private TextView tvLikesCount;
        private TextView tvCommentsCount;
        private ImageView ivLike;

        void init(View convertView){
            ivStyleImage = (ImageView)convertView.findViewById(R.id.ivStyleImage);
            tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
            tvUserName = (TextView)convertView.findViewById(R.id.tvUserName);
            tvLikesCount = (TextView)convertView.findViewById(R.id.tvLikesCount);
            tvCommentsCount = (TextView)convertView.findViewById(R.id.tvCommentsCount);
            ivLike = (ImageView)convertView.findViewById(R.id.ivLikes);
        }

        void populateViews(Style style){
            tvTitle.setText(style.getTitle());
            tvUserName.setText(style.getCreatedBy());
            tvLikesCount.setText(style.getLikesCount());
            tvCommentsCount.setText(style.getCommentsCount());


            ImageLoader.getInstance().displayImage(style.getImageUrl(), ivStyleImage);

        }
    }

    private int mLastPosition;
    private DecelerateInterpolator interpolator;

    private StylesViewClickListener listener;

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder viewHolder = null;
        final Style style = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_style, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.init(convertView);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.populateViews(style);

        viewHolder.tvCommentsCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(listener != null){
                    listener.OnCommentsRequested(style.getId());
                }
            }
        });

        viewHolder.ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(listener != null){
                    listener.OnLikeStyle(style.getId());
                }

            }
        });

        float initialTranslation = (mLastPosition <= position ? 500f : -500f);

        convertView.setTranslationY(initialTranslation);
        convertView.animate()
                .setInterpolator(new DecelerateInterpolator(1.0f))
                .translationY(0f)
                .setDuration(300l)
                .setListener(null);

        // Keep track of the last position we loaded
        mLastPosition = position;


        //Gplus

//        convertView.setTranslationX(0.0F);
//        convertView.setTranslationY(convertView.getHeight());
//        convertView.setRotationX(45.0F);
//        convertView.setScaleX(0.7F);
//        convertView.setScaleY(0.55F);
//
//        ViewPropertyAnimator localViewPropertyAnimator =
//                convertView.animate().rotationX(0.0F).rotationY(0.0F).translationX(0).translationY(0).setDuration(500).scaleX(
//                        1.0F).scaleY(1.0F).setInterpolator(interpolator);
//
//        localViewPropertyAnimator.setStartDelay(0).start();

        return convertView;
    }
}

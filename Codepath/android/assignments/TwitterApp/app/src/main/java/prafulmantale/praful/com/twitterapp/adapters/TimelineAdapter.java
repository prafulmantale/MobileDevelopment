package prafulmantale.praful.com.twitterapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.List;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.helpers.Utils;
import prafulmantale.praful.com.twitterapp.interfaces.ViewsClickListener;
import prafulmantale.praful.com.twitterapp.models.Tweet;

/**
 * Created by prafulmantale on 9/26/14.
 */
public class TimelineAdapter extends ArrayAdapter<Tweet> {

    private Context context;
    private ViewsClickListener listener;

    private class ViewHolder{

        private ImageView ivProfileImage;
        private TextView tvUserName;
        private TextView tvScreenName;
        private TextView tvCreatedTime;
        private TextView tvTweet;
        private TextView tvReply;
        private TextView tvRetweetsCount;
        private TextView tvFavoritesCount;
        private ImageView ivFollow;


        void init(View convertView){
            ivProfileImage = (ImageView)convertView.findViewById(R.id.ivProfilePicture_timeline);
            tvUserName = (TextView)convertView.findViewById(R.id.tvUserName_timeline);
            tvScreenName = (TextView)convertView.findViewById(R.id.tvUserHandle_timeline);
            tvCreatedTime = (TextView)convertView.findViewById(R.id.tvElapsedTime_timeline);
            tvTweet = (TextView)convertView.findViewById(R.id.tvTweet_timeline);
            tvReply = (TextView)convertView.findViewById(R.id.tvReply_timeline);
            tvRetweetsCount = (TextView)convertView.findViewById(R.id.tvRetweets_timeline);
            tvFavoritesCount = (TextView)convertView.findViewById(R.id.tvFavorites_timeline);
            ivFollow = (ImageView)convertView.findViewById(R.id.ivFollow_timeline);
        }

        void populateViews(Tweet tweet){
            tvUserName.setText(tweet.getUser().getName());
            tvScreenName.setText("@" + tweet.getUser().getScreenName());
            tvTweet.setText(Html.fromHtml(tweet.getFormattedBody()));
            tvTweet.setMovementMethod(LinkMovementMethod.getInstance());
            tvCreatedTime.setText(tweet.getRelativeTimeAgo());
            tvRetweetsCount.setText(tweet.getRetweet_count());
            tvFavoritesCount.setText(tweet.getFavorite_count());

            //ImageLoader.getInstance().displayImage(tweet.getUser().getProfileImageUrl(),ivProfileImage, Utils.roundedImageOptions);

            ImageLoader.getInstance().loadImage(tweet.getUser().getProfileImageUrl(), Utils.roundedImageOptions, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String s, View view) {
                }

                @Override
                public void onLoadingFailed(String s, View view, FailReason failReason) {
                }

                @Override
                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                    ivProfileImage.setImageBitmap(bitmap);
                }

                @Override
                public void onLoadingCancelled(String s, View view) {
                }
            });

            if(tweet.isFavorited()){
                tvFavoritesCount.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_favorite_on, 0, 0, 0);
                tvFavoritesCount.setTextColor(Color.parseColor("#FDD502"));
            }
            else{
                tvFavoritesCount.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_favorite_default, 0, 0, 0);
                tvFavoritesCount.setTextColor(Color.parseColor("#000000"));
            }
        }
    }

    public TimelineAdapter(Context context, List<Tweet> objects, ViewsClickListener listener) {

        super(context, R.layout.item_timeline_row, objects);

        this.context = context;
        try {
            this.listener = listener;
        }
        catch(Exception ex) {}
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder viewHolder = null;
        final Tweet tweet = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_timeline_row, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.init(convertView);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.populateViews(tweet);

        viewHolder.tvReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(listener != null) {
                    listener.OnReplyToTweetRequested(tweet);
                }
            }
        });

        viewHolder.tvFavoritesCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    if (tweet.isFavorited()) {
                        listener.OnDestroyFavoriteTweetRequested(tweet);
                    } else {
                        listener.OnCreateFavoriteTweetRequested(tweet);
                    }
                }
            }
        });

        viewHolder.ivProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.OnUserProfileRequested(tweet.getUser());
                }
            }
        });

        return convertView;
    }
}

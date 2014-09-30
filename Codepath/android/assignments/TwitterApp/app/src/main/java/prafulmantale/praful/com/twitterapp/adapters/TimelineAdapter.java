package prafulmantale.praful.com.twitterapp.adapters;

import android.content.Context;
import android.media.Image;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import java.util.List;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.models.Tweet;

/**
 * Created by prafulmantale on 9/26/14.
 */
public class TimelineAdapter extends ArrayAdapter<Tweet> {


    private class ViewHolder{

        private ImageView ivProfileImage;
        private TextView tvUserName;
        private TextView tvScreenName;
        private TextView tvCreatedTime;
        private TextView tvTweet;
        private ImageView ivReply;
        private TextView tvRetweetsCount;
        private TextView tvFavoritesCount;
        private ImageView ivFollow;

        void init(View convertView){
            ivProfileImage = (ImageView)convertView.findViewById(R.id.ivProfilePicture_timeline);
            tvUserName = (TextView)convertView.findViewById(R.id.tvUserName_timeline);
            tvScreenName = (TextView)convertView.findViewById(R.id.tvUserHandle_timeline);
            tvCreatedTime = (TextView)convertView.findViewById(R.id.tvElapsedTime_timeline);
            tvTweet = (TextView)convertView.findViewById(R.id.tvTweet_timeline);
            ivReply = (ImageView)convertView.findViewById(R.id.ivReply_timeline);
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

            ImageLoader.getInstance().displayImage(tweet.getUser().getProfileImageUrl(),ivProfileImage);
        }
    }

    public TimelineAdapter(Context context, List<Tweet> objects) {

        super(context, R.layout.item_timeline_row, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        Tweet tweet = getItem(position);

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

        return convertView;
    }
}

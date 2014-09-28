package prafulmantale.praful.com.twitterapp.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

            viewHolder.ivProfileImage = (ImageView)convertView.findViewById(R.id.ivProfilePicture_timeline);
            viewHolder.tvUserName = (TextView)convertView.findViewById(R.id.tvUserName_timeline);
            viewHolder.tvScreenName = (TextView)convertView.findViewById(R.id.tvUserHandle_timeline);
            viewHolder.tvCreatedTime = (TextView)convertView.findViewById(R.id.tvElapsedTime_timeline);
            viewHolder.tvTweet = (TextView)convertView.findViewById(R.id.tvTweet_timeline);
            viewHolder.ivReply = (ImageView)convertView.findViewById(R.id.ivReply_timeline);
            viewHolder.tvRetweetsCount = (TextView)convertView.findViewById(R.id.tvRetweets_timeline);
            viewHolder.tvFavoritesCount = (TextView)convertView.findViewById(R.id.tvFavorites_timeline);
            viewHolder.ivFollow = (ImageView)convertView.findViewById(R.id.ivFollow_timeline);


            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.tvUserName.setText(tweet.getUser().getName());
        viewHolder.tvScreenName.setText(tweet.getUser().getScreenName());
        viewHolder.tvTweet.setText(tweet.getBody());

        return convertView;
    }
}

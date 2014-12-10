package prafulmantale.praful.com.instagramviewer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import prafulmantale.praful.com.instagramviewer.R;
import prafulmantale.praful.com.instagramviewer.Utils.RoundedTransformation;
import prafulmantale.praful.com.instagramviewer.models.Comment;
import prafulmantale.praful.com.instagramviewer.models.Like;
import prafulmantale.praful.com.instagramviewer.models.Likes;

/**
 * Created by prafulmantale on 9/12/14.
 */
public class LikesViewerAdapter extends ArrayAdapter<Like> {

    public LikesViewerAdapter(Context context, List<Like> likesList) {
        super(context, R.layout.item_like, likesList);
    }


    private static class ViewHolder{
        ImageView ivProfilePicture;
        TextView tvUserName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Like like = getItem(position);
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_like, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.ivProfilePicture = (ImageView)convertView.findViewById(R.id.ivProfilePicture);
            viewHolder.tvUserName = (TextView)convertView.findViewById(R.id.tvUserName);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Picasso.with(getContext()).load(like.getUserDetails().getProfilePictureUrl()).transform(new RoundedTransformation(100, 3)).into(viewHolder.ivProfilePicture);
        viewHolder.tvUserName.setText(like.getUserDetails().getUsername());

        return convertView;
    }
}

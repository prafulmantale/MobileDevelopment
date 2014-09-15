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

    //To do
    private static class ViewHolder{

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Like like = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_like, parent, false);
        }

        ImageView ivProfilePic = (ImageView)convertView.findViewById(R.id.ivProfilePicture);
        TextView tvUserName = (TextView)convertView.findViewById(R.id.tvUserName);

        Picasso.with(getContext()).load(like.getUserDetails().getProfilePictureUrl()).transform(new RoundedTransformation(100, 3)).into(ivProfilePic);
        tvUserName.setText(like.getUserDetails().getUsername());

        return convertView;
    }
}

package prafulmantale.praful.com.instagramviewer.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import prafulmantale.praful.com.instagramviewer.R;
import prafulmantale.praful.com.instagramviewer.Utils.RoundedTransformation;
import prafulmantale.praful.com.instagramviewer.models.Comment;
import prafulmantale.praful.com.instagramviewer.models.MediaDetails;

/**
 * Created by prafulmantale on 9/12/14.
 */
public class CommentsViewerAdapter extends ArrayAdapter<Comment> {

    public CommentsViewerAdapter(Context context, List<Comment> commentList) {
        super(context, R.layout.item_comment, commentList);
    }

    private static class ViewHolder{
        ImageView ivProfilePic;
        TextView tvUserName;
        TextView tvComments;
        TextView tvCreatedTime;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Comment comment = getItem(position);
        ViewHolder viewHolder;


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_comment, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.ivProfilePic = (ImageView)convertView.findViewById(R.id.ivProfilePicture);
            viewHolder.tvUserName = (TextView)convertView.findViewById(R.id.tvUserName);
            viewHolder.tvComments = (TextView)convertView.findViewById(R.id.tvComment);
            viewHolder.tvCreatedTime = (TextView)convertView.findViewById(R.id.tvCreatedTime);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Picasso.with(getContext()).load(comment.getUserDetails().getProfilePictureUrl()).transform(new RoundedTransformation(100, 3)).into(viewHolder.ivProfilePic);
        viewHolder.tvUserName.setText(comment.getUserDetails().getUsername());
        viewHolder.tvComments.setText(comment.getText());
        viewHolder.tvCreatedTime.setText(comment.getElapsedTime());

        return convertView;
    }
}

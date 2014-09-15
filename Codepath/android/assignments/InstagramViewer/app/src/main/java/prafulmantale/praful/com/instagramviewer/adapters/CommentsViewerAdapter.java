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

    //To do
    private static class ViewHolder{

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Comment comment = getItem(position);


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_comment, parent, false);
        }


        ImageView ivProfilePic = (ImageView)convertView.findViewById(R.id.ivProfilePicture);
        TextView tvUserName = (TextView)convertView.findViewById(R.id.tvUserName);
        TextView tvComments = (TextView)convertView.findViewById(R.id.tvComment);
        TextView tvCreatedTime = (TextView)convertView.findViewById(R.id.tvCreatedTime);

        Picasso.with(getContext()).load(comment.getUserDetails().getProfilePictureUrl()).transform(new RoundedTransformation(100, 3)).into(ivProfilePic);

        tvUserName.setText(comment.getUserDetails().getUsername());

        tvComments.setText(comment.getText());

        tvCreatedTime.setText(comment.getDateTime());


        return convertView;
    }
}

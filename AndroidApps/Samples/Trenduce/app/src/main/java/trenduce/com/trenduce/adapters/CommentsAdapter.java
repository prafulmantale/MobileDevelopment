package trenduce.com.trenduce.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import trenduce.com.trenduce.R;
import trenduce.com.trenduce.model.Comment;

/**
 * Created by prafulmantale on 3/7/15.
 */
public class CommentsAdapter extends ArrayAdapter<Comment> {

    public CommentsAdapter(Context context, List<Comment> objects) {
        super(context, R.layout.item_comments, objects);
    }

    private class ViewHolder{

        ImageView ivProfileImage;
        TextView tvUserName;
        TextView tvComment;
        TextView tvCreatedTime;

        void init(View convertView){

            ivProfileImage = (ImageView)convertView.findViewById(R.id.ivProfileImage);
            tvUserName = (TextView)convertView.findViewById(R.id.tvUserName);
            tvComment = (TextView)convertView.findViewById(R.id.tvCommentText);

        }

        void populateView(Comment comment){
            tvUserName.setText(comment.getUser());
            tvComment.setText(comment.getText());
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        Comment comment = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_comments, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.init(convertView);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.populateView(comment);

        return convertView;
    }
}

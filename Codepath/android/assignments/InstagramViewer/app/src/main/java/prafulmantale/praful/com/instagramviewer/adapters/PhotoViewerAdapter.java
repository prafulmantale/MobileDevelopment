package prafulmantale.praful.com.instagramviewer.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import prafulmantale.praful.com.instagramviewer.R;
import prafulmantale.praful.com.instagramviewer.Utils.RoundedTransformation;
import prafulmantale.praful.com.instagramviewer.enums.RequesterTypes;
import prafulmantale.praful.com.instagramviewer.interfaces.RowActionsListener;
import prafulmantale.praful.com.instagramviewer.models.Comment;
import prafulmantale.praful.com.instagramviewer.models.MediaDetails;

/**
 * Created by prafulmantale on 9/12/14.
 */
public class PhotoViewerAdapter extends ArrayAdapter<MediaDetails> {

    private static final int MAX_COMMENTS = 3;
    private final Activity context;

    public PhotoViewerAdapter(Activity context, List<MediaDetails> mediaDetailsList) {
        super(context, R.layout.item_media, mediaDetailsList);
        this.context = context;
    }


    private static class ViewHolder{

        ImageView ivProfilePic;
        TextView tvUserName;
        TextView tvLocation;
        TextView tvCreatedTime;
        ImageView ivMedia;
        TextView tvLike;
        TextView tvCaption;
        TextView tvCommentCount;
        TextView tvComments;
        ImageView likeView;
        ImageView commentsView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final MediaDetails mediaDetails = getItem(position);
        final ViewHolder viewHolder;


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_media, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.ivProfilePic = (ImageView)convertView.findViewById(R.id.ivProfilePicture);
            viewHolder.tvUserName = (TextView)convertView.findViewById(R.id.tvUserName);
            viewHolder.tvLocation = (TextView)convertView.findViewById(R.id.tvLocation);
            viewHolder.tvCreatedTime = (TextView)convertView.findViewById(R.id.tvCreatedTime);
            viewHolder.ivMedia = (ImageView)convertView.findViewById(R.id.ivMedia);
            viewHolder.tvLike =  (TextView)convertView.findViewById(R.id.tvLikesCount);
            viewHolder.tvCaption = (TextView)convertView.findViewById(R.id.tvCaption);
            viewHolder.tvCommentCount = (TextView)convertView.findViewById(R.id.tvCommentsCount);
            viewHolder.tvComments = (TextView)convertView.findViewById(R.id.tvCommentsList);
            viewHolder.likeView = (ImageView)convertView.findViewById(R.id.ivLikeButton);
            viewHolder.commentsView = (ImageView)convertView.findViewById(R.id.ivCommentsButton);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Picasso.with(getContext()).load(mediaDetails.getProfilePictureUrl()).transform(new RoundedTransformation(100, 3)).into(viewHolder.ivProfilePic);

        viewHolder.tvUserName.setText(mediaDetails.getUsername());

        if(mediaDetails.getLocation() == null || mediaDetails.getLocation().isEmpty()){
            viewHolder.tvLocation.setVisibility(View.GONE);
            ViewGroup.LayoutParams layoutParams = viewHolder.tvUserName.getLayoutParams();
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        }
        else {
            viewHolder.tvLocation.setVisibility(View.VISIBLE);
            viewHolder.tvLocation.setText(mediaDetails.getLocation());
            ViewGroup.LayoutParams layoutParams = viewHolder.tvUserName.getLayoutParams();
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }

        viewHolder.tvCreatedTime.setText(mediaDetails.getElapsedTime());

        Picasso.with(getContext()).load(mediaDetails.getStandardResolutionUrl()).into(viewHolder.ivMedia);

        updateLikesCount(viewHolder.tvLike, mediaDetails);

        if(!mediaDetails.getCaption().isValid()){
            viewHolder.tvCaption.setVisibility(View.GONE);
        }
        else {
            viewHolder.tvCaption.setVisibility(View.VISIBLE);
            viewHolder.tvCaption.setText(Html.fromHtml("<font color=\"#206199\"><b>" + mediaDetails.getCaption().getUserDetails().getUsername()
                    + "  " + "</b></font>" + "<font color=\"#000000\">" + mediaDetails.getCaption().getText() + "</font>"));
        }

        updateCommentsView(viewHolder, mediaDetails);


        viewHolder.likeView.setTag(mediaDetails);

        updateLikeView(viewHolder.likeView, mediaDetails);

        viewHolder.likeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaDetails mediaDetails1 = (MediaDetails)viewHolder.likeView.getTag();
                mediaDetails1.setiLiked(!mediaDetails1.isiLiked());

                updateLikeView(viewHolder.likeView, mediaDetails1);
                viewHolder.likeView.setImageResource(mediaDetails1.isiLiked() ? R.drawable.ic_action_liked : R.drawable.ic_action_favorite);
                long newCount = mediaDetails1.getLikes().getCount() + (mediaDetails1.isiLiked() ?  + 1 : -1) ;
                mediaDetails1.getLikes().setCount(newCount);

                updateLikesCount(viewHolder.tvLike, mediaDetails1);
            }
        });

        viewHolder.commentsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RowActionsListener listener = (RowActionsListener)context;
                listener.OnCommentsListRequested(mediaDetails, RequesterTypes.ADD_COMMENT);
            }
        });

        return convertView;
    }

    private void updateLikeView(ImageView likeView, MediaDetails mediaDetails){
        if(mediaDetails.isiLiked()){
            likeView.setImageResource(R.drawable.ic_action_liked);
        }
        else{
            likeView.setImageResource(R.drawable.ic_action_favorite);
        }
    }

    private void updateLikesCount(TextView tvLike, MediaDetails mediaDetails){
        if(mediaDetails.getLikes().getCount() == 0){
            tvLike.setVisibility(View.GONE);
        }
        else {
            tvLike.setTag(mediaDetails);
            tvLike.setVisibility(View.VISIBLE);
            tvLike.setText(mediaDetails.getLikes().getCount() + "");
        }
    }

    private void updateCommentsView(ViewHolder viewHolder, MediaDetails mediaDetails){
        if(mediaDetails.getComments().getCount() == 0){
            viewHolder.tvCommentCount.setVisibility(View.GONE);
            viewHolder.tvComments.setVisibility(View.GONE);
        }
        else {
            viewHolder.tvCommentCount.setTag(mediaDetails);
            viewHolder.tvCommentCount.setVisibility(View.VISIBLE);
            viewHolder.tvComments.setVisibility(View.VISIBLE);
            viewHolder.tvCommentCount.setText("view all " + mediaDetails.getComments().getCount() + "  comments");

            updateCommentsList(viewHolder.tvComments, mediaDetails);
        }
    }

    private void updateCommentsList(TextView tvComments, MediaDetails mediaDetails){

        StringBuilder sb = new StringBuilder(100);
        for(int i = 0; i < mediaDetails.getComments().getCount(); i ++){

            if(i >= MAX_COMMENTS){
                break;
            }

            Comment comment = mediaDetails.getComments().getCommentsList().get(i);

            if(i > 0){
                sb.append("<br>");
            }

            sb.append("<font color=\"#206199\"><b>" + comment.getUserDetails().getUsername()
                    + "  " + "</b></font>" + "<font color=\"#000000\">" + comment.getText() + "</font>");

        }

       tvComments.setText(Html.fromHtml(sb.toString()));
    }
}
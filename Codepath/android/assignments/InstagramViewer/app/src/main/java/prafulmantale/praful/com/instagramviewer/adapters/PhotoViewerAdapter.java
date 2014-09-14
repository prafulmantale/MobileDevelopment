package prafulmantale.praful.com.instagramviewer.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import prafulmantale.praful.com.instagramviewer.R;
import prafulmantale.praful.com.instagramviewer.models.Comment;
import prafulmantale.praful.com.instagramviewer.models.MediaDetails;

/**
 * Created by prafulmantale on 9/12/14.
 */
public class PhotoViewerAdapter extends ArrayAdapter<MediaDetails> {

    public PhotoViewerAdapter(Context context, List<MediaDetails> mediaDetailsList) {
        super(context, R.layout.item_media, mediaDetailsList);
    }

    //To do
    private static class ViewHolder{

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MediaDetails mediaDetails = getItem(position);


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_media, parent, false);
        }


        ImageView ivProfilePic = (ImageView)convertView.findViewById(R.id.ivProfilePicture);
        ImageView ivMedia = (ImageView)convertView.findViewById(R.id.ivMedia);
        TextView tvUserName = (TextView)convertView.findViewById(R.id.tvUserName);
        TextView tvLocation = (TextView)convertView.findViewById(R.id.tvLocation);
        TextView tvCaption = (TextView)convertView.findViewById(R.id.tvCaption);
        TextView tvLike = (TextView)convertView.findViewById(R.id.tvLikesCount);
        TextView tvCreatedTime = (TextView)convertView.findViewById(R.id.tvCreatedTime);
        TextView tvComments = (TextView)convertView.findViewById(R.id.tvCommentsCount);

        Picasso.with(getContext()).load(mediaDetails.getProfilePictureUrl()).into(ivProfilePic);
        tvUserName.setText(mediaDetails.getUsername());

        if(mediaDetails.getLocation() == null || mediaDetails.getLocation().isEmpty()){
            tvLocation.setVisibility(View.GONE);
//            ViewGroup.LayoutParams layoutParams = tvUserName.getLayoutParams();
//            tvUserName.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        }
        else {
            tvLocation.setVisibility(View.VISIBLE);
            tvLocation.setText(mediaDetails.getLocation());
//            ViewGroup.LayoutParams layoutParams = tvUserName.getLayoutParams();
//            tvUserName.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        }

        tvCreatedTime.setText(mediaDetails.getDateTime());

        Picasso.with(getContext()).load(mediaDetails.getStandardResolutionUrl()).into(ivMedia);

        if(mediaDetails.getLikes().getCount() == 0){
            tvLike.setVisibility(View.GONE);
        }
        else {
            tvLike.setVisibility(View.VISIBLE);
            tvLike.setText(Html.fromHtml("&#x1f499;") + "    " + mediaDetails.getLikes().getCount() + "  likes");
        }


        if(!mediaDetails.getCaption().isValid()){
            tvCaption.setVisibility(View.GONE);
        }
        else {
            tvCaption.setVisibility(View.VISIBLE);
            tvCaption.setText(Html.fromHtml("<font color=\"#206199\"><b>" + mediaDetails.getCaption().getUserDetails().getUsername()
                    + "  " + "</b></font>" + "<font color=\"#000000\">" + mediaDetails.getCaption().getText() + "</font>"));
        }

        if(mediaDetails.getComments().getCount() == 0){
            tvComments.setVisibility(View.GONE);
        }
        else {
            tvComments.setVisibility(View.VISIBLE);
            tvComments.setText("view all " + mediaDetails.getComments().getCount() + "  comments");

            for(int i = 0; i < mediaDetails.getComments().getCount(); i ++){

                if(i >= 5){
                    break;
                }

                Comment comment = mediaDetails.getComments().getCommentsList().get(i);

                tvComments.append(Html.fromHtml("<br><font color=\"#206199\"><b>" + comment.getUserDetails().getUsername()
                        + "  " + "</b></font>" + "<font color=\"#000000\">" + comment.getText() + "</font>"));
            }
        }

        return convertView;
    }
}

package prafulmantale.praful.com.instagramviewer.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import prafulmantale.praful.com.instagramviewer.R;
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

        tvUserName.setText(mediaDetails.getUsername());

        if(mediaDetails.getLocation() == null || mediaDetails.getLocation().isEmpty()){
            tvLocation.setVisibility(View.INVISIBLE);
        }
        else {
            tvLocation.setVisibility(View.VISIBLE);
            tvLocation.setText(mediaDetails.getLocation());
        }


        Picasso.with(getContext()).load(mediaDetails.getProfilePictureUrl()).into(ivProfilePic);
        Picasso.with(getContext()).load(mediaDetails.getStandardResolutionUrl()).into(ivMedia);

        if(mediaDetails.getLikes().getCount() == 0){
            tvLike.setVisibility(View.INVISIBLE);

        }
        else {
            tvLike.setVisibility(View.VISIBLE);
            tvLike.setText(Html.fromHtml("&#x1f499;") + "    " + mediaDetails.getLikes().getCount() + "  likes");
        }

        if(mediaDetails.getComments().getCount() == 0){
            tvComments.setVisibility(View.INVISIBLE);
        }
        else {
            tvComments.setVisibility(View.VISIBLE);
            tvComments.setText("view all " + mediaDetails.getComments().getCount() + "  comments");
        }

        if(mediaDetails.getCaption() == null || mediaDetails.getCaption().isEmpty()){
            tvCaption.setVisibility(View.INVISIBLE);
        }
        else {
            tvCaption.setVisibility(View.VISIBLE);
            tvCaption.setText(Html.fromHtml("<small><font color=\"#206199\"><b>" + mediaDetails.getUsername() + "  " + "</b></font></small>" + "<small><font color=\"#000000\">" + mediaDetails.getCaption() + "</font></small>"));
        }
       tvCreatedTime.setText(mediaDetails.getDateTime());

        return convertView;
    }
}

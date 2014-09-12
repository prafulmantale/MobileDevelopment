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

        tvUserName.setText(mediaDetails.getUsername());
        //tvLocation.setText(mediaDetails.getLocation());
        tvLocation.setText("Some Nice Place");

        tvCaption.setText(Html.fromHtml("<small><font color=\"#206199\">" + mediaDetails.getUsername() + "  " + "</font></small>" + "<small><font color=\"#000000\">" + mediaDetails.getCaption() + "</font></small>"));
        Picasso.with(getContext()).load(mediaDetails.getProfilePictureUrl()).into(ivProfilePic);
        Picasso.with(getContext()).load(mediaDetails.getStandardResolutionUrl()).into(ivMedia);

        return convertView;
    }
}

package prafulmantale.prafulrm.prafulkumar.prafulrm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import junit.framework.Test;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.prafulrm.R;
import prafulmantale.prafulrm.prafulkumar.prafulrm.models.BoxOfficeMovie;

/**
 * Created by prafulmantale on 7/26/14.
 */
public class BoxOfficeMoviesAdapter extends ArrayAdapter<BoxOfficeMovie> {

    public BoxOfficeMoviesAdapter(Context context, List<BoxOfficeMovie> list) {
        super(context, 0, list);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        BoxOfficeMovie movie = getItem(position);

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_box_office_movie, parent, false);
        }

        TextView tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
        TextView tvCriticsScore = (TextView) convertView.findViewById(R.id.tvCriticsScore);
        TextView tvCast = (TextView) convertView.findViewById(R.id.tvCast);
        ImageView ivPosterImage = (ImageView) convertView.findViewById(R.id.ivPosterImage);

        tvTitle.setText(movie.getTitle());
        tvCriticsScore.setText("Score: " + movie.getCriticsScore() + "%");
        tvCast.setText(movie.getCastList());
        Picasso.with(getContext()).load(movie.getPosterUrl()).into(ivPosterImage);
        // Return the completed view to render on screen
        return convertView;
    }
}

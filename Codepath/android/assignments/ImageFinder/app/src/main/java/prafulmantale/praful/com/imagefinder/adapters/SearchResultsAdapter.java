package prafulmantale.praful.com.imagefinder.adapters;

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

import prafulmantale.praful.com.imagefinder.R;
import prafulmantale.praful.com.imagefinder.models.SearchResult;

/**
 * Created by prafulmantale on 9/22/14.
 *
 * Adapter for Search results
 */
public class SearchResultsAdapter extends ArrayAdapter<SearchResult> {

    private static class ViewHolder{
        ImageView ivImage;
        TextView tvTitle;
    }


    public SearchResultsAdapter(Context context,  List<SearchResult> searchResults) {
        super(context, R.layout.item_image_result, searchResults);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SearchResult searchResult = getItem(position);

        ViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.ivImage = (ImageView)convertView.findViewById(R.id.ivImage);
            viewHolder.tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }


        viewHolder.ivImage.setImageResource(0);

        Picasso.with(getContext()).load(searchResult.getTbUrl()).into(viewHolder.ivImage);

        viewHolder.tvTitle.setText(Html.fromHtml(searchResult.getTitle()));


        return convertView;
    }
}

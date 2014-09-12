package prafulmantale.praful.com.simpleuserlist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import prafulmantale.praful.com.simpleuserlist.R;
import prafulmantale.praful.com.simpleuserlist.models.User;

/**
 * Created by prafulmantale on 9/11/14.
 */
public class UsersAdapter extends ArrayAdapter<User> {

    public UsersAdapter(Context context,  List<User> users) {
        super(context, R.layout.item_user, users);
    }

    private static class ViewHolder{
        TextView tvName;
        TextView tvHome;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        User user = getItem(position);
        ViewHolder viewHolder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvHome = (TextView) convertView.findViewById(R.id.tvHome);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // Lookup view for data population
//        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
//        TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        // Populate the data into the template view using the data object
        viewHolder.tvName.setText(user.getName());
        viewHolder.tvHome.setText(user.getHome());
        // Return the completed view to render on screen
        return convertView;
    }
}

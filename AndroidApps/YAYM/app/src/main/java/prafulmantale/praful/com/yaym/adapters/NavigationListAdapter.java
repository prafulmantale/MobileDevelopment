package prafulmantale.praful.com.yaym.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.models.NavigationDrawerItem;

/**
 * Created by prafulmantale on 12/13/14.
 */
public class NavigationListAdapter extends BaseAdapter {

    private static final String TAG = NavigationListAdapter.class.getSimpleName();

    private Context context;
    private List<NavigationDrawerItem> drawerItemList;

    public NavigationListAdapter(Context context, List<NavigationDrawerItem> drawerItemList) {
        this.context = context;
        this.drawerItemList = drawerItemList;
    }

    @Override
    public int getCount() {
        return drawerItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return drawerItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;


        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.drawer_item_list, null);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
            viewHolder.tvItemText = (TextView)view.findViewById(R.id.tvDrawerItem);
        }
        else{
            viewHolder = (ViewHolder)view.getTag();
        }


        String prefix = "";
        if(position == 2 || position == 3 || position == 5){
            prefix = "      ";
        }
        viewHolder.tvItemText.setText(prefix + drawerItemList.get(position).getItemText());

        return view;
    }


    private class ViewHolder{
        TextView tvItemText;
    }

}

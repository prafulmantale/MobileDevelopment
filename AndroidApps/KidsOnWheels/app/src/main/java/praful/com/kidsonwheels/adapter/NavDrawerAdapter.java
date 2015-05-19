package praful.com.kidsonwheels.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import praful.com.kidsonwheels.R;

/**
 * Created by prafulmantale on 5/18/15.
 */
public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private String mNavTitles[];
    private int mIcons[];
    private String mUserName;        //String Resource for header View Name
    private int mProfileImageId;        //int Resource for header view mProfileImageId picture


    public static class ViewHolder extends RecyclerView.ViewHolder {

        int holderid;

        TextView tvItemText;
        ImageView ivDrawerIcon;
        TextView tvCount;
        ImageView ivProfile;
        TextView tvUserName;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);
            if (ViewType == TYPE_ITEM) {
                tvItemText = (TextView) itemView.findViewById(R.id.tvDrawerItemText);
                ivDrawerIcon = (ImageView) itemView.findViewById(R.id.ivDrawerItemIcon);
                tvCount = (TextView) itemView.findViewById(R.id.tvDrawerItemCount);
                holderid = 1;
            } else {
                tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
                ivProfile = (ImageView) itemView.findViewById(R.id.cvProfileImage);
                holderid = 0;
            }
        }
    }

    public NavDrawerAdapter(String titles[], int icons[], String name, int profile) {
        mNavTitles = titles;
        mIcons = icons;
        mUserName = name;
        mProfileImageId = profile;
    }


    @Override
    public NavDrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_list_item, parent, false);
            ViewHolder vhItem = new ViewHolder(v, viewType);
            return vhItem;
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_header, parent, false); //Inflating the layout
            ViewHolder vhHeader = new ViewHolder(v, viewType); //Creating ViewHolder and passing the object of type view
            return vhHeader;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(NavDrawerAdapter.ViewHolder holder, int position) {
        if (holder.holderid == 1) {
            holder.tvItemText.setText(mNavTitles[position - 1]);
            holder.ivDrawerIcon.setImageResource(mIcons[position - 1]);
        } else {
            holder.ivProfile.setImageResource(mProfileImageId);
            holder.tvUserName.setText(mUserName);
        }
    }

    @Override
    public int getItemCount() {
        return mNavTitles.length + 1;
    }


    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}

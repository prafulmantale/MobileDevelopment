package duggleetech.com.guardianangel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import duggleetech.com.guardianangel.R;

/**
 * Created by prafulmantale on 3/11/15.
 */
public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;

    private String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[];       // Int Array to store the passed icons resource value from MainActivity.java

    private String name;        //String Resource for header View Name
    private int profile;        //int Resource for header view profile picture


    public static class ViewHolder extends RecyclerView.ViewHolder {

        int Holderid;

        TextView tvItemText;
        ImageView ivDrawerIcon;
        TextView tvCount;
        ImageView ivProfile;
        TextView tvUserName;

        public ViewHolder(View itemView,int ViewType) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);


            // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created

            if(ViewType == TYPE_ITEM) {
                tvItemText = (TextView) itemView.findViewById(R.id.tvDrawerItemText); // Creating TextView object with the id of textView from item_row.xml
                ivDrawerIcon = (ImageView) itemView.findViewById(R.id.ivDrawerItemIcon);// Creating ImageView object with the id of ImageView from item_row.xml
                tvCount = (TextView)itemView.findViewById(R.id.tvDrawerItemCount);
                Holderid = 1;                                               // setting holder id as 1 as the object being populated are of type item row
            }
            else{

                tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);         // Creating Text View object from header.xml for name
                ivProfile = (ImageView) itemView.findViewById(R.id.cvProfileImage);// Creating Image view object from header.xml for profile pic
                Holderid = 0;                                                // Setting holder id = 0 as the object being populated are of type header view
            }
        }

    }


    public NavDrawerAdapter(String Titles[],int Icons[],String Name,  int Profile){ // MyAdapter Constructor with titles and icons parameter
        // titles, icons, name, email, profile pic are passed from the main activity as we
        mNavTitles = Titles;                //have seen earlier
        mIcons = Icons;
        name = Name;
        profile = Profile;                     //here we assign those passed values to the values we declared here
        //in adapter
    }


    @Override
    public NavDrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_list_item,parent,false); //Inflating the layout

            ViewHolder vhItem = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view

            return vhItem; // Returning the created object

            //inflate your layout and pass it to view holder

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_header,parent,false); //Inflating the layout

            ViewHolder vhHeader = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view

            return vhHeader; //returning the object created
        }

        return null;

    }

    //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
    // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
    // which view type is being created 1 for item row
    @Override
    public void onBindViewHolder(NavDrawerAdapter.ViewHolder holder, int position) {
        if(holder.Holderid ==1) {                              // as the list view is going to be called after the header view so we decrement the
            // position by 1 and pass it to the holder while setting the text and image
            holder.tvItemText.setText(mNavTitles[position - 1]); // Setting the Text with the array of our Titles
            holder.ivDrawerIcon.setImageResource(mIcons[position -1]);// Settimg the image with array of our icons
        }
        else{

            holder.ivProfile.setImageResource(profile);           // Similarly we set the resources for header view
            holder.tvUserName.setText(name);
        }
    }

    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        return mNavTitles.length + 1; // the number of items in the list will be +1 the titles including the header view.
    }


    // Witht the following method we check what type of view is being passed
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

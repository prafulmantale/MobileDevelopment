package prafulmantale.praful.com.twitterapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.models.UserProfile;

/**
 * Created by prafulmantale on 10/5/14.
 */
public class UserListAdapter extends ArrayAdapter<UserProfile>{

    private class ViewHolder{
        ImageView ivProfileImage;
        TextView tvUserName;
        TextView tvScreenName;
        TextView tvDescription;
        ImageView ivFriendshipStatus;

        protected void init(View convertedView){
            ivProfileImage = (ImageView)convertedView.findViewById(R.id.ivProfilePicture_user_list);
            tvUserName = (TextView)convertedView.findViewById(R.id.tvUserName_user_list);
            tvScreenName = (TextView)convertedView.findViewById(R.id.tvUserHandle_user_list);
            tvDescription = (TextView)convertedView.findViewById(R.id.tvDescription_user_list);
            ivFriendshipStatus = (ImageView)convertedView.findViewById(R.id.ivFollowingIndicator_user_list);
        }

        protected void populateData(UserProfile profile){

            if(profile.getProfileImageUrl() != null && !profile.getProfileImageUrl().isEmpty()){
                ImageLoader.getInstance().displayImage(profile.getProfileImageUrl(), ivProfileImage);
            }

            tvUserName.setText(profile.getName());
            tvScreenName.setText("@" + profile.getScreenName());
            tvDescription.setText(profile.getDescription());

            if(profile.isFollowing()){
                //Load image 1
            }
            else{
                //Load image 2
            }
        }
    }



    public UserListAdapter(Context context,  List<UserProfile> objects) {
        super(context, R.layout.item_user_list_row, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        UserProfile profile = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user_list_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.populateData(profile);

        return convertView;
    }
}

package prafulmantale.praful.com.twitterapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.twitterapp.R;
import prafulmantale.praful.com.twitterapp.adapters.UserListAdapter;
import prafulmantale.praful.com.twitterapp.application.RestClientApp;
import prafulmantale.praful.com.twitterapp.enums.UserListType;
import prafulmantale.praful.com.twitterapp.handlers.UsersListResponseHandler;
import prafulmantale.praful.com.twitterapp.helpers.AppConstants;
import prafulmantale.praful.com.twitterapp.models.UserProfile;

/**
 * Created by prafulmantale on 10/3/14.
 */
public class UsersListFragment extends Fragment{

    private static final String TAG = UsersListFragment.class.getName();

    private UserListAdapter followersAdapter;
    private List<UserProfile> followersList;
    private ListView lvFollowers;
    private View view;
    private UserListType listType;

    public UsersListFragment(){


    }

    public static UsersListFragment newInstance(int listType){
        UsersListFragment fragment = new UsersListFragment();
        Bundle args = new Bundle();
        args.putInt(AppConstants.KEY_USER_LIST_TYPE, listType);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int type = getArguments().getInt(AppConstants.KEY_USER_LIST_TYPE);

        listType = (type == 0) ? UserListType.Followers : UserListType.Following;

        followersList = new ArrayList<UserProfile>();
        followersAdapter = new UserListAdapter(getActivity(), followersList);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_users_list, container, false);

        lvFollowers = (ListView) view.findViewById(R.id.lvItemsList_users_list);

        lvFollowers.setAdapter(followersAdapter);

        if(listType == UserListType.Followers) {
            RestClientApp.getTwitterClient().getFollowersList(new UsersListResponseHandler(followersAdapter), "");
        }

        if(listType == UserListType.Following){
            RestClientApp.getTwitterClient().getFriendsList(new UsersListResponseHandler(followersAdapter), "");
        }

        return view;
    }
}

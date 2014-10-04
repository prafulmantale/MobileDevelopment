package prafulmantale.praful.com.twitterapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import prafulmantale.praful.com.twitterapp.R;

/**
 * Created by prafulmantale on 10/3/14.
 */
public class ItemsListFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items_list, container, false);

        return view;
    }
}

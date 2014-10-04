package prafulmantale.praful.com.twitterapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import prafulmantale.praful.com.twitterapp.fragments.ItemsListFragment;

/**
 * Created by prafulmantale on 10/4/14.
 */
public class ProfileFragmentsPageAdapter extends FragmentPagerAdapter {

    List<ItemsListFragment> fragmentList;

    public ProfileFragmentsPageAdapter(FragmentManager fm, List<ItemsListFragment> list) {
        super(fm);

        fragmentList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}

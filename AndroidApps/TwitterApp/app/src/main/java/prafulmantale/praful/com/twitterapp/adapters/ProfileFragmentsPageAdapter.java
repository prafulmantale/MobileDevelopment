package prafulmantale.praful.com.twitterapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by prafulmantale on 10/4/14.
 */
public class ProfileFragmentsPageAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList;

    public ProfileFragmentsPageAdapter(FragmentManager fm, List<Fragment> list) {
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

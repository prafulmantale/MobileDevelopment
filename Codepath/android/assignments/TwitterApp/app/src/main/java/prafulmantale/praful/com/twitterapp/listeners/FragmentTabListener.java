package prafulmantale.praful.com.twitterapp.listeners;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by prafulmantale on 10/6/14.
 */
public class FragmentTabListener<T extends Fragment> implements ActionBar.TabListener {

    private Fragment fragment;
    private final FragmentActivity fragmentActivity;
    private final String tag;
    private final Class<T> clazz;
    private final int fragmentContainerId;
    private final Bundle fragmentArgs;

    public FragmentTabListener(FragmentActivity fragmentActivity, String tag, Class<T> clazz){
        this.fragmentActivity = fragmentActivity;
        this.tag = tag;
        this.clazz = clazz;

        fragmentContainerId = android.R.id.content;
        fragmentArgs = new Bundle();
    }


    public FragmentTabListener(int fragmentContainerId, FragmentActivity fragmentActivity,
                               String tag, Class<T> clz) {

        this.fragmentContainerId = fragmentContainerId;
        this.fragmentActivity = fragmentActivity;
        this.tag = tag;
        this.clazz = clz;

        fragmentArgs = new Bundle();
    }

    public FragmentTabListener(int fragmentContainerId, FragmentActivity activity,
                               String tag, Class<T> clz, Bundle args) {
        this.fragmentActivity = activity;
        this.tag = tag;
        this.clazz = clz;
        this.fragmentContainerId = fragmentContainerId;
        this.fragmentArgs = args;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        android.support.v4.app.FragmentTransaction sft = fragmentActivity.getSupportFragmentManager().beginTransaction();

        if(fragment == null){
            fragment = Fragment.instantiate(fragmentActivity, clazz.getName(), fragmentArgs);
            sft.add(fragmentContainerId, fragment, tag);
        }
        else{
            sft.attach(fragment);
        }

        sft.commit();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        android.support.v4.app.FragmentTransaction sft = fragmentActivity.getSupportFragmentManager().beginTransaction();

        if(fragment != null){
            sft.detach(fragment);
        }

        sft.commit();
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}

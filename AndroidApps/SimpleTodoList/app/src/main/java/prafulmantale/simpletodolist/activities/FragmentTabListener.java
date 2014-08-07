package prafulmantale.simpletodolist.activities;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import prafulmantale.simpletodolist.R;


/**
 * Created by prafulmantale on 8/7/14.
 */
public class FragmentTabListener<T extends Fragment> implements TabListener {

    private Fragment fragment;
    private final FragmentActivity fragmentActivity;
    private final String tag;
    private final Class<T> tClass;
    private final int fragmentContainerId;
    private final Bundle fragmentArgs;

    public FragmentTabListener(FragmentActivity fragmentActivity, String tag, Class<T> tClass) {
        this.fragmentActivity = fragmentActivity;
        this.tag = tag;
        this.tClass = tClass;
        fragmentContainerId = android.R.id.content;
        fragmentArgs = new Bundle();
    }

    public FragmentTabListener(int fragmentContainerId, FragmentActivity fragmentActivity, String tag, Class<T> tClass) {
        this.fragmentActivity = fragmentActivity;
        this.tag = tag;
        this.tClass = tClass;
        this.fragmentContainerId = fragmentContainerId;
        this.fragmentArgs = new Bundle();
    }

    public FragmentTabListener(int fragmentContainerId, FragmentActivity fragmentActivity, String tag, Class<T> tClass, Bundle fragmentArgs) {
        this.fragmentActivity = fragmentActivity;
        this.tag = tag;
        this.tClass = tClass;
        this.fragmentContainerId = fragmentContainerId;
        this.fragmentArgs = fragmentArgs;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();

        if(fragment == null){

            fragment = Fragment.instantiate(fragmentActivity, tClass.getName(), fragmentArgs);
            transaction.add(fragmentContainerId, fragment, tag);

        }
        else{
            transaction.attach(fragment);
        }

        transaction.commit();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();

        if(fragment != null){
            transaction.detach(fragment);
        }

        transaction.commit();
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }

}

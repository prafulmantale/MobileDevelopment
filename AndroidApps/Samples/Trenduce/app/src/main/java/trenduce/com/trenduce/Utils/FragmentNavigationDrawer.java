package trenduce.com.trenduce.Utils;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import trenduce.com.trenduce.R;
import trenduce.com.trenduce.fragments.DashboardFragment;

/**
 * Created by prafulmantale on 3/8/15.
 */
public class FragmentNavigationDrawer extends DrawerLayout {

    private ActionBarDrawerToggle drawerToggle;
    private ListView lvDrawer;
    private ArrayAdapter<String> drawerAdapter;
    private ArrayList<NavigationDrawerItem> drawerNavItems;
    private int drawerContainerRes;
    private DashboardFragment dashboardFragment;
    private int prevSelection = -1;

    public FragmentNavigationDrawer(Context context) {
        super(context);
    }

    public FragmentNavigationDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FragmentNavigationDrawer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void setupDrawerConfiguration(ListView lvDrawer, int drawerItemsRes,  int drawerContainerRes){

        drawerNavItems = new ArrayList<NavigationDrawerItem>();
        drawerAdapter = new ArrayAdapter<String>(getActivity(), drawerItemsRes, new ArrayList<String>());
        this.drawerContainerRes = drawerContainerRes;
        this.lvDrawer = lvDrawer;
        lvDrawer.setAdapter(drawerAdapter);
        lvDrawer.setOnItemClickListener(new FragmentDrawerItemListener());
        drawerToggle = setupDrawerToggle();
        setDrawerListener(drawerToggle);
        setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

    }

    private ActionBarDrawerToggle setupDrawerToggle(){

        return new ActionBarDrawerToggle(getActivity(), this, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                // setTitle("Navigate");
                getActivity().invalidateOptionsMenu(); // call onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerClosed(View drawerView) {
//                setTitle(getCurrentTitle());
                getActivity().invalidateOptionsMenu(); // call onPrepareOptionsMenu()
            }
        };

    }

    public void addNavigationItem(String navTitle, String windowTitle, Class<? extends Fragment> fragmentClass) {
        drawerAdapter.add(navTitle);
        drawerNavItems.add(new NavigationDrawerItem(fragmentClass, windowTitle));
    }

    public void selectDrawerItem(int position) {

        if(prevSelection == position){
            closeDrawer(lvDrawer);
            return;
        }

        prevSelection = position;

        // Create a new fragment and specify the planet to show based on
        // position
        NavigationDrawerItem navItem = drawerNavItems.get(position);
        Fragment fragment = null;
        try {

            if(dashboardFragment == null){
                dashboardFragment = new DashboardFragment();
            }
            if(position == 0){
                fragment = dashboardFragment;
            }
            else {
                fragment = navItem.getFragmentClass().newInstance();
            }
            Bundle args = navItem.getFragmentArgs();
            if (args != null) {
                fragment.setArguments(args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
//                System.out.println("Frgments:" + fragmentManager.getBackStackEntryCount() + "  " + fragmentManager.getFragments());
            }
        });
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_from_left,
                R.anim.slide_in_from_right, R.anim.slide_out_from_left);
        transaction.replace(drawerContainerRes, fragment, navItem.getTitle());
        transaction.addToBackStack(null);
        transaction.commit();

        // Highlight the selected item, update the title, and close the drawer
        lvDrawer.setItemChecked(position, true);
        setTitle(navItem.getTitle());
        closeDrawer(lvDrawer);
    }

    public FragmentActivity getActivity() {
        return (FragmentActivity)getContext();
    }

    private ActionBar getActionBar() {
        return getActivity().getActionBar();
    }

    public boolean isDrawerOpen() {
        return isDrawerOpen(lvDrawer);
    }

    public ActionBarDrawerToggle getDrawerToggle() {
        return drawerToggle;
    }

    private void setTitle(CharSequence title) {
        getActionBar().setTitle(title);
    }

    private class FragmentDrawerItemListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            selectDrawerItem(position);
        }
    }

    private class NavigationDrawerItem {

        private Class<? extends Fragment> fragmentClass;
        private String title;
        private Bundle fragmentArgs;


        public NavigationDrawerItem(Class<? extends Fragment> fragmentClass, String title, Bundle fragmentArgs) {
            this.fragmentClass = fragmentClass;
            this.title = title;
            this.fragmentArgs = fragmentArgs;
        }

        public NavigationDrawerItem(Class<? extends Fragment> fragmentClass, String title) {
            this(fragmentClass, title, null);
        }

        public Class<? extends Fragment> getFragmentClass() {
            return fragmentClass;
        }

        public String getTitle() {
            return title;
        }

        public Bundle getFragmentArgs() {
            return fragmentArgs;
        }
    }
}

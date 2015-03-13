
package duggleetech.com.guardianangel.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import duggleetech.com.guardianangel.R;
import duggleetech.com.guardianangel.adapter.NavDrawerAdapter;
import duggleetech.com.guardianangel.application.AngelApplication;
import duggleetech.com.guardianangel.fragment.AngelToFragment;
import duggleetech.com.guardianangel.fragment.CommandCenterFragment;
import duggleetech.com.guardianangel.fragment.FeedbackFragment;
import duggleetech.com.guardianangel.fragment.InviteFriendsFragment;
import duggleetech.com.guardianangel.fragment.MyAngelsFragment;
import duggleetech.com.guardianangel.fragment.SettingsFragment;
import duggleetech.com.guardianangel.fragment.SpreadWordFragment;
import duggleetech.com.guardianangel.fragment.YourStoryFragment;


public class MainActivity extends ActionBarActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    private String[] drawerItemsList;

    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    int ICONS[] = {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,
            R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};

    String NAME = "User Name";
    int PROFILE = R.drawable.ic_sample_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        if (savedInstanceState == null) {
            selectItem(1);
        }


    }

    private void initialize(){

        toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        drawerItemsList = getResources().getStringArray(R.array.drawer_items_array);

        mRecyclerView = (RecyclerView)findViewById(R.id.rvLeftDrawer);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new NavDrawerAdapter(drawerItemsList,ICONS,NAME,PROFILE);

        mRecyclerView.setAdapter(mAdapter);

        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());



                if(child!=null && mGestureDetector.onTouchEvent(motionEvent)){
                    drawerLayout.closeDrawers();

                    int position = recyclerView.getChildPosition(child);

                    if(position > 0 && position <= drawerItemsList.length) {
                        selectItem(recyclerView.getChildPosition(child));
                    }

                    return true;

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }
        });

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }



        }; // Drawer Toggle Object Made
        drawerLayout.setDrawerListener(drawerToggle); // Drawer Listener set to the Drawer toggle
        drawerToggle.syncState();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void selectItem(int position) {

        FragmentManager fragmentManager = getFragmentManager();

        switch (position) {

            case 1:

                Fragment cmdFragment = new CommandCenterFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, cmdFragment).commit();
                break;

            case 2:

                Fragment maFragment = new MyAngelsFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, maFragment).commit();
                break;

            case 3:

                Fragment atFragment = new AngelToFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, atFragment).commit();
                break;

            case 4:

                Fragment ysFragment = new YourStoryFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, ysFragment).commit();
                break;

            case 5:

                Fragment fbFragment = new FeedbackFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fbFragment).commit();
                break;

            case 6:

                Fragment inviteFriendsFragment = new InviteFriendsFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, inviteFriendsFragment).commit();
                break;


            case 7:

                Fragment spreadWordFragment = new SpreadWordFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, spreadWordFragment).commit();
                break;


            case 8:

                Fragment settingsFragment = new SettingsFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, settingsFragment).commit();
                break;

            default:
                break;

        }

        if(position > 0 && position <= drawerItemsList.length) {
            toolbar.setTitle(drawerItemsList[position - 1]);
        }
    }


    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        drawerToggle.onConfigurationChanged(newConfig);
    }

}
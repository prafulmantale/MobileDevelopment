package prafulmantale.praful.com.yaym.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.adapters.NavigationListAdapter;
import prafulmantale.praful.com.yaym.fragments.CcyPairSettingsFragment;
import prafulmantale.praful.com.yaym.fragments.DashboardFragment;
import prafulmantale.praful.com.yaym.helpers.AppConstants;
import prafulmantale.praful.com.yaym.models.NavigationDrawerItem;

public class MainActivity extends FragmentActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static String selectedCurrencyPair;
    private ImageView backButton;

    private DrawerLayout drawerLayout;
    private ListView lvDrawerItems;
    private List<NavigationDrawerItem> drawerItems;
    private NavigationListAdapter adapter;
    private String [] drawerItemsStrings;

    private CharSequence displayTitle; //title displayed in the Action Bar
    private CharSequence appTitle;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeActionBar();

        if(getIntent() != null) {
            selectedCurrencyPair = getIntent().getStringExtra(AppConstants.INTENT_KEY_CCYPAIR);
        }

        initialize();
        setupListeners();

        if(savedInstanceState == null){
            displayFragment(0);
        }
    }


    private void initialize(){

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);

        lvDrawerItems = (ListView)findViewById(R.id.lvDrawerMenu);
        drawerItems = new ArrayList<NavigationDrawerItem>();
        drawerItemsStrings = getResources().getStringArray(R.array.nav_drawer_menu_items);

        for(String str : drawerItemsStrings){
            drawerItems.add(new NavigationDrawerItem(str));
        }

        adapter = new NavigationListAdapter(getBaseContext(), drawerItems);
        lvDrawerItems.setAdapter(adapter);

        appTitle = displayTitle = getTitle();

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_action_drawer,
                R.string.app_name, R.string.app_name){

            @Override
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(appTitle);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                getActionBar().setTitle(displayTitle);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    private void setupListeners(){

        lvDrawerItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //Risk Warehouse and Customers
                if(position == 1 || position == 4){
                    return;
                }

                displayFragment(position);
            }
        });
    }

    private void displayFragment(int position){

        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        String tag = null;
        switch (position){

            case 0 :
                tag = "dashboard";
                fragment = fragmentManager.findFragmentByTag(tag);
                if(fragment == null){
                    Log.d(TAG, "Dashboard fragment created");
                    fragment = new DashboardFragment();
                }
                break;

            case 2 :
                tag = "ccypairsettings";
                fragment = fragmentManager.findFragmentByTag(tag);
                if(fragment == null){
                    Log.d(TAG, "Ccy Pair Settting fragment created");
                    fragment = new CcyPairSettingsFragment();
                }
                break;
            case 3 :
                fragment = fragmentManager.findFragmentByTag("riskpolicies");
                break;
            case 5:
                fragment = fragmentManager.findFragmentByTag("routingrules");
                break;
            default:
                break;
        }

        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameContainer, fragment, tag)
                    .commit();

            lvDrawerItems.setItemChecked(position, true);
            lvDrawerItems.setSelection(position);
            setTitle(drawerItemsStrings[position]);
            drawerLayout.closeDrawer(lvDrawerItems);
        }

    }

    private void initializeActionBar(){
//        ActionBar actionBar = getActionBar();
//        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_style));
//
//        View view = getLayoutInflater().inflate(R.layout.action_bar_title, null);
//
//        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
//                ActionBar.LayoutParams.MATCH_PARENT,
//                Gravity.CENTER);
//
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setHomeButtonEnabled(false);
//        actionBar.setCustomView(view, params);
//        actionBar.setDisplayShowHomeEnabled(true);
//
//        backButton = (ImageView)findViewById(R.id.ivBackbutton);
//        backButton.setVisibility(View.VISIBLE);
//
//        View homeIcon = findViewById(android.R.id.home);
//        ((View) homeIcon.getParent()).setVisibility(View.GONE);
//
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition (R.anim.stay, R.anim.slide_out_from_right);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        boolean drawerOpen = drawerLayout.isDrawerOpen(lvDrawerItems);

        try {
            menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        }
        catch (Exception ex){

        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        this.displayTitle = title;
        getActionBar().setTitle(displayTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
}

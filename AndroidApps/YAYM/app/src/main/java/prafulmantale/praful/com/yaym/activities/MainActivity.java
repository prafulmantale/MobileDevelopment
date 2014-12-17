package prafulmantale.praful.com.yaym.activities;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.application.YMApplication;
import prafulmantale.praful.com.yaym.fragments.CcyPairSettingsFragment;
import prafulmantale.praful.com.yaym.fragments.DashboardFragment;
import prafulmantale.praful.com.yaym.fragments.FrequencySettingsFragment;
import prafulmantale.praful.com.yaym.helpers.AppConstants;
import prafulmantale.praful.com.yaym.helpers.FragmentNavigationDrawer;
import prafulmantale.praful.com.yaym.interfaces.DashboardActionsListener;
import prafulmantale.praful.com.yaym.services.RWPollService;

public class MainActivity extends FragmentActivity implements DashboardActionsListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    public static String selectedCurrencyPair;

    private FragmentNavigationDrawer drawerLayout;
    private YMApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeActionBar();

        if(getIntent() != null) {
            selectedCurrencyPair = getIntent().getStringExtra(AppConstants.INTENT_KEY_CCYPAIR);
        }

        initialize();

        if(savedInstanceState == null){
            drawerLayout.selectDrawerItem(0);
        }
    }


    private void initialize(){

        application = (YMApplication)getApplication();
        drawerLayout = (FragmentNavigationDrawer)findViewById(R.id.drawerLayout);
        drawerLayout.setupDrawerConfiguration((ListView)findViewById(R.id.lvDrawerMenu),
                R.layout.drawer_item_list,
                R.id.frameContainer);

        drawerLayout.addNavigationItem("Dashboard", "Dashboard", DashboardFragment.class);
        drawerLayout.addNavigationItem("Risk Warehouse", "Risk Warehouse", null);
        drawerLayout.addNavigationItem("        Currency Pairs", "Currency Pairs", CcyPairSettingsFragment.class);
        drawerLayout.addNavigationItem("        Risk Policies", "Risk Policies", CcyPairSettingsFragment.class);
        drawerLayout.addNavigationItem("Customers", "Customers", null);
        drawerLayout.addNavigationItem("        Routing Rules", "Routing Rules", CcyPairSettingsFragment.class);
    }


    private void initializeActionBar(){
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_style));

        View view = getLayoutInflater().inflate(R.layout.action_bar_title, null);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setCustomView(view, params);
        actionBar.setDisplayShowHomeEnabled(true);

        View homeIcon = findViewById(android.R.id.home);
        ((View) homeIcon.getParent()).setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
//        if(count == 0){
//            showLogoutAlert();
//        }
//        else {
            super.onBackPressed();
            overridePendingTransition(R.anim.stay, R.anim.slide_out_from_right);
//        }
    }

    private void showLogoutAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.logout_confirm_message)
                .setCancelable(false)
                .setPositiveButton(R.string.logout_confirm_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                        overridePendingTransition (R.anim.slide_in_from_left, R.anim.slide_out_from_right);
                    }
                })
                .setNegativeButton(R.string.logout_confirm_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

        TextView textView = (TextView) dialog.findViewById(android.R.id.message);
        textView.setTypeface(application.getTypeface());

        Button yesButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        yesButton.setTypeface(application.getTypeface());

        Button noButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        noButton.setTypeface(application.getTypeface());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(drawerLayout.getDrawerToggle().onOptionsItemSelected(item)){
            return true;
        }

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.menuSettings:

                FrequencySettingsFragment.newInstance().show(getSupportFragmentManager(), "frequency_settings");
                return true;

            case R.id.menuAbout:
                startActivity(new Intent(this, AboutActivity.class));
                overridePendingTransition(R.anim.slide_in_from_top, R.anim.slide_out_from_top);
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        boolean drawerOpen = drawerLayout.isDrawerOpen();

        try {
            menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        }
        catch (Exception ex){

        }

        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerLayout.getDrawerToggle().syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        drawerLayout.getDrawerToggle().onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startPollService();
    }

    private void startPollService(){
        startService(new Intent(this, RWPollService.class));
    }

    private void stopPollService(){
        stopService(new Intent(this, RWPollService.class));
    }

    @Override
    protected void onDestroy() {
        stopPollService();
        super.onDestroy();
    }

    @Override
    public void onDetailsViewRequested(String ccyPair) {
        selectedCurrencyPair = ccyPair;

        Intent intent = new Intent(this, YieldDetailsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.stay);
    }
}

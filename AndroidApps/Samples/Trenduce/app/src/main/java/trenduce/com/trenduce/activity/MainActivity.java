package trenduce.com.trenduce.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import trenduce.com.trenduce.R;
import trenduce.com.trenduce.Utils.FragmentNavigationDrawer;
import trenduce.com.trenduce.fragments.DashboardFragment;

public class MainActivity extends FragmentActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private FragmentNavigationDrawer drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDashboard();

        initialize();
        
        if(savedInstanceState == null){
            drawerLayout.selectDrawerItem(0);
        }
    }

    private void initialize() {

        drawerLayout = (FragmentNavigationDrawer)findViewById(R.id.drawerLayout);
        drawerLayout.setupDrawerConfiguration((ListView)findViewById(R.id.lvDrawerMenu),
                R.layout.drawer_item_list,
                R.id.flContainer);

        drawerLayout.addNavigationItem("Prafulkumar Mantale", "Prafulkumar Mantale", null);
        drawerLayout.addNavigationItem("Dashboard", "Dashboard", DashboardFragment.class);
        drawerLayout.addNavigationItem("Invite Friends", "Invite Friends", null);
        drawerLayout.addNavigationItem("Settings", "Settings", null);

    }

    private void showDashboard(){

        DashboardFragment fragment = new DashboardFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.flContainer, fragment);

        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

package prafulmantale.praful.com.xerotouch;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setupTabs();
    }

    private void setupTabs() {

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);
        ActionBar.Tab tab = actionBar.newTab().setText("DASHBOARD")
                .setTabListener(new SupportFragmentTabListener<Fragment1>(R.id.flContainer, this,
                "first", Fragment1.class));
        actionBar.addTab(tab);
        actionBar.selectTab(tab);

        ActionBar.Tab tab2 = actionBar.newTab().setText("INVOICES")
                .setTabListener(new SupportFragmentTabListener<Fragment2>(R.id.flContainer, this,
                        "two", Fragment2.class));
        actionBar.addTab(tab2);

        ActionBar.Tab tab3 = actionBar.newTab().setText("EXPENSES")
                .setTabListener(new SupportFragmentTabListener<Fragment3>(R.id.flContainer, this,
                        "three", Fragment3.class));
        actionBar.addTab(tab3);

        ActionBar.Tab tab4 = actionBar.newTab().setText("CONTACTS")
                .setTabListener(new SupportFragmentTabListener<Fragment4>(R.id.flContainer, this,
                        "four", Fragment4.class));
        actionBar.addTab(tab4);


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

package prafulmantale.listexplore.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.listexplore.R;
import prafulmantale.listexplore.adapters.TwoLineRowAdapter;
import prafulmantale.listexplore.models.TwoLineRowModel;


public class SimpleListActivity extends Activity {

    private ListView listView;
    private TwoLineRowAdapter adapter;
    private List<TwoLineRowModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list);

        list = new ArrayList<TwoLineRowModel>();
        listView = (ListView)findViewById(R.id.listView);
        adapter = new TwoLineRowAdapter(this.getBaseContext(), list);
        listView.setAdapter(adapter);

        initialize();
    }

    private void initialize() {

        list.add(new TwoLineRowModel("FirstLine1", "SecodLine1"));
        list.add(new TwoLineRowModel("FirstLine2", "SecodLine2"));
        list.add(new TwoLineRowModel("FirstLine3", "SecodLine3"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.simple_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

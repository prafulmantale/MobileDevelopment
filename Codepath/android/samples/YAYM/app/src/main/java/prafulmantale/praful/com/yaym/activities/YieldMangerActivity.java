package prafulmantale.praful.com.yaym.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.adapters.PositionsAdapter;
import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;


public class YieldMangerActivity extends Activity {

    private ListView lvPositions;
    private ArrayAdapter adapter;
    private List<RWPositionSnapshot> snapshots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yield_manger);

        initialize();
    }

    private void initialize(){

        lvPositions = (ListView)findViewById(R.id.lvPositionsList);
        View headerView = ((LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_positions_header, null, false);

        lvPositions.addHeaderView(headerView);
        lvPositions.setHeaderDividersEnabled(true);
        lvPositions.setDividerHeight(1);

        snapshots = new ArrayList<RWPositionSnapshot>();
        snapshots.add(new RWPositionSnapshot());
        snapshots.add(new RWPositionSnapshot());
        adapter = new PositionsAdapter(getBaseContext(), snapshots);
        lvPositions.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.yield_manger, menu);
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

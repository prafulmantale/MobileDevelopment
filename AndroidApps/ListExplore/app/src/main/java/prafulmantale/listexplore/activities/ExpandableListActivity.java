package prafulmantale.listexplore.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import prafulmantale.listexplore.R;

import prafulmantale.listexplore.adapters.MyExpandableListAdapter;
import prafulmantale.listexplore.models.Group;

public class ExpandableListActivity extends Activity {

    SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);
        
        createData();

        ExpandableListView listView = (ExpandableListView)findViewById(R.id.expandableListView);

        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this, groups);

        View header = getLayoutInflater().inflate(R.layout.header, null);
        View footer = getLayoutInflater().inflate(R.layout.footer, null);
        listView.addHeaderView(header);
        listView.addFooterView(footer);

        listView.setAdapter(adapter);
    }

    private void createData() {

        for (int i = 0; i < 5; i++) {
            Group group = new Group("Test " + i);

            for (int j = 0; j < 3; j++) {
                group.getChildren().add("Sub Item" + j);
            }

            groups.append(i, group);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.expandable_list, menu);
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

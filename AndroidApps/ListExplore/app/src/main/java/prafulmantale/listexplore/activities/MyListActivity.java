package prafulmantale.listexplore.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import prafulmantale.listexplore.R;

public class MyListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String []values = new String[]{"Windows", "Mac", "Unix", "Linux"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.rowlayout, R.id.label, values);

        setListAdapter(adapter);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        String item = (String)getListAdapter().getItem(position);

        Toast.makeText(this, item + " Selected", Toast.LENGTH_SHORT).show();

    }
}

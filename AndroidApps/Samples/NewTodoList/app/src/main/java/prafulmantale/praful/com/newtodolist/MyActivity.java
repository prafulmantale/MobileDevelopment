package prafulmantale.praful.com.newtodolist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MyActivity extends Activity {

    private ToDoItemAdapter adapter;
    private ArrayList<ToDoItem> list;
    private DynamicListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        list = new ArrayList<ToDoItem>();
        list.add(new ToDoItem("Item 1"));
        list.add(new ToDoItem("Item 2"));
        list.add(new ToDoItem("Item 3"));
        list.add(new ToDoItem("Item 4"));

        listView = (DynamicListView)findViewById(R.id.listView);
        listView.setCheeseList(list);

        adapter = new ToDoItemAdapter(this, list);

        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }
}

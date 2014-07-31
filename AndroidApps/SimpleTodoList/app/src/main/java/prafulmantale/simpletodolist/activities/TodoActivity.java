package prafulmantale.simpletodolist.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import prafulmantale.simpletodolist.R;


public class TodoActivity extends Activity {

    private final String DATA_FILE = "todo.txt";
    private final int REQUEST_CODE = 200;
    private File filesDir;
    private File todoFile;

    private List<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        initialize();
        //addTestData();
    }

    //Initialize
    private void initialize(){
        lvItems = (ListView)findViewById(R.id.lvItems);

        //items = new ArrayList<String>();
        //Initialize data source
        filesDir = getFilesDir();
        todoFile = new File(filesDir, DATA_FILE);

        readItems();

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);

        setupListeners();
    }

    private void setupListeners(){
        setupListViewListener();
    }

    private void addTestData(){
        items.add("First item");
        items.add("Second item");
    }

    public void addTodoItem(View view){
        EditText editText = (EditText)findViewById(R.id.eNewItem);

        String newItem = editText.getText().toString();

        if(newItem == null || newItem.isEmpty()){
            Toast.makeText(getBaseContext(),R.string.item_invalid_input, Toast.LENGTH_SHORT).show();
            return;
        }

        items.add(newItem);
        editText.setText("");

        notifyDataChange();
    }

    private void setupListViewListener(){
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long rowId) {
                items.remove(position);

                notifyDataChange();

                Toast.makeText(getBaseContext(), R.string.item_delete_successful, Toast.LENGTH_SHORT).show();

                return true;//Check why does it return true or false
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowId) {
                Intent intent = new Intent(TodoActivity.this, EditItemActivity.class);
                intent.putExtra("item", items.get(position));
                intent.putExtra("position", position);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            if(data.hasExtra("item") && data.hasExtra("position")) {
                String item = data.getStringExtra("item");
                int position = data.getIntExtra("position",-1);
                if(position != -1) {
                    items.set(position, item);
                    notifyDataChange();
                    Toast.makeText(getBaseContext(), R.string.item_edit_successful, Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            Toast.makeText(getBaseContext(), R.string.item_edit_failed, Toast.LENGTH_SHORT).show();
        }
    }

    //Read items from file/data source
    private void readItems(){

        try {

            if(todoFile != null) {
                items = new ArrayList<String>(FileUtils.readLines(todoFile));
            }
        }
        catch (IOException ex){
            items = new ArrayList<String>();
            ex.printStackTrace();
        }
    }


    //Save items to file/data source
    private void saveItems(){

        try {
            FileUtils.writeLines(todoFile, items);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void notifyDataChange(){
        itemsAdapter.notifyDataSetChanged(); // notify UI
        saveItems();//Notify to data source  i.e. todo.txt
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.todo, menu);
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

package prafulmantale.todoapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ToDoActivity extends Activity {

    ListView listView;
    EditText editText;
    Button button;
    //List<String> list;
    List<ToDoListItem> list;
    //ArrayAdapter<String> adapter;
    ToDoListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);//View that we are going to interact with

        init();

    }

    private void init(){
        initSeedValues();
        initViewObjects();
        initHolders();
        //initSeedValues();
    }
    private void initViewObjects(){
        listView = (ListView)findViewById(R.id.listView2);
        button = (Button)findViewById(R.id.addButton);
        editText = (EditText)findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText() == null || editText.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(), "Can not add empty item", Toast.LENGTH_SHORT).show();
                    return;
                }

                String item = editText.getText().toString();
                if(list.contains(item)){
                    Toast.makeText(getBaseContext(), "Can not add duplicate item", Toast.LENGTH_SHORT).show();
                    return;
                }

                list.add(new ToDoListItem(item));
                adapter.notifyDataSetChanged();
                editText.setText("");
                saveItems();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                list.remove(position);
                adapter.notifyDataSetChanged();
                saveItems();
                return false;
            }
        });

    }

    public void removeItem(View view){
        ToDoListItem item = (ToDoListItem) view.getTag();
        adapter.remove(item);
        adapter.notifyDataSetChanged();
        saveItems();
    }

    public void saveItem(View view){

        EditText et = (EditText)view.getTag();
        ToDoListItem item = (ToDoListItem) et.getTag();

        item.setItem(et.getText().toString());
        adapter.notifyDataSetChanged();
        saveItems();
    }

    public void edittextclicked(View view){
        view.setSelected(true);

    }

    private void initHolders(){

        //list = new ArrayList<String>();
        //adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, list);
        adapter = new ToDoListAdapter(this, list);
        listView.setAdapter(adapter);
    }

    private void initSeedValues(){
        if(list == null){
            //list = new ArrayList<String>();
            list = new ArrayList<ToDoListItem>();
        }

//        list.add("Get up");
//        list.add("Get ready");
//        list.add("Attend startup class");
        File file = getFilesDir();

        try {
            File todofile = new File(file, "todo.txt");
            ArrayList<String> ll = new ArrayList<String>(FileUtils.readLines(todofile));
            for(String s : ll){
                list.add(new ToDoListItem(s));
            }

        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void saveItems(){
        File file = getFilesDir();
        File todofile = new File(file, "todo.txt");
        try {
            ArrayList<String> ll = new ArrayList<String>();
            for(ToDoListItem it : list) {
                ll.add(it.getItem());
            }
            //FileUtils.writeLines(todofile, list);
            FileUtils.writeLines(todofile, ll);
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.to_do, menu);
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

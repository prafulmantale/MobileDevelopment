package prafulmantale.simpletodolist.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import prafulmantale.simpletodolist.R;
import prafulmantale.simpletodolist.adapters.ToDoItemAdapter;
import prafulmantale.simpletodolist.daos.ToDoItemDAO;
import prafulmantale.simpletodolist.dialogs.EditItemDialog;
import prafulmantale.simpletodolist.models.ToDoItem;


public class TodoActivity extends FragmentActivity implements EditItemDialog.EditItemDialogListener, ToDoItemAdapter.ItemDetailsListener
, ActionBar.TabListener{

    private final String DATA_FILE = "todo.txt";
    private final int REQUEST_CODE = 200;
    private File filesDir;
    private File todoFile;

    //private List<String> items;
    private List<ToDoItem> items;

    //private ArrayAdapter<String> itemsAdapter;
    private ToDoItemAdapter itemsAdapter;
    private ListView lvItems;

    private ToDoItemDAO toDoItemDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        //addTabs();
         initialize();
         //addTestData();
    }

    //Initialize
    private void initialize(){

        toDoItemDAO = new ToDoItemDAO(this);
        toDoItemDAO.openConnection();

        lvItems = (ListView)findViewById(R.id.lvItems);

        items = new ArrayList<ToDoItem>();
        //Initialize data source
        filesDir = getFilesDir();
        todoFile = new File(filesDir, DATA_FILE);

        //readItems();
        readItemsFromDB();

        //itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        itemsAdapter = new ToDoItemAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);

        setupListeners();


    }

    private void addTabs(){
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab = actionBar.newTab().setText(R.string.tab_showall);
        tab.setTabListener(this);
        actionBar.addTab(tab);
       // tab.setTabListener(new FragmentTabListener<ShowAllFragment>(R.id.showallfragment, this, "ShowAll", ShowAllFragment.class));

        tab = actionBar.newTab().setText(R.string.tab_hidecompleted);
        tab.setTabListener(this);
        //tab.setTabListener(new FragmentTabListener<HideCompletedFragment>(R.id.hidecompletedfragment, this, "HideCompleted", HideCompletedFragment.class));

        actionBar.addTab(tab);
    }

    private void setupListeners(){
        setupListViewListener();
    }

    private void addTestData(){
        //items.add("First item");
        //items.add("Second item");
        items.add(new ToDoItem("First item"));
        items.add(new ToDoItem("Second item"));
    }

    public void addTodoItem(View view){
        EditText editText = (EditText)findViewById(R.id.eNewItem);

        String newItem = editText.getText().toString();

        if(newItem == null || newItem.isEmpty()){
            Toast.makeText(getBaseContext(),R.string.item_invalid_input, Toast.LENGTH_SHORT).show();
            return;
        }

        //items.add(newItem);
        //items.add(new ToDoItem(newItem));
        items.add(toDoItemDAO.createToDoItem(newItem));
        editText.setText("");

        notifyDataChange();
    }

    private void setupListViewListener(){
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long rowId) {

                final int pos = position;
                final View v = view;

                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    view.animate().setDuration(1000).alpha(0).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            //super.onAnimationEnd(animation);

                            //???? move to a helper method
                            toDoItemDAO.deleteToDoItem(items.get(pos));
                            items.remove(pos);

                            notifyDataChange();
                            v.setAlpha(1);
                            Toast.makeText(getBaseContext(), R.string.item_delete_successful, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {

                    view.animate().setDuration(1000).alpha(0).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            toDoItemDAO.deleteToDoItem(items.get(pos));
                            items.remove(pos);

                            notifyDataChange();
                            v.setAlpha(1);
                            Toast.makeText(getBaseContext(), R.string.item_delete_successful, Toast.LENGTH_SHORT).show();
                        }
                    });
                }


                return true;//Check why does it return true or false
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowId) {

                //startEditItemActivity(position);
                //showEditItemDialog(position);
            }
        });
    }

    private void startItemDetailsActivity(int position){
        Intent intent = new Intent(TodoActivity.this, ItemDetails.class);
        intent.putExtra(ItemDetails.EXTRA_ITEM, items.get(position));
        intent.putExtra(ItemDetails.EXTRA_POSITION, position);
        startActivityForResult(intent, REQUEST_CODE);
    }
    private void startEditItemActivity(int position){
        Intent intent = new Intent(TodoActivity.this, EditItemActivity.class);
        //intent.putExtra("item", items.get(position));
        intent.putExtra(ItemDetails.EXTRA_ITEM, items.get(position).getItem());
        intent.putExtra(ItemDetails.EXTRA_POSITION, position);
        startActivityForResult(intent, REQUEST_CODE);
    }


    private void showEditItemDialog(int position){
        FragmentManager manager = getFragmentManager();

        //String item = items.get(position);
        String item = items.get(position).getItem();
        EditItemDialog dialog = EditItemDialog.newInstance("Edit Item Below:", item, position);
        dialog.show(manager, "fragment_edit_item");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            if(data.hasExtra(ItemDetails.EXTRA_ITEM) && data.hasExtra(ItemDetails.EXTRA_POSITION)) {
                String item = data.getStringExtra(ItemDetails.EXTRA_ITEM);
                int position = data.getIntExtra(ItemDetails.EXTRA_POSITION,-1);

                updateEditedItem(item, position);
            }
            else if(data.hasExtra(ItemDetails.EXTRA_ITEMD) && data.hasExtra(ItemDetails.EXTRA_POSITION)){
                ToDoItem toDoItem = (ToDoItem)data.getSerializableExtra(ItemDetails.EXTRA_ITEMD);
                int position = data.getIntExtra(ItemDetails.EXTRA_POSITION,-1);
                updateEditedItem(toDoItem, position);
            }
        }
        else{
            Toast.makeText(getBaseContext(), R.string.item_edit_not_done, Toast.LENGTH_SHORT).show();
        }
    }

    private void readItemsFromDB(){
        items = toDoItemDAO.getAll();
    }
    //Read items from file/data source
    private void readItems(){

        try {

            if(todoFile != null) {
                //items = new ArrayList<String>(FileUtils.readLines(todoFile));
                List<String> listOfItems = new ArrayList<String>(FileUtils.readLines(todoFile));
                for(String item : listOfItems){
                    items.add(new ToDoItem(item));
                }
            }
        }
        catch (IOException ex){
            //items = new ArrayList<String>();
            items = new ArrayList<ToDoItem>();
            ex.printStackTrace();
        }
    }


    //Save items to file/data source
    private void saveItems(){

        try {
            //FileUtils.writeLines(todoFile, items);
            List<String> listOfItems = new ArrayList<String>();
            for(ToDoItem item : items){
                listOfItems.add(item.getItem());
            }

            FileUtils.writeLines(todoFile, listOfItems);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void notifyDataChange(){
        itemsAdapter.notifyDataSetChanged(); // notify UI
        //saveItems();//Notify to data source  i.e. todo.txt
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

    @Override
    public void onFinishEditDialog(String item, int position) {
        updateEditedItem(item, position);
    }

    /*
     * Update the items list if the new item text is proper and show the success toast else show unsuccessful toast
     */
    private void updateEditedItem(String item, int position){
        if(position != -1) {
            //items.set(position, item);
            items.get(position).setItem(item);
            toDoItemDAO.updateToDoItem(items.get(position));
            notifyDataChange();
            //Toast.makeText(getBaseContext(), R.string.item_edit_successful, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getBaseContext(), R.string.item_edit_not_done, Toast.LENGTH_SHORT).show();
        }
    }

    private void updateEditedItem(ToDoItem item, int position){
        if(position != -1) {
            items.set(position, item);
            toDoItemDAO.updateToDoItem(item);
            notifyDataChange();
            //Toast.makeText(getBaseContext(), R.string.item_edit_successful, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getBaseContext(), R.string.item_edit_not_done, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.todo, menu);
        return true;
    }

    @Override
    protected void onDestroy() {

        //toDoItemDAO.closeConnection();

        super.onDestroy();
    }

    @Override
    public void OnItemDetailsRequested(int position) {
        startItemDetailsActivity(position);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            // When the given tab is selected, show the tab contents in the
            // container view.
            Fragment fragment = new DummySectionFragment();
            Bundle args = new Bundle();
            args.putInt(DummySectionFragment.ARG_SECTION_NUMBER,
                    tab.getPosition() + 1);
            fragment.setArguments(args);
            getFragmentManager().beginTransaction();
                    //.replace(R.id.container, fragment).commit();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public static class DummySectionFragment extends Fragment {
        public static final String ARG_SECTION_NUMBER = "placeholder_text";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            TextView textView = new TextView(getActivity());
            textView.setGravity(Gravity.CENTER);
            textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return textView;
        }
    }
}

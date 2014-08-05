package prafulmantale.simpletodolist.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import prafulmantale.simpletodolist.R;
import prafulmantale.simpletodolist.models.ToDoItem;

public class ItemDetails extends Activity {

    private CheckBox scheduleCheckbox;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private ToDoItem toDoItem;
    private int position;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        initialize();

    }


    private void initialize(){
        Intent intent = getIntent();
        if(intent.hasExtra("item") && intent.hasExtra("position")) {
            toDoItem = (ToDoItem)intent.getSerializableExtra("item");
            position = intent.getIntExtra("position", -1);
        }
        else{
            finish();
            return;
        }

        scheduleCheckbox = (CheckBox)findViewById(R.id.itemCheck);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        timePicker = (TimePicker)findViewById(R.id.timepicker);
        editText = (EditText)findViewById(R.id.itemLabel);

        scheduleCheckbox.setChecked(toDoItem.isDueDateConfigured());
        editText.setText(toDoItem.getItem());

        setupListeners();
    }

    private void setupListeners(){
        scheduleCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    datePicker.setVisibility(View.VISIBLE);
                    timePicker.setVisibility(View.VISIBLE);
                    final Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    datePicker.updateDate(year, month, day);
                }
                else{
                    datePicker.setVisibility(View.INVISIBLE);
                    timePicker.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void saveItem(View view){

        String newItemValue = editText.getText().toString();
        if(newItemValue == null || newItemValue.isEmpty()){
            //No Op
        }
        else{
            Intent data = new Intent();
            toDoItem.setItem(newItemValue);
            toDoItem.setDueDateConfigured(scheduleCheckbox.isChecked());
            data.putExtra("itemd", toDoItem);
            data.putExtra("position", position);
            setResult(RESULT_OK, data);
        }

        finish();
    }

    public void cancelSaveItem(View view){
        finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.item_details, menu);
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

package prafulmantale.simpletodolist.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import prafulmantale.simpletodolist.R;

public class EditItemActivity extends Activity {

    private String item;
    private int position;
    private EditText editText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        initialize();
    }


    private void initialize(){
        Intent intent = getIntent();
        if(intent.hasExtra("item") && intent.hasExtra("position")) {
            item = getIntent().getStringExtra("item");
            position = getIntent().getIntExtra("position", -1);

            editText = (EditText)findViewById(R.id.eEditedItem);
            editText.setText(item);
        }
        else{
            finish();
        }
    }

    public void saveItem(View view){

        String newItemValue = editText.getText().toString();
        if(newItemValue == null || newItemValue.isEmpty()){
            //No Op
        }
        else{
            Intent data = new Intent();
            data.putExtra("item", newItemValue);
            data.putExtra("position", position);
            setResult(RESULT_OK, data);
        }

        finish();
    }

    public void cancelSaveItem(View view){
        finish();
    }


    @Override
    public void finish(){
        super.finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_item, menu);
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

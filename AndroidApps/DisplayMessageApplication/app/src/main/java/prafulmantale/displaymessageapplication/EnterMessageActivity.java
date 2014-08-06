package prafulmantale.displaymessageapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class EnterMessageActivity extends Activity {

    private EditText editText;
    public static final String DISPLAY_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_message);

        editText = (EditText)findViewById(R.id.edittext);
    }

    public void sendMessage(View view){

        Intent intent = new Intent(EnterMessageActivity.this, DisplayMessageActivity.class);
        intent.putExtra(DISPLAY_MESSAGE, editText.getText().toString());
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.enter_message, menu);
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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_search:
                opensearch();
                return true;

            case R.id.action_settings:
                opensettings();
                return true;

            default:
                return super.onMenuItemSelected(featureId, item);
        }
    }

    private void opensearch() {


    }

    private void opensettings(){

    }
}

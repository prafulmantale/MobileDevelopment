package prafulmantale.yamba1.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import prafulmantale.yamba1.R;


public class StatusActivity extends Activity {

    private final static String TAG = "StatusActivity";
    private Button updateButton;
    private EditText editStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_activity);

        initialize();

        setupListeners();
    }

    private void setupListeners() {

//        updateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String status = editStatus.getText().toString();
//                if(status == null || status.isEmpty() || status.trim().isEmpty()) {
//                    Log.d(TAG, "Update button clicked");
//                }
//                else{
//                    Log.d(TAG, status);
//                }
//            }
//        });
    }

    public void updateClicked(View view){
        String status = editStatus.getText().toString();
        if(status == null || status.isEmpty() || status.trim().isEmpty()) {
            Log.d(TAG, "updateClicked - Update button clicked");
        }
        else{
            Log.d(TAG, status);
        }
    }


    private void initialize(){
        updateButton = (Button)findViewById(R.id.updateButton);
        editStatus = (EditText)findViewById(R.id.statusMessage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.status_activty, menu);
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

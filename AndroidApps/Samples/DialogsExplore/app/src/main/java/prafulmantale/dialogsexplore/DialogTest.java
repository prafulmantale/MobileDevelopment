package prafulmantale.dialogsexplore;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class DialogTest extends Activity {

    private static final int ALERT_DIALOG_ID = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dialog_test, menu);
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

    public void showAlert(View view){

        System.out.println("In show alert");

        showDialog(ALERT_DIALOG_ID);

    }

    @Override
    protected Dialog onCreateDialog(int id) {

        switch (id){
            case ALERT_DIALOG_ID:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("End activity");
                builder.setCancelable(true);
                builder.setPositiveButton("I agree", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DialogTest.this.finish();
                    }
                });

                builder.setNegativeButton("Do not agree", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getApplicationContext(), "Canceling action", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                break;

        }

        return super.onCreateDialog(id);
    }
}

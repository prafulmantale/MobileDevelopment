package prafulmantale.intentandintentfilters.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import prafulmantale.intentandintentfilters.R;


/*
    Asynchronous messages from one component to another
    Components can be from same or different applications
    Send the object to android system telling the target
    Data can be sent using Bundle for the receiving component to use

 */
public class MainActivity extends Activity {

    public static final String MESSAGE_HOLDER = "message";
    public static final String BACK_MSG_HOLDER = "back";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void openTarget(View view){
        startExplicitIntent();
    }

    public static boolean isIntentAvailable(Context ctx, Intent intent){
        final PackageManager packageManager = ctx.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        return list.size() > 0;
    }

    private void startMyIntent(){
        Intent intent = new Intent(Intent.ACTION_ANSWER);
        intent.setType("text/plain");
        //intent.putExtra(MESSAGE_HOLDER, "News for you");
        startActivity(intent);

    }
    private void startBrowser(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.google.com"));
        startActivity(intent);

    }

    private void startSharedInten(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "News for you");
        startActivity(intent);
    }

    private void startExplicitIntent(){
        Intent intent = new Intent(MainActivity.this, TargetActivity.class);
        intent.putExtra(MESSAGE_HOLDER, "I am calling you");
        startActivity(intent);
    }

    private void startActvityForResult(){
        Intent intent = new Intent(MainActivity.this, TargetActivity.class);
        intent.putExtra(MESSAGE_HOLDER, "I am calling you");

        startActivityForResult(intent, 20);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 20 && resultCode == RESULT_OK) {
            if(data.hasExtra(BACK_MSG_HOLDER)) {
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(data.getStringExtra(BACK_MSG_HOLDER));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

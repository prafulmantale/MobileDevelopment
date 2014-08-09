package prafulmantale.appandactivitylifecycle;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;


public class LifeCycle extends Activity {


    private Spinner spinner;

    /*
    Called before first component of the application starts
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        spinner = (Spinner)findViewById(R.id.spinner);

        //Application object starts before any of the component of app start and runs as long as at least one component is running

        Application application = getApplication();

        System.out.println(application.getApplicationInfo().processName);
        notify("onCreate");
    }

    @Override
    protected void onPause() {
        super.onPause();
        notify("onPause");
    }


    @Override
    protected void onResume() {
        super.onResume();
        notify("onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        notify("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        notify("onDestroy");
    }

    /*
                        Called when Android system requests that the application cleans up the memory
                     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    /*
        Called when the configuration of the current device changes
        Activities are restarted.
        Non persistent data required for restoring the activities state between restarts should be save and passed along during restart

        Typical examples: Orientation, Locale, Smallest width

     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        System.out.println("************Inside the configuration change");
        super.onConfigurationChanged(newConfig);
    }



    /*
        Save the instance state so that it can be recreated in the OnResume once the activity is active again
        Can store : primitive data types, arrays, strings, Parcelable or serializable objects

        Back or finish of activity - not called as the activity is removed from the activity stack and recycled
        But if user presses home button, it must be saved
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("spinnersel", spinner.getSelectedItemPosition());
        super.onSaveInstanceState(outState);
        notify("onSaveInstanceState");
    }

    /*
    Called on create , used to restore the state from saved instance bundle


     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null )
            spinner.setSelection(savedInstanceState.getInt("spinnersel"));
        notify("onRestoreInstanceState");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.life_cycle, menu);
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


    private void notify(String methodName){
        String className = this.getClass().getName();

        String []strings = className.split("\\.");

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(methodName + " " + strings[strings.length - 1]);
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentText(className);
        Notification notification = builder.getNotification();


        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify((int)System.currentTimeMillis(), notification);

    }

    public void openSecond(View view){
        Intent intent = new Intent(LifeCycle.this, SecondActivity.class);
        startActivity(intent);
    }

}

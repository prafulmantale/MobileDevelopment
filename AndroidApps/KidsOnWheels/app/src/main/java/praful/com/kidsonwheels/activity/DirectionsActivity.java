package praful.com.kidsonwheels.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import praful.com.kidsonwheels.R;
import praful.com.kidsonwheels.fragment.DirectionsFragment;
import praful.com.kidsonwheels.interfaces.DirectionsFragmentActionsListener;
import praful.com.kidsonwheels.model.Student;

public class DirectionsActivity extends ActionBarActivity implements DirectionsFragmentActionsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.activity_home_container, new DirectionsFragment()).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_directions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void pickupStateUpdated(Student student, Student.PickupState newState) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition (R.anim.slide_in_from_left, R.anim.slide_out_from_right);
    }

    @Override
    public void ShowTravelSteps() {
        Intent intent = new Intent(this, TravelStepsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.stay);
    }
}

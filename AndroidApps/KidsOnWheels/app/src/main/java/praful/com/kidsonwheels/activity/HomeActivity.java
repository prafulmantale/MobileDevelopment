package praful.com.kidsonwheels.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import praful.com.kidsonwheels.R;
import praful.com.kidsonwheels.fragment.StudentsFragment;
import praful.com.kidsonwheels.manager.DataManager;


public class HomeActivity extends ActionBarActivity {

    private View mHeaderView;
    private Button mActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupViews();
        setupListeners();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.activity_home_container, new StudentsFragment()).commit();
        }
    }

    private void setupViews(){
        mHeaderView = findViewById(R.id.header_view);
        mActionButton = (Button)findViewById(R.id.action_button);
    }

    private void setupListeners(){
        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DataManager.getInstance().isAllDone()){
                    Toast.makeText(HomeActivity.this, "All pickups done", Toast.LENGTH_LONG).show();
                    return;
                }
                startActivity(new Intent(HomeActivity.this, DirectionsActivity.class));
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

}

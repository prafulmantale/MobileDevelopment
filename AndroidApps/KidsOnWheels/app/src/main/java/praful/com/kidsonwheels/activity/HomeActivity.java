package praful.com.kidsonwheels.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import praful.com.kidsonwheels.R;
import praful.com.kidsonwheels.application.KOWApplication;
import praful.com.kidsonwheels.fragment.StudentsFragment;
import praful.com.kidsonwheels.manager.DataManager;
import praful.com.kidsonwheels.utils.LocationUtils;


public class HomeActivity extends ActionBarActivity {

    private View mHeaderView;
    private Button mActionButton;
    private KOWApplication mApplication;
    private View mEmptyView;
    private static final String HOME_FRAG_TAG = "home_frag";
    private static final int LOCATION_REQ_CODE = 501;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupViews();
        setupListeners();

        if(LocationUtils.isLocationProviderAvailable(this)) {
            showFragment();
        }
        else{
            showEmptyView();
        }

        KOWApplication.bus.register(this);
    }

    private void showFragment(){
        if(mEmptyView != null) {
            mEmptyView.setVisibility(View.GONE);
        }

        Fragment fragment =  getSupportFragmentManager().findFragmentByTag(HOME_FRAG_TAG);
        if(fragment == null){
            getSupportFragmentManager().beginTransaction().add(R.id.activity_home_container, new StudentsFragment(), HOME_FRAG_TAG).commit();
        }
    }

    private void showEmptyView(){
        if(mEmptyView != null){
            return;
        }

        ViewStub viewStub = (ViewStub)findViewById(R.id.error_view);
        mEmptyView = viewStub.inflate();
        Button button = (Button)mEmptyView.findViewById(R.id.enable_location_service_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent locationIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(locationIntent, LOCATION_REQ_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LOCATION_REQ_CODE){
            if(LocationUtils.isLocationProviderAvailable(this)){
                showFragment();
            }
        }
    }

    @Subscribe
    public void getMessage(String s){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
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

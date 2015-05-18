package praful.com.kidsonwheels.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import praful.com.kidsonwheels.R;
import praful.com.kidsonwheels.utils.LocationUtils;
import praful.com.kidsonwheels.utils.NetworkUtils;


public class WelcomeActivity extends ActionBarActivity {

    @InjectView(R.id.progressBar) ProgressBar mProgressBar;
    @InjectView(R.id.enable_location_button) Button mEnableLocationButton;
    @InjectView(R.id.location_service_error_layout) View mLocationServiceErrorLayout;
    @InjectView(R.id.retry_button) Button mRetryButton;
    @InjectView(R.id.network_connection_error_layout) View mNetworkConnectionErrorLayout;

    private static final int LOCATION_REQ_CODE = 501;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupViews();
    }

    private void setupViews() {
        mProgressBar.setVisibility(View.GONE);
        boolean requiredServicesAvailable = true;
        mLocationServiceErrorLayout.setVisibility(View.GONE);
        mNetworkConnectionErrorLayout.setVisibility(View.GONE);
        if (!LocationUtils.isLocationProviderAvailable(getBaseContext())) {
            mLocationServiceErrorLayout.setVisibility(View.VISIBLE);
            return;
        }
        if (!NetworkUtils.isNetworkAvailable(getBaseContext())) {
            mNetworkConnectionErrorLayout.setVisibility(View.VISIBLE);
            return;
        }
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @OnClick(R.id.enable_location_button)
    void onEnableLocation(View view) {
        Intent locationIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(locationIntent, LOCATION_REQ_CODE);
    }

    @OnClick(R.id.retry_button)
    void onRetry(View view){
        setupViews();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOCATION_REQ_CODE) {
            if (LocationUtils.isLocationProviderAvailable(this)) {
                setupViews();
            }
        }
    }
}

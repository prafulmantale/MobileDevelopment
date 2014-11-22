package prafulmantale.praful.com.yaym.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.adapters.SnapshotAdapter;
import prafulmantale.praful.com.yaym.application.YMApplication;
import prafulmantale.praful.com.yaym.caches.RWSummaryCache;
import prafulmantale.praful.com.yaym.caches.RulesCache;
import prafulmantale.praful.com.yaym.caches.SnapshotCache;
import prafulmantale.praful.com.yaym.enums.APIRequest;
import prafulmantale.praful.com.yaym.enums.RequestStatus;
import prafulmantale.praful.com.yaym.handlers.NetworkResponseHandler;
import prafulmantale.praful.com.yaym.helpers.AppConstants;
import prafulmantale.praful.com.yaym.interfaces.NetworkResponseListener;
import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;
import prafulmantale.praful.com.yaym.models.RWSummary;
import prafulmantale.praful.com.yaym.services.RWPollService;
import prafulmantale.praful.com.yaym.widgets.YieldPercentageView;


public class YieldMangerActivity extends Activity implements NetworkResponseListener{

    private static final String TAG = YieldMangerActivity.class.getSimpleName();

    private ListView lvPositions;
    private SnapshotAdapter adapter;
    private List<RWPositionSnapshot> snapshots;
    private RWPositionSnapshot prevSelectedSnapshot;
//    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean swipedToRefresh = false;

    private YMApplication application;
    private ProgressDialog progressDialog;

    private YieldPercentageView yieldPercentageView;
    private TextView tvYieldValue;
    private TextView tvVolumeValue;
    private TextView tvRelaizedPnLValue;
    private TextView tvUnrealizedPnLValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yield_manger);

        progressDialog = ProgressDialog.show(this, "", getString(R.string.loading_progress_message));
        initialize();
//        initializeActionBar();

        getRules();
        setupListeners();
    }

    @Override
    protected void onStart() {

        registerReceiver(marketDataReceiver,
                new IntentFilter(AppConstants.RW_SNAPSHOT_RECEIVED));

        super.onStart();
    }

    @Override
    protected void onStop() {

        unregisterReceiver(marketDataReceiver);

        super.onStop();
    }

    private void initialize(){

        application = (YMApplication)getApplication();

        lvPositions = (ListView)findViewById(R.id.lvPositionsList);
        View headerView = findViewById(R.id.lvHeader);//((LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_positions_header, null, false);

        TextView tvCcyPair = (TextView)headerView.findViewById(R.id.tvCurrencyPair_header);
        TextView tvPositionsStatus = (TextView)headerView.findViewById(R.id.tvPosition_header);
        TextView tvUnrealizedPnL = (TextView)headerView.findViewById(R.id.tvUnrealizedPnl_header);
        tvCcyPair.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf"));
        tvPositionsStatus.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf"));
        tvUnrealizedPnL.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf"));

        TextView tvYieldText = (TextView)findViewById(R.id.tvYeildText);
        tvYieldText.setText(Html.fromHtml(getString(R.string.risk_capacity_yield_usd_mio_text)));
        tvYieldText.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf"));

        TextView tvVolumeText = (TextView)findViewById(R.id.tvVolumeText);
        tvVolumeText.setText(Html.fromHtml(getString(R.string.risk_capacity_volume_usd_thousand_text)));
        tvVolumeText.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf"));

        TextView tvRelPnLText = (TextView)findViewById(R.id.tvRealizedPnLText);
        tvRelPnLText.setText(Html.fromHtml(getString(R.string.risk_capacity_realized_pnl_usd_text)));
        tvRelPnLText.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf"));

        TextView tvUnRelPnLText = (TextView)findViewById(R.id.tvUnRealizedPnLText);
        tvUnRelPnLText.setText(Html.fromHtml(getString(R.string.risk_capacity_unrealized_pnl_usd_text)));
        tvUnRelPnLText.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf"));

        tvYieldValue = (TextView)findViewById(R.id.tvYieldValue);
        tvYieldValue.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf"));


        tvVolumeValue = (TextView)findViewById(R.id.tvVolumeValue);
        tvVolumeValue.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf"));

        tvRelaizedPnLValue = (TextView)findViewById(R.id.tvRealizedPnLValue);
        tvRelaizedPnLValue.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf"));

        tvUnrealizedPnLValue = (TextView)findViewById(R.id.tvUnRealizedPnLValue);
        tvUnrealizedPnLValue.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf"));

        yieldPercentageView = (YieldPercentageView)findViewById(R.id.ypv);

        //lvPositions.addHeaderView(headerView);
        //lvPositions.setHeaderDividersEnabled(true);

//        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
//        swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);

    }

    private void setupListeners(){
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipedToRefresh = true;
//                startService(new Intent(YieldMangerActivity.this, RefreshService.class));
//            }
//        });
    }

    private void initializeActionBar(){

        ActionBar actionBar = getActionBar();

        View view = getLayoutInflater().inflate(R.layout.action_bar_title, null);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setCustomView(view, params);
        actionBar.setDisplayShowHomeEnabled(true);

        //Hack to hide the home icon -- Otherwise the action bar was getting displayed on top of Tabs
        View homeIcon = findViewById(android.R.id.home);
        ((View) homeIcon.getParent()).setVisibility(View.GONE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        pause = false;
        startPollService();
    }



    private void getRules(){
        application.getClient().getRWRules(new NetworkResponseHandler(this, APIRequest.RULES));
    }

    @Override
    public void OnNetworkResponseReceived(RequestStatus status, APIRequest requestType, Object responseObject) {

        if(APIRequest.RULES == requestType){
            if(status == RequestStatus.SUCCESS) {
                JSONObject obj = (JSONObject) responseObject;
                RulesCache.getInstance().updateCache(obj);
            }

            snapshots = new ArrayList<RWPositionSnapshot>();
            adapter = new SnapshotAdapter(getBaseContext());
            lvPositions.setAdapter(adapter);

            if(progressDialog != null){
                progressDialog.dismiss();
            }

            startPollService();
        }
    }

    static boolean pause;
    @Override
    protected void onPause() {
        super.onPause();
        pause = true;
        stopPollService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.yield_manger, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.menuSettings:
                return true;
            case R.id.menuAbout:
                startActivity(new Intent(this, AboutActivity.class));
                overridePendingTransition(R.anim.slide_in_from_top, R.anim.slide_out_from_top);
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        showLogoutAlert();
    }

    private void showLogoutAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.logout_confirm_message)
                .setCancelable(false)
                .setPositiveButton(R.string.logout_confirm_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        YieldMangerActivity.this.finish();
                        overridePendingTransition (R.anim.slide_in_from_left, R.anim.slide_out_from_right);
                    }
                })
                .setNegativeButton(R.string.logout_confirm_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void startPollService(){
        startService(new Intent(this, RWPollService.class));
    }

    private void stopPollService(){
        stopService(new Intent(this, RWPollService.class));
    }

    BroadcastReceiver marketDataReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            updateRiskCapacity();

            List<RWPositionSnapshot> list = SnapshotCache.getInstance().getSnapshots();
            adapter.updateViews(lvPositions, list);

//            if(swipedToRefresh) {
//                swipedToRefresh = false;
//                if (swipeRefreshLayout != null) {
//                    swipeRefreshLayout.setRefreshing(false);
//                }
//            }
        }
    };

    private void updateRiskCapacity(){
        RWSummary rwSummary = RWSummaryCache.getInstance().getRWSummary();

        if(rwSummary == null){
            return;
        }

        yieldPercentageView.setYieldPercentage(rwSummary.getNetRiskPercentage(), rwSummary.getNetRiskPercentageDisplay());

        tvYieldValue.setText(rwSummary.getYieldDisplay());
        tvVolumeValue.setText(rwSummary.getVolumeDisplay());
        tvRelaizedPnLValue.setText(rwSummary.getRealizedPnLDisplay());
        tvUnrealizedPnLValue.setText(rwSummary.getUnrealizedPnlDisplay());
    }
}

package prafulmantale.praful.com.yaym.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.adapters.PositionsAdapter;
import prafulmantale.praful.com.yaym.application.YMApplication;
import prafulmantale.praful.com.yaym.caches.RulesCache;
import prafulmantale.praful.com.yaym.caches.SnapshotCache;
import prafulmantale.praful.com.yaym.enums.APIRequest;
import prafulmantale.praful.com.yaym.enums.RequestStatus;
import prafulmantale.praful.com.yaym.handlers.NetworkResponseHandler;
import prafulmantale.praful.com.yaym.interfaces.NetworkResponseListener;
import prafulmantale.praful.com.yaym.interfaces.SnapshotUpdateListener;
import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;
import prafulmantale.praful.com.yaym.services.RWPollService;
import prafulmantale.praful.com.yaym.services.RefreshService;


public class YieldMangerActivity extends Activity implements NetworkResponseListener, SnapshotUpdateListener {

    private static final String TAG = YieldMangerActivity.class.getSimpleName();

    private ListView lvPositions;
    private PositionsAdapter adapter;
    private List<RWPositionSnapshot> snapshots;
    private RWPositionSnapshot prevSelectedSnapshot;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean swipedToRefresh = false;

    private YMApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yield_manger);

        initialize();
        initializeActionBar();

        if(savedInstanceState == null){
            snapshots = new ArrayList<RWPositionSnapshot>();
            adapter = new PositionsAdapter(getBaseContext(), snapshots);
            lvPositions.setAdapter(adapter);
        }

        getRules();
        setupListeners();
        startPollService();
    }

    private void initialize(){

        application = (YMApplication)getApplication();

        lvPositions = (ListView)findViewById(R.id.lvPositionsList);
        View headerView = ((LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_positions_header, null, false);

        TextView tvCcyPair = (TextView)headerView.findViewById(R.id.tvCurrencyPair_header);
        TextView tvPositionsStatus = (TextView)headerView.findViewById(R.id.tvPosition_header);
        TextView tvUnrealizedPnL = (TextView)headerView.findViewById(R.id.tvUnrealizedPnl_header);
        tvCcyPair.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Bold.ttf"));
        tvPositionsStatus.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Bold.ttf"));
        tvUnrealizedPnL.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Bold.ttf"));


        lvPositions.addHeaderView(headerView);
        lvPositions.setHeaderDividersEnabled(true);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    private void setupListeners(){
        lvPositions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RWPositionSnapshot snapshot = adapter.getItem(position);
                snapshot.setItemSelected(true);
                if(prevSelectedSnapshot != null){
                    prevSelectedSnapshot.setItemSelected(false);
                }

                prevSelectedSnapshot = snapshot;
            }
        });

        SnapshotCache.getInstance().addListener(this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipedToRefresh = true;
                startService(new Intent(YieldMangerActivity.this, RefreshService.class));
            }
        });
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
        //getMenuInflater().inflate(R.menu.yield_manger, menu);
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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition (R.anim.slide_in_from_left, R.anim.slide_out_from_right);
    }

    private void startPollService(){
        startService(new Intent(this, RWPollService.class));
    }

    private void stopPollService(){
        stopService(new Intent(this, RWPollService.class));
    }

    @Override
    public void onSnapshotUpdated() {

        List<RWPositionSnapshot> list = SnapshotCache.getInstance().getSnapshots();

        if (list != null && list.size() > 0) {
            snapshots.clear();
            snapshots.addAll(list);
            adapter.notifyDataSetChanged();
        }

        if(swipedToRefresh) {
            swipedToRefresh = false;
            if (swipeRefreshLayout != null) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }
}

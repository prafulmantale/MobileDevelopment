package prafulmantale.praful.com.yaym.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.adapters.PositionsAdapter;
import prafulmantale.praful.com.yaym.enums.APIRequest;
import prafulmantale.praful.com.yaym.enums.RequestStatus;
import prafulmantale.praful.com.yaym.handlers.NetworkResponseHandler;
import prafulmantale.praful.com.yaym.interfaces.NetworkResponseListener;
import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;
import prafulmantale.praful.com.yaym.models.RWSummary;


public class YieldMangerActivity extends Activity implements NetworkResponseListener {

    private ListView lvPositions;
    private PositionsAdapter adapter;
    private List<RWPositionSnapshot> snapshots;
    private RWPositionSnapshot prevSelectedSnapshot;

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

        getRWSnapshot();
        setupListeners();
    }

    private void initialize(){

        lvPositions = (ListView)findViewById(R.id.lvPositionsList);
        View headerView = ((LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_positions_header, null, false);

        lvPositions.addHeaderView(headerView);
        lvPositions.setHeaderDividersEnabled(true);

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
    }

    public void startPoll(){
        if(pause){
            return;
        }
        System.out.println("######################  startPoll ");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getRWSnapshot();
                    }
                });
            }
        }, 15000);
    }

    private void getRWSnapshot(){
        LoginActivity.client.getRWSnapshot(new NetworkResponseHandler(this, APIRequest.SNAPSHOT), LoginActivity.cookieStore);
        startPoll();
    }

    @Override
    public void OnNetworkResponseReceived(RequestStatus status, APIRequest requestType, Object responseObject) {

        //System.out.println(requestType.toString() + "|" + status + "|" + responseObject);
        if(APIRequest.SNAPSHOT == requestType) {

            if(status == RequestStatus.SUCCESS){

                try {
                    JSONObject obj = (JSONObject) responseObject;

                    JSONArray arr = obj.getJSONArray("rwpositionSnapshot");
                    List<RWPositionSnapshot> list = RWPositionSnapshot.fromJSON(arr);

                    if (list != null && list.size() > 0) {
                        snapshots.clear();
                        snapshots.addAll(list);

                        adapter.notifyDataSetChanged();
                    }

                    JSONObject summary = obj.getJSONObject("summary");
                    RWSummary rwSummary = RWSummary.fromJSON(summary);

                        //System.out.println("Summary: " + rwSummary);
                }
                catch (JSONException ex){
                    System.out.println("Excption!!!!!!!!!!");
                    ex.printStackTrace();
                }
            }

        }
    }

    static boolean pause;
    @Override
    protected void onPause() {
        super.onPause();
        pause = true;
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
}

package prafulmantale.praful.com.yaym.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
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
import prafulmantale.praful.com.yaym.models.LoginRequest;
import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;
import prafulmantale.praful.com.yaym.models.RWSummary;


public class YieldMangerActivity extends Activity implements NetworkResponseListener {

    private ListView lvPositions;
    private PositionsAdapter adapter;
    private List<RWPositionSnapshot> snapshots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yield_manger);

        initialize();
        getRWSnapshot();
    }

    private void initialize(){

        lvPositions = (ListView)findViewById(R.id.lvPositionsList);
        //View headerView = ((LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_positions_header, null, false);

        //lvPositions.addHeaderView(headerView);
        //lvPositions.setHeaderDividersEnabled(true);
        lvPositions.setDividerHeight(1);

        snapshots = new ArrayList<RWPositionSnapshot>();
        adapter = new PositionsAdapter(getBaseContext(), snapshots);
        lvPositions.setAdapter(adapter);


    }

    public void startPoll(){
        System.out.println("######################  startPoll ");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getRWSnapshot();
            }
        }, 5000);
    }

    private void getRWSnapshot(){
        LoginActivity.client.getRWSnapshot(new NetworkResponseHandler(this, APIRequest.SNAPSHOT));
        startPoll();
    }

    private static int counter = 1;

    @Override
    public void OnNetworkResponseReceived(RequestStatus status, APIRequest requestType, Object responseObject) {

        System.out.println(requestType.toString() + "|" + status + "|" + responseObject);
        if(APIRequest.SNAPSHOT == requestType) {

            if(status == RequestStatus.SUCCESS){

                try {
                    JSONObject obj = (JSONObject) responseObject;

                    JSONArray arr = obj.getJSONArray("rwpositionSnapshot");
                    List<RWPositionSnapshot> list = RWPositionSnapshot.fromJSON(arr);

                    if (list != null && list.size() > 0) {
                        counter ++;
                        snapshots.clear();
                        if(counter <= 2) {
                            snapshots.addAll(list);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    JSONObject summary = obj.getJSONObject("summary");
                    RWSummary rwSummary = RWSummary.fromJSON(summary);

                        System.out.println("Summary: " + rwSummary);
                }
                catch (JSONException ex){
                    System.out.println("Excption!!!!!!!!!!");
                    ex.printStackTrace();
                }
            }

        }


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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

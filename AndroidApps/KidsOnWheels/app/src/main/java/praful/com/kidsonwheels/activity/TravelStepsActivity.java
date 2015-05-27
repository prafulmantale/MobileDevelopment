package praful.com.kidsonwheels.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import praful.com.kidsonwheels.R;
import praful.com.kidsonwheels.adapter.TravelStepsViewAdapter;
import praful.com.kidsonwheels.manager.DataManager;
import praful.com.kidsonwheels.manager.DirectionsManager;
import praful.com.kidsonwheels.model.TravelLeg;
import praful.com.kidsonwheels.model.TravelStep;

public class TravelStepsActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private List<TravelStep> mTravelSteps;
    private TravelStepsViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_steps);

//        ListView listView1 = (ListView) findViewById(R.id.listView1);
//        TravelLeg leg = DirectionsManager.getInstance().getDirectionsResult().getRoutes().get(0).getLegs().get(DataManager.getInstance().getCurrentPickup());
//
//        List<String> items = new ArrayList<>();
//        for(TravelStep step : leg.getSteps()){
//            items.add(step.getInstruction());
//        }
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, items);
//
//        listView1.setAdapter(adapter);

        setupViews();
    }

    private void setupViews(){
        TravelLeg leg = DirectionsManager.getInstance().getDirectionsResult().getRoutes().get(0).getLegs().get(DataManager.getInstance().getCurrentPickup());

        mTravelSteps = leg.getSteps();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_travel_steps);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);
        mAdapter= new TravelStepsViewAdapter(mTravelSteps);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_travel_steps, menu);
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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.stay, R.anim.slide_out_from_left);
    }
}

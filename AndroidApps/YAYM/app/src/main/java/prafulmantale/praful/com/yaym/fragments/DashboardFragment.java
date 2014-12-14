package prafulmantale.praful.com.yaym.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.adapters.SnapshotAdapter;
import prafulmantale.praful.com.yaym.application.YMApplication;
import prafulmantale.praful.com.yaym.caches.RWSummaryCache;
import prafulmantale.praful.com.yaym.caches.SnapshotCache;
import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;
import prafulmantale.praful.com.yaym.models.RWSummary;
import prafulmantale.praful.com.yaym.widgets.YieldPercentageView;

/**
 * Created by prafulmantale on 12/13/14.
 */
public class DashboardFragment extends Fragment{

    private final static String TAG = DashboardFragment.class.getSimpleName();

    private ListView lvPositions;
    private SnapshotAdapter adapter;
    private List<RWPositionSnapshot> snapshots;
    //    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean swipedToRefresh = false;

    private YieldPercentageView yieldPercentageView;
    private TextView tvYieldValue;
    private TextView tvVolumeValue;
    private TextView tvRelaizedPnLValue;
    private TextView tvUnrealizedPnLValue;

    private YMApplication application;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragement_dashboard, container, false);

        initializeViews(view);
        setupListeners();

        return view;
    }

    private void initializeViews(View view){
        application = (YMApplication) ( getActivity().getApplication());

        lvPositions = (ListView)view.findViewById(R.id.lvPositionsList);

        TextView tvYieldText = (TextView)view.findViewById(R.id.tvYeildText);
        tvYieldText.setText(Html.fromHtml(getString(R.string.risk_capacity_yield_usd_mio_text)));

        TextView tvVolumeText = (TextView)view.findViewById(R.id.tvVolumeText);
        tvVolumeText.setText(Html.fromHtml(getString(R.string.risk_capacity_volume_usd_thousand_text)));

        TextView tvRelPnLText = (TextView)view.findViewById(R.id.tvRealizedPnLText);
        tvRelPnLText.setText(Html.fromHtml(getString(R.string.risk_capacity_realized_pnl_usd_text)));

        TextView tvUnRelPnLText = (TextView)view.findViewById(R.id.tvUnRealizedPnLText);
        tvUnRelPnLText.setText(Html.fromHtml(getString(R.string.risk_capacity_unrealized_pnl_usd_text)));

        tvYieldValue = (TextView)view.findViewById(R.id.tvYieldValue);
        tvVolumeValue = (TextView)view.findViewById(R.id.tvVolumeValue);
        tvRelaizedPnLValue = (TextView)view.findViewById(R.id.tvRealizedPnLValue);
        tvUnrealizedPnLValue = (TextView)view.findViewById(R.id.tvUnRealizedPnLValue);

        yieldPercentageView = (YieldPercentageView)view.findViewById(R.id.ypv);

//        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
//        swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);

        snapshots = new ArrayList<RWPositionSnapshot>();
        adapter = new SnapshotAdapter(getActivity().getBaseContext());
        lvPositions.setAdapter(adapter);

    }

    private void setupListeners(){
        //        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipedToRefresh = true;
//                startService(new Intent(YieldMangerActivity.this, RefreshService.class));
//            }
//        });

        lvPositions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //???
                //Inform activity that Details fragment needs to be loaded

//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                intent.putExtra(AppConstants.INTENT_KEY_CCYPAIR, RulesCache.getInstance().getCurrencyPairsList().get(position));
//                startActivity(intent);
               // overridePendingTransition(R.anim.slide_in_from_right, R.anim.stay);
            }
        });
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

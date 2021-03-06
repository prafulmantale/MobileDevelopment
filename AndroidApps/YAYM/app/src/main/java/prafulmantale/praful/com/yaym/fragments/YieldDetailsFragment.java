package prafulmantale.praful.com.yaym.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.activities.MainActivity;
import prafulmantale.praful.com.yaym.application.YMApplication;
import prafulmantale.praful.com.yaym.asynctasks.HttpGetAsyncTask;
import prafulmantale.praful.com.yaym.caches.HistoricalDataCache;
import prafulmantale.praful.com.yaym.caches.RateDataCache;
import prafulmantale.praful.com.yaym.caches.SnapshotCache;
import prafulmantale.praful.com.yaym.helpers.AppConstants;
import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;

/**
 * Created by prafulmantale on 11/22/14.
 */
public class YieldDetailsFragment extends Fragment {

    private static final String TAG = YieldDetailsFragment.class.getSimpleName();

    private TextView tvYieldValue;
    private TextView tvVolumeValue;
    private TextView tvRelaizedPnLValue;
    private TextView tvUnrealizedPnLValue;
    private TextView tvCurrencyPair;

    private String selectedCurrencyPair;

    private TextView tvOneMinute;
    private TextView tvFifteenMinute;
    private TextView tvOneHour;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Non view initializations
        selectedCurrencyPair = MainActivity.selectedCurrencyPair;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_yield_details, container, false);

        initializeViews(view);
        setupListeners();

        return view;
    }

    private void setupListeners() {
        tvOneHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(true);
                tvOneHour.setTextColor(getResources().getColor(R.color.white));

                tvOneMinute.setSelected(false);
                tvOneMinute.setTextColor(getResources().getColor(R.color.risk_capacity_text));

                tvFifteenMinute.setSelected(false);
                tvFifteenMinute.setTextColor(getResources().getColor(R.color.risk_capacity_text));
            }
        });

        tvOneMinute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(true);
                tvOneMinute.setTextColor(getResources().getColor(R.color.white));

                tvOneHour.setSelected(false);
                tvOneHour.setTextColor(getResources().getColor(R.color.risk_capacity_text));

                tvFifteenMinute.setSelected(false);
                tvFifteenMinute.setTextColor(getResources().getColor(R.color.risk_capacity_text));
            }
        });

        tvFifteenMinute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(true);
                tvFifteenMinute.setTextColor(getResources().getColor(R.color.white));

                tvOneHour.setSelected(false);
                tvOneHour.setTextColor(getResources().getColor(R.color.risk_capacity_text));

                tvOneMinute.setSelected(false);
                tvOneMinute.setTextColor(getResources().getColor(R.color.risk_capacity_text));
            }
        });
    }

    @Override
    public void onStart() {
        getActivity().registerReceiver(marketDataReceiver, new IntentFilter(AppConstants.RW_SNAPSHOT_RECEIVED));

        selectedCurrencyPair = MainActivity.selectedCurrencyPair;

        updateData();
        getHistoricalData();
        getRateData();

        super.onStart();
    }

    @Override
    public void onStop() {

        getActivity().unregisterReceiver(marketDataReceiver);
        super.onStop();
    }

    private void initializeViews(View view){

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
        tvCurrencyPair = (TextView)view.findViewById(R.id.tvYDCurrencyPair);

        tvOneMinute = (TextView)view.findViewById(R.id.tvChartDurationOneMinute);
        tvFifteenMinute = (TextView)view.findViewById(R.id.tvChartDurationFifteenMinute);
        tvOneHour = (TextView)view.findViewById(R.id.tvChartDurationOneHour);
        tvOneHour.setSelected(true);
        tvOneHour.setTextColor(getResources().getColor(R.color.white));
    }

    private void updateData(){
        RWPositionSnapshot snapshot = SnapshotCache.getInstance().getSnapshot(selectedCurrencyPair);
        tvCurrencyPair.setText(snapshot.getCurrencyPair());
        tvYieldValue.setText(snapshot.getYieldDisplay());
        tvVolumeValue.setText(snapshot.getVolumeInUSDDisplay());
        tvRelaizedPnLValue.setText(snapshot.getRealizedPnLInUSDDisplay());
        tvUnrealizedPnLValue.setText(snapshot.getUnrealizedPnLDisplay());
    }


    BroadcastReceiver marketDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
             updateData();
        }
    };

    private void getHistoricalData(){
        new HttpGetAsyncTask(handler, YMApplication.getHistoricalDataUrl(selectedCurrencyPair), AppConstants.HandlerMessageIds.HISTDATA).execute();
    }

    private void getRateData(){
        new HttpGetAsyncTask(handler, YMApplication.getRateDataUrl(selectedCurrencyPair), AppConstants.HandlerMessageIds.RATEDATA).execute();
    }

    private void update(int what){

        ChartsFragment fragment = null;

        try {
            fragment = (ChartsFragment) getFragmentManager().findFragmentById(R.id.fragmentCharts);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        if(fragment == null){
            return;
        }

        if(what == AppConstants.HandlerMessageIds.RATEDATA){
            fragment.updateRateData();
        }

        if(what == AppConstants.HandlerMessageIds.HISTDATA){
            fragment.updateHistoricalData();
        }
    }

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            String response = (String)msg.obj;

           // Log.d(TAG, "Response Message: .... \r\n" + response);

            if(response != null && response.isEmpty() == false){
                if(response.equals(AppConstants.STATUS_FAILURE)){

                    //Toast.makeText(getActivity(), R.string.error_cannot_connect_to_server, Toast.LENGTH_LONG).show();

                    return;
                }

                try {


                    if(msg.what == AppConstants.HandlerMessageIds.HISTDATA){
                        JSONObject data = new JSONObject(response);
                        if(data.get(AppConstants.STATUS_TEXT).equals(AppConstants.STATUS_OK)){

                            Log.d(TAG, "Historical data received is successful");

                            HistoricalDataCache.getInstance().updateCache(data.getJSONArray("sampleSnapshotList"));
                        }
                    }

                    if(msg.what == AppConstants.HandlerMessageIds.RATEDATA){

                        JSONObject data = new JSONObject(response);
                        if(data.get(AppConstants.STATUS_TEXT).equals(AppConstants.STATUS_OK)){

                            Log.d(TAG, "Rate data received...");

                            RateDataCache.getInstance().updateCache(data);
                        }
                    }

                    update(msg.what);

                }
                catch (JSONException jex){
                    Log.e(TAG, "Exception in handleMessage: " + jex.getMessage());
                    jex.printStackTrace();
                }
            }
        }
    };
}

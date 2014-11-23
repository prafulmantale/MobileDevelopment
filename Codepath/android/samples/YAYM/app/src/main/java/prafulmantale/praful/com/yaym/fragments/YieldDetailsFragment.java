package prafulmantale.praful.com.yaym.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.activities.MainActivity;
import prafulmantale.praful.com.yaym.caches.SnapshotCache;
import prafulmantale.praful.com.yaym.helpers.AppConstants;
import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;

/**
 * Created by prafulmantale on 11/22/14.
 */
public class YieldDetailsFragment extends Fragment {


    private TextView tvYieldValue;
    private TextView tvVolumeValue;
    private TextView tvRelaizedPnLValue;
    private TextView tvUnrealizedPnLValue;
    private TextView tvCurrencyPair;

    private String selectedCurrencyPair;


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

        return view;
    }

    @Override
    public void onStart() {
        getActivity().registerReceiver(marketDataReceiver, new IntentFilter(AppConstants.RW_SNAPSHOT_RECEIVED));

        selectedCurrencyPair = MainActivity.selectedCurrencyPair;

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
        tvYieldText.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Regular.ttf"));

        TextView tvVolumeText = (TextView)view.findViewById(R.id.tvVolumeText);
        tvVolumeText.setText(Html.fromHtml(getString(R.string.risk_capacity_volume_usd_thousand_text)));
        tvVolumeText.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/OpenSans-Regular.ttf"));

        TextView tvRelPnLText = (TextView)view.findViewById(R.id.tvRealizedPnLText);
        tvRelPnLText.setText(Html.fromHtml(getString(R.string.risk_capacity_realized_pnl_usd_text)));
        tvRelPnLText.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/OpenSans-Regular.ttf"));

        TextView tvUnRelPnLText = (TextView)view.findViewById(R.id.tvUnRealizedPnLText);
        tvUnRelPnLText.setText(Html.fromHtml(getString(R.string.risk_capacity_unrealized_pnl_usd_text)));
        tvUnRelPnLText.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/OpenSans-Regular.ttf"));

        tvYieldValue = (TextView)view.findViewById(R.id.tvYieldValue);
        tvYieldValue.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/OpenSans-Regular.ttf"));


        tvVolumeValue = (TextView)view.findViewById(R.id.tvVolumeValue);
        tvVolumeValue.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Regular.ttf"));

        tvRelaizedPnLValue = (TextView)view.findViewById(R.id.tvRealizedPnLValue);
        tvRelaizedPnLValue.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Regular.ttf"));

        tvUnrealizedPnLValue = (TextView)view.findViewById(R.id.tvUnRealizedPnLValue);
        tvUnrealizedPnLValue.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Regular.ttf"));

        tvCurrencyPair = (TextView)view.findViewById(R.id.tvYDCurrencyPair);
        tvCurrencyPair.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Regular.ttf"));
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
}

package prafulmantale.praful.com.yaym.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.activities.MainActivity;
import prafulmantale.praful.com.yaym.caches.HistoricalDataCache;
import prafulmantale.praful.com.yaym.caches.RateDataCache;
import prafulmantale.praful.com.yaym.widgets.Charts.VolumeChart;
import prafulmantale.praful.com.yaym.widgets.Charts.YieldChart;
import prafulmantale.praful.com.yaym.widgets.Charts.RateChart;

/**
 * Created by prafulmantale on 11/22/14.
 */
public class ChartsFragment extends Fragment{

    private VolumeChart barChart;
    private YieldChart lineChart;
    private RateChart ohlcChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_charts, container, false);

        barChart = (VolumeChart)view.findViewById(R.id.barChart);
        lineChart = (YieldChart)view.findViewById(R.id.lineChart);
        ohlcChart = (RateChart)view.findViewById(R.id.ohlcChart);

        return view;
    }

    public void updateHistoricalData(){
        barChart.setDataSource(HistoricalDataCache.getInstance().getVolumes());
        lineChart.setDataSource(HistoricalDataCache.getInstance().getYields());
    }

    public void updateRateData(){
        ohlcChart.setDataSource(MainActivity.selectedCurrencyPair, RateDataCache.getInstance().getRateData());
    }

}

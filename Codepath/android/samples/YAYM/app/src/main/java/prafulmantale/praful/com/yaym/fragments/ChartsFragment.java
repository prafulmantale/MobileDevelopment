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
import prafulmantale.praful.com.yaym.widgets.BarChart;
import prafulmantale.praful.com.yaym.widgets.LineChart;
import prafulmantale.praful.com.yaym.widgets.OHLCChart;

/**
 * Created by prafulmantale on 11/22/14.
 */
public class ChartsFragment extends Fragment{

    private BarChart barChart;
    private LineChart lineChart;
    private OHLCChart ohlcChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_charts, container, false);

        barChart = (BarChart)view.findViewById(R.id.barChart);
        lineChart = (LineChart)view.findViewById(R.id.lineChart);
        ohlcChart = (OHLCChart)view.findViewById(R.id.ohlcChart);

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

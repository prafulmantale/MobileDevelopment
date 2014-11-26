package prafulmantale.praful.com.yaym.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.caches.HistoricalDataCache;
import prafulmantale.praful.com.yaym.widgets.BarChart;
import prafulmantale.praful.com.yaym.widgets.LineChart;

/**
 * Created by prafulmantale on 11/22/14.
 */
public class ChartsFragment extends Fragment{

    private BarChart barChart;
    private LineChart lineChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_charts, container, false);

        barChart = (BarChart)view.findViewById(R.id.barChart);
        lineChart = (LineChart)view.findViewById(R.id.lineChart);


        return view;
    }

    public void update(){
        barChart.setDataSource(HistoricalDataCache.getInstance().getVolumes());
        lineChart.setDataSource(HistoricalDataCache.getInstance().getYields());
    }
}

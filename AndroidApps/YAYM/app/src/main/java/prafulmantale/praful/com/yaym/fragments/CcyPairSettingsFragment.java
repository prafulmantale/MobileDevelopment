package prafulmantale.praful.com.yaym.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.adapters.CcyPairsSettingsAdapter;
import prafulmantale.praful.com.yaym.caches.RefDataCache;
import prafulmantale.praful.com.yaym.caches.RulesCache;

/**
 * Created by prafulmantale on 12/12/14.
 */
public class CcyPairSettingsFragment extends Fragment {

    private TextView tvSelected;
    private TextView tvAvailable;
    private ListView lvCcyPairs;
    private List<String> currencyParisList;
    private CcyPairsSettingsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSelectedAdapter();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ccypair_settings, container, false);

        initializeViews(view);
        setupListeners();
        return view;
    }


    private void initializeViews(View view){
        tvSelected = (TextView)view.findViewById(R.id.tvSelectedCcyPairs);
        tvSelected.setSelected(true);

        tvAvailable = (TextView)view.findViewById(R.id.tvAvailableCcyPairs);

        lvCcyPairs = (ListView)view.findViewById(R.id.lvCcypairlist);
        lvCcyPairs.setAdapter(adapter);
    }

    private void setupListeners(){

        tvAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAvailable.setSelected(true);
                tvSelected.setSelected(false);
                setAvailableAdapter();
                lvCcyPairs.setAdapter(adapter);
            }
        });

        tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSelected.setSelected(true);
                tvAvailable.setSelected(false);
                setSelectedAdapter();
                lvCcyPairs.setAdapter(adapter);
            }
        });
    }

    private void setSelectedAdapter(){

        currencyParisList = RulesCache.getInstance().getCurrencyPairsList();
        adapter = new CcyPairsSettingsAdapter(getActivity().getBaseContext(), currencyParisList, true);

    }

    private void setAvailableAdapter(){
        currencyParisList = RefDataCache.getInstance().getCurrencyPairsList();
        adapter = new CcyPairsSettingsAdapter(getActivity().getBaseContext(), currencyParisList, false);
    }
}

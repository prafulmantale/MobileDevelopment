package prafulmantale.praful.com.yaym.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import prafulmantale.praful.com.yaym.R;

/**
 * Created by prafulmantale on 12/12/14.
 */
public class CcyPairSettingsFragment extends Fragment {

    private TextView tvSelected;
    private TextView tvAvailable;

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
    }

    private void setupListeners(){

        tvAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAvailable.setSelected(true);
                tvSelected.setSelected(false);
            }
        });

        tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSelected.setSelected(true);
                tvAvailable.setSelected(false);
            }
        });
    }
}

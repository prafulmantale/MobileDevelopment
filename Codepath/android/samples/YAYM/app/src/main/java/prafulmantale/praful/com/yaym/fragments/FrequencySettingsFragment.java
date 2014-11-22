package prafulmantale.praful.com.yaym.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.helpers.AppConstants;

/**
 * Created by prafulmantale on 11/22/14.
 */
public class FrequencySettingsFragment extends DialogFragment{

    private RadioGroup radioGroup;

    public FrequencySettingsFragment(){

    }

    public static FrequencySettingsFragment newInstance(){
        FrequencySettingsFragment frequencySettingsFragment = new FrequencySettingsFragment();
//        Bundle args = new Bundle();
//        args.putString("something", "something");
//        frequencySettingsFragment.setArguments(args);
        return frequencySettingsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragement_frequency_settings, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        radioGroup = (RadioGroup)view.findViewById(R.id.rgUpdateFrequency);
        setCurrentSelection(view);
        setupListeners();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getDialog().getWindow().getAttributes().windowAnimations = R.style.frequency_pref_dialog_animation;
    }

    private void setCurrentSelection(View view){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int frequency = 5;
        if(pref != null){
            frequency = pref.getInt(AppConstants.PREF_KEY_FREQUENCY, frequency);
        }

        if(frequency == 1){
            ((RadioButton)view.findViewById(R.id.rbOneSecond)).setChecked(true);
        }
        else if(frequency == 5){
            ((RadioButton)view.findViewById(R.id.rbFiveSecond)).setChecked(true);
        }
        else if(frequency == 15){
            ((RadioButton)view.findViewById(R.id.rbFifteenSecond)).setChecked(true);
        }
        else if(frequency == 30){
            ((RadioButton)view.findViewById(R.id.rbThirtySecond)).setChecked(true);
        }
        else if(frequency == -1){
            ((RadioButton)view.findViewById(R.id.rbManual)).setChecked(true);
        }
        else{
            ((RadioButton)view.findViewById(R.id.rbFiveSecond)).setChecked(true);
        }
    }

    private void setupListeners(){

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int newFreq = -10;
                switch (checkedId){
                    case R.id.rbOneSecond:
                        newFreq = 1;
                        break;
                    case R.id.rbFiveSecond:
                        newFreq = 5;
                        break;
                    case R.id.rbFifteenSecond:
                        newFreq = 15;
                        break;
                    case R.id.rbThirtySecond:
                        newFreq = 30;
                        break;
                    case R.id.rbManual:
                        newFreq = -1;
                        break;
                    default:
                        break;
                }

                saveFrequency(newFreq);
                dismiss();
            }
        });
    }

    private void saveFrequency(int freq){

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(AppConstants.PREF_KEY_FREQUENCY, freq);
        editor.commit();
    }
}

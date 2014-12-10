package prafulmantale.praful.com.imagefinder;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import prafulmantale.praful.com.imagefinder.helpers.AppConstants;

public class OptionsDialog extends DialogFragment {

    public OptionsDialog() {
    }

    public static OptionsDialog newInstance(String title){

        OptionsDialog optionsDialog = new OptionsDialog();

        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.KEY_TITLE, title);

        optionsDialog.setArguments(bundle);

        return optionsDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Container is the parent
        View view = inflater.inflate(R.layout.fragment_options_dialog, container);

        String title = getArguments().getString(AppConstants.KEY_TITLE);
        getDialog().setTitle(title);

        return view;
    }
}

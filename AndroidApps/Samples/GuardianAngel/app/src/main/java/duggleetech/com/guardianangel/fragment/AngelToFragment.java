package duggleetech.com.guardianangel.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import duggleetech.com.guardianangel.R;

/**
 * Created by prafulmantale on 3/11/15.
 */
public class AngelToFragment extends android.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_angel_to, container, false);

        return view;
    }
}

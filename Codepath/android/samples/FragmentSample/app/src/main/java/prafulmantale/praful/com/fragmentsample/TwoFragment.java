package prafulmantale.praful.com.fragmentsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by prafulmantale on 10/2/14.
 */
public class TwoFragment extends Fragment{

    private TextView tvFoo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        tvFoo = (TextView)view.findViewById(R.id.textView);
        return view;
    }
}

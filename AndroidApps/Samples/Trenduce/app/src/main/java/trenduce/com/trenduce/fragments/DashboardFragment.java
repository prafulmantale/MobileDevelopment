package trenduce.com.trenduce.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import trenduce.com.trenduce.R;
import trenduce.com.trenduce.Utils.Constants;
import trenduce.com.trenduce.activity.CommentsViewerActivity;
import trenduce.com.trenduce.adapters.StylesAdapter;
import trenduce.com.trenduce.asynctasks.HttpGetAsyncTask;
import trenduce.com.trenduce.asynctasks.HttpPostAsyncTask;
import trenduce.com.trenduce.interfaces.StylesViewClickListener;
import trenduce.com.trenduce.model.LikeRequest;
import trenduce.com.trenduce.model.Style;
import trenduce.com.trenduce.model.UserProfile;

/**
 * Created by prafulmantale on 3/8/15.
 */
public class DashboardFragment extends Fragment implements StylesViewClickListener {

    private static final String TAG = DashboardFragment.class.getSimpleName();

    private StylesAdapter adapter;
    private List<Style> styleList;
    private ListView lvStyles;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        initializeViews(view);

        return view;
    }


    private void initializeViews(View view){
        styleList = new ArrayList<Style>();
        adapter = new StylesAdapter(getActivity(), styleList, this);
        lvStyles = (ListView)view.findViewById(R.id.lvStyles);
        lvStyles.setAdapter(adapter);

        getStyles();
    }

    private void getStyles(){
        new HttpGetAsyncTask( handler, Constants.STYLES_API, Constants.HandlerIds.STYLES_ALL).execute();
    }

    @Override
    public void OnCommentsRequested(String styleId) {
        Intent intent = new Intent(getActivity(), CommentsViewerActivity.class);
        intent.putExtra(Constants.INTENT_KEY_STYLE_ID, styleId);
        startActivity(intent);
    }

    @Override
    public void OnLikeStyle(String styleId) {

        LikeRequest request = new LikeRequest();
        request.setUser(UserProfile.getInstance().getEmailId());

        new HttpPostAsyncTask(request.toJSONObject(), handler, Constants.COMMENTS_API + "/" + styleId + "/like", Constants.HandlerIds.STYLE_LIKE).execute();
    }

    @Override
    public void OnUnLikeStyle(String styleId) {

    }

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            String response = (String)msg.obj;

            Log.d(TAG, "Response Message: .... \r\n" + response);
            Log.d(TAG, "What Message: .... \r\n" + msg.what);

            if(response != null && response.equals(Constants.STATUS_FAILURE)){

                return;
            }

            if (msg.what == Constants.HandlerIds.STYLES_ALL) {
                try {
                    JSONArray data = new JSONArray(response);

                    List<Style> styles1 = Style.fromJSONArray(data);
                    adapter.addAll(styles1);

                }
                catch (Exception ex){

                }
            }


        }
    };


}

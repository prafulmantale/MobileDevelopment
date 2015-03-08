package trenduce.com.trenduce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import trenduce.com.trenduce.R;
import trenduce.com.trenduce.Utils.Constants;
import trenduce.com.trenduce.adapters.StylesAdapter;
import trenduce.com.trenduce.asynctasks.HttpGetAsyncTask;
import trenduce.com.trenduce.asynctasks.HttpPostAsyncTask;
import trenduce.com.trenduce.interfaces.StylesViewClickListener;
import trenduce.com.trenduce.model.LikeRequest;
import trenduce.com.trenduce.model.Style;
import trenduce.com.trenduce.model.UserProfile;

public class MainActivity extends ActionBarActivity implements StylesViewClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private StylesAdapter adapter;
    private List<Style> styleList;
    private ListView lvStyles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        styleList = new ArrayList<Style>();
        adapter = new StylesAdapter(this, styleList, this);
        lvStyles = (ListView)findViewById(R.id.lvStyles);
        lvStyles.setAdapter(adapter);

        getStyles();
    }

    private void getStyles(){
        new HttpGetAsyncTask( handler, Constants.STYLES_API, Constants.HandlerIds.STYLES_ALL).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    @Override
    public void OnCommentsRequested(String styleId) {
        Intent intent = new Intent(this, CommentsViewerActivity.class);
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
}

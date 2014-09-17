package prafulmantale.praful.com.instagramviewer.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.instagramviewer.R;
import prafulmantale.praful.com.instagramviewer.adapters.CommentsViewerAdapter;
import prafulmantale.praful.com.instagramviewer.models.Comment;
import prafulmantale.praful.com.instagramviewer.models.MediaDetails;

public class CommentsViewerActivity extends Activity {

    private ListView listView;
    private CommentsViewerAdapter adapter;
    private List<Comment> commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        initializeActionBar();

        MediaDetails mediaDetails = (MediaDetails)getIntent().getSerializableExtra("MEDIA");

        initialize(mediaDetails);
    }

    private void initialize(MediaDetails mediaDetails){
        listView = (ListView)findViewById(R.id.lvComments);
        if(mediaDetails.getComments().getCount() == 0) {
            commentList = new ArrayList<Comment>();
        }
        else{
            commentList = mediaDetails.getComments().getCommentsList();
        }
        adapter = new CommentsViewerAdapter(this, commentList);
        listView.setAdapter(adapter);
    }

    private void initializeActionBar(){

        final ActionBar actionBar = getActionBar();

        View viewActionBar = getLayoutInflater().inflate(R.layout.activity_action_bar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        TextView textView = (TextView) viewActionBar.findViewById(R.id.tvActionBarTitle);
        textView.setText(R.string.title_activity_comments);
        actionBar.setCustomView(viewActionBar, params);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.comments, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

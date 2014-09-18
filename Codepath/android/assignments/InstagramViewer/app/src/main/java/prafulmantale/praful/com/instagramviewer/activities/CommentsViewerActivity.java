package prafulmantale.praful.com.instagramviewer.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.instagramviewer.R;
import prafulmantale.praful.com.instagramviewer.Utils.AppConstants;
import prafulmantale.praful.com.instagramviewer.adapters.CommentsViewerAdapter;
import prafulmantale.praful.com.instagramviewer.enums.RequesterTypes;
import prafulmantale.praful.com.instagramviewer.models.Comment;
import prafulmantale.praful.com.instagramviewer.models.MediaDetails;

public class CommentsViewerActivity extends Activity {

    private ListView listView;
    private CommentsViewerAdapter adapter;
    private List<Comment> commentList;

    private EditText evWriteComment;
    private ImageView ivSendComment;
    private RequesterTypes requesterType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        initializeActionBar();

        MediaDetails mediaDetails = (MediaDetails)getIntent().getSerializableExtra(AppConstants.MEDIA_KEY);
        int reqType = getIntent().getIntExtra(AppConstants.REQ_TYPE_KEY, 0);
        requesterType = (reqType == 0) ? RequesterTypes.VIEW_ALL_COMMENTS : RequesterTypes.ADD_COMMENT;

        initialize(mediaDetails);
    }

    private void initialize(final MediaDetails mediaDetails){
        listView = (ListView)findViewById(R.id.lvComments);
        evWriteComment = (EditText)findViewById(R.id.evWriteComment);
        ivSendComment = (ImageView)findViewById(R.id.ivCommentSend);

        evWriteComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.toString().isEmpty()){
                    ivSendComment.setImageResource(R.drawable.ic_social_send_disabled);
                }
                else{
                    ivSendComment.setImageResource(R.drawable.ic_social_send_now);
                }
            }
        });

        ivSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(evWriteComment.getText().toString().trim().isEmpty()){
                    return;
                }

                mediaDetails.getComments().setCount(mediaDetails.getComments().getCount() + 1);

                evWriteComment.setText("");
            }
        });

        if(mediaDetails.getComments().getCount() == 0) {
            commentList = new ArrayList<Comment>();
        }
        else{
            commentList = mediaDetails.getComments().getCommentsList();
        }
        adapter = new CommentsViewerAdapter(this, commentList);
        listView.setAdapter(adapter);

        if(requesterType == RequesterTypes.ADD_COMMENT){
            evWriteComment.requestFocus();
        }
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

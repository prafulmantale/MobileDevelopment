package trenduce.com.trenduce.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import trenduce.com.trenduce.R;
import trenduce.com.trenduce.Utils.Constants;
import trenduce.com.trenduce.adapters.CommentsAdapter;
import trenduce.com.trenduce.asynctasks.HttpGetAsyncTask;
import trenduce.com.trenduce.asynctasks.HttpPostAsyncTask;
import trenduce.com.trenduce.model.Comment;
import trenduce.com.trenduce.model.UserProfile;

public class CommentsViewerActivity extends ActionBarActivity{

    private static final String TAG = CommentsViewerActivity.class.getSimpleName();

    private CommentsAdapter adapter;
    private List<Comment> commentList;
    private ListView lvComments;
    private String styleId;

    private EditText etComment;
    private ImageView ivSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_viewer);

        if(getIntent() != null && getIntent().hasExtra(Constants.INTENT_KEY_STYLE_ID)){
            styleId = getIntent().getStringExtra(Constants.INTENT_KEY_STYLE_ID);
        }

        initialize();

        getComments();
    }

    private void getComments(){
            new HttpGetAsyncTask( handler, Constants.COMMENTS_API + "/" + styleId + "/comments", Constants.HandlerIds.COMMENTS_STYLE).execute();
    }

    private void postComment(Comment comment){
        new HttpPostAsyncTask(comment.toJSONObject(), handler, Constants.COMMENTS_API + "/" + styleId + "/comment", Constants.HandlerIds.POST_COMMENT).execute();

    }

    private void initialize(){

        initializeViews();
        setupListeners();

        commentList = new ArrayList<Comment>();

        adapter = new CommentsAdapter(this, commentList);

        lvComments.setAdapter(adapter);
    }

    private void initializeViews(){

        lvComments = (ListView)findViewById(R.id.lvComments);
        etComment = (EditText)findViewById(R.id.etWriteComment);
        ivSubmit = (ImageView)findViewById(R.id.ivCommentSend);

    }

    private void setupListeners(){

        etComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String commentText = etComment.getText().toString();

                if(commentText == null || commentText.trim().isEmpty()){

                    ivSubmit.setImageResource(R.drawable.ic_social_send_disabled);
                }
                else{
                    ivSubmit.setImageResource(R.drawable.ic_social_send_now);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ivSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String commentText = etComment.getText().toString();

                if (commentText == null || commentText.trim().isEmpty()) {
                    return;
                }

                Comment comment = new Comment();
                comment.setUser(UserProfile.getInstance().getEmailId());
                comment.setText(commentText);
                postComment(comment);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comments_viewer, menu);
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

            if (msg.what == Constants.HandlerIds.COMMENTS_STYLE) {
                try {
                    JSONArray data = new JSONArray(response);

                    List<Comment> comments = Comment.fromJSONArray(data);
                    adapter.addAll(comments);

                }
                catch (Exception ex){

                }
            }


        }
    };
}

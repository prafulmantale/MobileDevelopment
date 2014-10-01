package prafulmantale.praful.com.simplechat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;


public class ChatActivity extends Activity {

    private static final String TAG = ChatActivity.class.getName();
    public static final String USER_ID_KEY = "userId";
    private static final int MAX_CHAT_MESSAGES_TO_SHOW = 50;
    private Handler handler = new Handler();

    private static String userID;

    private EditText etMessage;
    private Button btnSend;

    private ListView lvChat;
    private ArrayList<Message> mMessages;
    private ChatListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        if(ParseUser.getCurrentUser() != null){

            startWithCurrentUser();
        }
        else{
            login();
        }

        handler.postDelayed(runnable, 100);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            refreshMessages();
            handler.postDelayed(this, 100);
        }
    };

    private void refreshMessages() {
        receiveMessage();
    }

    private void startWithCurrentUser() {
        userID = ParseUser.getCurrentUser().getObjectId();
        setUpMessagePosting();
    }

    private void setUpMessagePosting() {

        etMessage = (EditText)findViewById(R.id.etMessage);
        btnSend = (Button)findViewById(R.id.btSend);

        lvChat = (ListView) findViewById(R.id.lvChat);
        mMessages = new ArrayList<Message>();
        mAdapter = new ChatListAdapter(ChatActivity.this, userID, mMessages);
        lvChat.setAdapter(mAdapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = etMessage.getText().toString();

                if(data.length() == 0){
                    return;
                }

                Message message = new Message();
                message.setBody(data);
                message.setUserId(userID);

                message.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        receiveMessage();
                    }
                });
            }
        });

    }

    private void login() {

        ParseAnonymousUtils.logIn(new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if(e != null){
                    Log.d(TAG, "Anaonymous login failed");
                }
                else{
                    startWithCurrentUser();
                }
            }
        });
    }

    private void receiveMessage() {
        // Construct query to execute
        ParseQuery<Message> query = ParseQuery.getQuery(Message.class);
        query.setLimit(MAX_CHAT_MESSAGES_TO_SHOW);
        query.orderByAscending("createdAt");
        // Execute query for messages asynchronously
        query.findInBackground(new FindCallback<Message>() {
            public void done(List<Message> messages, ParseException e) {
                if (e == null) {
                    mMessages.clear();
                    mMessages.addAll(messages);
                    mAdapter.notifyDataSetChanged();
                    lvChat.invalidate();
                } else {
                    Log.d("message", "Error: " + e.getMessage());
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chat, menu);
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

package prafulmantale.praful.com.instagramviewer.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.instagramviewer.R;
import prafulmantale.praful.com.instagramviewer.Utils.AppConstants;
import prafulmantale.praful.com.instagramviewer.adapters.PhotoViewerAdapter;
import prafulmantale.praful.com.instagramviewer.enums.RequesterTypes;
import prafulmantale.praful.com.instagramviewer.interfaces.RowActionsListener;
import prafulmantale.praful.com.instagramviewer.models.MediaDetails;
import prafulmantale.praful.com.instagramviewer.models.UserDetails;
import prafulmantale.praful.com.instagramviewer.restclient.InstagramClient;


public class PhotoViewerActivity extends Activity  implements RowActionsListener{

//    CLIENT ID	62babaf98b5c4bfe99f041c9adc30d21
//    CLIENT SECRET	2e3e0bb4d92e443daa5072375bf87c8b
//    WEBSITE URL	http://www.prafulmantale.com
//    REDIRECT URI	http://www.prafulmantale.com


    //https://api.instagram.com/v1/media/search?lat=48.858844&lng=2.294351&access_token=1494410638.1fb234f.fa70833370b9413a855979b011090de8

    private ListView listView;
    private List<MediaDetails> mediaDetailsList;
    private PhotoViewerAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);

        initializeActionBar();
        initialize();

        getPopularMedia();
    }

    private void initializeActionBar(){

        final ActionBar actionBar = getActionBar();

        View viewActionBar = getLayoutInflater().inflate(R.layout.activity_action_bar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView textView = (TextView) viewActionBar.findViewById(R.id.tvActionBarTitle);
        textView.setText(R.string.app_title);
        actionBar.setCustomView(viewActionBar, params);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    private void initialize(){

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMedia();
            }
        });

        swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        listView = (ListView)findViewById(R.id.lvMedia);
        mediaDetailsList = new ArrayList<MediaDetails>();
        adapter = new PhotoViewerAdapter(this, mediaDetailsList);
        listView.setAdapter(adapter);
    }

    public void showAllComments(View view){
        showAllComments(view, RequesterTypes.VIEW_ALL_COMMENTS);
    }

    public void showAllComments(View view, RequesterTypes requesterType){
        Intent intent = new Intent(this, CommentsViewerActivity.class);
        intent.putExtra(AppConstants.MEDIA_KEY, (MediaDetails)view.getTag());
        intent.putExtra(AppConstants.REQ_TYPE_KEY, requesterType.getValue());
        startActivity(intent);
    }

    public void showAllLikes(View view){
        Intent intent = new Intent(this, LikesViewerActivity.class);
        intent.putExtra(AppConstants.MEDIA_KEY, (MediaDetails)view.getTag());
        startActivity(intent);
    }

    private void getPopularMedia(){

        InstagramClient client = new InstagramClient();
        client.getPopularMedia(new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int code, JSONObject body) {
                //System.out.println("body: \r\n" + body);
                JSONArray data = null;
                try {
                    data = body.getJSONArray("data");
                    mediaDetailsList = MediaDetails.fromJSON(data);
                    adapter.clear();

                    // Load model objects into the adapter
                    for (MediaDetails mediaDetails : mediaDetailsList) {
                        adapter.add(mediaDetails);

                    }
                }
                catch (JSONException ex){
                    ex.printStackTrace();
                }
                finally {
                    swipeRefreshLayout.setRefreshing(false);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable e, JSONObject errorResponse) {
                Log.d("body", errorResponse.toString());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.photo_viewer, menu);
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

    @Override
    public void OnCommentsListRequested(MediaDetails mediaDetails, RequesterTypes requesterTypes) {
        View view = new View(this);
        view.setTag(mediaDetails);

        showAllComments(view, requesterTypes);
    }

    @Override
    public void OnUserDetailsRequested(MediaDetails mediaDetails) {

        if(mediaDetails == null || mediaDetails.getUserDetails() == null || !mediaDetails.getUserDetails().isValid()){
            Log.d("PVA", "User id is not available to fetch user details");
            return;
        }
        getUserDetails(mediaDetails.getUserDetails());
    }

    private void showUserDetails(UserDetails userDetails){

        Intent intent = new Intent(this, UserDetailsActivity.class);

        intent.putExtra(AppConstants.USER_DETAILS_KEY, userDetails);

        startActivity(intent);
    }

    private void getUserDetails(UserDetails userDetails){

        InstagramClient client = new InstagramClient();
        client.getUserDetails(new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int code, JSONObject body) {
                try {

                    UserDetails userDetails1 = UserDetails.fromJSON(body, "data");

                    showUserDetails(userDetails1);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    Toast.makeText(getBaseContext(), "Could not fetch user details. Please try again later", Toast.LENGTH_SHORT).show();
                } finally {

                }
            }

            @Override
            public void onFailure(Throwable e, JSONObject errorResponse) {
                Log.d("body", errorResponse.toString());

                Toast.makeText(getBaseContext(), "Could not fetch user details. Please try again later", Toast.LENGTH_SHORT).show();
            }
        }, userDetails.getId());

    }
}
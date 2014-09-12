package prafulmantale.praful.com.instagramviewer.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.instagramviewer.R;
import prafulmantale.praful.com.instagramviewer.adapters.PhotoViewerAdapter;
import prafulmantale.praful.com.instagramviewer.models.MediaDetails;
import prafulmantale.praful.com.instagramviewer.restclient.InstagramClient;


public class PhotoViewerActivity extends Activity {

//    CLIENT ID	62babaf98b5c4bfe99f041c9adc30d21
//    CLIENT SECRET	2e3e0bb4d92e443daa5072375bf87c8b
//    WEBSITE URL	http://www.prafulmantale.com
//    REDIRECT URI	http://www.prafulmantale.com


    //https://api.instagram.com/v1/media/search?lat=48.858844&lng=2.294351&access_token=1494410638.1fb234f.fa70833370b9413a855979b011090de8

    private ListView listView;
    private List<MediaDetails> mediaDetailsList;
    private PhotoViewerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);

        initialize();

        getPopularMedia();

    }

    private void initialize(){

        listView = (ListView)findViewById(R.id.lvMedia);
        mediaDetailsList = new ArrayList<MediaDetails>();

        //?? Test
//        mediaDetailsList.add(new MediaDetails());
//        mediaDetailsList.add(new MediaDetails());

        adapter = new PhotoViewerAdapter(this, mediaDetailsList);
        listView.setAdapter(adapter);
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

//                    // Load model objects into the adapter
                    for (MediaDetails mediaDetails : mediaDetailsList) {
                        adapter.add(mediaDetails);
                        //System.out.println("mediaDetails:" + mediaDetails);
                    }
                }
                catch (JSONException ex){
                    ex.printStackTrace();
                }
                finally {
                    adapter.notifyDataSetChanged();

                }

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
}

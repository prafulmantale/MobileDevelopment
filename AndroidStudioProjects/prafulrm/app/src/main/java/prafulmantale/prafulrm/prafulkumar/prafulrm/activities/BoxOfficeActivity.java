package prafulmantale.prafulrm.prafulkumar.prafulrm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.prafulrm.R;
import prafulmantale.prafulrm.prafulkumar.prafulrm.adapters.BoxOfficeMoviesAdapter;
import prafulmantale.prafulrm.prafulkumar.prafulrm.models.BoxOfficeMovie;
import prafulmantale.prafulrm.prafulkumar.prafulrm.restclient.RottenTomatoClient;


public class BoxOfficeActivity extends Activity {
    private ListView lvMovies;
    private BoxOfficeMoviesAdapter adapterMovies;

    private RottenTomatoClient client;

    public static final String MOVIE_DETAIL_KEY = "movie";
    public static final String MOVIE_TITLE_KEY = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_office);

        lvMovies = (ListView) findViewById(R.id.lvMovies);
        List<BoxOfficeMovie> aMovies = new ArrayList<BoxOfficeMovie>();
        adapterMovies = new BoxOfficeMoviesAdapter(this, aMovies);
        lvMovies.setAdapter(adapterMovies);
        setupMovieSelectedListener();
        fetchBoxOfficeMovies();
    }

    public void setupMovieSelectedListener() {
        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View item, int position, long rowId) {
                // Launch the detail view passing movie as an extra
                Intent i = new Intent(BoxOfficeActivity.this, BoxOfficeDetailActivity.class);
                i.putExtra(MOVIE_DETAIL_KEY, adapterMovies.getItem(position));
                i.putExtra(MOVIE_TITLE_KEY, adapterMovies.getItem(position).getTitle());
                startActivity(i);
            }
        });
    }

    private void fetchBoxOfficeMovies(){
        client = new RottenTomatoClient();
        client.getBoxOfficeMovies(new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int code, JSONObject body) {
                JSONArray items = null;
                try{
                    items = body.getJSONArray("movies");
                    List<BoxOfficeMovie>  movies = BoxOfficeMovie.fromJSON(items);

                    // Load model objects into the adapter
                    for (BoxOfficeMovie movie : movies) {
                        adapterMovies.add(movie);
                    }
                }
                catch (JSONException ex){
                    ex.printStackTrace();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.box_office, menu);
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

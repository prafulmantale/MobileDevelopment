package prafulmantale.praful.com.imagefinder.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.imagefinder.R;
import prafulmantale.praful.com.imagefinder.adapters.SearchResultsAdapter;
import prafulmantale.praful.com.imagefinder.handlers.SearchResultsHandler;
import prafulmantale.praful.com.imagefinder.models.SearchResult;
import prafulmantale.praful.com.imagefinder.query.QueryParameters;
import prafulmantale.praful.com.imagefinder.restclient.ImageSearchClient;


public class ImageFinderActivity extends Activity {

    private EditText etSearchQuery;
    private GridView gvImageResult;
    private Button btnSearch;

    private List<SearchResult> searchResults;
    private SearchResultsHandler searchResultsHandler;

    private SearchResultsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_finder);

        initializeViews();

        searchResults = new ArrayList<SearchResult>();

        adapter = new SearchResultsAdapter(this, searchResults);

        gvImageResult.setAdapter(adapter);

        searchResultsHandler = new SearchResultsHandler(searchResults, adapter);
    }


    private void initializeViews(){
        etSearchQuery = (EditText)findViewById(R.id.etSearchQuery);
        gvImageResult = (GridView)findViewById(R.id.gvImageResult);
        btnSearch = (Button)findViewById(R.id.btnSearch);

        etSearchQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(etSearchQuery.getText().toString().trim().length() == 0){
                    //Disable search button
                }
                else{
                    //Enable search button
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        gvImageResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showImageDisplay(searchResults.get(position));
            }
        });
    }

    private void showImageDisplay(SearchResult searchResult){
        Intent intent = new Intent(this, ImageDisplayActivity.class);

        intent.putExtra("SR", searchResult);

        startActivity(intent);
    }

    public void onSearchClick(View view){

        //To do -- If query string has not changes then do not fire query
        String queryString = etSearchQuery.getText().toString();

        if(queryString.isEmpty()){
            return;
        }

        searchResults.clear();
        getImages(queryString);

    }

    private void getImages(String queryText){

        QueryParameters queryParameters = new QueryParameters();
        queryParameters.setQueryText(queryText);

        ImageSearchClient client = new ImageSearchClient();
        client.getImages(searchResultsHandler, queryParameters);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.image_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch(id){
            case R.id.miSearch:
                //Do something
                return true;

            case R.id.miOptions:
                //Do something
                return true;

            case R.id.action_settings:
                return true;
        }


        return super.onOptionsItemSelected(item);
    }
}

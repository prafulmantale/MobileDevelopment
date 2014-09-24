package prafulmantale.praful.com.imagefinder.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.imagefinder.R;
import prafulmantale.praful.com.imagefinder.adapters.SearchResultsAdapter;
import prafulmantale.praful.com.imagefinder.handlers.SearchResultsHandler;
import prafulmantale.praful.com.imagefinder.listeners.EndlessScrollListener;
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

    private QueryParameters queryParameters;
    private ImageSearchClient client = new ImageSearchClient();

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_finder);

        queryParameters = QueryParameters.getInstance();

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
        btnSearch.setEnabled(false);

        etSearchQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(etSearchQuery.getText().toString().trim().length() == 0){
                    btnSearch.setEnabled(false);
                }
                else{
                    btnSearch.setEnabled(true);
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

        gvImageResult.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalCount) {
                if(queryParameters.getQueryText().trim().isEmpty()){
                    return;
                }
                if(queryParameters.getStartIndex() == totalCount){
                    return;
                }

                queryParameters.setStartIndex(totalCount);

                getImages();
            }
        });
    }

    private void showImageDisplay(SearchResult searchResult){
        Intent intent = new Intent(this, ImageDisplayActivity.class);

        intent.putExtra("SR", searchResult);

        startActivity(intent);
    }

    public void onSearchClick(View view){

        String queryString = etSearchQuery.getText().toString();

        if(queryString.isEmpty()){
            return;
        }

//        //If query string has not changes then do not fire query
//        if(queryParameters.getQueryText().trim().equalsIgnoreCase(queryString)){
//            return;
//        }

        resetForNewQuery();
        queryParameters.setQueryText(queryString);
        getImages();

    }

    private void resetForNewQuery(){
        queryParameters.setStartIndex(0);
        adapter.clear();
        searchResults.clear();
    }

    private void getImages(){

        if(isNetworkAvailable() == false){
            Toast.makeText(this, R.string.error_network_not_available, Toast.LENGTH_SHORT).show();
            return;
        }
        client.getImages(searchResultsHandler, queryParameters);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.image_search, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.miSearch);

        searchView = (SearchView)searchMenuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                resetForNewQuery();
                queryParameters.setQueryText(query);
                getImages();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch(id){
            case R.id.miSearch:
                //Do something
                return true;

            case R.id.miOptions:
                showOptions();
                return true;

            case R.id.action_settings:
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void showOptions() {

        Intent intent = new Intent(this, OptionsActivity.class);

        startActivity(intent);
    }


    private  boolean isNetworkAvailable(){
        boolean available = true;

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if(activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()){
            return true;
        }

        return false;
    }
}

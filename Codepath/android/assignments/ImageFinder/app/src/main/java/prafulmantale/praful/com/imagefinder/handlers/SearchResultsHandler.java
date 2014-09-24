package prafulmantale.praful.com.imagefinder.handlers;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import prafulmantale.praful.com.imagefinder.adapters.SearchResultsAdapter;
import prafulmantale.praful.com.imagefinder.models.SearchResult;

/**
 * Created by prafulmantale on 9/19/14.
 */
public class SearchResultsHandler extends JsonHttpResponseHandler {

    private static final String TAG = "SEARCH_RESULT_HANDLER";

    private List<SearchResult> resultList;
    private SearchResultsAdapter adapter;

    public SearchResultsHandler(List<SearchResult> resultList, SearchResultsAdapter adapter) {
        this.resultList = resultList;
        this.adapter = adapter;
    }

    @Override
    public void onSuccess(int statusCode, JSONObject response) {

        if(response == null){
            return;
        }

        List<SearchResult> results = SearchResult.fromJSON(response);

        adapter.addAll(results);
    }

    @Override
    public void onFailure(Throwable e, JSONObject errorResponse) {

        Log.d(TAG, errorResponse.toString());
    }
}

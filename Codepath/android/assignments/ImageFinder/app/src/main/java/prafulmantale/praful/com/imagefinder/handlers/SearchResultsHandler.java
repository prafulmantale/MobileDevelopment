package prafulmantale.praful.com.imagefinder.handlers;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

/**
 * Created by prafulmantale on 9/19/14.
 */
public class SearchResultsHandler extends JsonHttpResponseHandler {

    private final String TAG = "SEARCH_RESULT_HANDLER";

    @Override
    public void onSuccess(int statusCode, JSONObject response) {


    }

    @Override
    public void onFailure(Throwable e, JSONObject errorResponse) {

        Log.d(TAG, errorResponse.toString());
    }
}

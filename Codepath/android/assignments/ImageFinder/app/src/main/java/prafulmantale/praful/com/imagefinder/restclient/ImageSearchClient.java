package prafulmantale.praful.com.imagefinder.restclient;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import prafulmantale.praful.com.imagefinder.query.QueryParameters;

/**
 * Created by prafulmantale on 9/19/14.
 */
public class ImageSearchClient{

    private static final String API_URL = "http://ajax.googleapis.com/ajax/services/search/images";
    private static final String KEY_VERSION = "v";
    private static final String KEY_QUERY = "q";
    private static final String KEY_RESULTS_PER_PAGE = "rsz";


    private AsyncHttpClient httpClient;

    public ImageSearchClient() {
        this.httpClient = new AsyncHttpClient();
    }

    public void getImages(JsonHttpResponseHandler jsonHttpResponseHandler, QueryParameters queryParameters){

        //Move this to Query Parameters
        RequestParams requestParams = new RequestParams();
        requestParams.put(KEY_VERSION, queryParameters.getVersion());
        requestParams.put(KEY_QUERY, queryParameters.getQueryText());
        requestParams.put(KEY_RESULTS_PER_PAGE, queryParameters.getRSZ());

        httpClient.get(API_URL, requestParams, jsonHttpResponseHandler);

    }
}

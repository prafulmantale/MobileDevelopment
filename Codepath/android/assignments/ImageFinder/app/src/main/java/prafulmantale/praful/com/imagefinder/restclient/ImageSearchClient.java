package prafulmantale.praful.com.imagefinder.restclient;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by prafulmantale on 9/19/14.
 */
public class ImageSearchClient{

    private static final String API_URL = "";
    private static final String KEY = "";

    private AsyncHttpClient httpClient;

    public ImageSearchClient() {
        this.httpClient = new AsyncHttpClient();
    }

    public void getImages(JsonHttpResponseHandler jsonHttpResponseHandler){

        RequestParams requestParams = new RequestParams();

        httpClient.get(API_URL, requestParams, jsonHttpResponseHandler);

    }
}

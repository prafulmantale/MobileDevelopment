package prafulmantale.prafulrm.prafulkumar.prafulrm.restclient;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by prafulmantale on 7/26/14.
 */
public class RottenTomatoClient {

    private final String API_KEY = "zgee42ss9jen5rggamzsaum3";
    private final String API_BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/";

    private AsyncHttpClient client;

    public RottenTomatoClient(){
        this.client = new AsyncHttpClient();
    }

    private String getApiURL(String relativeURL){
        return API_BASE_URL + relativeURL;
    }

    public void getBoxOfficeMovies(JsonHttpResponseHandler responseHandler){
        String url = getApiURL("lists/movies/box_office.json");
        RequestParams params = new RequestParams("apikey", API_KEY);
        client.get(url, params, responseHandler);
    }
}



package prafulmantale.praful.com.instagramviewer.restclient;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by prafulmantale on 9/12/14.
 */
public class InstagramClient {


    private static final String API_MEDIA_POPULAR_URL = "https://api.instagram.com/v1/media/popular";
    private static final String ACCESS_TOKEN_KEY = "access_token";
    private static final String ACCESS_TOKEN = "1494410638.1fb234f.fa70833370b9413a855979b011090de8";
    private static final String API_USER_DETAILS_URL = "https://api.instagram.com/v1/users/";

    private AsyncHttpClient httpClient;


    public InstagramClient() {
        this.httpClient = new AsyncHttpClient();
    }


    public void getPopularMedia(JsonHttpResponseHandler responseHandler){
        RequestParams params = new RequestParams(ACCESS_TOKEN_KEY, ACCESS_TOKEN);
        httpClient.get(API_MEDIA_POPULAR_URL, params, responseHandler);
    }

    public void getUserDetails(JsonHttpResponseHandler responseHandler, String userID){
        RequestParams params = new RequestParams(ACCESS_TOKEN_KEY, ACCESS_TOKEN);

        httpClient.get(API_USER_DETAILS_URL + userID + "/", params, responseHandler);
    }
}

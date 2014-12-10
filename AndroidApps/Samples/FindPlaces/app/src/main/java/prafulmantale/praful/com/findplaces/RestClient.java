package prafulmantale.praful.com.findplaces;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * Created by prafulmantale on 10/28/14.
 */
public class RestClient {

    AsyncHttpClient httpClient;

    public RestClient() {
        httpClient = new AsyncHttpClient();
    }

    public void getMatches(JsonHttpResponseHandler responseHandler, String str){

        httpClient.get(str, null, responseHandler);
    }
}

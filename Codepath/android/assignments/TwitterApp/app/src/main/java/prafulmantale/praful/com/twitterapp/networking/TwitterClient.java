package prafulmantale.praful.com.twitterapp.networking;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import java.util.HashMap;
import java.util.Map;

import prafulmantale.praful.com.twitterapp.enums.APIRequest;
import prafulmantale.praful.com.twitterapp.enums.HttpMethod;
import prafulmantale.praful.com.twitterapp.query.QueryParameters;

/**
 * Created by prafulmantale on 9/25/14.
 */
public class TwitterClient  extends OAuthBaseClient{


    private static class Request{
        private String url;
        private HttpMethod httpMethod;


        protected Request(String url, HttpMethod httpMethod) {
            this.url = url;
            this.httpMethod = httpMethod;
        }

        public String getUrl() {
            return url;
        }

        public HttpMethod getHttpMethod() {
            return httpMethod;
        }
    }

    private static Map<APIRequest, Request> requestMap = new HashMap<APIRequest, Request>();

    static {
        requestMap.put(APIRequest.HOME_TIMELINE, new Request("statuses/home_timeline.json", HttpMethod.Get));
    }


    private static final String API_CONSUMER_KEY = "v8nOFys30AJS0D4iSlOFUN6sQ"; //API key
    private static final String API_CONSUMER_SECRET = "Ha8WYGznXDvBcXX3ImXpgyKJ3tJW9yW5B18sUsfny7cfQwvC83"; //API secret

    private static final String API_BASE_URL = "https://api.twitter.com/1.1";

    private static final String API_CALLBACK = "oauth://prafulmantale";

    private static final Class<? extends Api> API_CLASS = TwitterApi.class;

    public TwitterClient(Context context){

        super(context, API_CLASS, API_BASE_URL, API_CONSUMER_KEY, API_CONSUMER_SECRET, API_CALLBACK);
    }

    public void sendRequest(JsonHttpResponseHandler responseHandler, APIRequest apiRequest, QueryParameters queryParameters){

        if(apiRequest == APIRequest.HOME_TIMELINE){
            getHomeTimeline(responseHandler, queryParameters);
        }
    }

    private void getHomeTimeline(JsonHttpResponseHandler responseHandler, QueryParameters queryParameters){

        String url = getApiUrl(requestMap.get(APIRequest.HOME_TIMELINE).url);

        RequestParams params = null;
        if(queryParameters.getMax_id() != null || queryParameters.getSince_id() != null) {
            params = new RequestParams();

            if(queryParameters.getMax_id() != null){
                params.put("max_id", queryParameters.getMax_id());
            }

            if (queryParameters.getSince_id() != null){
                params.put("since_id", queryParameters.getSince_id());
            }
        }

        System.out.println("Request: " + url + params);
        getClient().get(url, params, responseHandler);
    }

}

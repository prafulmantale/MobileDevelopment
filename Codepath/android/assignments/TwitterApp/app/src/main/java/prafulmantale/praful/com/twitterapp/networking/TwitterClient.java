package prafulmantale.praful.com.twitterapp.networking;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import java.util.HashMap;
import java.util.Map;

import prafulmantale.praful.com.twitterapp.enums.APIRequest;
import prafulmantale.praful.com.twitterapp.enums.HttpMethod;

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


    private static final String API_CONSUMER_KEY = "NcaM1a5ErkPhLFjtYndwI4JkW"; //API key
    private static final String API_CONSUMER_SECRET = "ReCGYvs5MJpPRLPj1oLeRXwapDYeNasnwbUP7MTYYeYuQ4LPtx"; //API secret

    private static final String API_BASE_URL = "https://api.twitter.com/1.1";

    private static final String API_CALLBACK = "oauth://prafulmantale";

    private static final Class<? extends Api> API_CLASS = TwitterApi.class;


    private AsyncHttpClient client = new AsyncHttpClient();


    public TwitterClient(Context context){

        super(context, API_CLASS, API_BASE_URL, API_CONSUMER_KEY, API_CONSUMER_SECRET, API_CALLBACK);
    }

    public void sendRequest(AsyncHttpResponseHandler responseHandler, APIRequest apiRequest){

        if(apiRequest == APIRequest.HOME_TIMELINE){
            getHomeTimeline(responseHandler);
        }
    }

    private void getHomeTimeline(AsyncHttpResponseHandler responseHandler){

        String url = getApiUrl(requestMap.get(APIRequest.HOME_TIMELINE).url);
        RequestParams params = new RequestParams();
        params.put("since_id", "1");

        System.out.println("Url:" + url + " params: " + params);
        client.get(url, params, responseHandler);
    }

}
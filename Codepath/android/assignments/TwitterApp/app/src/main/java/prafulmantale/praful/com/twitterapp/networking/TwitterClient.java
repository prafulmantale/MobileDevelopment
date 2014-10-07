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
import prafulmantale.praful.com.twitterapp.models.TweetRequest;
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
        requestMap.put(APIRequest.LOGGEDIN_USER_INFO, new Request("account/verify_credentials.json", HttpMethod.Get));
        requestMap.put(APIRequest.TWEET, new Request("statuses/update.json", HttpMethod.Post));
        requestMap.put(APIRequest.CREATE_FAVORITE, new Request("favorites/create.json", HttpMethod.Post));
        requestMap.put(APIRequest.DESTROY_FAVORITE, new Request("favorites/destroy.json", HttpMethod.Post));
        requestMap.put(APIRequest.USER_TIMELINE, new Request("statuses/user_timeline.json", HttpMethod.Get));
        requestMap.put(APIRequest.USER_MENTIONS, new Request("statuses/mentions_timeline.json", HttpMethod.Get));
        requestMap.put(APIRequest.USER_PROFILE, new Request("users/lookup.json", HttpMethod.Get));

        requestMap.put(APIRequest.FRIENDS_LIST, new Request("friends/list.json", HttpMethod.Get));
        requestMap.put(APIRequest.FOLLOWERS_LIST, new Request("followers/list.json", HttpMethod.Get));
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

        if(apiRequest == APIRequest.LOGGEDIN_USER_INFO){
            getLoggedInUserDetails(responseHandler);
        }

        if(apiRequest == APIRequest.USER_TIMELINE){
            getUserTimeline(responseHandler, queryParameters);
        }

        if(apiRequest == APIRequest.USER_PROFILE){
            getUserProfile(responseHandler, queryParameters);
        }

        if(apiRequest == APIRequest.USER_MENTIONS){
            getMentionsTimeline(responseHandler, queryParameters);
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

    private void getMentionsTimeline(JsonHttpResponseHandler responseHandler, QueryParameters queryParameters){

        String url = getApiUrl(requestMap.get(APIRequest.USER_MENTIONS).url);

        RequestParams params = null;
        params = new RequestParams();
        if(queryParameters.getMax_id() != null || queryParameters.getSince_id() != null) {


            if(queryParameters.getMax_id() != null){
                params.put("max_id", queryParameters.getMax_id());
            }

            if (queryParameters.getSince_id() != null){
                params.put("since_id", queryParameters.getSince_id());
            }


        }

        params.put("count", "5");

        System.out.println("Request: " + url + params);
        getClient().get(url, params, responseHandler);
    }

    private void getUserTimeline(JsonHttpResponseHandler responseHandler, QueryParameters queryParameters){

        String url = getApiUrl(requestMap.get(APIRequest.USER_TIMELINE).url);

        RequestParams params = null;
        if(queryParameters.getMax_id() != null || queryParameters.getSince_id() != null || queryParameters.getUserID() != null) {
            params = new RequestParams();

            if(queryParameters.getMax_id() != null){
                params.put("max_id", queryParameters.getMax_id());
            }

            if (queryParameters.getSince_id() != null){
                params.put("since_id", queryParameters.getSince_id());
            }

            if(queryParameters.getUserID() != null){
                params.put("user_id", queryParameters.getUserID());
            }
        }

        System.out.println("Request: " + url + params);
        getClient().get(url, params, responseHandler);
    }

    private void getUserProfile(JsonHttpResponseHandler responseHandler, QueryParameters queryParameters){

        String url = getApiUrl(requestMap.get(APIRequest.USER_PROFILE).url);

        RequestParams params = null;
        if( queryParameters.getUserID() != null) {
            params = new RequestParams();
                params.put("user_id", queryParameters.getUserID());
        }

        System.out.println("Request: " + url + params);
        getClient().get(url, params, responseHandler);
    }

    private void getLoggedInUserDetails(JsonHttpResponseHandler responseHandler){
        String url = getApiUrl(requestMap.get(APIRequest.LOGGEDIN_USER_INFO).url);
        getClient().get(url, null, responseHandler);
    }

    public void postTweet(JsonHttpResponseHandler responseHandler, TweetRequest request){

        String url = getApiUrl(requestMap.get(APIRequest.TWEET).url);
        RequestParams params = new RequestParams();
        params.put("status", request.getBody());
        if(request.getTweetID() != -1){
            params.put("in_reply_to_status_id", String.valueOf(request.getTweetID()));
        }

        getClient().post(url, params, responseHandler);
    }

    public void postCreateFavoriteTweet(JsonHttpResponseHandler responseHandler, TweetRequest request){

        String url = getApiUrl(requestMap.get(APIRequest.CREATE_FAVORITE).url);
        RequestParams params = new RequestParams();
        params.put("id", String.valueOf(request.getTweetID()));

        getClient().post(url, params, responseHandler);
    }

    public void postDestroyFavoriteTweet(JsonHttpResponseHandler responseHandler, TweetRequest request){

        String url = getApiUrl(requestMap.get(APIRequest.DESTROY_FAVORITE).url);
        RequestParams params = new RequestParams();
        params.put("id", String.valueOf(request.getTweetID()));

        getClient().post(url, params, responseHandler);
    }


    public void getFriendsList(JsonHttpResponseHandler responseHandler, String userId){
        String url = getApiUrl(requestMap.get(APIRequest.FRIENDS_LIST).url);
        RequestParams params = new RequestParams();
        if(userId != null && !userId.isEmpty()) {
            params.put("user_id", userId);
        }
//        params.put("count", "20");
//        params.put("cursor", "-1");
        params.put("skip_status", "1");

        System.out.println(url + params);
        getClient().get(url, params, responseHandler);
    }

    public void getFollowersList(JsonHttpResponseHandler responseHandler, String userId){
        String url = getApiUrl(requestMap.get(APIRequest.FOLLOWERS_LIST).url);
        RequestParams params = new RequestParams();
        if(userId != null && !userId.isEmpty()) {
            params.put("user_id", userId);
        }
//        params.put("count", "20");
//        params.put("cursor", "-1");
        params.put("skip_status", "1");

        System.out.println(url + params);
        getClient().get(url, params, responseHandler);
    }

}

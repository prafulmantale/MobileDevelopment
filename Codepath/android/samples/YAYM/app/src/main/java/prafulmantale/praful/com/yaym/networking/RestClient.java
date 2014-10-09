package prafulmantale.praful.com.yaym.networking;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import prafulmantale.praful.com.yaym.helpers.AppConstants;
import prafulmantale.praful.com.yaym.helpers.Utils;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class RestClient {

    private static final String API_BASE_URL =  "https://demo3.ym.integral.net/fxi/fxiapi/";
    private static final String SNAPSHOt_URL = "snapshot";

    private AsyncHttpClient client;

    public RestClient() {
        this.client = new AsyncHttpClient();
    }

    public void getRWSnapshot(JsonHttpResponseHandler handler){
        RequestParams params = new RequestParams();

        params.put(AppConstants.PARAM_KEY_ORG,"YMSBAQA");
        params.put(AppConstants.PARAM_KEY_CCYPAIR, AppConstants.TEXT_ALL);

        client.get(Utils.getAPIUrl(API_BASE_URL, SNAPSHOt_URL), params, handler);

    }

}

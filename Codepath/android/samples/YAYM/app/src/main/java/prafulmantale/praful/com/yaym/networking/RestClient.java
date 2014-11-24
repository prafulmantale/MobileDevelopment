package prafulmantale.praful.com.yaym.networking;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import prafulmantale.praful.com.yaym.application.YMApplication;
import prafulmantale.praful.com.yaym.helpers.AppConstants;
import prafulmantale.praful.com.yaym.helpers.Utils;
import prafulmantale.praful.com.yaym.models.LoginRequest;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class RestClient {

    private static final String SNAPSHOt_URL = "rw/riskwarehouse/snapshot";

    private AsyncHttpClient client;
    private PersistentCookieStore cookieStore;
    private LoginRequest loginRequest;


    public RestClient(PersistentCookieStore cookieStore) {
        this.client = new AsyncHttpClient();
        this.client.setCookieStore(cookieStore);
        this.cookieStore = cookieStore;
    }

    public void getRWSnapshot(JsonHttpResponseHandler handler){
        RequestParams params = new RequestParams();

        params.put(AppConstants.PARAM_KEY_ORG, loginRequest.getOrganization());
        params.put(AppConstants.PARAM_KEY_CCYPAIR, AppConstants.TEXT_ALL);

        client.setCookieStore(cookieStore);

        client.get(Utils.getAPIUrl(YMApplication.getAppBaseUrl(), SNAPSHOt_URL), params, handler);

    }
}

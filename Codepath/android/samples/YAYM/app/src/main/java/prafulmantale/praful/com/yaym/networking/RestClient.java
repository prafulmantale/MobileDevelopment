package prafulmantale.praful.com.yaym.networking;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.apache.http.entity.StringEntity;

import prafulmantale.praful.com.yaym.helpers.AppConstants;
import prafulmantale.praful.com.yaym.helpers.Utils;
import prafulmantale.praful.com.yaym.models.LoginRequest;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class RestClient {

    private static final String API_BASE_URL =  "https://demo3.ym.integral.net/fxi/";
    private static final String SNAPSHOt_URL = "rw/riskwarehouse/snapshot";
    private static final String RULES_URL = "rw/riskwarehouse/rule";
    private static final String LOGIN_URL = "admin/auth/login";

    private AsyncHttpClient client;
    private PersistentCookieStore cookieStore;
    private LoginRequest loginRequest;


    public RestClient(PersistentCookieStore cookieStore) {
        this.client = new AsyncHttpClient();
        this.client.setCookieStore(cookieStore);
        this.cookieStore = cookieStore;
    }


    public void getRWRules(JsonHttpResponseHandler handler){
        RequestParams params = new RequestParams();

        params.put(AppConstants.PARAM_KEY_ORG, loginRequest.getOrganization());
        params.put(AppConstants.PARAM_KEY_NAMESPACE, loginRequest.getOrganization());

        client.setCookieStore(cookieStore);

        client.get(Utils.getAPIUrl(API_BASE_URL, RULES_URL), params, handler);

    }

    public void getRWSnapshot(JsonHttpResponseHandler handler){
        RequestParams params = new RequestParams();

        params.put(AppConstants.PARAM_KEY_ORG, loginRequest.getOrganization());
        params.put(AppConstants.PARAM_KEY_CCYPAIR, AppConstants.TEXT_ALL);

        client.setCookieStore(cookieStore);

        client.get(Utils.getAPIUrl(API_BASE_URL, SNAPSHOt_URL), params, handler);

    }

    public void login(Context context, JsonHttpResponseHandler handler, LoginRequest request){

        cookieStore.clear();
        String url = Utils.getAPIUrl(API_BASE_URL, LOGIN_URL);
        client.addHeader("Content-Type", "application/json; charset=UTF-8");

        loginRequest = request;
        try {
            StringEntity entity = new StringEntity(request.toJSON());
            client.post(context, url, entity, "application/json", handler);
        }
        catch (Exception ex){

        }
    }

}

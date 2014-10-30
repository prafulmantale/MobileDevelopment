package prafulmantale.praful.com.yaym.networking;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.apache.http.entity.StringEntity;

import prafulmantale.praful.com.yaym.activities.LoginActivity;
import prafulmantale.praful.com.yaym.helpers.AppConstants;
import prafulmantale.praful.com.yaym.helpers.Utils;
import prafulmantale.praful.com.yaym.models.LoginRequest;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class RestClient {

    private static final String API_BASE_URL =  "https://demo3.ym.integral.net/fxi/";
    private static final String SNAPSHOt_URL = "rw/riskwarehouse/snapshot";
    private static final String LOGIN_URL = "admin/auth/login";

    private AsyncHttpClient client;


    public RestClient(PersistentCookieStore cookieStore) {
        this.client = new AsyncHttpClient();
        this.client.setCookieStore(cookieStore);
    }



    public void getRWSnapshot(JsonHttpResponseHandler handler, PersistentCookieStore cookieStore){
        RequestParams params = new RequestParams();

        params.put(AppConstants.PARAM_KEY_ORG,"YMSBAQA");
        params.put(AppConstants.PARAM_KEY_CCYPAIR, AppConstants.TEXT_ALL);

        String url = Utils.getAPIUrl(API_BASE_URL, SNAPSHOt_URL);
        System.out.println("getRWSnapshot: " + url + params);
        client.setCookieStore(cookieStore);

        client.get(Utils.getAPIUrl(API_BASE_URL, SNAPSHOt_URL), params, handler);

    }

    public void login(Context context, JsonHttpResponseHandler handler, LoginRequest request){

        LoginActivity.cookieStore.clear();
        System.out.println("### login" + request.getRequestParams().toString());
        String url = Utils.getAPIUrl(API_BASE_URL, LOGIN_URL);
        System.out.println("### login url" + url);
        client.addHeader("Content-Type", "application/json; charset=UTF-8");


        try {
            StringEntity entity = new StringEntity(request.toJSON());
            client.post(context, url, entity, "application/json", handler);
        }
        catch (Exception ex){

        }
    }

}

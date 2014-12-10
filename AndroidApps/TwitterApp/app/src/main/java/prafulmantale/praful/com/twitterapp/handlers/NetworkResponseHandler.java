package prafulmantale.praful.com.twitterapp.handlers;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import prafulmantale.praful.com.twitterapp.enums.APIRequest;
import prafulmantale.praful.com.twitterapp.enums.RefreshType;
import prafulmantale.praful.com.twitterapp.interfaces.NetworkResponseListener;

/**
 * Created by prafulmantale on 10/8/14.
 */
public class NetworkResponseHandler extends JsonHttpResponseHandler{

    public enum RequestStatus{
        SUCCESS,
        FAILURE
    }

    private static final String TAG = NetworkResponseHandler.class.getName();

    private NetworkResponseListener listener;
    private APIRequest requestType;
    private RefreshType refreshType;

    public NetworkResponseHandler(NetworkResponseListener listener, APIRequest requestType, RefreshType refreshType) {
        this.listener = listener;
        this.requestType = requestType;
        this.refreshType = refreshType;
    }

    @Override
    public void onSuccess(JSONObject response) {
        listener.OnNetworkResponseReceived(RequestStatus.SUCCESS, requestType, response, refreshType);
    }

    @Override
    public void onSuccess(JSONArray response) {
        listener.OnNetworkResponseReceived(RequestStatus.SUCCESS, requestType, response, refreshType);
    }

    @Override
    public void onFailure(Throwable e, JSONObject errorResponse) {
        Log.d("DEBUG: Failure \r\n", e.getMessage());
        listener.OnNetworkResponseReceived(RequestStatus.SUCCESS, requestType, null, refreshType);
    }

    @Override
    protected void handleFailureMessage(Throwable e, String responseBody) {
        super.handleFailureMessage(e, responseBody);
        listener.OnNetworkResponseReceived(RequestStatus.SUCCESS, requestType, null, refreshType);
    }
}

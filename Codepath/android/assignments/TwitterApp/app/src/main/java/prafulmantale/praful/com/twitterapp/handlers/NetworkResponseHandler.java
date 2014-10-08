package prafulmantale.praful.com.twitterapp.handlers;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import prafulmantale.praful.com.twitterapp.enums.APIRequest;
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

    public NetworkResponseHandler(NetworkResponseListener listener, APIRequest requestType) {
        this.listener = listener;
        this.requestType = requestType;
    }

    @Override
    public void onSuccess(JSONObject response) {
        listener.OnNetworkResponseReceived(RequestStatus.SUCCESS, requestType, response);
    }

    @Override
    public void onFailure(Throwable e, JSONObject errorResponse) {
        Log.d("DEBUG: Failure \r\n", e.getMessage());
        listener.OnNetworkResponseReceived(RequestStatus.SUCCESS, requestType, null);
    }

    @Override
    protected void handleFailureMessage(Throwable e, String responseBody) {
        super.handleFailureMessage(e, responseBody);
        listener.OnNetworkResponseReceived(RequestStatus.SUCCESS, requestType, null);
    }
}

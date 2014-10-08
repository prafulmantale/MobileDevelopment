package prafulmantale.praful.com.twitterapp.interfaces;

import prafulmantale.praful.com.twitterapp.enums.APIRequest;
import prafulmantale.praful.com.twitterapp.enums.RefreshType;
import prafulmantale.praful.com.twitterapp.handlers.NetworkResponseHandler;

/**
 * Created by prafulmantale on 10/8/14.
 */
public interface NetworkResponseListener {

    public void OnNetworkResponseReceived(NetworkResponseHandler.RequestStatus status, APIRequest requestType, Object responseObject, RefreshType refreshType);
}

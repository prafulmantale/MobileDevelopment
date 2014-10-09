package prafulmantale.praful.com.yaym.interfaces;

import prafulmantale.praful.com.yaym.enums.APIRequest;
import prafulmantale.praful.com.yaym.handlers.NetworkResponseHandler;

/**
 * Created by prafulmantale on 10/9/14.
 */
public interface NetworkResponseListener {

    public void OnNetworkResponseReceived(NetworkResponseHandler.RequestStatus status, APIRequest requestType, Object responseObject);
}

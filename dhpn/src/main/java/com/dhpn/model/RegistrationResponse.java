package com.dhpn.model;

import com.dhpn.enums.RequestStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by prafulmantale on 3/1/15.
 */
public class RegistrationResponse extends BaseResponse{


    public RegistrationResponse(RequestStatus status) {
        super(status);
    }
}

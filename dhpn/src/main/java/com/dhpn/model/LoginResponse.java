package com.dhpn.model;

import com.dhpn.enums.RequestStatus;

/**
 * Created by prafulmantale on 3/1/15.
 */
public class LoginResponse extends BaseResponse{

    public LoginResponse(RequestStatus status) {
        super(status);
    }
}

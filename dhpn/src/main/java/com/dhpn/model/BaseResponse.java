package com.dhpn.model;

import com.dhpn.enums.RequestStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by prafulmantale on 3/1/15.
 */
public abstract class BaseResponse {

    @JsonProperty("status")
    protected RequestStatus status;

    @JsonProperty("errorcode")
    protected String errorCode;

    @JsonProperty("errormsg")
    protected String errorMessage;

    public BaseResponse(RequestStatus status) {
        this.status = status;
        errorMessage = "";
        errorCode = "";
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

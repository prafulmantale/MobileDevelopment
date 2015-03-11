package com.dhpn.model;

import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * Created by prafulmantale on 3/1/15.
 */
public class RegistrationRequest {

    @NotNull
    @NotBlank
    @NotEmpty
    @Email
    private String emailId;

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 9, max = 16)
    private String password;

    public RegistrationRequest() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

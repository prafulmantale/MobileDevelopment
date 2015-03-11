package com.dhpn.enums;

/**
 * Created by prafulmantale on 3/9/15.
 */
public enum  Role {

    ROLE_ADMIN ("ROLE_ADMIN"),
    ROLE_USER ("ROLE_USER");

    Role(String value){
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}

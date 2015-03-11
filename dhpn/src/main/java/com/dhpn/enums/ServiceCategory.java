package com.dhpn.enums;

/**
 * Created by prafulmantale on 2/21/15.
 */
public enum ServiceCategory {

    None ("Unspecified"),
    COOK ("Cook"),
    DOMESTICHELP ("Domestic Help"),
    NANNY ("Nanny"),
    DOMESTICDRIVER ("Domestic Driver");

    ServiceCategory(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}

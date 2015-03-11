package com.dhpn.enums;

/**
 * Created by prafulmantale on 3/2/15.
 */
public enum IdentificationType {

    LICENSE ("License"),
    RATIONCARD ("Ration Card"),
    PASSPORT ("Passport"),
    PANCARD ("Pan Card");

    IdentificationType(String value) {
    }

    private String value;

    public String getValue() {
        return value;
    }
}

package com.dhpn.model;

import com.dhpn.enums.IdentificationType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by prafulmantale on 3/2/15.
 */
@Document
public class Identification {

    private IdentificationType type;

    private String number;

    private String imageUrl;


    public Identification() {
    }

    public IdentificationType getType() {
        return type;
    }

    public void setType(IdentificationType type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

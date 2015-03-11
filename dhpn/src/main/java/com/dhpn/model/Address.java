package com.dhpn.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by prafulmantale on 2/21/15.
 */

@Document
public class Address {

    @JsonProperty("addr1")
    @Field(value = "addr1")
    private String addressLine1;

    @JsonProperty("addr2")
    @Field(value = "addr2")
    private String addressLine2;

    @JsonProperty("city")
    @Field(value = "city")
    private String city;

    @JsonProperty("state")
    @Field(value = "state")
    private String state;

    @JsonProperty("country")
    @Field(value = "country")
    private String country;

    @JsonProperty("pin")
    @Field(value = "pin")
    private String pinCode;

    public Address() {

        addressLine1 = "";
        addressLine2 = "";
        city = "";
        state = "";
        country = "";
        pinCode = "";
    }


    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }


    @Override
    public String toString() {
        return "Address{" +
                "addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pinCode='" + pinCode + '\'' +
                '}';
    }
}

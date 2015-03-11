package com.dhpn.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by prafulmantale on 3/1/15.
 */
public class UserProfileUpdateRequest {

    @JsonProperty("email")
    private String emailId;

    @JsonProperty("fn")
    private String firstName;

    @JsonProperty("ln")
    private String lastName;

    @JsonProperty("addr")
    private Address address;

    @JsonProperty("contacts")
    private List<ContactInfo> contacts;

    public UserProfileUpdateRequest() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<ContactInfo> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactInfo> contacts) {
        this.contacts = contacts;
    }
}

package com.dhpn.model;

import com.dhpn.enums.Role;
import com.dhpn.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by prafulmantale on 2/21/15.
 */

@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Field(value = "email")
    @JsonProperty("email")
    private String emailId;

    @Field(value = "pass")
    @JsonProperty("pass")
    private String password;

    @Field(value = "fn")
    @JsonProperty("fn")
    private String firstName;

    @Field(value = "ln")
    @JsonProperty("ln")
    private String lastName;

    @Field(value = "role")
    @JsonIgnore
    private String role;

    @Field(value = "image")
    @JsonProperty("image")
    private String imageUrl;

    @Field(value = "addr")
    @JsonProperty("addr")
    private Address address;

    @Field(value = "contacts")
    @JsonProperty("contacts")
    private List<ContactInfo> contacts;

    @Field(value = "ts")
    @JsonProperty("ts")
    private Date createdTimestamp;

    @Field(value = "dts")
    @JsonProperty("dts")
    private String displayCreatedTimestamp;

    @Field(value = "lts")
    private long lastUpdated;

    @Field(value = "dlts")
    @JsonProperty("dlts")
    private String displayLastUpdateTime;

    @Field(value = "reviews")
    @JsonIgnore
    private List<String> reviews;

    @Field(value = "inactive")
    @JsonIgnore
    private boolean inActive;

    @Field(value = "passexp")
    @JsonIgnore
    private boolean passwordExpired;

    public User() {

        role = Role.ROLE_USER.getValue();

        contacts = new ArrayList<ContactInfo>();
        createdTimestamp = new Date();
        displayCreatedTimestamp = DateTimeUtils.getFormattedCurrentTime();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getDisplayCreatedTimestamp() {
        return displayCreatedTimestamp;
    }

    public void setDisplayCreatedTimestamp(String displayCreatedTimestamp) {
        this.displayCreatedTimestamp = displayCreatedTimestamp;
    }

    public String getDisplayLastUpdateTime() {
        return displayLastUpdateTime;
    }

    public void setDisplayLastUpdateTime(String displayLastUpdateTime) {
        this.displayLastUpdateTime = displayLastUpdateTime;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public boolean isInActive() {
        return inActive;
    }

    public void setInActive(boolean inActive) {
        this.inActive = inActive;
    }

    public boolean isPasswordExpired() {
        return passwordExpired;
    }

    public void setPasswordExpired(boolean passwordExpired) {
        this.passwordExpired = passwordExpired;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", address=" + address +
                ", contacts=" + contacts +
                ", createdTimestamp=" + createdTimestamp +
                ", displayCreatedTimestamp='" + displayCreatedTimestamp + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", displayLastUpdateTime='" + displayLastUpdateTime + '\'' +
                ", reviews=" + reviews +
                ", inActive=" + inActive +
                ", passwordExpired=" + passwordExpired +
                '}';
    }

}

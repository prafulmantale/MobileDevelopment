package com.dhpn.model;

import com.dhpn.enums.IdentificationType;
import com.dhpn.enums.ServiceCategory;
import com.dhpn.model.User;
import com.dhpn.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by prafulmantale on 2/21/15.
 */

@Document(collection = "serviceproviders")
public class ServiceProvider {

    @Id
    private String id;

    @Field(value = "fn")
    @JsonProperty("fn")
    private String firstName;

    @Field(value = "ln")
    @JsonProperty("ln")
    private String lastName;

    @Field(value = "image")
    @JsonProperty("image")
    private String imageUrl;

    @Field(value = "contacts")
    @JsonProperty("contacts")
    private List<ContactInfo> contacts;


    @Field(value = "ids")
    @JsonProperty("ids")
    private List<Identification> identifications;

    @Field(value = "sp")
    @JsonProperty("sp")
    private List<ServiceCategory> servicesProvided;

    @Field(value = "ct")
    @JsonIgnore
    private Date createdTime;

    @Field(value = "dct")
    @JsonProperty("dct")
    private String displayCreatedTime;

    @Field(value = "reviews")
    @JsonIgnore
    private List<String> reviews;



    public ServiceProvider() {

        createdTime = new Date();
        displayCreatedTime = DateTimeUtils.getFormattedCurrentTime();

        this.identifications = new ArrayList<Identification>();
        servicesProvided = new ArrayList<ServiceCategory>();
        reviews = new ArrayList<String>();
        contacts = new ArrayList<ContactInfo>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<ContactInfo> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactInfo> contacts) {
        this.contacts = contacts;
    }

    public List<Identification> getIdentifications() {
        return identifications;
    }

    public void setIdentifications(List<Identification> identifications) {
        this.identifications = identifications;
    }

    public List<ServiceCategory> getServicesProvided() {
        return servicesProvided;
    }

    public void setServicesProvided(List<ServiceCategory> servicesProvided) {
        this.servicesProvided = servicesProvided;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getDisplayCreatedTime() {
        return displayCreatedTime;
    }

    public void setDisplayCreatedTime(String displayCreatedTime) {
        this.displayCreatedTime = displayCreatedTime;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }
}

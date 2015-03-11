package com.dhpn.model;

import com.dhpn.enums.TransportType;

import java.util.List;

/**
 * Created by prafulmantale on 3/5/15.
 */
public class TransportInfo {

    private TransportType type;

    private String registrationNumber;

    private String registrationPlateImageUrl;

    private DriverInfo driverInfo;

    private String company;

    private List<Review> reviews;


    public TransportInfo() {
    }

    public TransportType getType() {
        return type;
    }

    public void setType(TransportType type) {
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationPlateImageUrl() {
        return registrationPlateImageUrl;
    }

    public void setRegistrationPlateImageUrl(String registrationPlateImageUrl) {
        this.registrationPlateImageUrl = registrationPlateImageUrl;
    }

    public DriverInfo getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(DriverInfo driverInfo) {
        this.driverInfo = driverInfo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}

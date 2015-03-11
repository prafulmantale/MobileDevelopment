package com.dhpn.model;

/**
 * Created by prafulmantale on 3/5/15.
 */
public class DriverInfo {

    private String name;

    private String licenseNumber;

    private String imageUrl;

    public DriverInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

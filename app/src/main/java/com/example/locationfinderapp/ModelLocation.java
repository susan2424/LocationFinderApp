package com.example.locationfinderapp;

public class ModelLocation {
    int id;
    String locationAddress;
    String locationLatitude;
    String locationLongitude;

    // Default constructor
    ModelLocation() {}

    // Constructor with parameters for the new fields
    public ModelLocation(String locationAddress, String locationLatitude, String locationLongitude) {
        this.locationAddress = locationAddress;
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
    }

    // Constructor with ID
    public ModelLocation(int id, String locationAddress, String locationLatitude, String locationLongitude) {
        this.id = id;
        this.locationAddress = locationAddress;
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
    }

    // Getters and Setters for the entries in the database
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(String locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public String getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(String locationLongitude) {
        this.locationLongitude = locationLongitude;
    }
}

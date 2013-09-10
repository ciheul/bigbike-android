package com.ciheul.bigbike.loader;

public class ShelterModel {

    private String name;
    private String shelterType;
    private int capacity;
    private double longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShelterType() {
        return shelterType;
    }

    public void setShelterType(String shelterType) {
        this.shelterType = shelterType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    private double latitude;

}

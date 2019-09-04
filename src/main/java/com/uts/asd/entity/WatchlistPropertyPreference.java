package com.uts.asd.entity;

/*
 * @author Harold Seefeld
 */

import java.util.UUID;

public class WatchlistPropertyPreference {

    public WatchlistPropertyPreference() {}

    public WatchlistPropertyPreference(String customerID, String typeID, String preferenceID, int garageSpaces, int numOfBathrooms, int numOfBedrooms, int postCode) {
        this.customerID = customerID;
        this.typeID = typeID;
        this.preferenceID = preferenceID;
        this.garageSpaces = garageSpaces;
        this.numOfBathrooms = numOfBathrooms;
        this.numOfBedrooms = numOfBedrooms;
        this.postCode = postCode;
    }

    public WatchlistPropertyPreference(String customerID, String typeID, int garageSpaces, int numOfBathrooms, int numOfBedrooms, int postCode) {
        this.customerID = customerID;
        this.typeID = typeID;
        this.garageSpaces = garageSpaces;
        this.numOfBathrooms = numOfBathrooms;
        this.numOfBedrooms = numOfBedrooms;
        this.postCode = postCode;
        // Creating a random UUID (Universally unique identifier).
        preferenceID = UUID.randomUUID().toString();
    }

    // Declare private variables
    private String customerID;
    private String typeID;
    private String preferenceID;
    private int garageSpaces;
    private int numOfBathrooms;
    private int numOfBedrooms;
    private int postCode;

    // Getters and setters
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public int getGarageSpaces() {
        return garageSpaces;
    }

    public void setGarageSpaces(int garageSpaces) {
        this.garageSpaces = garageSpaces;
    }

    public int getNumOfBathrooms() {
        return numOfBathrooms;
    }

    public void setNumOfBathrooms(int numOfBathrooms) {
        this.numOfBathrooms = numOfBathrooms;
    }

    public int getNumOfBedrooms() {
        return numOfBedrooms;
    }

    public void setNumOfBedrooms(int numOfBedrooms) {
        this.numOfBedrooms = numOfBedrooms;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getPreferenceID() {
        return preferenceID;
    }
}

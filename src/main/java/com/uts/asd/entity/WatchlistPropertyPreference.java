package com.uts.asd.entity;

/*
 * @author Harold Seefeld
 */

public class WatchlistPropertyPreference {

    public WatchlistPropertyPreference() {}

    public WatchlistPropertyPreference(String customerID, String typeID, int preferenceID, int garageSpaces, int numOfBathrooms, int numOfBedrooms, int postCode) {
        CustomerID = customerID;
        TypeID = typeID;
        PreferenceID = preferenceID;
        GarageSpaces = garageSpaces;
        NumOfBathrooms = numOfBathrooms;
        NumOfBedrooms = numOfBedrooms;
        PostCode = postCode;
    }

    // Declare private variables
    private String CustomerID;
    private String TypeID;
    private int PreferenceID;
    private int GarageSpaces;
    private int NumOfBathrooms;
    private int NumOfBedrooms;
    private int PostCode;

    // Getters and setters
    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getTypeID() {
        return TypeID;
    }

    public void setTypeID(String typeID) {
        TypeID = typeID;
    }

    public int getGarageSpaces() {
        return GarageSpaces;
    }

    public void setGarageSpaces(int garageSpaces) {
        GarageSpaces = garageSpaces;
    }

    public int getNumOfBathrooms() {
        return NumOfBathrooms;
    }

    public void setNumOfBathrooms(int numOfBathrooms) {
        NumOfBathrooms = numOfBathrooms;
    }

    public int getNumOfBedrooms() {
        return NumOfBedrooms;
    }

    public void setNumOfBedrooms(int numOfBedrooms) {
        NumOfBedrooms = numOfBedrooms;
    }

    public int getPostCode() {
        return PostCode;
    }

    public void setPostCode(int postCode) {
        PostCode = postCode;
    }

    public int getPreferenceID() {
        return PreferenceID;
    }
}

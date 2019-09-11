package com.uts.asd.entity;

/*
 * @author Harold Seefeld
 */

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

public class WatchlistPropertyPreference {

    // Declare private variables
    private int customerID;
    @NotBlank (message = "Property type cannot be left empty.") @NotNull (message = "Property type cannot be left empty.")
    private String typeID;
    private String preferenceID;
    private int garageSpaces;
    private int numOfBathrooms;
    private int numOfBedrooms;
    @Size(min=2, max=20, message = "Suburb must be between 2 and 20 characters long.")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Suburb must consist of alphabetical letters only.")
    private String suburb;

    public WatchlistPropertyPreference() {}

    public WatchlistPropertyPreference(int customerID, String typeID, String preferenceID, int garageSpaces, int numOfBathrooms, int numOfBedrooms, String suburb) {
        this.customerID = customerID;
        this.typeID = typeID;
        this.preferenceID = preferenceID;
        this.garageSpaces = garageSpaces;
        this.numOfBathrooms = numOfBathrooms;
        this.numOfBedrooms = numOfBedrooms;
        this.suburb = suburb;
    }

    public WatchlistPropertyPreference(int customerID, String preferenceID) {
        this.customerID = customerID;
        this.preferenceID = preferenceID;
    }

    // Getters and setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
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

    public String getSuburb() { return suburb; }

    public void setSuburb(String suburb) { this.suburb = suburb; }

    public String getPreferenceID() {
        return preferenceID;
    }

    public void assignPreferenceID() {
        // Creating a random UUID (Universally unique identifier)
        preferenceID = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "WatchlistPropertyPreference{" +
                "customerID='" + customerID + '\'' +
                ", typeID='" + typeID + '\'' +
                ", preferenceID='" + preferenceID + '\'' +
                ", garageSpaces=" + garageSpaces +
                ", numOfBathrooms=" + numOfBathrooms +
                ", numOfBedrooms=" + numOfBedrooms +
                ", suburb=" + suburb +
                '}';
    }
}

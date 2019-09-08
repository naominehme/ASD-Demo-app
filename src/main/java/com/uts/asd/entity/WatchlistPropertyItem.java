package com.uts.asd.entity;

/*
 * @author Harold Seefeld
 */

import com.google.cloud.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WatchlistPropertyItem {

    // Declare private variables
    private String customerID;
    @NotNull @NotBlank
    private String propertyID;
    private String createdDate;

    public WatchlistPropertyItem () {}

    public WatchlistPropertyItem(String customerID, String propertyID) {
        setCustomerID(customerID);
        setPropertyID(propertyID);
        setCreatedDate(Timestamp.now().toString());
    }

    public WatchlistPropertyItem(String customerID, String propertyID, String createdDate) {
        setCustomerID(customerID);
        setPropertyID(propertyID);
        setCreatedDate(createdDate);
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getPropertyID() {
        return propertyID;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setPropertyID(String propertyID) {
        this.propertyID = propertyID;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "WatchlistPropertyItem{" +
                "customerID='" + customerID + '\'' +
                ", propertyID='" + propertyID + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}

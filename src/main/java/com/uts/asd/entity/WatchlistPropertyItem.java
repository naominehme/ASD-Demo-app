package com.uts.asd.entity;

/*
 * @author Harold Seefeld
 */

import com.google.cloud.Timestamp;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WatchlistPropertyItem {

    // Declare private variables
    private String customerID;
    @Min(0)
    private int propertyID;
    private String createdDate;
    private Property property;

    public WatchlistPropertyItem () {}

    public WatchlistPropertyItem(String customerID, int propertyID) {
        setCustomerID(customerID);
        setPropertyID(propertyID);
        setCreatedDate(Timestamp.now().toString());
    }

    public WatchlistPropertyItem(String customerID, int propertyID, String createdDate) {
        setCustomerID(customerID);
        setPropertyID(propertyID);
        setCreatedDate(createdDate);
    }

    public String getCustomerID() {
        return customerID;
    }

    public int getPropertyID() { return propertyID; }

    public String getCreatedDate() {
        return createdDate;
    }

    public Property getProperty() { return property; }

    public void setProperty(Property property) { this.property = property; }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setPropertyID(int propertyID) {
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
                ", createdDate='" + createdDate + '\'' +
                ", property=" + property +
                '}';
    }
}

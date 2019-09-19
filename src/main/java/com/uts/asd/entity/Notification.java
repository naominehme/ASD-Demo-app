package com.uts.asd.entity;

public class Notification {

    // Declare private variables
    private int customerID;
    private int propertyID;
    private String createdDate;
    private Property property;
    private Bid bid;

    public Notification(int customerID, int propertyID, String createdDate) {
        this.customerID = customerID;
        this.propertyID = propertyID;
        this.createdDate = createdDate;
    }

    public Notification() { }

    public int getCustomerID() { return customerID; }

    public void setCustomerID(int customerID) { this.customerID = customerID; }

    public int getPropertyID() { return propertyID; }

    public void setPropertyID(int propertyID) { this.propertyID = propertyID; }

    public String getCreatedDate() { return createdDate; }

    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }

    public Property getProperty() { return property; }

    public void setProperty(Property property) { this.property = property; }

    public Bid getBid() { return bid; }

    public void setBid(Bid bid) { this.bid = bid; }
}

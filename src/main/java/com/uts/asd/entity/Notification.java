package com.uts.asd.entity;

import com.google.cloud.Timestamp;

import java.util.UUID;

public class Notification {

    // Declare private variables
    private String customerID;
    private int propertyID;
    private int bidID;
    private String notificationID;
    private String createdDate;
    private Property property;
    private Bid bid;

    public Notification(String notificationID, String customerID, int propertyID, int bidID, String createdDate) {
        this.notificationID = notificationID;
        this.customerID = customerID;
        this.propertyID = propertyID;
        this.bidID = bidID;
        this.createdDate = createdDate;
    }

    public Notification(String customerID, int propertyID, int bidID, String createdDate) {
        this.customerID = customerID;
        this.propertyID = propertyID;
        this.bidID = bidID;
        this.createdDate = createdDate;
    }

    public Notification(String customerID, String notificationID) {
        this.customerID = customerID;
        this.notificationID = notificationID;
        this.createdDate = Timestamp.now().toString();
    }

    public Notification() { }

    public String getCustomerID() { return customerID; }

    public void setCustomerID(String customerID) { this.customerID = customerID; }

    public int getPropertyID() { return propertyID; }

    public void setPropertyID(int propertyID) { this.propertyID = propertyID; }

    public int getBidID() { return bidID; }

    public void setBidID(int bidID) { this.bidID = bidID; }

    public String getNotificationID() { return notificationID; }

    public void setNotificationID(String notificationID) { this.notificationID = notificationID; }

    public String getCreatedDate() { return createdDate; }

    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }

    public Property getProperty() { return property; }

    public void setProperty(Property property) { this.property = property; }

    public Bid getBid() { return bid; }

    public void setBid(Bid bid) { this.bid = bid; }

    public void assignNotificationID() {
        // Creating a random UUID (Universally unique identifier)
        notificationID = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Notification{" +
                "customerID=" + customerID +
                ", propertyID=" + propertyID +
                ", createdDate='" + createdDate + '\'' +
                ", property=" + property +
                ", bid=" + bid +
                '}';
    }
}

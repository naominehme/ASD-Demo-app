package com.uts.asd.entity;

/*
 * @author Harold Seefeld
 */

import java.util.Date;

public class WatchlistPropertyItem {

    // Declare private variables
    private String CustomerID;
    private String PropertyID;
    private Date CreatedDate;

    public WatchlistPropertyItem(String customerID, String propertyID) {
        CustomerID = customerID;
        PropertyID = propertyID;
    }

    public WatchlistPropertyItem(String customerID, String propertyID, Date createdDate) {
        CustomerID = customerID;
        PropertyID = propertyID;
        CreatedDate = createdDate;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public String getPropertyID() {
        return PropertyID;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }
}

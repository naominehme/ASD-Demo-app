package com.uts.asd.entity;

import java.util.concurrent.CompletableFuture;

public class NotificationIdentifier {

    private String customerID;
    private CompletableFuture<Notification> notificationCompletableFuture;

    public NotificationIdentifier(String customerID, CompletableFuture<Notification> notificationCompletableFuture) {
        this.customerID = customerID;
        this.notificationCompletableFuture = notificationCompletableFuture;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public CompletableFuture<Notification> getNotificationCompletableFuture() {
        return notificationCompletableFuture;
    }

    public void setNotificationCompletableFuture(CompletableFuture<Notification> notificationCompletableFuture) {
        this.notificationCompletableFuture = notificationCompletableFuture;
    }

    @Override
    public String toString() {
        return "NotificationIdentifier{" +
                "customerID='" + customerID + '\'' +
                ", notificationCompletableFuture=" + notificationCompletableFuture +
                '}';
    }
}

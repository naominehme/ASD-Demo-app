package com.uts.asd.repository;

import com.google.firebase.database.*;
import com.uts.asd.entity.Notification;
import com.uts.asd.entity.NotificationPreference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/*
 * @author Harold Seefeld
 */

@Repository
public class NotificationRepository {

    Logger logger = LoggerFactory.getLogger(NotificationRepository.class);

    public ArrayList<Notification> getNotificationItems(String customerID) {
        CompletableFuture<ArrayList<Notification>> completableFuture = new CompletableFuture<>();
        // Get data from NoSQL
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Notification/" + customerID);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                logger.info("Data retrieved: " + dataSnapshot.getValue());
                ArrayList<Notification> notificationArrayList = new ArrayList<>();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    // Create new WatchlistPropertyItem object
                    Notification notification = new Notification(
                            childSnapshot.child("notificationID").getValue(String.class),
                            childSnapshot.child("customerID").getValue(String.class),
                            childSnapshot.child("propertyID").getValue(long.class).intValue(),
                            childSnapshot.child("bidID").getValue(long.class).intValue(),
                            childSnapshot.child("createdDate").getValue(String.class));
                    notificationArrayList.add(notification);
                    logger.info(notification.toString());
                }
                // Complete the Async request
                completableFuture.complete(notificationArrayList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                logger.error(error.toString());
            }
        });

        ArrayList<Notification> notifications = new ArrayList<>();
        // Wait for request to complete
        try {
            notifications = completableFuture.get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return notifications;
    }

    public NotificationPreference getNotificationPreferences(String customerID) {
        CompletableFuture<NotificationPreference> completableFuture = new CompletableFuture<>();
        // Get data from NoSQL
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("NotificationPreference/" + customerID);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                logger.info("Data retrieved: " + dataSnapshot.getValue());
                // Create new WatchlistPropertyItem object
                NotificationPreference notificationPreference = new NotificationPreference(
                        dataSnapshot.child("customerID").getValue(String.class),
                        dataSnapshot.child("notificationsEnabled").getValue(Boolean.class),
                        dataSnapshot.child("soundEnabled").getValue(Boolean.class));
                // Complete the Async request
                completableFuture.complete(notificationPreference);
                logger.info(notificationPreference.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                logger.error(error.toString());
            }
        });

        NotificationPreference notificationPreference = new NotificationPreference();
        // Wait for request to complete
        try {
            notificationPreference = completableFuture.get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return notificationPreference;
    }

    public String addNotificationToNotifications(Notification notification) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("Notification/" + notification.getCustomerID() +
                        "/" + notification.getNotificationID());

        CompletableFuture<String> completableFuture = new CompletableFuture();
        ref.setValue(notification, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                logger.error("Data could not be saved " + databaseError.getMessage());
                completableFuture.complete(databaseError.getMessage());
            } else {
                logger.info("Data saved successfully.");
                completableFuture.complete("Added '" + notification.getNotificationID() + "' notification successfully.");
            }
        });

        //

        return getStringFromCompletableFuture(completableFuture);
    }

    public String setNotificationPreferences(NotificationPreference notificationPreference) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("NotificationPreference/" + notificationPreference.getCustomerID());

        CompletableFuture<String> completableFuture = new CompletableFuture();
        ref.setValue(notificationPreference, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                logger.error("Data could not be saved " + databaseError.getMessage());
                completableFuture.complete(databaseError.getMessage());
            } else {
                logger.info("Data saved successfully.");
                completableFuture.complete("Set notfications for " + notificationPreference.getCustomerID() + " successfully.");
            }
        });

        //

        return getStringFromCompletableFuture(completableFuture);
    }

    public String removeNotificationFromNotifications(Notification notification) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("Notification/" + notification.getCustomerID() +
                        "/" + notification.getNotificationID());

        CompletableFuture<String> completableFuture = new CompletableFuture();
        ref.setValue(null, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                logger.error("Data could not be saved " + databaseError.getMessage());
                completableFuture.complete(databaseError.getMessage());
            } else {
                logger.info("Data saved successfully.");
                completableFuture.complete("Removed '" + notification.getNotificationID() + "' notification successfully.");
            }
        });

        return getStringFromCompletableFuture(completableFuture);
    }

    private String getStringFromCompletableFuture(CompletableFuture<String> completableFuture) {
        String result = "";
        // Wait for request to complete
        try {
            result = completableFuture.get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return result;
    }
}


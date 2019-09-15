package com.uts.asd.repository;

import com.google.firebase.database.*;
import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;
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

    public String addNotificationToNotifications(String notification, int customerID) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("Notification/" +
                        customerID + "/");

        CompletableFuture<String> completableFuture = new CompletableFuture();
        ref.setValue(notification, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                logger.error("Data could not be saved " + databaseError.getMessage());
                completableFuture.complete(databaseError.getMessage());
            } else {
                logger.info("Data saved successfully.");
                completableFuture.complete("Added '" + customerID + "' notification successfully.");
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


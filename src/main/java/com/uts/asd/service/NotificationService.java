package com.uts.asd.service;

import com.uts.asd.entity.Notification;
import com.uts.asd.entity.NotificationPreference;
import com.uts.asd.mapper.BidRepository;
import com.uts.asd.mapper.PropertyRepository;
import com.uts.asd.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/*
 * @author Harold Seefeld
 */

@Service
public class NotificationService {

    Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    BidRepository bidRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Async
    public CompletableFuture<ArrayList<Notification>> getNotificationItems(String customerID) {
        logger.info("Attempting to get notification items for customerID {}", customerID);
        ArrayList<Notification> notificationItems = notificationRepository.getNotificationItems(customerID);
        // Retrieve bid information for each notification item
        for (Notification notification : notificationItems) {
            try {
                getNotificationDetails(notification);
            }
            catch (Exception e) {
                logger.error(e.toString());
            }
        }
        return CompletableFuture.completedFuture(notificationItems);
    }

    @Async
    public CompletableFuture<NotificationPreference> getNotificationPreferences(String customerID) {
        NotificationPreference notificationPreference = notificationRepository.getNotificationPreferences(customerID);
        return CompletableFuture.completedFuture(notificationPreference);
    }

    public Notification getNotificationDetails(Notification notification) {
        // Retrieve bid information for the notification item
        try {
            notification.setBid(bidRepository.searchById(notification.getBidID()));
            // Set the property ID if the bid type is a bid
            notification.setPropertyID(notification.getBid().getPid());
            notification.setProperty(propertyRepository.searchById(notification.getPropertyID()));
        }
        catch (Exception e) {
            logger.error(e.toString());
        }
        return notification;
    }

    @Async
    public CompletableFuture<String> runAsyncRemoveNotification(Notification notification) {
        String result = notificationRepository.removeNotificationFromNotifications(notification);
        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<String> runAsyncSetNotificationPreferences(NotificationPreference notificationPreference) {
        String result = notificationRepository.setNotificationPreferences(notificationPreference);
        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<String> runAsyncAddNotification(Notification notification) {
        String result = notificationRepository.addNotificationToNotifications(notification);
        return CompletableFuture.completedFuture(result);
    }

}

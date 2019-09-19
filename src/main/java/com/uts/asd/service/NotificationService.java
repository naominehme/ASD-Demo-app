package com.uts.asd.service;

import com.uts.asd.entity.Notification;
import com.uts.asd.entity.WatchlistPropertyItem;
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
    public CompletableFuture<ArrayList<Notification>> getNotificationItems(int customerID) {
        logger.info("Attempting to get notification items for customerID {}", customerID);
        ArrayList<Notification> notificationItems = notificationRepository.getNotificationItems(customerID);
        // Retrieve property information for each notification item
        for (Notification notification : notificationItems) {
            try {
                notification.setProperty(propertyRepository.searchById(notification.getPropertyID()));
            }
            catch (Exception e) {
                logger.error(e.toString());
            }
        }
        // Retrieve bid information for each notification item
        for (Notification notification : notificationItems) {
            try {
                notification.setBid(bidRepository.searchById(notification.getPropertyID()));
            }
            catch (Exception e) {
                logger.error(e.toString());
            }
        }
        return CompletableFuture.completedFuture(notificationItems);
    }

}

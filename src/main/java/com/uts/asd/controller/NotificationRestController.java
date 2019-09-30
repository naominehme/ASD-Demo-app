package com.uts.asd.controller;

import com.google.cloud.Timestamp;
import com.uts.asd.entity.*;
import com.uts.asd.repository.NotificationRepository;
import com.uts.asd.service.NotificationService;
import com.uts.asd.service.WatchlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class NotificationRestController {

    Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationService notificationService;

    @Autowired
    WatchlistService watchlistService;

    private String DEFAULT_CUSTOMER_ID = "-1";

    private HashMap<String, CompletableFuture<Notification>> completableFutureHashMap = new HashMap<>();

    public void setDefaultCustomerID(String id) {
        DEFAULT_CUSTOMER_ID = id;
    }

    @RequestMapping("/notification/remove/{notificationID}")
    public void removePropertyFromWatchlist(HttpServletRequest request, HttpServletResponse response, @PathVariable("notificationID") String notificationID) throws IOException {
        String customerID = getCustomerIDFromRequest(request);
        Notification notification = new Notification(customerID, notificationID);
        logger.info("Attempting to remove notification {}", notificationID);
        // Launch async lookup
        CompletableFuture<String> result = notificationService.runAsyncRemoveNotification(notification);
        // Wait until done
        CompletableFuture.allOf(result).join();
        response.sendRedirect("/notifications");
    }

    @MessageMapping("/notificationListener")
    @SendToUser("/topic/notifications")
    public Notification listenForNotification(SimpMessageHeaderAccessor headerAccessor) throws Exception {
        CompletableFuture<Notification> completableFuture = new CompletableFuture<>();
        completableFutureHashMap.put(getCustomerIDFromRequest(headerAccessor), completableFuture);
        return completableFuture.get();
    }

    public void createNotifications(HttpServletRequest request, Bid bid) throws ExecutionException, InterruptedException {
        // Create a notification for each customer listening to the property on the watchlist
        ArrayList<WatchlistPropertyItem> watchlistPropertyItems = watchlistService.getWatchlistPropertyItemsByProperty(bid.getPid()).get();
        for (WatchlistPropertyItem watchlistPropertyItem : watchlistPropertyItems) {
            Notification notification = new Notification();
            // Set client-provided variables
            notification.setPropertyID(bid.getPid());
            notification.setBidID(bid.getId());
            // Set server-controlled variables
            notification.setCreatedDate(Timestamp.now().toString());
            notification.assignNotificationID();
            notification.setCustomerID(watchlistPropertyItem.getCustomerID());
            logger.debug("Attempting to add bid notification to notifications: {}", notification);
            // Launch async add which will attempt to send out websocket notifications once completed
            notificationService.runAsyncAddNotification(notification).thenRunAsync(() -> {
                CompletableFuture<Notification> completableFuture = completableFutureHashMap.getOrDefault(notification.getCustomerID(), null);
                if (completableFuture == null) return;
                Notification finalNotification = notificationService.getNotificationDetails(notification);
                // Set Watchlist Item to get first image URL only
                Property property = notification.getProperty();
                if (property == null) return;
                property.setUrl(property.getUrl().split(";")[0]);
                // Complete the future
                completableFuture.complete(finalNotification);
            });
        }
        // If the completable future is completed, remove the entry for that Completable future
        completableFutureHashMap.entrySet().removeIf(entry -> entry.getValue().isDone() || entry.getValue().isCancelled() || entry.getValue().isCompletedExceptionally());
    }

    private String getCustomerIDFromRequest(HttpServletRequest request) {
        String customerIDString = (String)request.getSession().getAttribute("customerID");
        if (customerIDString == null) {
            customerIDString = DEFAULT_CUSTOMER_ID;
        }
        return customerIDString;
    }

    private String getCustomerIDFromRequest(SimpMessageHeaderAccessor simpMessageHeaderAccessor) {
        String customerIDString = (String)simpMessageHeaderAccessor.getSessionAttributes().get("customerID");
        if (customerIDString == null) {
            customerIDString = DEFAULT_CUSTOMER_ID;
        }
        return customerIDString;
    }
}

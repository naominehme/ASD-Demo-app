package com.uts.asd.controller;

import com.google.cloud.Timestamp;
import com.uts.asd.entity.*;
import com.uts.asd.repository.NotificationRepository;
import com.uts.asd.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
public class NotificationController {

    Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationService notificationService;

    private int DEFAULT_CUSTOMER_ID = -1;

    public void setDefaultCustomerID(int id) {
        DEFAULT_CUSTOMER_ID = id;
    }

    private void loadNotificationData(Model model, int customerID) throws ExecutionException, InterruptedException {
        // Launch async lookups
        CompletableFuture<ArrayList<Notification>> notificationItems = notificationService.getNotificationItems(customerID);

        // Set Watchlist Item to get first image URL only
        for (Notification notification : notificationItems.get()) {
            Property property = notification.getProperty();
            if (property == null) { continue; }
            property.setUrl(property.getUrl().split(";")[0]);
        }

        // Add to model
        model.addAttribute("notificationItems", notificationItems.get());
        model.addAttribute("defaultCustomer", customerID == DEFAULT_CUSTOMER_ID ? true : false);
    }

    @GetMapping("/notifications")
    public String getNotifications(Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        int customerID = getCustomerIDFromRequest(request);

        loadNotificationData(model, customerID);
        return "Notification";
    }

    @RequestMapping("/watchlist/remove/notification/{notificationID}")
    public void removePropertyFromWatchlist(HttpServletRequest request, HttpServletResponse response, @PathVariable("notificationID") String notificationID) throws IOException {
        int customerID = getCustomerIDFromRequest(request);
        Notification notification = new Notification(customerID, notificationID);
        logger.info("Attempting to remove notification {}", notificationID);
        // Launch async lookup
        CompletableFuture<String> result = notificationService.runAsyncRemoveNotification(notification);
        // Wait until done
        CompletableFuture.allOf(result).join();
        response.sendRedirect("/watchlist");
    }

    public void createNotification(HttpServletRequest request, Bid bid) {
        Notification notification = new Notification();
        // Set client-provided variables
        notification.setPropertyID(bid.getPid());
        notification.setBidID(bid.getId());
        // Set server-controlled variables
        notification.setCustomerID(getCustomerIDFromRequest(request));
        notification.setCreatedDate(Timestamp.now().toString());
        notification.assignNotificationID();

        logger.info("Attempting to add bid notification to notifications: {}", notification);
        // Launch async add
        notificationService.runAsyncAddNotification(notification);
    }

    private int getCustomerIDFromRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int customerID;
        String customerIDString = (String)request.getSession().getAttribute("customerID");
        if (customerIDString == null) {
            customerID = DEFAULT_CUSTOMER_ID;
        } else {
            customerID = Integer.parseInt(customerIDString);
        }
        return customerID;
    }
}

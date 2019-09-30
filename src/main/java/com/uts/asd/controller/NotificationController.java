package com.uts.asd.controller;

import com.uts.asd.entity.*;
import com.uts.asd.repository.NotificationRepository;
import com.uts.asd.service.NotificationService;
import com.uts.asd.service.WatchlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    WatchlistService watchlistService;

    private String DEFAULT_CUSTOMER_ID = "-1";

    public void setDefaultCustomerID(String id) {
        DEFAULT_CUSTOMER_ID = id;
    }

    private void loadNotificationData(Model model, String customerID) throws ExecutionException, InterruptedException {
        // Launch async lookups
        CompletableFuture<ArrayList<Notification>> notificationItems = notificationService.getNotificationItems(customerID);
        CompletableFuture<NotificationPreference> notificationPreference = notificationService.getNotificationPreferences(customerID);

        // Set Watchlist Item to get first image URL only
        for (Notification notification : notificationItems.get()) {
            Property property = notification.getProperty();
            if (property == null) { continue; }
            property.setUrl(property.getUrl().split(";")[0]);
        }

        // Add to model
        model.addAttribute("notificationItems", notificationItems.get());
        model.addAttribute("notificationPreference", notificationPreference.get());
        model.addAttribute("defaultCustomer", customerID == DEFAULT_CUSTOMER_ID ? true : false);
    }

    @PostMapping("/notifications/set/preferences")
    public String setNotificationPreferences(NotificationPreference notificationPreference, HttpServletRequest request, HttpServletResponse response) throws IOException, ExecutionException, InterruptedException {
        // Set server-controlled variables
        notificationPreference.setCustomerID(getCustomerIDFromRequest(request));
        logger.info("Attempting to set notification preferences: {}", notificationPreference);
        // Launch async set
        CompletableFuture<String> result = notificationService.runAsyncSetNotificationPreferences(notificationPreference);
        // Wait until done
        CompletableFuture.allOf(result).join();

        response.sendRedirect("/notifications");
        return null;
    }

    @GetMapping("/notifications")
    public String getNotifications(Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        String customerID = getCustomerIDFromRequest(request);
        // Load notification data for the specified user
        loadNotificationData(model, customerID);
        // Render notification page after data loaded into model
        return "Notification";
    }

    private String getCustomerIDFromRequest(HttpServletRequest request) {
        String customerIDString = (String)request.getSession().getAttribute("customerID");
        if (customerIDString == null) {
            customerIDString = DEFAULT_CUSTOMER_ID;
        }
        return customerIDString;
    }
}

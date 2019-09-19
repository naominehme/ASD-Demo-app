package com.uts.asd.controller;

import com.uts.asd.entity.Notification;
import com.uts.asd.entity.Property;
import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;
import com.uts.asd.repository.NotificationRepository;
import com.uts.asd.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    private void loadNotificationData(Model model, int customerID, WatchlistPropertyItem watchlistPropertyItem, WatchlistPropertyPreference watchlistPropertyPreference) throws ExecutionException, InterruptedException {
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

        loadNotificationData(model, customerID, new WatchlistPropertyItem(), new WatchlistPropertyPreference());
        return "Notification";
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

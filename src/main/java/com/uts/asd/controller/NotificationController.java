package com.uts.asd.controller;

import com.google.cloud.Timestamp;
import com.uts.asd.entity.*;
import com.uts.asd.repository.NotificationRepository;
import com.uts.asd.service.NotificationService;
import com.uts.asd.service.WatchlistService;
import org.eclipse.jetty.server.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
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
        String customerID = getCustomerIDFromRequest(request);

        loadNotificationData(model, customerID);
        return "Notification";
    }

    private String getCustomerIDFromRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String customerIDString = (String)request.getSession().getAttribute("customerID");
        if (customerIDString == null) {
            customerIDString = DEFAULT_CUSTOMER_ID;
        }
        return customerIDString;
    }
}

package com.uts.asd.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;
import com.uts.asd.service.WatchlistService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WatchlistController {

    Logger logger = LoggerFactory.getLogger(WatchlistController.class);

    @Autowired
    private WatchlistService watchlistService;

    @RequestMapping("/addPropertyToWatchlist")
    public void addPropertyToWatchlist(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String customerID = getCustomerIDFromRequest(request);
        String propertyID = request.getParameter("propertyID");
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(customerID, propertyID);
        logger.info("Attempting to add property to watchlist with propertyID {} and customerID {}", propertyID, customerID);
        try {
            if (propertyID != null) {
                watchlistService.addPropertyToWatchlist(watchlistPropertyItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/removePropertyFromWatchlist")
    public void removePropertyFromWatchlist(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String customerID = getCustomerIDFromRequest(request);
        String propertyID = request.getParameter("propertyID");
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(customerID, propertyID);
        logger.info("Attempting to remove property from watchlist with propertyID {} and customerID {}", propertyID, customerID);
        try {
            if (propertyID != null) {
                watchlistService.removePropertyFromWatchlist(watchlistPropertyItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/addPropertyPreferenceToWatchlist")
    public void addPropertyPreferenceToWatchlist(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String customerID = getCustomerIDFromRequest(request);
        String propertyID = request.getParameter("propertyID");
        WatchlistPropertyPreference watchlistPropertyPreference = new WatchlistPropertyPreference();
        try {
            if (propertyID != null) {
                watchlistService.addPropertyPreferencesToWatchlist(watchlistPropertyPreference);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/removePropertyPreferencesFromWatchlist")
    public void removePropertyPreferencesFromWatchlist(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String customerID = getCustomerIDFromRequest(request);
        String propertyID = request.getParameter("propertyID");
        WatchlistPropertyPreference watchlistPropertyPreference = new WatchlistPropertyPreference();
        try {
            if (propertyID != null) {
                watchlistService.removePropertyPreferencesFromWatchlist(watchlistPropertyPreference);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCustomerIDFromRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String customerID = request.getParameter("customerID");
        if (customerID == null) {
            customerID = (String) session.getAttribute("customerID");
        }
        return customerID;
    }
}

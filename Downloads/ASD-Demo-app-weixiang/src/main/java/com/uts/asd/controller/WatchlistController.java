package com.uts.asd.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;
import com.uts.asd.repository.WatchlistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class WatchlistController {

    Logger logger = LoggerFactory.getLogger(WatchlistController.class);

    @Autowired
    private WatchlistRepository watchlistRepository;

    @RequestMapping("/addPropertyToWatchlist")
    public void addPropertyToWatchlist(HttpServletRequest request,HttpServletResponse response) {
        String customerID = getCustomerIDFromRequest(request);
        String propertyID = request.getParameter("propertyID");
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(customerID, propertyID);
        logger.info("Attempting to add property to watchlist with propertyID {} and customerID {}", propertyID, customerID);
        watchlistRepository.addPropertyToWatchlist(watchlistPropertyItem);
    }

    @RequestMapping("/removePropertyFromWatchlist")
    public void removePropertyFromWatchlist(HttpServletRequest request,HttpServletResponse response) {
        String customerID = getCustomerIDFromRequest(request);
        String propertyID = request.getParameter("propertyID");
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(customerID, propertyID);
        logger.info("Attempting to remove property from watchlist with propertyID {} and customerID {}", propertyID, customerID);
        watchlistRepository.removePropertyFromWatchlist(watchlistPropertyItem);
    }

    @RequestMapping("/addPropertyPreferenceToWatchlist")
    public void addPropertyPreferenceToWatchlist(HttpServletRequest request,HttpServletResponse response) {
        String customerID = getCustomerIDFromRequest(request);
        String typeID = request.getParameter("typeID");
        int garageSpaces = Integer.parseInt(request.getParameter("garageSpaces"));
        int numOfBathrooms = Integer.parseInt(request.getParameter("numOfBathrooms"));
        int numOfBedrooms = Integer.parseInt(request.getParameter("numOfBedrooms"));
        int postCode = Integer.parseInt(request.getParameter("postCode"));
        WatchlistPropertyPreference watchlistPropertyPreference = new WatchlistPropertyPreference(customerID, typeID, garageSpaces, numOfBathrooms, numOfBedrooms, postCode);
        logger.info("Attempting to add property preferences to watchlist: {}", watchlistPropertyPreference.toString());
        watchlistRepository.addPropertyPreferencesToWatchlist(watchlistPropertyPreference);
    }

    @RequestMapping("/removePropertyPreferencesFromWatchlist")
    public void removePropertyPreferencesFromWatchlist(HttpServletRequest request,HttpServletResponse response) {
        String customerID = getCustomerIDFromRequest(request);
        String preferenceID = request.getParameter("preferenceID");
        WatchlistPropertyPreference watchlistPropertyPreference = new WatchlistPropertyPreference(customerID, preferenceID);
        watchlistRepository.removePropertyPreferencesFromWatchlist(watchlistPropertyPreference);
    }

    @RequestMapping("/getWatchlistPropertyItems")
    public DeferredResult<ArrayList<WatchlistPropertyItem>> getWatchlistPropertyItems(HttpServletRequest request, HttpServletResponse response) {
        String customerID = getCustomerIDFromRequest(request);
        logger.info("Attempting to get property items from watchlist for customerID {}", customerID);
        DeferredResult result = new DeferredResult();
        watchlistRepository.getWatchlistPropertyItems(customerID, result);
        return result;
    }

    @RequestMapping("/getWatchlistPropertyPreferences")
    public DeferredResult<ArrayList<WatchlistPropertyPreference>> getWatchlistPropertyPreferences(HttpServletRequest request,HttpServletResponse response) {
        String customerID = getCustomerIDFromRequest(request);
        logger.info("Attempting to get property preferences from watchlist for customerID {}", customerID);
        DeferredResult result = new DeferredResult();
        watchlistRepository.getWatchlistPropertyPreferences(customerID, result);
        return result;
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

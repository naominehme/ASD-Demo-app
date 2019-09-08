package com.uts.asd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;
import com.uts.asd.repository.WatchlistRepository;
import com.uts.asd.service.WatchlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
public class WatchlistController {

    Logger logger = LoggerFactory.getLogger(WatchlistController.class);

    @Autowired
    WatchlistRepository watchlistRepository;

    @Autowired
    WatchlistService watchlistService;

    private final String DEFAULT_CUSTOMER_ID="C-1";

    @GetMapping("/watchlist")
    public String getWatchlist(Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        String customerID = getCustomerIDFromRequest(request);
        // Launch async lookups
        CompletableFuture<ArrayList<WatchlistPropertyItem>> watchlistPropertyItems = watchlistService.getWatchlistPropertyItems(customerID);
        CompletableFuture<ArrayList<WatchlistPropertyPreference>> watchlistPropertyPreferences = watchlistService.getWatchlistPropertyPreferences(customerID);

        // Wait until all are done
        CompletableFuture.allOf(watchlistPropertyItems, watchlistPropertyPreferences).join();

        // Add to model
        model.addAttribute("WatchlistPropertyItems", watchlistPropertyItems.get());
        model.addAttribute("WatchlistPropertyPreferences", watchlistPropertyPreferences.get());

        return "Watchlist";
    }

    @RequestMapping("/addPropertyToWatchlist")
    public DeferredResult<String> addPropertyToWatchlist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DeferredResult deferredResult = new DeferredResult();
        WatchlistPropertyItem watchlistPropertyItem = null;
        String customerID = getCustomerIDFromRequest(request);
        String propertyID = request.getParameter("propertyID");

        if (customerID == null || propertyID == null || customerID.trim().isEmpty() || propertyID.trim().isEmpty()) {
            deferredResult.setResult("Fields cannot be null or blank.");
            return deferredResult;
        }

        try {
            // TODO: Can do further validation in constructor if necessary
            watchlistPropertyItem = new WatchlistPropertyItem(customerID, propertyID);
        } catch (Exception e) {
            deferredResult.setResult(e.getMessage());
            return deferredResult;
        }

        logger.info("Attempting to add property to watchlist with propertyID {} and customerID {}", propertyID, customerID);
        watchlistRepository.addPropertyToWatchlist(watchlistPropertyItem, deferredResult);
        return deferredResult;
    }

    @RequestMapping("/watchlist/remove/property/{propertyID}")
    public void removePropertyFromWatchlist(HttpServletRequest request, HttpServletResponse response, @PathVariable("propertyID") String propertyID) throws IOException {
        String customerID = getCustomerIDFromRequest(request);
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(customerID, propertyID);
        logger.info("Attempting to remove property from watchlist with propertyID {} and customerID {}", propertyID, customerID);
        watchlistRepository.removePropertyFromWatchlist(watchlistPropertyItem);
        response.sendRedirect("/watchlist");
    }

    @RequestMapping("/addPropertyPreferenceToWatchlist")
    public DeferredResult<String> addPropertyPreferenceToWatchlist(HttpServletRequest request,HttpServletResponse response) {
        DeferredResult deferredResult = new DeferredResult();
        WatchlistPropertyPreference watchlistPropertyPreference = null;

        try {
            String customerID = getCustomerIDFromRequest(request);
            String typeID = request.getParameter("typeID");
            int garageSpaces = Integer.parseInt(request.getParameter("garageSpaces"));
            int numOfBathrooms = Integer.parseInt(request.getParameter("numOfBathrooms"));
            int numOfBedrooms = Integer.parseInt(request.getParameter("numOfBedrooms"));
            int postCode = Integer.parseInt(request.getParameter("postCode"));

            watchlistPropertyPreference = new WatchlistPropertyPreference(customerID, typeID, garageSpaces, numOfBathrooms, numOfBedrooms, postCode);
        } catch (Exception e) {
            deferredResult.setResult(e.getMessage());
            return deferredResult;
        }

        logger.info("Attempting to add property preferences to watchlist: {}", watchlistPropertyPreference.toString());
        watchlistRepository.addPropertyPreferencesToWatchlist(watchlistPropertyPreference, deferredResult);
        return deferredResult;
    }

    @RequestMapping("/watchlist/remove/preference/{preferenceID}")
    public void removePropertyPreferencesFromWatchlist(HttpServletRequest request, HttpServletResponse response, @PathVariable("preferenceID") String preferenceID) throws IOException {
        String customerID = getCustomerIDFromRequest(request);
        WatchlistPropertyPreference watchlistPropertyPreference = new WatchlistPropertyPreference(customerID, preferenceID);
        watchlistRepository.removePropertyPreferencesFromWatchlist(watchlistPropertyPreference);
        response.sendRedirect("/watchlist");
    }

    private String getCustomerIDFromRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String customerID = request.getParameter("customerID");
        if (customerID == null) {
            customerID = DEFAULT_CUSTOMER_ID;
        }
        return customerID;
    }
}

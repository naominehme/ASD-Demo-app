package com.uts.asd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.cloud.Timestamp;
import com.uts.asd.entity.Property;
import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;
import com.uts.asd.repository.WatchlistRepository;
import com.uts.asd.service.WatchlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WatchlistController {

    Logger logger = LoggerFactory.getLogger(WatchlistController.class);

    @Autowired
    WatchlistRepository watchlistRepository;

    @Autowired
    WatchlistService watchlistService;

    private int DEFAULT_CUSTOMER_ID = -1;

    public void setDefaultCustomerID(int id) {
        DEFAULT_CUSTOMER_ID = id;
    }

    private void loadWatchlistData(Model model, int customerID, WatchlistPropertyItem watchlistPropertyItem, WatchlistPropertyPreference watchlistPropertyPreference) throws ExecutionException, InterruptedException {
        // Launch async lookups
        CompletableFuture<ArrayList<WatchlistPropertyItem>> watchlistPropertyItems = watchlistService.getWatchlistPropertyItems(customerID);
        CompletableFuture<ArrayList<WatchlistPropertyPreference>> watchlistPropertyPreferences = watchlistService.getWatchlistPropertyPreferences(customerID);

        // Set Watchlist Item to get first image URL only
        for (WatchlistPropertyItem propertyItem : watchlistPropertyItems.get()) {
            Property property = propertyItem.getProperty();
            if (property == null) { continue; }
            property.setUrl(property.getUrl().split(";")[0]);
        }

        // Add to model
        model.addAttribute("watchlistPropertyItems", watchlistPropertyItems.get());
        model.addAttribute("watchlistPropertyPreferences", watchlistPropertyPreferences.get());
        model.addAttribute("watchlistPropertyItem", watchlistPropertyItem);
        model.addAttribute("watchlistPropertyPreference", watchlistPropertyPreference);
        model.addAttribute("defaultCustomer", customerID == DEFAULT_CUSTOMER_ID ? true : false);
    }

    @GetMapping("/watchlist")
    public String getWatchlist(Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        int customerID = getCustomerIDFromRequest(request);

        loadWatchlistData(model, customerID, new WatchlistPropertyItem(), new WatchlistPropertyPreference());
        return "Watchlist";
    }

    @PostMapping("/watchlist/add/property")
    public String addPropertyToWatchlist(Model model, @Validated WatchlistPropertyItem watchlistPropertyItem, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws IOException, ExecutionException, InterruptedException {
        if (bindingResult.hasErrors()) {
            loadWatchlistData(model, getCustomerIDFromRequest(request), watchlistPropertyItem, new WatchlistPropertyPreference());
            return "Watchlist";
        }

        // Set server-controlled variables
        watchlistPropertyItem.setCustomerID(getCustomerIDFromRequest(request));
        watchlistPropertyItem.setCreatedDate(Timestamp.now().toString());

        logger.info("Attempting to add property to watchlist: {}", watchlistPropertyItem);
        // Launch async lookup
        CompletableFuture<String> result = watchlistService.runAsyncAddProperty(watchlistPropertyItem);
        // Wait until done
        CompletableFuture.allOf(result).join();

        response.sendRedirect("/watchlist");
        return null;
    }

    @GetMapping("/watchlist/add/property/id")
    public void addPropertyToWatchlist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem();
        int propertyID = -1;
        try {
            propertyID = Integer.parseInt(request.getParameter("propertyID"));
        } catch (Exception e) {
            logger.error(e.toString());
            return;
        }
        watchlistPropertyItem.setPropertyID(propertyID);
        // Set server-controlled variables
        watchlistPropertyItem.setCustomerID(getCustomerIDFromRequest(request));
        watchlistPropertyItem.setCreatedDate(Timestamp.now().toString());

        logger.info("Attempting to add property to watchlist: {}", watchlistPropertyItem);
        // Launch async lookup
        CompletableFuture<String> result = watchlistService.runAsyncAddProperty(watchlistPropertyItem);
        // Wait until done
        CompletableFuture.allOf(result).join();

        response.sendRedirect("/watchlist");
    }

    @RequestMapping("/watchlist/remove/property/{propertyID}")
    public void removePropertyFromWatchlist(HttpServletRequest request, HttpServletResponse response, @PathVariable("propertyID") int propertyID) throws IOException {
        int customerID = getCustomerIDFromRequest(request);
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(customerID, propertyID);
        logger.info("Attempting to remove property from watchlist with propertyID {} and customerID {}", propertyID, customerID);
        // Launch async lookup
        CompletableFuture<String> result = watchlistService.runAsyncRemoveProperty(watchlistPropertyItem);
        // Wait until done
        CompletableFuture.allOf(result).join();
        response.sendRedirect("/watchlist");
    }

    @PostMapping("/watchlist/add/preference")
    public String addPropertyPreferenceToWatchlist(Model model, @Validated WatchlistPropertyPreference watchlistPropertyPreference, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws IOException, ExecutionException, InterruptedException {
        if (bindingResult.hasErrors()) {
            loadWatchlistData(model, getCustomerIDFromRequest(request), new WatchlistPropertyItem(), watchlistPropertyPreference);
            return "Watchlist";
        }

        // Set server-controlled variables
        watchlistPropertyPreference.setCustomerID(getCustomerIDFromRequest(request));
        watchlistPropertyPreference.assignPreferenceID();

        logger.info("Attempting to add property to watchlist: {}", watchlistPropertyPreference);
        // Launch async lookup
        CompletableFuture<String> result = watchlistService.runAsyncAddPreference(watchlistPropertyPreference);
        // Wait until done
        CompletableFuture.allOf(result).join();

        response.sendRedirect("/watchlist");
        return null;
    }

    @RequestMapping("/watchlist/remove/preference/{preferenceID}")
    public void removePropertyPreferencesFromWatchlist(HttpServletRequest request, HttpServletResponse response, @PathVariable("preferenceID") String preferenceID) throws IOException {
        int customerID = getCustomerIDFromRequest(request);
        WatchlistPropertyPreference watchlistPropertyPreference = new WatchlistPropertyPreference(customerID, preferenceID);
        // Launch async lookup
        CompletableFuture<String> result = watchlistService.runAsyncRemovePreference(watchlistPropertyPreference);
        // Wait until done
        CompletableFuture.allOf(result).join();
        response.sendRedirect("/watchlist");
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

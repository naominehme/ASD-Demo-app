package com.uts.asd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.google.cloud.Timestamp;
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
import org.springframework.web.context.request.async.DeferredResult;

@Controller
public class WatchlistController {

    Logger logger = LoggerFactory.getLogger(WatchlistController.class);

    @Autowired
    WatchlistRepository watchlistRepository;

    @Autowired
    WatchlistService watchlistService;

    private final String DEFAULT_CUSTOMER_ID="C-1";

    private void loadWatchlistData(Model model, String customerID, WatchlistPropertyItem watchlistPropertyItem, WatchlistPropertyPreference watchlistPropertyPreference) throws ExecutionException, InterruptedException {
        // Launch async lookups
        CompletableFuture<ArrayList<WatchlistPropertyItem>> watchlistPropertyItems = watchlistService.getWatchlistPropertyItems(customerID);
        CompletableFuture<ArrayList<WatchlistPropertyPreference>> watchlistPropertyPreferences = watchlistService.getWatchlistPropertyPreferences(customerID);

        // Wait until all are done
        CompletableFuture.allOf(watchlistPropertyItems, watchlistPropertyPreferences).join();

        // Add to model
        model.addAttribute("watchlistPropertyItems", watchlistPropertyItems.get());
        model.addAttribute("watchlistPropertyPreferences", watchlistPropertyPreferences.get());
        model.addAttribute("watchlistPropertyItem", watchlistPropertyItem);
        model.addAttribute("watchlistPropertyPreference", watchlistPropertyPreference);
        model.addAttribute("defaultCustomer", customerID == DEFAULT_CUSTOMER_ID ? true : false);
    }

    @GetMapping("/watchlist")
    public String getWatchlist(Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        String customerID = getCustomerIDFromRequest(request);

        loadWatchlistData(model, customerID, new WatchlistPropertyItem(), new WatchlistPropertyPreference());
        return "Watchlist";
    }

    @PostMapping("/watchlist/add/property")
    public String addPropertyToWatchlist(Model model, @Validated WatchlistPropertyItem watchlistPropertyItem, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws IOException, ExecutionException, InterruptedException {
        if (bindingResult.hasErrors()) {
            loadWatchlistData(model, getCustomerIDFromRequest(request), watchlistPropertyItem, new WatchlistPropertyPreference());
            return "Watchlist";
        }

        DeferredResult deferredResult = new DeferredResult();
        // Set server-controlled variables
        watchlistPropertyItem.setCustomerID(getCustomerIDFromRequest(request));
        watchlistPropertyItem.setCreatedDate(Timestamp.now().toString());

        logger.info("Attempting to add property to watchlist: {}", watchlistPropertyItem);
        watchlistRepository.addPropertyToWatchlist(watchlistPropertyItem, deferredResult);

        response.sendRedirect("/watchlist");
        return null;
    }

    @RequestMapping("/watchlist/remove/property/{propertyID}")
    public void removePropertyFromWatchlist(HttpServletRequest request, HttpServletResponse response, @PathVariable("propertyID") String propertyID) throws IOException {
        String customerID = getCustomerIDFromRequest(request);
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(customerID, propertyID);
        logger.info("Attempting to remove property from watchlist with propertyID {} and customerID {}", propertyID, customerID);
        watchlistRepository.removePropertyFromWatchlist(watchlistPropertyItem);
        response.sendRedirect("/watchlist");
    }

    @PostMapping("/watchlist/add/preference")
    public String addPropertyPreferenceToWatchlist(Model model, @Validated WatchlistPropertyPreference watchlistPropertyPreference, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws IOException, ExecutionException, InterruptedException {
        if (bindingResult.hasErrors()) {
            loadWatchlistData(model, getCustomerIDFromRequest(request), new WatchlistPropertyItem(), watchlistPropertyPreference);
            return "Watchlist";
        }

        DeferredResult deferredResult = new DeferredResult();
        // Set server-controlled variables
        watchlistPropertyPreference.setCustomerID(getCustomerIDFromRequest(request));
        watchlistPropertyPreference.assignPreferenceID();

        logger.info("Attempting to add property to watchlist: {}", watchlistPropertyPreference);
        watchlistRepository.addPropertyPreferencesToWatchlist(watchlistPropertyPreference, deferredResult);

        response.sendRedirect("/watchlist");
        return null;
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

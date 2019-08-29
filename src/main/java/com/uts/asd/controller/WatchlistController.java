package com.uts.asd.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;
import com.uts.asd.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WatchlistController {
    @Autowired
    private WatchlistService watchlistService;

    @RequestMapping("/addPropertyToWatchlist")
    public void addPropertyToWatchlist(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String customerID = (String) session.getAttribute("customerID");
        String propertyID = request.getParameter("propertyID");
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(customerID, propertyID);
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
        HttpSession session = request.getSession();
        String customerID = (String) session.getAttribute("customerID");
        String propertyID = request.getParameter("propertyID");
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(customerID, propertyID);
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
        HttpSession session = request.getSession();
        String customerID = (String) session.getAttribute("customerID");
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
        HttpSession session = request.getSession();
        String customerID = (String) session.getAttribute("customerID");
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
}

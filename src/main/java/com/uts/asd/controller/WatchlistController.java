package com.uts.asd.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uts.asd.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WatchlistController {
    @Autowired
    private WatchlistService watchlistService;

    @RequestMapping("/watchlist_add")
    public void addPropertyToWatchlist(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String propertyID = request.getParameter("id");
        try {
            if (propertyID != null) {
                watchlistService.addPropertyToWatchlist(propertyID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/watchlist_remove")
    public void removePropertyFromWatchlist(HttpServletRequest request,HttpServletResponse response) throws IOException {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
}

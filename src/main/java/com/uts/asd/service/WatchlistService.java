package com.uts.asd.service;

import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;
import com.uts.asd.mapper.PropertyRepository;
import com.uts.asd.repository.WatchlistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/*
 * @author Harold Seefeld
 */

@Service
public class WatchlistService {

    Logger logger = LoggerFactory.getLogger(WatchlistService.class);

    @Autowired
    WatchlistRepository watchlistRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Async
    public CompletableFuture<ArrayList<WatchlistPropertyItem>> getWatchlistPropertyItems(int customerID) {
        logger.info("Attempting to get property items from watchlist for customerID {}", customerID);
        ArrayList<WatchlistPropertyItem> watchlistPropertyItems = watchlistRepository.getWatchlistPropertyItems(customerID);
        // Retrieve property information for each watchlist property item
        for (WatchlistPropertyItem watchlistPropertyItem : watchlistPropertyItems) {
            try {
                watchlistPropertyItem.setProperty(propertyRepository.searchById(watchlistPropertyItem.getPropertyID()));
            }
            catch (Exception e) {
                logger.error(e.toString());
            }
        }
        return CompletableFuture.completedFuture(watchlistPropertyItems);
    }

    @Async
    public CompletableFuture<ArrayList<WatchlistPropertyPreference>> getWatchlistPropertyPreferences(int customerID) {
        logger.info("Attempting to get property preferences from watchlist for customerID {}", customerID);
        ArrayList<WatchlistPropertyPreference> watchlistPropertyPreferences = watchlistRepository.getWatchlistPropertyPreferences(customerID);
        return CompletableFuture.completedFuture(watchlistPropertyPreferences);
    }

    @Async
    public CompletableFuture<String> runAsyncAddProperty(WatchlistPropertyItem watchlistPropertyItem) {
        String result = watchlistRepository.addPropertyToWatchlist(watchlistPropertyItem);
        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<String> runAsyncAddPreference(WatchlistPropertyPreference watchlistPropertyPreference) {
        String result = watchlistRepository.addPropertyPreferencesToWatchlist(watchlistPropertyPreference);
        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<String> runAsyncRemoveProperty(WatchlistPropertyItem watchlistPropertyItem) {
        String result = watchlistRepository.removePropertyFromWatchlist(watchlistPropertyItem);
        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<String> runAsyncRemovePreference(WatchlistPropertyPreference watchlistPropertyPreference) {
        String result = watchlistRepository.removePropertyPreferencesFromWatchlist(watchlistPropertyPreference);
        return CompletableFuture.completedFuture(result);
    }

}

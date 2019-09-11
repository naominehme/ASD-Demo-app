package com.uts.asd.service;

import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;
import com.uts.asd.repository.WatchlistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Async
    public CompletableFuture<ArrayList<WatchlistPropertyItem>> getWatchlistPropertyItems(String customerID) {
        logger.info("Attempting to get property items from watchlist for customerID {}", customerID);
        DeferredResult result = new DeferredResult();
        watchlistRepository.getWatchlistPropertyItems(customerID, result);
        // No nothing while waiting for result from the DeferredResult
        do {} while (!result.hasResult());
        return CompletableFuture.completedFuture((ArrayList<WatchlistPropertyItem>)result.getResult());
    }

    @Async
    public CompletableFuture<ArrayList<WatchlistPropertyPreference>> getWatchlistPropertyPreferences(String customerID) {
        logger.info("Attempting to get property preferences from watchlist for customerID {}", customerID);
        DeferredResult result = new DeferredResult();
        watchlistRepository.getWatchlistPropertyPreferences(customerID, result);
        // No nothing while waiting for result from the DeferredResult
        do {} while (!result.hasResult());
        return CompletableFuture.completedFuture((ArrayList<WatchlistPropertyPreference>)result.getResult());
    }

    @Async
    public CompletableFuture<String> runAsyncAddProperty(WatchlistPropertyItem watchlistPropertyItem) {
        DeferredResult result = new DeferredResult();
        watchlistRepository.addPropertyToWatchlist(watchlistPropertyItem, result);
        // No nothing while waiting for result from the DeferredResult
        do {} while (!result.hasResult());
        return CompletableFuture.completedFuture((String)result.getResult());
    }

    @Async
    public CompletableFuture<String> runAsyncAddPreference(WatchlistPropertyPreference watchlistPropertyPreference) {
        DeferredResult result = new DeferredResult();
        watchlistRepository.addPropertyPreferencesToWatchlist(watchlistPropertyPreference, result);
        // No nothing while waiting for result from the DeferredResult
        do {} while (!result.hasResult());
        return CompletableFuture.completedFuture((String)result.getResult());
    }

    @Async
    public CompletableFuture<String> runAsyncRemoveProperty(WatchlistPropertyItem watchlistPropertyItem) {
        DeferredResult result = new DeferredResult();
        watchlistRepository.removePropertyFromWatchlist(watchlistPropertyItem, result);
        // No nothing while waiting for result from the DeferredResult
        do {} while (!result.hasResult());
        return CompletableFuture.completedFuture((String)result.getResult());
    }

    @Async
    public CompletableFuture<String> runAsyncRemovePreference(WatchlistPropertyPreference watchlistPropertyPreference) {
        DeferredResult result = new DeferredResult();
        watchlistRepository.removePropertyPreferencesFromWatchlist(watchlistPropertyPreference, result);
        // No nothing while waiting for result from the DeferredResult
        do {} while (!result.hasResult());
        return CompletableFuture.completedFuture((String)result.getResult());
    }

}

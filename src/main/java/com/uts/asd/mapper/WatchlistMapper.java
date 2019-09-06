package com.uts.asd.mapper;

/*
 * @author Harold Seefeld
 */

import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;
import org.springframework.web.context.request.async.DeferredResult;

public interface WatchlistMapper {

    public void addPropertyToWatchlist (WatchlistPropertyItem watchlistPropertyItem, DeferredResult result);

    public void removePropertyFromWatchlist (WatchlistPropertyItem watchlistPropertyItem);

    public void addPropertyPreferencesToWatchlist (WatchlistPropertyPreference watchlistPropertyPreference, DeferredResult result);

    public void removePropertyPreferencesFromWatchlist (WatchlistPropertyPreference watchlistPropertyPreference);

    public void getWatchlistPropertyItems(String customerID, DeferredResult result);

    public void getWatchlistPropertyPreferences(String customerID, DeferredResult result);
}

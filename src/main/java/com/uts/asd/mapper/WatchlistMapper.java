package com.uts.asd.mapper;

/*
 * @author Harold Seefeld
 */

import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;

public interface WatchlistMapper {

    public void addPropertyToWatchlist (WatchlistPropertyItem watchlistPropertyItem);

    public void removePropertyFromWatchlist (WatchlistPropertyItem watchlistPropertyItem);

    public void addPropertyPreferencesToWatchlist (WatchlistPropertyPreference watchlistPropertyPreference);

    public void removePropertyPreferencesFromWatchlist (WatchlistPropertyPreference watchlistPropertyPreference);
}

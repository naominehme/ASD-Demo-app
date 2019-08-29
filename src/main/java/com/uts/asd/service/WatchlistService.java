package com.uts.asd.service;

import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;
import org.springframework.stereotype.Service;

import com.uts.asd.mapper.WatchlistMapper;

/*
 * @author Harold Seefeld
 */

@Service
public class WatchlistService implements WatchlistMapper{

    public void addPropertyToWatchlist(WatchlistPropertyItem watchlistPropertyItem) {
        //watchlistMapper.addPropertyToWatchlist(watchlistPropertyItem);
    }

    public void removePropertyFromWatchlist(WatchlistPropertyItem watchlistPropertyItem) {
       //watchlistMapper.removePropertyFromWatchlist(watchlistPropertyItem);
    }

    public void addPropertyPreferencesToWatchlist(WatchlistPropertyPreference watchlistPropertyPreference) {
        //watchlistMapper.addPropertyPreferencesToWatchlist(watchlistPropertyPreference);
    }

    public void removePropertyPreferencesFromWatchlist(WatchlistPropertyPreference watchlistPropertyPreference) {
        //watchlistMapper.removePropertyPreferencesFromWatchlist(watchlistPropertyPreference);
    }
}

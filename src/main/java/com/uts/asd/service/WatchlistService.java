package com.uts.asd.service;

import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uts.asd.mapper.WatchlistMapper;

/*
 * @author Harold Seefeld
 */

@Service
public class WatchlistService {
    @Autowired
    private static WatchlistMapper watchlistMapper;

    public static void addPropertyToWatchlist(WatchlistPropertyItem watchlistPropertyItem) {
        watchlistMapper.addPropertyToWatchlist(watchlistPropertyItem);
    }

    public static void removePropertyFromWatchlist(WatchlistPropertyItem watchlistPropertyItem) {
        watchlistMapper.removePropertyFromWatchlist(watchlistPropertyItem);
    }

    public static void addPropertyPreferencesToWatchlist(WatchlistPropertyPreference watchlistPropertyPreference) {
        watchlistMapper.addPropertyPreferencesToWatchlist(watchlistPropertyPreference);
    }

    public static void removePropertyPreferencesFromWatchlist(WatchlistPropertyPreference watchlistPropertyPreference) {
        watchlistMapper.removePropertyPreferencesFromWatchlist(watchlistPropertyPreference);
    }
}

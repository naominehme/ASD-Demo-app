package com.uts.asd.mapper;

/*
 * @author Harold Seefeld
 */

import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;

import java.util.ArrayList;

public interface WatchlistMapper {

    public String addPropertyToWatchlist (WatchlistPropertyItem watchlistPropertyItem);

    public String removePropertyFromWatchlist (WatchlistPropertyItem watchlistPropertyItem);

    public String addPropertyPreferencesToWatchlist (WatchlistPropertyPreference watchlistPropertyPreference);

    public String removePropertyPreferencesFromWatchlist (WatchlistPropertyPreference watchlistPropertyPreference);

    public ArrayList<WatchlistPropertyItem> getWatchlistPropertyItems(String customerID);

    public ArrayList<WatchlistPropertyPreference> getWatchlistPropertyPreferences(String customerID);
}

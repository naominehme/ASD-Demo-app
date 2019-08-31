package com.uts.asd.service;

import com.google.firebase.database.*;
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
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("WatchlistPropertyItem");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                System.out.println(document);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
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

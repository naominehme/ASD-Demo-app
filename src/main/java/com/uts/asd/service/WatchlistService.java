package com.uts.asd.service;

import com.google.cloud.Timestamp;
import com.google.firebase.database.*;
import com.uts.asd.controller.WatchlistController;
import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.uts.asd.mapper.WatchlistMapper;

/*
 * @author Harold Seefeld
 */

@Service
public class WatchlistService implements WatchlistMapper{

    Logger logger = LoggerFactory.getLogger(WatchlistService.class);

    public void addPropertyToWatchlist(WatchlistPropertyItem watchlistPropertyItem) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("WatchlistPropertyItem/" +
                        watchlistPropertyItem.getCustomerID() + "/" +
                        watchlistPropertyItem.getPropertyID());

        ref.setValueAsync(watchlistPropertyItem);
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

    public void getWatchlistPropertyItems(String customerID) {
        // TODO: Get specific customerID preferences
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("WatchlistPropertyItem/" + customerID);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                logger.info("Data retrieved: " + dataSnapshot.getValue());

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    // Create new WatchlistPropertyItem object
                    WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(
                            (String)childSnapshot.child("customerID").getValue(),
                            (String)childSnapshot.child("propertyID").getValue(),
                            (String) childSnapshot.child("createdDate").getValue());
                    logger.info(watchlistPropertyItem.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                logger.error(error.toString());
            }
        });
    }

    public void getWatchlistPropertyPreferences(String customerID) {

    }
}

package com.uts.asd.repository;

import com.google.firebase.database.*;
import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uts.asd.mapper.WatchlistMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;

/*
 * @author Harold Seefeld
 */

@Repository
public class WatchlistRepository implements WatchlistMapper{

    Logger logger = LoggerFactory.getLogger(WatchlistRepository.class);

    public void addPropertyToWatchlist(WatchlistPropertyItem watchlistPropertyItem, DeferredResult result) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("WatchlistPropertyItem/" +
                        watchlistPropertyItem.getCustomerID() + "/" +
                        watchlistPropertyItem.getPropertyID());

        ref.setValue(watchlistPropertyItem, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                logger.error("Data could not be saved " + databaseError.getMessage());
                result.setResult(databaseError.getMessage());
            } else {
                logger.info("Data saved successfully.");
                result.setResult("Added '" + watchlistPropertyItem.getPropertyID() + "' successfully.");
            }
        });
    }

    public void removePropertyFromWatchlist(WatchlistPropertyItem watchlistPropertyItem, DeferredResult result) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("WatchlistPropertyItem/" +
                        watchlistPropertyItem.getCustomerID() + "/" +
                        watchlistPropertyItem.getPropertyID());

        ref.setValue(null, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                logger.error("Data could not be saved " + databaseError.getMessage());
                result.setResult(databaseError.getMessage());
            } else {
                logger.info("Data saved successfully.");
                result.setResult("Added '" + watchlistPropertyItem.getPropertyID() + "' successfully.");
            }
        });
    }

    public void addPropertyPreferencesToWatchlist(WatchlistPropertyPreference watchlistPropertyPreference, DeferredResult result) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("WatchlistPropertyPreference/" +
                        watchlistPropertyPreference.getCustomerID() + "/" +
                        watchlistPropertyPreference.getPreferenceID());

        ref.setValue(watchlistPropertyPreference, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                logger.error("Data could not be saved " + databaseError.getMessage());
                result.setResult(databaseError.getMessage());
            } else {
                logger.info("Data saved successfully.");
                result.setResult("Added '" + watchlistPropertyPreference.getPreferenceID() + "' successfully.");
            }
        });
    }

    public void removePropertyPreferencesFromWatchlist(WatchlistPropertyPreference watchlistPropertyPreference, DeferredResult result) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("WatchlistPropertyPreference/" +
                        watchlistPropertyPreference.getCustomerID() + "/" +
                        watchlistPropertyPreference.getPreferenceID());

        ref.setValue(null, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                logger.error("Data could not be saved " + databaseError.getMessage());
                result.setResult(databaseError.getMessage());
            } else {
                logger.info("Data saved successfully.");
                result.setResult("Added '" + watchlistPropertyPreference.getPreferenceID() + "' successfully.");
            }
        });
    }

    public void getWatchlistPropertyItems(int customerID, DeferredResult result) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("WatchlistPropertyItem/" + customerID);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                logger.info("Data retrieved: " + dataSnapshot.getValue());
                ArrayList<WatchlistPropertyItem> watchlistPropertyItemArrayList = new ArrayList<>();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    // Create new WatchlistPropertyItem object
                    WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(
                            childSnapshot.child("customerID").getValue(long.class).intValue(),
                            childSnapshot.child("propertyID").getValue(long.class).intValue(),
                            childSnapshot.child("createdDate").getValue(String.class));
                    watchlistPropertyItemArrayList.add(watchlistPropertyItem);
                    logger.info(watchlistPropertyItem.toString());
                }

                result.setResult(watchlistPropertyItemArrayList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                logger.error(error.toString());
                result.setErrorResult(error);
            }
        });
    }

    public void getWatchlistPropertyPreferences(int customerID, DeferredResult result) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("WatchlistPropertyPreference/" + customerID);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                logger.info("Data retrieved: " + dataSnapshot.getValue());
                ArrayList<WatchlistPropertyPreference> watchlistPropertyPreferenceArrayListArrayList = new ArrayList<>();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    // Create new WatchlistPropertyItem object
                    WatchlistPropertyPreference watchlistPropertyPreference = new WatchlistPropertyPreference(
                            childSnapshot.child("customerID").getValue(long.class).intValue(),
                            childSnapshot.child("typeID").getValue(String.class),
                            childSnapshot.child("preferenceID").getValue(String.class),
                            childSnapshot.child("garageSpaces").getValue(long.class).intValue(),
                            childSnapshot.child("numOfBathrooms").getValue(long.class).intValue(),
                            childSnapshot.child("numOfBedrooms").getValue(long.class).intValue(),
                            childSnapshot.child("suburb").getValue(String.class));
                    watchlistPropertyPreferenceArrayListArrayList.add(watchlistPropertyPreference);
                    logger.info(watchlistPropertyPreference.toString());
                }

                result.setResult(watchlistPropertyPreferenceArrayListArrayList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                logger.error(error.toString());
                result.setErrorResult(error);
            }
        });
    }
}


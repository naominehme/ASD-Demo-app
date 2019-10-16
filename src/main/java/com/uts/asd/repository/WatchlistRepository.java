package com.uts.asd.repository;

import com.google.firebase.database.*;
import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uts.asd.mapper.WatchlistMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/*
 * @author Harold Seefeld
 */

@Repository
public class WatchlistRepository implements WatchlistMapper{

    Logger logger = LoggerFactory.getLogger(WatchlistRepository.class);

    public String addPropertyToWatchlist(WatchlistPropertyItem watchlistPropertyItem) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("WatchlistPropertyItem/" +
                        watchlistPropertyItem.getCustomerID() + "/" +
                        watchlistPropertyItem.getPropertyID());

        CompletableFuture<String> completableFuture = new CompletableFuture();
        ref.setValue(watchlistPropertyItem, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                logger.error("Data could not be saved " + databaseError.getMessage());
                completableFuture.complete(databaseError.getMessage());
            } else {
                logger.info("Data saved successfully.");
                completableFuture.complete("Added '" + watchlistPropertyItem.getPropertyID() + "' successfully.");
            }
        });

        return getStringFromCompletableFuture(completableFuture);
    }

    public String removePropertyFromWatchlist(WatchlistPropertyItem watchlistPropertyItem) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("WatchlistPropertyItem/" +
                        watchlistPropertyItem.getCustomerID() + "/" +
                        watchlistPropertyItem.getPropertyID());

        CompletableFuture<String> completableFuture = new CompletableFuture();
        ref.setValue(null, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                logger.error("Data could not be saved " + databaseError.getMessage());
                completableFuture.complete(databaseError.getMessage());
            } else {
                logger.info("Data saved successfully.");
                completableFuture.complete("Removed '" + watchlistPropertyItem.getPropertyID() + "' successfully.");
            }
        });

        return getStringFromCompletableFuture(completableFuture);
    }

    public String addPropertyPreferencesToWatchlist(WatchlistPropertyPreference watchlistPropertyPreference) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("WatchlistPropertyPreference/" +
                        watchlistPropertyPreference.getCustomerID() + "/" +
                        watchlistPropertyPreference.getPreferenceID());

        CompletableFuture<String> completableFuture = new CompletableFuture();
        ref.setValue(watchlistPropertyPreference, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                logger.error("Data could not be saved " + databaseError.getMessage());
                completableFuture.complete(databaseError.getMessage());
            } else {
                logger.info("Data saved successfully.");
                completableFuture.complete("Added '" + watchlistPropertyPreference.getPreferenceID() + "' successfully.");
            }
        });

        return getStringFromCompletableFuture(completableFuture);
    }

    public String removePropertyPreferencesFromWatchlist(WatchlistPropertyPreference watchlistPropertyPreference) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("WatchlistPropertyPreference/" +
                        watchlistPropertyPreference.getCustomerID() + "/" +
                        watchlistPropertyPreference.getPreferenceID());

        CompletableFuture<String> completableFuture = new CompletableFuture();
        ref.setValue(null, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                logger.error("Data could not be saved " + databaseError.getMessage());
                completableFuture.complete(databaseError.getMessage());
            } else {
                logger.info("Data saved successfully.");
                completableFuture.complete("Removed '" + watchlistPropertyPreference.getPreferenceID() + "' successfully.");
            }
        });

        return getStringFromCompletableFuture(completableFuture);
    }

    private String getStringFromCompletableFuture(CompletableFuture<String> completableFuture) {
        String result = "";
        // Wait for request to complete
        try {
            result = completableFuture.get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return result;
    }

    public ArrayList<WatchlistPropertyItem> getWatchlistPropertyItems(String customerID) {
        CompletableFuture<ArrayList<WatchlistPropertyItem>> completableFuture = new CompletableFuture<>();
        // Get data from NoSQL
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("WatchlistPropertyItem/" + customerID);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                logger.info("Data retrieved: " + dataSnapshot.getValue());
                ArrayList<WatchlistPropertyItem> watchlistPropertyItemArrayList = new ArrayList<>();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    // Create new WatchlistPropertyItem object
                    WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(
                            childSnapshot.child("customerID").getValue(String.class),
                            childSnapshot.child("propertyID").getValue(long.class).intValue(),
                            childSnapshot.child("createdDate").getValue(String.class));
                    watchlistPropertyItemArrayList.add(watchlistPropertyItem);
                    logger.info(watchlistPropertyItem.toString());
                }
                // Complete the Async request
                completableFuture.complete(watchlistPropertyItemArrayList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                logger.error(error.toString());
            }
        });

        ArrayList<WatchlistPropertyItem> watchlistPropertyItems = new ArrayList<>();
        // Wait for request to complete
        try {
            watchlistPropertyItems = completableFuture.get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return watchlistPropertyItems;
    }

    public ArrayList<WatchlistPropertyItem> getAllWatchlistPropertyItems() {
        CompletableFuture<ArrayList<WatchlistPropertyItem>> completableFuture = new CompletableFuture<>();
        // Get data from NoSQL
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("WatchlistPropertyItem/");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                logger.info("Data retrieved: " + dataSnapshot.getValue());
                ArrayList<WatchlistPropertyItem> watchlistPropertyItemArrayList = new ArrayList<>();
                for (DataSnapshot customerSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot childSnapshot : customerSnapshot.getChildren()) {
                        // Create new WatchlistPropertyItem object
                        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(
                                childSnapshot.child("customerID").getValue(String.class),
                                childSnapshot.child("propertyID").getValue(long.class).intValue(),
                                childSnapshot.child("createdDate").getValue(String.class));
                        watchlistPropertyItemArrayList.add(watchlistPropertyItem);
                    }
                }
                // Complete the Async request
                completableFuture.complete(watchlistPropertyItemArrayList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                logger.error(error.toString());
            }
        });

        ArrayList<WatchlistPropertyItem> watchlistPropertyItems = new ArrayList<>();
        // Wait for request to complete
        try {
            watchlistPropertyItems = completableFuture.get(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return watchlistPropertyItems;
    }

    public ArrayList<WatchlistPropertyPreference> getWatchlistPropertyPreferences(String customerID) {
        CompletableFuture<ArrayList<WatchlistPropertyPreference>> completableFuture = new CompletableFuture<>();
        // Get data from NoSQL
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("WatchlistPropertyPreference/" + customerID);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                logger.info("Data retrieved: " + dataSnapshot.getValue());
                ArrayList<WatchlistPropertyPreference> watchlistPropertyPreferenceArrayList = new ArrayList<>();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    // Create new WatchlistPropertyItem object
                    WatchlistPropertyPreference watchlistPropertyPreference = new WatchlistPropertyPreference(
                            childSnapshot.child("customerID").getValue(String.class),
                            childSnapshot.child("typeID").getValue(String.class),
                            childSnapshot.child("preferenceID").getValue(String.class),
                            childSnapshot.child("garageSpaces").getValue(long.class).intValue(),
                            childSnapshot.child("numOfBathrooms").getValue(long.class).intValue(),
                            childSnapshot.child("numOfBedrooms").getValue(long.class).intValue(),
                            childSnapshot.child("suburb").getValue(String.class));
                    watchlistPropertyPreferenceArrayList.add(watchlistPropertyPreference);
                }

                completableFuture.complete(watchlistPropertyPreferenceArrayList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                logger.error(error.toString());
            }
        });

        ArrayList<WatchlistPropertyPreference> watchlistPropertyPreferences = new ArrayList<>();
        // Wait for request to complete
        try {
            watchlistPropertyPreferences = completableFuture.get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return watchlistPropertyPreferences;
    }
}


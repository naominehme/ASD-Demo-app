package com.uts.asd;

import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;
import com.uts.asd.repository.WatchlistRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WatchlistTests {

    @Autowired
    private WatchlistRepository repository;

    final private String VALID_CUSTOMER_ID = "-2";
    final private int VALID_PROPERTY_ID = -2;
    final private String INVALID_CUSTOMER_ID = "-999";
    final private int INVALID_PROPERTY_ID = -999;

    @Before
    public void beforeClass() {
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(VALID_CUSTOMER_ID, VALID_PROPERTY_ID);
        repository.addPropertyToWatchlist(watchlistPropertyItem);
        repository.addPropertyPreferencesToWatchlist(new WatchlistPropertyPreference(
                VALID_CUSTOMER_ID,"House","TEST",0,0,0, "TESTBURB"
        ));
    }

    @Test
    public void addProperty_WithValidData_ReturnWorks() {
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(VALID_CUSTOMER_ID, VALID_PROPERTY_ID);
        String result = repository.addPropertyToWatchlist(watchlistPropertyItem);
        Assert.assertTrue(result.contains("successful"));
    }

    @Test
    public void addPropertyPreference_WithValidData_ReturnWorks() {
        String result = repository.addPropertyPreferencesToWatchlist(new WatchlistPropertyPreference(
                VALID_CUSTOMER_ID,"House","TEST",0,0,0, "TESTBURB"
        ));
        Assert.assertTrue(result.contains("successful"));
    }

    @Test
    public void getProperty_WithValidData_ReturnWorks() {
        ArrayList<WatchlistPropertyItem> watchlistPropertyItems = repository.getWatchlistPropertyItems(VALID_CUSTOMER_ID);
        Assert.assertNotEquals("[]", watchlistPropertyItems.toString());
    }

    @Test
    public void getProperty_WithInvalidData_ReturnNull() {
        ArrayList<WatchlistPropertyItem> watchlistPropertyItems = repository.getWatchlistPropertyItems(INVALID_CUSTOMER_ID);
        Assert.assertEquals("[]", watchlistPropertyItems.toString());
    }

    @Test
    public void getPropertyPreferences_WithValidData_ReturnWorks() {
        ArrayList<WatchlistPropertyPreference> watchlistPropertyItems = repository.getWatchlistPropertyPreferences(VALID_CUSTOMER_ID);
        Assert.assertNotEquals("[]", watchlistPropertyItems.toString());
    }

    @Test
    public void getPropertyPreferences_WithInvalidData_ReturnNull() {
        ArrayList<WatchlistPropertyPreference> watchlistPropertyItems = repository.getWatchlistPropertyPreferences(INVALID_CUSTOMER_ID);
        Assert.assertEquals("[]", watchlistPropertyItems.toString());
    }
}

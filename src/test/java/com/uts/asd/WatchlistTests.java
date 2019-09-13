package com.uts.asd;

import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.repository.WatchlistRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.async.DeferredResult;

import javax.swing.*;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WatchlistTests {

    @Autowired
    private WatchlistRepository repository;

    final private int VALID_CUSTOMER_ID = -2;
    final private int VALID_PROPERTY_ID = -2;
    final private int INVALID_CUSTOMER_ID = -999;
    final private int INVALID_PROPERTY_ID = -999;

    @Before
    public void beforeClass() {
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(VALID_CUSTOMER_ID, VALID_PROPERTY_ID);
        DeferredResult deferredResult = new DeferredResult();
        repository.addPropertyToWatchlist(watchlistPropertyItem);
    }

    @Test
    public void addProperty_WithValidData_ReturnWorks() {
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(VALID_CUSTOMER_ID, VALID_PROPERTY_ID);
        String result = repository.addPropertyToWatchlist(watchlistPropertyItem);
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
}

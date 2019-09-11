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

@RunWith(SpringRunner.class)
@SpringBootTest
public class WatchlistTests {

    @Autowired
    private WatchlistRepository repository;

    final private String VALID_CUSTOMER_ID = "TestCustomer";
    final private String VALID_PROPERTY_ID = "TestProperty";
    final private String INVALID_CUSTOMER_ID = "InvalidTestCustomer";
    final private String INVALID_PROPERTY_ID = "InvalidProperty";

    @Before
    public void beforeClass() {
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(VALID_CUSTOMER_ID, VALID_PROPERTY_ID);
        DeferredResult deferredResult = new DeferredResult();
        repository.addPropertyToWatchlist(watchlistPropertyItem, deferredResult);
    }

    @Test
    public void addProperty_WithValidData_ReturnWorks() {
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(VALID_CUSTOMER_ID, VALID_PROPERTY_ID);
        DeferredResult deferredResult = new DeferredResult();
        repository.addPropertyToWatchlist(watchlistPropertyItem, deferredResult);
        while (!deferredResult.hasResult()) {
            // Do nothing
        }
        Assert.assertTrue(String.valueOf(deferredResult.getResult()).contains("successful"));
    }

    @Test
    public void getProperty_WithValidData_ReturnWorks() {
        DeferredResult deferredResult = new DeferredResult();
        repository.getWatchlistPropertyItems(VALID_CUSTOMER_ID, deferredResult);
        while (!deferredResult.hasResult()) {
            // Do nothing
        }
        System.out.println(deferredResult.getResult());
        Assert.assertNotEquals(deferredResult.getResult().toString(), "[]");
    }

    @Test
    public void getProperty_WithInvalidData_ReturnNull() {
        DeferredResult deferredResult = new DeferredResult();
        repository.getWatchlistPropertyItems(INVALID_CUSTOMER_ID, deferredResult);
        while (!deferredResult.hasResult()) {
            // Do nothing
        }
        System.out.println(deferredResult.getResult());
        Assert.assertEquals(deferredResult.getResult().toString(), "[]");
    }
}

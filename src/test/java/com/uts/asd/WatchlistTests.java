package com.uts.asd;

import com.sun.org.apache.bcel.internal.classfile.Constant;
import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.repository.WatchlistRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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

    final private String CUSTOMER_ID = "TestCustomer";
    final private String INVALID_CUSTOMER_ID = "InvalidTestCustomer";
    final private String PROPERTY_ID = "TestProperty";

    @Before
    public void beforeClass() {
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(CUSTOMER_ID, PROPERTY_ID);
        DeferredResult deferredResult = new DeferredResult();
        repository.addPropertyToWatchlist(watchlistPropertyItem, deferredResult);
    }

    @Test
    public void addProperty_WithValidData_ReturnWorks() {
        WatchlistPropertyItem watchlistPropertyItem = new WatchlistPropertyItem(CUSTOMER_ID, PROPERTY_ID);
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
        repository.getWatchlistPropertyItems(CUSTOMER_ID, deferredResult);
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

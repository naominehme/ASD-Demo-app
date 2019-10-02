package com.uts.asd;

import com.uts.asd.entity.Notification;
import com.uts.asd.entity.WatchlistPropertyItem;
import com.uts.asd.entity.WatchlistPropertyPreference;
import com.uts.asd.repository.NotificationRepository;
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
import java.util.concurrent.CompletableFuture;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationTests {

    @Autowired
    private NotificationRepository repository;

    final private String VALID_CUSTOMER_ID = "-2";
    final private String VALID_NOTIFICATION_ID = "TEST";
    final private int VALID_PROPERTY_ID = -2;

    @Test
    public void addProperty_WithValidData_ReturnWorks() {
        String result = repository.addNotificationToNotifications(
                new Notification(VALID_CUSTOMER_ID, VALID_NOTIFICATION_ID));
        Assert.assertTrue(result.contains("successful"));
    }

    @Test
    public void getProperty_WithValidData_ReturnWorks() {
        ArrayList<Notification> notificationItems = repository.getNotificationItems(VALID_CUSTOMER_ID);
        Assert.assertNotEquals("[]", notificationItems.toString());
    }

    @Test
    public void removeNotification_WithValidData_ReturnWorks() {
        String result = repository.removeNotificationFromNotifications(
                new Notification(VALID_CUSTOMER_ID, VALID_NOTIFICATION_ID));
        Assert.assertTrue(result.contains("successful"));
    }

}

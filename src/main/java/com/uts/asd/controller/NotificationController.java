package com.uts.asd.controller;

import com.uts.asd.repository.NotificationRepository;
import com.uts.asd.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationService notificationService;

    private int DEFAULT_CUSTOMER_ID = -1;

    public void setDefaultCustomerID(int id) {
        DEFAULT_CUSTOMER_ID = id;
    }


}

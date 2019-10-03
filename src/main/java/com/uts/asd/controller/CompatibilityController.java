package com.uts.asd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CompatibilityController {

    @RequestMapping("/forceLogin")
    public void forceLogin(HttpServletRequest request) {
        String customerID = request.getParameter("customerID");
        request.getSession().setAttribute("customerID", customerID);
    }

}

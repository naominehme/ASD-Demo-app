package com.uts.asd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uts.asd.entity.User;
import com.uts.asd.service.UserService;

@Controller
@EnableAutoConfiguration
public class UserController {

    @Autowired
    private UserService userService;



    @RequestMapping("/register")
    String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    @ResponseBody
    String registerUser(User user, Model model) {
        return userService.registerUser(user);
    }

}
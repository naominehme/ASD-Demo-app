package com.uts.asd.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.uts.asd.service.UserService;
import com.uts.asd.entity.User;
@Controller
public class RegisterController {

  @Autowired
  public UserService userService;

  @PostMapping(value = "/addUser")
  public String addUser(@ModelAttribute("user") User user, ModelMap model) {
    userService.addUser(user);
    model.addAttribute("username", user.getFirtName());

    return "welcome";
  }
}
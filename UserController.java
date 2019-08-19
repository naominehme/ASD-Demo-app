package com.uts.asd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.uts.asd.entity.USer;
import com.uts.asd.service.UserService;

/*
 * @author Weixiang Gao
 */

@Controller
public class userController {
	@Autowired
	private UserService userService;


	@RequestMapping("/register.do")
	public String register(HttpServletRequest request, HttpServletResponse response){
	    String userId = request.getParameter("userId");
	    String firstName = request.getParameter("firstname");
	    String lastName = request.getParameter("lastname");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    String username = request.getParameter("username");
	    String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String postcode = request.getParameter("postcode");
        try{
                         if(null!=email && null!=password &&null!=username &&null!=first &&null!=last &&null!=phone){
                             UserService.register(new User(first,last,username,password,email,phone));
                         }

            }catch (Exception e) {
                     e.printStackTrace();
             }
             return "login.html";
         }

}


















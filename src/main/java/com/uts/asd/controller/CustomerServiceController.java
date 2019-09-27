package com.uts.asd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.uts.asd.entity.Auction;
import com.uts.asd.entity.Bid;
import com.uts.asd.entity.CustomerServiceEntity;
import com.uts.asd.entity.Increment;
import com.uts.asd.entity.Property;
import com.uts.asd.service.BidService;
import com.uts.asd.service.CS_Service;
import com.uts.asd.service.MailService;
import com.uts.asd.service.PropertyService;
import com.uts.asd.util.JsonUtil;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerServiceController {
	@Autowired
	private CS_Service cs_Service;
  
    @RequestMapping("/addProblem") 
    public void ProblemAdd(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
    	Gson gson = new Gson();
    	try {
    		String problem = request.getParameter("problem");             
 		
    		CustomerServiceEntity c = new CustomerServiceEntity();	
    		String json = JsonUtil.readJsonFile("src/main/resources/increment.json");
    		Increment i =gson.fromJson(json, Increment.class);
    		c.setId(i.getCustomerServiceid());    		
    		i.setPropertyid(i.getCustomerServiceid()+1);   		
    		String a2 = gson.toJson(i);
    		JsonUtil.writeJsonFile(a2, "src/main/resources/increment.json");  		
    		c.setProblem(problem);
    		cs_Service.addProblem(c);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	//response.sendRedirect("/CustomerService.html");
    	response.sendRedirect("Property.html");
    }   
}

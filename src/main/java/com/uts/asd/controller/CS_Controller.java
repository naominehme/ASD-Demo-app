package com.uts.asd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
//import com.uts.asd.entity.Collectionz;
import com.uts.asd.entity.CS;
import com.uts.asd.entity.Increment;
import com.uts.asd.entity.Property;
//import com.uts.asd.service.ActionService;
import com.uts.asd.service.BidService;
import com.uts.asd.service.CS_Service;
//import com.uts.asd.service.CollectionService;
import com.uts.asd.service.MailService;
import com.uts.asd.service.PropertyService;
import com.uts.asd.util.JsonUtil;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CS_Controller {
	@Autowired
	private CS_Service cs_Service;
	int theid;
    
	public String time( ){ 
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");  // date format
		String time = dateFormat.format( now ); 
		return time; 
		} 	
	
    @RequestMapping(value = "/CustomerService", method = RequestMethod.GET)
    public String CustomerService(HttpServletRequest request, HttpServletResponse response, Model model, CS cs) 
            throws IOException {
		try {		
		      List<CS> theList = cs_Service.showAll(cs);		
	       	  request.setAttribute("theList", theList);		
	        }catch (Exception e) {
			 e.printStackTrace();
		    }
		  return "/CustomerService";
       }
    
    @RequestMapping("/addProblem") 
    public ModelAndView ProblemAdd(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
    	Gson gson = new Gson();
    	try {    		 
    		String problem = request.getParameter("problem");  
    		String status = "Processing"; 
    		String time = time();  
    		String feedback = "null";
    		
    		CS cs = new CS();	
    		String json = JsonUtil.readJsonFile("src/main/resources/increment.json");
    		Increment i =gson.fromJson(json, Increment.class);
    		cs.setId(i.getCustomerServiceid());    		
    		i.setCustomerServiceid(i.getCustomerServiceid()+1);   		
    		String a2 = gson.toJson(i);
    		JsonUtil.writeJsonFile(a2, "src/main/resources/increment.json");  		
    		cs.setProblem(problem);
    		cs.setStatus(status);
    		cs.setTime(time);
    		cs.setFeedback(feedback);
    		cs_Service.addProblem(cs);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return new ModelAndView("redirect:/CustomerService");
    }   
    
    @RequestMapping(value = "/theFeedback", method = RequestMethod.GET)
    public String theFeedback(HttpServletRequest request, HttpServletResponse response, Model model, CS cs) 
            throws IOException {
		try {		
		      List<CS> theList = cs_Service.showAll(cs);		
	       	  request.setAttribute("theList", theList);		
	        }catch (Exception e) {
			 e.printStackTrace();
		    }
		  return "/Feedback";
       }
    
    @RequestMapping("/feedbackID/{id}")
    public void feedbackID(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Integer id) 
           throws IOException {    	
    	try {
    		  theid = id;
       	} catch (Exception e) {
          e.printStackTrace();
    	}
    	response.sendRedirect("/replyFeedback.html");
        }
    
      
    @RequestMapping("/problemFeedback")
    public ModelAndView  problemFeedback(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Gson gson = new Gson();
    	try {
    		String feedback = request.getParameter("feedback");   
    		String status = "replied";
		
    		CS cs = new CS(theid);
    		cs = cs_Service.showAll(cs).get(0);
    		cs.setFeedback(feedback);
    		cs.setStatus(status);
    		
    		cs_Service.addProblem(cs);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return new ModelAndView("redirect:/theFeedback");
    } 
   
}

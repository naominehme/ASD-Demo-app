package com.uts.asd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.uts.asd.entity.Auction;
import com.uts.asd.entity.Bid;
import com.uts.asd.entity.Deposit;
import com.uts.asd.entity.Increment;
import com.uts.asd.entity.Property;
import com.uts.asd.entity.User;
import com.uts.asd.service.AuctionService;
import com.uts.asd.service.BidService;
import com.uts.asd.service.DepositService;
import com.uts.asd.service.PropertyService;
import com.uts.asd.service.UserService;
import com.uts.asd.util.JsonUtil;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PropertyController {
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private AuctionService actionService;
	@Autowired
	private BidService bidService;
	@Autowired
	private DepositService depositService;
	@Autowired
	private UserService userService;
	
	int theid;
	
	@RequestMapping("/test")
	public String test(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Gson gson = new Gson();
		PrintWriter writer=response.getWriter();
		gson.toJson("123",writer);
		//gson.toJson("1234",writer);
		//gson.toJson();
        writer.close();
        
		return null;
	}
	
	@RequestMapping("/updateEmail.do")
	public String update(HttpServletRequest request, HttpServletResponse response,User user)throws IOException {
		Gson gson = new Gson();
		try {
			userService.updateEmail(user);
			PrintWriter writer = response.getWriter();
			gson.toJson("Success", writer);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/home")
	public String home(HttpServletRequest request, HttpServletResponse response,Property property) throws IOException {
		Gson gson = new Gson();
		try {
			gson.toJson(property, Property.class);
			List<Property> list = propertyService.searchAll(property);
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

	@RequestMapping("/homedetail/{id}")
	public String homeDetail(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Integer id)
			throws IOException {
		try {
			Property pp = new Property(id);
			Property p = propertyService.searchById(pp);
			if (null!=p.getUrl()&&p.getUrl().contains(";")) {
				p.getList().add(p.getUrl().split(";"));
			}
			List<Auction> alist = actionService.searchCondition(pp);
			if (alist.size()>0) {
				p.setAuction(alist.get(0));
			}
			List<Bid> blist = bidService.searchCondition(pp);
			p.setBid(blist);
			List<Deposit> dlist = depositService.searchAll(pp);
			p.setDeposit(dlist);
			request.setAttribute("p", p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "PropertyDetail";
	}
	
	
	
	
	
	
    @RequestMapping(value = "/theProperty", method = RequestMethod.GET)
    public String theProperty(HttpServletRequest request, HttpServletResponse response, Model model, Property property) 
            throws IOException {
		try {		
		      List<Property> theList = propertyService.searchAll(property);		
	       	  request.setAttribute("theList", theList);		
	        }catch (Exception e) {
			 e.printStackTrace();
		    }
		     return "/Property";
       }
   
    
    @RequestMapping("/addProperty")
    public ModelAndView  propertyAdd(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
    	Gson gson = new Gson();
    	try {
    		String address = request.getParameter("address");             
    		String bedroom = request.getParameter("bedroom");
    		String bathroom = request.getParameter("bathroom");
    		String garage = request.getParameter("garage");
    		String state = request.getParameter("state");
    		String title = request.getParameter("title");
    		String sqm = request.getParameter("sqm");
    		String suburb = request.getParameter("suburb");
    		String url = request.getParameter("url");
    		
    		int bedroomInt = Integer.parseInt(bedroom);
    		int bathroomInt = Integer.parseInt(bathroom);
    		int garageInt = Integer.parseInt(garage);
    		int stateInt = Integer.parseInt(state);
    		int sqmInt = Integer.parseInt(sqm);
    		
    		Property p = new Property();	
    		String json = JsonUtil.readJsonFile("src/main/resources/increment.json");
    		Increment i =gson.fromJson(json, Increment.class);
    		p.setId(i.getPropertyid());                   
    		i.setPropertyid(i.getPropertyid()+1);
    		String a2 = gson.toJson(i);
    		JsonUtil.writeJsonFile(a2, "src/main/resources/increment.json");
    		p.setAddress(address);
    		p.setBedroom(bedroomInt);
    		p.setBathroom(bathroomInt);
    		p.setGarage(garageInt);
    		p.setState(stateInt);
    		p.setTitle(title);
    		p.setSqm(sqmInt);
    		p.setSuburb(suburb);
    		p.setUrl(url);
    		propertyService.addProperty(p);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return new ModelAndView("redirect:/theProperty");
    }   
    
    
    @RequestMapping("/update/{id}")
    public void update(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Integer id) 
           throws IOException {    	
    	try {
    		  theid = id;
       	} catch (Exception e) {
          e.printStackTrace();
    	}
    	response.sendRedirect("/Property_update.html");
        }
    
      
    @RequestMapping("/updateProperty")
    public ModelAndView  propertyUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Gson gson = new Gson();
    	try {
    		String address = request.getParameter("address");             
    		String bedroom = request.getParameter("bedroom");
    		String bathroom = request.getParameter("bathroom");
    		String garage = request.getParameter("garage");
    		String state = request.getParameter("state");
    		String title = request.getParameter("title");
    		String sqm = request.getParameter("sqm");
    		String suburb = request.getParameter("suburb");
    		String url = request.getParameter("url");
    		
    		int bedroomInt = Integer.parseInt(bedroom);
    		int bathroomInt = Integer.parseInt(bathroom);
    		int garageInt = Integer.parseInt(garage);
    		int stateInt = Integer.parseInt(state);
    		int sqmInt = Integer.parseInt(sqm);
    		
    		Property p = new Property(theid);
    		p.setAddress(address);
    		p.setBedroom(bedroomInt);
    		p.setBathroom(bathroomInt);
    		p.setGarage(garageInt);
    		p.setState(stateInt);
    		p.setTitle(title);
    		p.setSqm(sqmInt);
    		p.setSuburb(suburb);
    		p.setUrl(url);
    		propertyService.addProperty(p);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return new ModelAndView("redirect:/theProperty");
    }   
	
    @RequestMapping("/delete/{id}")
    public ModelAndView  deleteProperty(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Integer deleteid) throws IOException {
    	try { 		
    		Property p = new Property(deleteid);    		
    		propertyService.removeProperty(p);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return new ModelAndView("redirect:/theProperty");
    }  
}

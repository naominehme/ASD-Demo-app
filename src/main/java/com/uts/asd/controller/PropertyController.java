package com.uts.asd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.uts.asd.entity.Auction;
import com.uts.asd.entity.Bid;
import com.uts.asd.entity.Property;
import com.uts.asd.service.ActionService;
import com.uts.asd.service.BidService;
import com.uts.asd.service.PropertyService;

@Controller
public class PropertyController {
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private ActionService actionService;
	@Autowired
	private BidService bidService;
	
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
		return "Homepage";
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
			request.setAttribute("p", p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "PropertyDetail";
	}
	
	@RequestMapping("/backendAdd")
	public String backendAdd(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Gson gson = new Gson();
		try {
			propertyService.addProperty(null);
			PrintWriter writer=response.getWriter();
			gson.toJson("Success",writer);
	        writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

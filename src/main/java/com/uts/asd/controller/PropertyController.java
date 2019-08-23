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
import com.uts.asd.entity.Property;
import com.uts.asd.service.PropertyService;

@RestController
public class PropertyController {
	@Autowired
	private PropertyService propertyService;
	
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
	
	@RequestMapping("/bid.do")
	public String bidding(HttpServletRequest request,HttpServletResponse response, @RequestParam Double money) throws IOException {
		Gson gson = new Gson();
		try {
			//propertyService.addBidding(money);
			PrintWriter writer=response.getWriter();
			gson.toJson("Success",writer);
	        writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/Home")
	public String home(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Gson gson = new Gson();
		try {
			propertyService.searchAll(null);
			PrintWriter writer=response.getWriter();
			gson.toJson("Success",writer);
	        writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

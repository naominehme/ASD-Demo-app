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
import com.uts.asd.entity.User;
import com.uts.asd.service.BiddingService;
import com.uts.asd.service.PropertyService;

@RestController
@RequestMapping("/bid")
public class BiddingController {
	@Autowired
	private BiddingService biddingService;
	
	@RequestMapping("/bidding.do")
	public void bidding(HttpServletRequest request,HttpServletResponse response, @RequestParam Double money) throws IOException {
		Gson gson = new Gson();
		PrintWriter writer=response.getWriter();
		try {
			//propertyService.addBidding(money);
			gson.toJson("success",writer);
			gson.toJson("1",writer);
	        writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			gson.toJson("fail,please contact adminstrator",writer);
			gson.toJson("-1",writer);
		}
	}
	
	@RequestMapping("/biddingRecord.do")
	public void biddingRecord(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Gson gson = new Gson();
		PrintWriter writer=response.getWriter();
		try {
			
			gson.toJson("success",writer);
			gson.toJson("1",writer);
	        writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			gson.toJson("fail,please contact adminstrator",writer);
			gson.toJson("-1",writer);
		}
	}
	
	@RequestMapping("/myBidding.do")
	public void personalBidding(HttpServletRequest request,HttpServletResponse response,User user) throws IOException {
		Gson gson = new Gson();
		PrintWriter writer=response.getWriter();
		try {
			
			gson.toJson("success",writer);
			gson.toJson("1",writer);
	        writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			gson.toJson("fail,please contact adminstrator",writer);
			gson.toJson("-1",writer);
		}
	}
}

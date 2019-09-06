package com.uts.asd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import com.uts.asd.entity.Bid;
import com.uts.asd.entity.Increment;
import com.uts.asd.entity.Property;
import com.uts.asd.entity.User;
import com.uts.asd.service.BidService;
import com.uts.asd.service.PropertyService;
import com.uts.asd.util.JsonUtil;

@RestController
public class BiddingController {
	@Autowired
	private BidService bidService;
	
	@RequestMapping("/bid.do")
	public String bidding(HttpServletRequest request, HttpServletResponse response,Bid bid)throws IOException {
		Gson gson = new Gson();
		try {
			String json = JsonUtil.readJsonFile("src/main/resources/increment.json");
			Increment i =gson.fromJson(json, Increment.class);
			bid.setId(i.getBidid());
			i.setBidid(i.getBidid()+1);
			String a2 = gson.toJson(i);
			JsonUtil.writeJsonFile(a2, "src/main/resources/increment.json");
			bid.setTime(new Date().getTime());
			bid.setState("Success");
			bidService.addAction(bid);
//			Property p = new Property(0,"abc st",1,1,1,0,"Brand New",700);
//			propertyService.addProperty(p);
//			Property p1 = propertyService.searchById(p);
			PrintWriter writer = response.getWriter();
			gson.toJson("success", writer);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/record.do")
	public String record(HttpServletRequest request, HttpServletResponse response,Integer pid)throws IOException {
		Gson gson = new Gson();
		try {
			List<Bid> blist = bidService.searchCondition(new Property(pid));
			PrintWriter writer = response.getWriter();
			gson.toJson(blist,writer);
			//gson.toJson("1", writer);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

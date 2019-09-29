package com.uts.asd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uts.asd.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.uts.asd.service.BidService;
import com.uts.asd.service.MailService;
import com.uts.asd.service.PropertyService;
import com.uts.asd.util.JsonUtil;

@RestController
public class BiddingController {
	@Autowired
	private BidService bidService;
	@Autowired
	private MailService mailService;
	@Autowired
	private NotificationRestController notificationRestController;
	
	@RequestMapping("/bid.do")
	public String bidding(HttpServletRequest request, HttpServletResponse response,Bid bid)throws IOException {
		Gson gson = new Gson();
		PrintWriter writer = response.getWriter();
		try {
			String email = request.getParameter("email");
			String json = JsonUtil.readJsonFile("src/main/resources/increment.json");
			Increment i =gson.fromJson(json, Increment.class);
			bid.setId(i.getBidid());
			i.setBidid(i.getBidid()+1);
			String a2 = gson.toJson(i);
			JsonUtil.writeJsonFile(a2, "src/main/resources/increment.json");
			bid.setTime(new Date().getTime());
			bid.setState("Success");
			if (0 != bid.getPrice()) {
				bidService.addAction(bid);
				if (null!=email&&!"".equals(email)) {
					mailService.sendMail("<h2>Dear Customer</h2><br/><h2>You have successfully placed a bid, see more detail click the link below</h2></br></br><a href='https://asd-demo-app-naomi.herokuapp.com/homedetail/"+bid.getPid()+"'>Property Link</a>", email);
				}
				gson.toJson("Success, you will receive a email shortly!", writer);
			}else {
				throw new NumberFormatException();
			}
			notificationRestController.createNotifications(request, bid);
//			Property p1 = propertyService.searchById(p);
		} catch (Exception e) {
			e.printStackTrace();
			gson.toJson("fail,please input vaild number", writer);
		}finally {
			writer.close();
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

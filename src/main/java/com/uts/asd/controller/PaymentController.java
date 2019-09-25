package com.uts.asd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.uts.asd.entity.Transaction;
import com.uts.asd.entity.User;
import com.uts.asd.service.PayService;
import com.uts.asd.service.TransactionService;

@Controller
public class PaymentController {
	@Autowired
	private PayService payService;
	@Autowired
	private TransactionService transactionService; 
	
	@RequestMapping("/pay.do")
	public String payment(HttpServletRequest request, HttpServletResponse response, User user, double money,Integer pid) throws IOException {
		Gson gson = new Gson();
		try {
			PrintWriter writer = response.getWriter();
			boolean judge = payService.pay(user, money,pid);
			if (judge) {
				gson.toJson("Pay successfully,please refresh this page",writer);
			}else {
				gson.toJson("You do not have enough balance",writer);
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/topup.do")
	public String topup(HttpServletRequest request, HttpServletResponse response,User user, double money) throws IOException {
		Gson gson = new Gson();
		try {
			payService.topUp(user, money);
			PrintWriter writer = response.getWriter();
			gson.toJson("Top up successfully",writer);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/payRecord")
	public String payRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String id = request.getParameter("id");
			User user = new User(id);
			List<Transaction> list = transactionService.searchAll(user);
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mytransaction";
	}
	
}

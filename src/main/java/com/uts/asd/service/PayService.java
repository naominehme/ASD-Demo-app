package com.uts.asd.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.uts.asd.entity.Deposit;
import com.uts.asd.entity.Increment;
import com.uts.asd.entity.Transaction;
import com.uts.asd.entity.User;
import com.uts.asd.mapper.DepositRepository;
import com.uts.asd.mapper.TransactionRepository;
import com.uts.asd.mapper.UserRepository;
import com.uts.asd.util.JsonUtil;

@Service
public class PayService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private DepositRepository depositRepository;
	
	public boolean pay(User user,double money,Integer pid) {
		Gson gson = new Gson();
		User u = userRepository.searchById(user);
		double nop = u.getBalance() - money;
		if (nop>=0) {
			u.setBalance(nop);
			userRepository.update(u);
			String json = JsonUtil.readJsonFile("src/main/resources/increment.json");
			Increment i =gson.fromJson(json, Increment.class);
			int id = i.getTransactionid();
			int did = i.getDepositid();
			transactionRepository.add(new Transaction(id,money,new Date().getTime(),u.getId(),"Pay Deposit"));
			depositRepository.add(new Deposit(did,u.getId(),pid));
			i.setTransactionid(id+1);
			i.setDepositid(did+1);
			String a2 = gson.toJson(i);
			JsonUtil.writeJsonFile(a2, "src/main/resources/increment.json");
			return true;
		}else {
			return false;
		}
	}
	
	public void topUp(User user,double money) {
		Gson gson = new Gson();
		User u = userRepository.searchById(user);
		u.setBalance(u.getBalance()+money);
		userRepository.update(u);
		String json = JsonUtil.readJsonFile("src/main/resources/increment.json");
		Increment i =gson.fromJson(json, Increment.class);
		int id = i.getTransactionid();
		transactionRepository.add(new Transaction(id,money,new Date().getTime(),u.getId(),"Top Up"));
		i.setTransactionid(id+1);
		String a2 = gson.toJson(i);
		JsonUtil.writeJsonFile(a2, "src/main/resources/increment.json");
	}
}

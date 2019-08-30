package com.uts.asd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uts.asd.entity.Property;
import com.uts.asd.entity.User;
import com.uts.asd.mapper.BiddingMapper;
import com.uts.asd.mapper.PropertyMapper;

@Service
public class BiddingService {
	@Autowired
	private BiddingMapper biddingMapper;

	public void addBidding(double money) {
		biddingMapper.addBidding(money);
	}
	
	public void searchAllBidding(User user) {
		
	}
}

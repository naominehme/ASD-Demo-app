package com.uts.asd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uts.asd.entity.Property;
import com.uts.asd.mapper.BiddingMapper;
import com.uts.asd.mapper.PropertyMapper;

@Service
public class PropertyService {
	@Autowired
	private PropertyMapper propertyMapper;
	@Autowired
	private BiddingMapper biddingMapper;

	public void addProperty(Property p) {
		propertyMapper.addProperty(p);
	}

	public List<Property> searchAll(Property p) {
		return propertyMapper.searchAll(p);
	}

	public Property searchById(Property p) {
		return propertyMapper.searchById(p);
	}
	
	public void addBidding(double money) {
		biddingMapper.addBidding(money);
	}
}

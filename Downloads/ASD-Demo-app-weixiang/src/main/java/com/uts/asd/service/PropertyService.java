package com.uts.asd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uts.asd.entity.Property;
import com.uts.asd.mapper.BiddingMapper;
import com.uts.asd.mapper.PropertyMapper;
import com.uts.asd.mapper.PropertyRepository;

@Service
public class PropertyService {
	@Autowired
	private PropertyMapper propertyMapper;
	@Autowired
	private BiddingMapper biddingMapper;
	@Autowired
	private PropertyRepository propertyRepository;

	public void addProperty(Property p) {
		//propertyMapper.addProperty(p);
		propertyRepository.addProperty(p);
	}

	public List<Property> searchAll(Property p) {
		return propertyRepository.searchAll(p);
	}
	
	public List<Property> searchAll(Property p,Integer num) {
		return propertyMapper.searchAll(p);
	}

	public Property searchById(Property p) {
		//return propertyMapper.searchById(p);
		return propertyRepository.searchById(p);
	}
	
	public void addBidding(double money) {
		biddingMapper.addBidding(money);
	}
}

package com.uts.asd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.fabiomaffioletti.firebase.repository.Filter;
import com.github.fabiomaffioletti.firebase.repository.Filter.FilterBuilder;
import com.uts.asd.entity.CustomerServiceEntity;
import com.uts.asd.entity.Property;
import com.uts.asd.mapper.BiddingMapper;
import com.uts.asd.mapper.CustomerServiceRepository;
import com.uts.asd.mapper.PropertyMapper;
import com.uts.asd.mapper.PropertyRepository;
import com.uts.asd.util.JsonUtil;

@Service
public class CS_Service {

	@Autowired
	private CustomerServiceRepository customerServiceRepository;

	public void addProblem(CustomerServiceEntity c) {
		customerServiceRepository.addProblem(c);
	}
	
}

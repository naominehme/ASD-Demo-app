package com.uts.asd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.fabiomaffioletti.firebase.repository.Filter;
import com.github.fabiomaffioletti.firebase.repository.Filter.FilterBuilder;
import com.uts.asd.entity.CS;
import com.uts.asd.entity.Property;
import com.uts.asd.mapper.BiddingMapper;
import com.uts.asd.mapper.CS_Repository;
import com.uts.asd.mapper.PropertyMapper;
import com.uts.asd.mapper.PropertyRepository;
import com.uts.asd.util.JsonUtil;

@Service
public class CS_Service {

	@Autowired
	private CS_Repository cs_Repository;

	public void addProblem(CS cs) {
		cs_Repository.addProblem(cs);
	}
	

	public List<CS> showAll(CS cs) throws Exception {
		Filter filter =  FilterBuilder.builder().build();
		return cs_Repository.showAll(cs,filter);
	}
	

	
}
 

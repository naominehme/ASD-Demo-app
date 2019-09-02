package com.uts.asd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.fabiomaffioletti.firebase.repository.Filter;
import com.github.fabiomaffioletti.firebase.repository.Filter.FilterBuilder;
import com.uts.asd.entity.Auction;
import com.uts.asd.entity.Property;
import com.uts.asd.mapper.ActionRepository;

@Service
public class ActionService {
	@Autowired
	private ActionRepository actionRepository;

	public void addAction(Auction a) {
		actionRepository.addAction(a);
	}

	public List<Auction> searchAll(Property p) {
		return actionRepository.searchAll(p);
	}
	
	public List<Auction> searchCondition(Property p) {
		Filter filter =  FilterBuilder.builder().orderBy("pid").equalTo(p.getId()).build();
		return actionRepository.searchCondition(filter,p);
	}
	
	public Auction searchById(Auction a) {
		//return propertyMapper.searchById(p);
		return actionRepository.searchById(a);
	}
	
}

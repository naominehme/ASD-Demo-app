package com.uts.asd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.fabiomaffioletti.firebase.repository.Filter;
import com.github.fabiomaffioletti.firebase.repository.Filter.FilterBuilder;
import com.uts.asd.entity.Auction;
import com.uts.asd.entity.Bid;
import com.uts.asd.entity.Property;
import com.uts.asd.mapper.ActionRepository;
import com.uts.asd.mapper.BidRepository;

@Service
public class BidService {
	@Autowired
	private BidRepository bidRepository;

	public void addAction(Bid b) {
		bidRepository.addBidding(b);
	}

	public List<Bid> searchAll(Bid b) {
		return bidRepository.searchAll(b);
	}
	
	public List<Bid> searchCondition(Property p) {
		Filter filter =  FilterBuilder.builder().orderBy("pid").equalTo(p.getId()).build();
		return bidRepository.searchCondition(filter,p);
	}
	
	public Bid searchById(Bid b) {
		return bidRepository.searchById(b);
	}
	
}

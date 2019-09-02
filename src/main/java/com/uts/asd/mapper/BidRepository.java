package com.uts.asd.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import com.github.fabiomaffioletti.firebase.repository.Filter;
import com.uts.asd.entity.Auction;
import com.uts.asd.entity.Bid;
import com.uts.asd.entity.Property;
@Repository
public class BidRepository extends DefaultFirebaseRealtimeDatabaseRepository<Bid, Integer> {
	
	public Bid searchById(Bid b) {
		return get(b.getId(), b);
	}
	
	public List<Bid> searchAll(Bid b) {
		return findAll(b);
	}
	
	public List<Bid> searchCondition(Filter filter,Property p) {
		return find(filter, p);
	}
	
	public void addBidding(Bid b) {
		set(b, b);
	}
	
}

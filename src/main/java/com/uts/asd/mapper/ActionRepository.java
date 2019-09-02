package com.uts.asd.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import com.github.fabiomaffioletti.firebase.repository.Filter;
import com.uts.asd.entity.Auction;
import com.uts.asd.entity.Property;
@Repository
public class ActionRepository extends DefaultFirebaseRealtimeDatabaseRepository<Auction, Integer> {
	
	public Auction searchById(Auction action) {
		return get(action.getId(), action);
	}
	
	public List<Auction> searchAll(Property p) {
		return findAll(p);
	}
	
	public List<Auction> searchCondition(Filter filter,Property p) {
		return find(filter, p);
	}
	
	public void addAction(Auction a) {
		set(a, a);
	}
	
}

package com.uts.asd.mapper;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import com.github.fabiomaffioletti.firebase.repository.Filter;
import com.uts.asd.entity.Deposit;
import com.uts.asd.entity.Property;
import com.uts.asd.entity.Transaction;
import com.uts.asd.entity.User;
@Repository
public class DepositRepository extends DefaultFirebaseRealtimeDatabaseRepository<Deposit, Integer> {
	
	public void add(Deposit deposit) {
		set(deposit,deposit);
	}
	
	public List<Deposit> searchAll(Filter filter,Property p){
		return find(filter,p);
	}
}

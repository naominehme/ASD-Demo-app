package com.uts.asd.mapper;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import com.github.fabiomaffioletti.firebase.repository.Filter;
import com.uts.asd.entity.Property;
import com.uts.asd.entity.Transaction;
import com.uts.asd.entity.User;
@Repository
public class TransactionRepository extends DefaultFirebaseRealtimeDatabaseRepository<Transaction, Integer> {
	
	public void add(Transaction transaction) {
		set(transaction,transaction);
	}
	
	public List<Transaction> searchAll(Filter filter, User user){
		return find(filter,user);
	}
}

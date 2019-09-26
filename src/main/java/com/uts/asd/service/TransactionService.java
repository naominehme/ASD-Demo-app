package com.uts.asd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.fabiomaffioletti.firebase.repository.Filter;
import com.github.fabiomaffioletti.firebase.repository.Filter.FilterBuilder;
import com.uts.asd.entity.Transaction;
import com.uts.asd.entity.User;
import com.uts.asd.mapper.TransactionRepository;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;

	public List<Transaction> searchAll(User user) {
		Filter filter = FilterBuilder.builder().orderBy("uid").equalTo(user.getId()).build();
		return transactionRepository.searchAll(filter,user);
		
	}
	
}

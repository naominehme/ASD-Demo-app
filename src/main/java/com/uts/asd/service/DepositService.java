package com.uts.asd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.fabiomaffioletti.firebase.repository.Filter;
import com.github.fabiomaffioletti.firebase.repository.Filter.FilterBuilder;
import com.uts.asd.entity.Deposit;
import com.uts.asd.entity.Property;
import com.uts.asd.entity.Transaction;
import com.uts.asd.entity.User;
import com.uts.asd.mapper.DepositRepository;
import com.uts.asd.mapper.TransactionRepository;

@Service
public class DepositService {
	@Autowired
	private DepositRepository depositRepository;
	
	public List<Deposit> searchAll(Property p){
		Filter filter =  FilterBuilder.builder().orderBy("pid").equalTo(p.getId()).build();
		return depositRepository.searchAll(filter, p);
		
	}
	
}

package com.uts.asd.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import com.github.fabiomaffioletti.firebase.repository.Filter;
import com.uts.asd.controller.CustomerServiceController;
import com.uts.asd.entity.CustomerServiceEntity;
import com.uts.asd.entity.Property;
@Repository
public class CustomerServiceRepository extends DefaultFirebaseRealtimeDatabaseRepository<CustomerServiceEntity, Integer> {
	
	public void addProblem(CustomerServiceEntity c) {
		set(c, c);
	}
	
}



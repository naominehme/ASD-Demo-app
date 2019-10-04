package com.uts.asd.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import com.github.fabiomaffioletti.firebase.repository.Filter;
import com.uts.asd.entity.CS;
@Repository
public class CS_Repository extends DefaultFirebaseRealtimeDatabaseRepository<CS, Integer> {
	
	public void addProblem(CS cs) {
		set(cs, cs);
	}
	
	public List<CS> showAll(CS cs,Filter filter) {
		return find(filter,cs);
	}
    	
}



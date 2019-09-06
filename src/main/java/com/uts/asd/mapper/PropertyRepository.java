package com.uts.asd.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import com.github.fabiomaffioletti.firebase.repository.Filter;
import com.uts.asd.entity.Property;
@Repository
public class PropertyRepository extends DefaultFirebaseRealtimeDatabaseRepository<Property, Integer> {
	
	public Property searchById(Property property) {
		return get(property.getId(), property);
	}
	
	public List<Property> searchAll(Property p,Filter filter) {
		return find(filter,p);
	}
	
	public void addProperty(Property p) {
		set(p, p);
	}
	
}

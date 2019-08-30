package com.uts.asd.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import com.uts.asd.entity.Property;
@Repository
public class PropertyRepository extends DefaultFirebaseRealtimeDatabaseRepository<Property, String> {
	
	public Property searchById(Property property) {
		return get(property.getPropertyID(), 1);
	}
	
	public List<Property> searchAll(Property p) {
		return findAll(p.getPropertyID());
	}
	
}

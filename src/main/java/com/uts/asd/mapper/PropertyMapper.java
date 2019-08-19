package com.uts.asd.mapper;

import java.util.List;

import com.uts.asd.entity.Property;

public interface PropertyMapper {
	public void addProperty(Property p);
	public List<Property> searchAll(Property p);
	public Property searchById(Property p);
}

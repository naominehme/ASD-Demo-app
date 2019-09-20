package com.uts.asd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.fabiomaffioletti.firebase.repository.Filter;
import com.github.fabiomaffioletti.firebase.repository.Filter.FilterBuilder;
import com.uts.asd.entity.Property;
import com.uts.asd.mapper.BiddingMapper;
import com.uts.asd.mapper.PropertyMapper;
import com.uts.asd.mapper.PropertyRepository;
import com.uts.asd.util.JsonUtil;

@Service
public class PropertyService {
	@Autowired
	private PropertyMapper propertyMapper;
	@Autowired
	private BiddingMapper biddingMapper;
	@Autowired
	private PropertyRepository propertyRepository;

	public void addProperty(Property p) {
		//propertyMapper.addProperty(p);
		propertyRepository.addProperty(p);
	}
	
	public void removeProperty(Property p) {
		propertyRepository.removeProperty(p);
	}

	public List<Property> searchAll(Property p) throws Exception {
		Filter filter =  null;
		if (JsonUtil.isAllFieldNull(p)) {
			filter = FilterBuilder.builder().build();
		} else {
			if (null!=p.getSuburb() && !"".equals(p.getSuburb())) {
				filter = FilterBuilder.builder().orderBy("suburb").equalTo(p.getSuburb()).build();
			}else if (null!=p.getBedroom()) {
				filter = FilterBuilder.builder().orderBy("bedroom").equalTo(p.getBedroom()).build();
			}else if(null != p.getSqm()) {
				switch (p.getSqm()) {
				case 1:
					filter = FilterBuilder.builder().orderBy("sqm").startAt(0).endAt(100).build();
					break;
				case 2:
					filter = FilterBuilder.builder().orderBy("sqm").startAt(100).endAt(300).build();
					break;
				case 3:
					filter = FilterBuilder.builder().orderBy("sqm").startAt(300).endAt(600).build();
					break;
				case 4:
					filter = FilterBuilder.builder().orderBy("sqm").startAt(600).endAt(1000).build();
					break;
				case 5:
					filter = FilterBuilder.builder().orderBy("sqm").startAt(1000).endAt(2000).build();
					break;
				default:
					break;
				}
			}else {
				filter = FilterBuilder.builder().build();
			}
		}
		
		return propertyRepository.searchAll(p,filter);
	}
	
	public Property searchById(Property p) {
		//return propertyMapper.searchById(p);
		return propertyRepository.searchById(p);
	}
	
}

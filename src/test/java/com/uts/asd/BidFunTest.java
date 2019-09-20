package com.uts.asd;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uts.asd.entity.Bid;
import com.uts.asd.entity.Property;
import com.uts.asd.service.BidService;
import com.uts.asd.service.PropertyService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidFunTest {
	@Autowired
	private BidService bidService;
	@Autowired
	private PropertyService propertyService;
	
	@Test
	public void test1() {
		List<Bid> list =  bidService.searchCondition(new Property(1));
		Assert.assertNotEquals("[]", list.toString());
	}
	
	@Test
	public void test2() {
		List<Bid> list =  bidService.searchCondition(new Property());
		Assert.assertEquals("[]", list.toString());
	}
	
	@Test
	public void test3() {
		Property p = propertyService.searchById(new Property(100));
		Assert.assertEquals(null, p);
	}
	
	@Test
	public void test4() {
		Property p = propertyService.searchById(new Property(1));
		Assert.assertNotEquals(null, p);
	}
}

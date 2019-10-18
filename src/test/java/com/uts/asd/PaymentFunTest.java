package com.uts.asd;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uts.asd.entity.Bid;
import com.uts.asd.entity.Property;
import com.uts.asd.entity.Transaction;
import com.uts.asd.entity.User;
import com.uts.asd.service.BidService;
import com.uts.asd.service.PayService;
import com.uts.asd.service.PropertyService;
import com.uts.asd.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentFunTest {
	@Autowired
	private PayService payService;
	@Autowired
	private TransactionService transactionService;
	
	@Test
	public void test11() {
		List<Transaction> list = transactionService.searchAll(new User("-LqBYSFb-tM8SmNb916"));
		Assert.assertEquals("[]", list.toString());
	}
	
}

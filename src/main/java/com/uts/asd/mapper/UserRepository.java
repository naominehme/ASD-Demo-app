package com.uts.asd.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import com.github.fabiomaffioletti.firebase.repository.Filter;
import com.uts.asd.entity.Auction;
import com.uts.asd.entity.Bid;
import com.uts.asd.entity.Property;
import com.uts.asd.entity.User;
@Repository
public class UserRepository extends DefaultFirebaseRealtimeDatabaseRepository<User, String> {
	
	public User searchById(User user) {
		return get(user.getId(), user);
	}
	
	public void update(User user) {
		update(user,user);
	}
}

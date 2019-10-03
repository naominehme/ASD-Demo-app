package com.uts.asd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uts.asd.entity.User;
import com.uts.asd.mapper.UserMapper;
import com.uts.asd.mapper.UserRepository;

/*
 * @author Weixiang Gao
 */

@Service
public class UserService {
	@Autowired
	private static UserMapper userMapper;
	@Autowired
	private UserRepository userRepository; 

	public static void register(User user) {
		userMapper.register(user);
	}
	
	public User searchById(User user) {
		return userRepository.searchById(user);
	}
	
	public void updateEmail(User user) {
		User u = userRepository.searchById(user);
		u.setEmail(user.getEmail());
		userRepository.update(u);
	}
}

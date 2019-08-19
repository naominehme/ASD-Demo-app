package com.uts.asd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uts.asd.entity.User;
import com.uts.asd.mapper.UserMapper;

/*
 * @author Weixiang Gao
 */

@Service
public class UserService {
	@Autowired
	private static UserMapper userMapper;

	public static void register(User user) {
		userMapper.register(user);
	}
}

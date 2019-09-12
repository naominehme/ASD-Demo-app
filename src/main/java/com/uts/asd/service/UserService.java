package com.uts.asd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uts.asd.mapper.UserRepository;
import com.uts.asd.entity.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean verifyUser(User user) {

        if (userRepository.findByNameAndPassword(user.getName(), user.getPassword()).isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public String registerUser(User user) {

        if (userRepository.findByName(user.getName()).isEmpty()) {
            userRepository.save(user);
            return "Sign Up Succeed";

        } else {

            return "Username " + user.getName() + "already exits";
        }

    }
}
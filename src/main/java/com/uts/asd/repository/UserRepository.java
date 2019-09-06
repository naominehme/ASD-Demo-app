package com.uts.asd.repository;

import org.springframework.data.jpa.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uts.asd.mapper.UserMapper;
/*
 * @author Weixiang Gao
 */
@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {

}
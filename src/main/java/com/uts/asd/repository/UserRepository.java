package com.uts.asd.repository;

import org.springframework.data.jpa.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codebyamir.model.User;
/*
 * @author Harold Seefeld
 */
@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {

}
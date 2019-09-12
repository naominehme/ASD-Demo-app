package com.uts.asd.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uts.asd.entity.User;

public interface UserRepository extends JpaRepository<User, int> {

    public List<User> findByName(String name);
    
    public List<User> findByNameAndPassword(String name,String password);

    @Query("from User u where u.name=:name")
    public List<User> findUser(@Param("name") String name);

}
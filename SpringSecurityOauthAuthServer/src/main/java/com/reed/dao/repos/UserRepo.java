package com.reed.dao.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reed.dao.entities.User;

public interface UserRepo extends JpaRepository<User, Long>{

	public User findByUserName(String user);
	
}

package com.reed.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reed.dao.entities.Role;
import com.reed.dao.entities.User;
import com.reed.dao.repos.UserRepo;

@Service
public class MyRestService {

	private static Logger LOGGER = LoggerFactory.getLogger(MyRestService.class);
	@Autowired
	UserRepo userRepo;
	
	public User saveUser(User user)
	{
		LOGGER.info("About to save user and roles from service call "+user);
		
		
		List<Role> allRoles = user.getRoles();
		user.setRoles(null);
		
		allRoles.forEach(e->e.setUser(user));
		
		user.setRoles(allRoles);
		
		return userRepo.save(user);
	}

	public List<User> getUser() {
		return userRepo.findAll();
	}
	
}

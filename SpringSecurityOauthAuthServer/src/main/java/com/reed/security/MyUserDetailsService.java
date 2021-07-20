package com.reed.security;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.reed.dao.entities.Role;
import com.reed.dao.entities.User;
import com.reed.dao.repos.RoleRepo;
import com.reed.dao.repos.UserRepo;

@Component
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	RoleRepo roleRepo;
	
private static Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		
		User user = userRepo.findByUserName(username);
		long userId = user.getUserId();
		ArrayList<Role> allRoles = (ArrayList<Role>) roleRepo.findByUserId(userId);
		
		if(user==null)
			return null;
		MyUserDetails details = new MyUserDetails(user);
		details.setRoles(allRoles);
		
		LOGGER.info("About to search user for authentication.....");
		
		return details;
		
	}

}

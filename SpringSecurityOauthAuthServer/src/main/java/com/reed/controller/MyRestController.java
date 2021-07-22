package com.reed.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reed.dao.entities.User;
import com.reed.service.MyRestService;

@RestController
public class MyRestController {

	@Autowired
	MyRestService myRestService;
	private static Logger LOGGER = LoggerFactory.getLogger(MyRestController.class);
	
	@PostMapping("/users")
	public ResponseEntity<Object> saveUser(@RequestBody User user)
	{
		LOGGER.info("About to save user and roles "+user);
		User saveUser = myRestService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
	}
	
	@GetMapping("/hello")
	public ResponseEntity<Object> hello()
	{
		LOGGER.info("About to hello you....");
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Hello ........");
	}
	
	@GetMapping("/welcome")
	public ResponseEntity<Object> welcome()
	{
		LOGGER.info("About to welcome you....");
	
		return ResponseEntity.status(HttpStatus.CREATED).body("Hello from REST");
	}
	
}

package com.reed.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reed.dao.entities.User;
import com.reed.data.AuthenticateUser;
import com.reed.security.MyUserDetailsService;
import com.reed.service.MyRestService;
import com.reed.util.JwtUtil;

@RestController
public class MyRestController {

	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	MyUserDetailsService myUserdetailsService;
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
	
	@GetMapping("/oauth/users")
	public ResponseEntity<Object> getUser()
	{
		LOGGER.info("About to get user and roles ");
	
		return ResponseEntity.status(HttpStatus.CREATED).body(myRestService.getUser());
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
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> getJwtToken(@RequestBody AuthenticateUser authenticateUser) {
		System.out.println(authenticateUser);	
		try
		{
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticateUser.getUserName(),
					authenticateUser.getPassword()));
			UserDetails principal = (UserDetails) authenticate.getPrincipal();
			
		//	final UserDetails userDetails = myUserdetailsService.loadUserByUsername(authenticateUser.getUserName());
			final String jwt = jwtUtil.generateToken(principal);
			return ResponseEntity.status(HttpStatus.CREATED).body(jwt);
		}
		catch(BadCredentialsException badCredentialsException)
		{
			System.out.println("Bad creds....");
		}
		
		return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
	}
	
}

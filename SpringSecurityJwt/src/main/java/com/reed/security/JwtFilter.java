package com.reed.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.reed.util.JwtUtil;
@Component
public class JwtFilter extends OncePerRequestFilter{
	
	
	private static Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	MyUserDetailsService myUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {	
		String authorization = request.getHeader("Authorization");
		LOGGER.info("Authorization server - authorization header :: "+authorization);
		String jwt = "";
		String user = "";
		if(authorization !=null && authorization.startsWith("Bearer "))
		{
			jwt = authorization.substring(7);
			user = jwtUtil.getUsernameFromToken(jwt);
			LOGGER.info("About to get user from jwt :: "+authorization);	
		}
		if(!user.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null)
		{
		UserDetails userDetails =	myUserDetailsService.loadUserByUsername(user);
		if(jwtUtil.validateToken(jwt, userDetails))
		{
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
					new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
		}
		filterChain.doFilter(request, response);	
	}

}

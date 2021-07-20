package com.reed.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.reed.dao.entities.Role;
import com.reed.dao.entities.User;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public  class MyUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Role> roles = new ArrayList<Role>();

	private User user;
	
	public MyUserDetails(User user)
	{
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		ArrayList<SimpleGrantedAuthority> allRole = new ArrayList<SimpleGrantedAuthority>();
		
		for(Role role : roles)
		{
			
			allRole.add(new SimpleGrantedAuthority(role.getRole()));
			
		}
		
		return allRole;
	}
	
	

	@Override
	public String getPassword() {
	return user.getPassword();
	}

	@Override
	public String getUsername() {
	return user.getPassword();
	}

	@Override
	public boolean isAccountNonExpired() {
	return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

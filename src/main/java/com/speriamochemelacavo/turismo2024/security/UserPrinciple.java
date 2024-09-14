package com.speriamochemelacavo.turismo2024.security;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.models.users.User;

@Component
public class UserPrinciple implements UserDetails{

	@Autowired
	private User user;
	
	public UserPrinciple () {
	}
	
	public UserPrinciple (User user) {
		this.user = user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Role getRole() {
		return user.getRole();
	}
	
	public String getCAP() {
		return this.user.getCAP();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collections.singleton(user.getRole().toString().toUpperCase());
		return null;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

}

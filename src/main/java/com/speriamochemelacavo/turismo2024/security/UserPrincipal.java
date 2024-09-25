package com.speriamochemelacavo.turismo2024.security;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.models.users.User;

@Component
public class UserPrincipal extends User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4271315388868186740L;

	public UserPrincipal() {
		super();
	}
	
	public UserPrincipal(String username, String password, Role role) {
		super(username, password, role);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(getRole());
	}
}

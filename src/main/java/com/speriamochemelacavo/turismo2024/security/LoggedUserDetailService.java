package com.speriamochemelacavo.turismo2024.security;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.services.UsersService;

@Service
@Primary
public class LoggedUserDetailService extends UsersService implements UserDetailsService{
    
	//TODO da togliere - serve solamente per far vedere il bottone carica utenti all'interno del div menù
	private boolean isLoaded = false;

	//TODO da togliere - serve solamente per far vedere il bottone carica utenti all'interno del div menù
	public boolean isLoaded() {
		return isLoaded;
	}
	
	//TODO da togliere - serve solamente per far vedere il bottone carica utenti all'interno del div menù
	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}
	
	public User getLoggedUser() {
		if (isLogged()) {
			try {
				return findByUserName(((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
			} catch (SQLIntegrityConstraintViolationException e) {
				e.printStackTrace();
			}
		}
		
		return new User();
	}
	
	public boolean isLogged() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth.getPrincipal() == null ||
				auth.getPrincipal() == "anonymousUser" ||
				!(auth.getPrincipal() instanceof UserPrincipal) ||
				((UserPrincipal) auth.getPrincipal()).getUsername() == null) {
			return false;
		}
		 return true;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user;
		
		try {
			user = findByUserName(username);
			UserPrincipal userPrinciple = new UserPrincipal(user.getUsername(), user.getPassword(), user.getRole());
			return userPrinciple;
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			return new UserPrincipal("Tourist", "", Role.ROLE_TOURIST);
		}
	}
	
}

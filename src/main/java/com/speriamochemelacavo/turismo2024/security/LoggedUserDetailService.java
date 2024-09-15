package com.speriamochemelacavo.turismo2024.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
		return findByUserName(((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
	}
	
	public boolean isLogged() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth == null || auth instanceof AnonymousAuthenticationToken || auth.getPrincipal() == "anonymousUser") {
			return false;
		}
		 return true;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUserName(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("utente non trovato");
		}
		
		UserPrincipal userPrinciple = new UserPrincipal(user.getUsername(), user.getPassword(), user.getRole());
		return userPrinciple;
	}
	
}

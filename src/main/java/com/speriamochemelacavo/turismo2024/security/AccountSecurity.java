package com.speriamochemelacavo.turismo2024.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.services.UsersService;

@Service
public class AccountSecurity implements UserDetailsService{

    private Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	private boolean isLoaded;
	
	@Autowired
	private UsersService userService;

	public boolean isLogged() {
		boolean isLogged;
		if (auth != null && auth.isAuthenticated()) {
			isLogged = true;
		} else {
			isLogged = false;
		}
		return isLogged;
	}
	
	public String getLoggedUserName() {
		 if (auth != null && auth.getPrincipal() instanceof UserDetails) {
	            UserDetails userDetails = (UserDetails) auth.getPrincipal();
	            return userDetails.getUsername();
		 }
		return "Utente non autenticato!";
	}

	public boolean isLoaded() {
		return isLoaded;
	}

	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		com.speriamochemelacavo.turismo2024.models.users.User user = userService.findByUserName(userName);
		if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
	    return User.builder()
        		.username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
	}	
}

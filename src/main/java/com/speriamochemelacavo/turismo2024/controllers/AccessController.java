package com.speriamochemelacavo.turismo2024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.services.UsersService;

@RestController
@RequestMapping
public class AccessController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsersService accountService;
	
	
	@PostMapping("/registration")
	public RedirectView registerUser(@ModelAttribute User newUser) {
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		newUser.setRole(Role.ROLE_AUTHENTICATED_TOURIST);
		accountService.addUser(newUser);
		return new RedirectView("/login");
	}
}

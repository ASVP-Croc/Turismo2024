package com.speriamochemelacavo.turismo2024.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.speriamochemelacavo.turismo2024.models.users.AuthenticatedUser;
import com.speriamochemelacavo.turismo2024.services.AccountsManager;

@RestController
public class HomeController {
	
	@Autowired
	AccountsManager accountManager;
	
	@GetMapping("/")
	public String welcome() {
		return "Benvenuto in TURISMO2024!";
	}
	
	@GetMapping("/users")
	public List<AuthenticatedUser> getUsers(){
		return accountManager.findAll();
	}
	
	@GetMapping("/user/{id}")
	public AuthenticatedUser getUserById(@PathVariable int id){
		return accountManager.findById(id);
	}

}

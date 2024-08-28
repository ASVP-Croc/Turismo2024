package com.speriamochemelacavo.turismo2024.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.speriamochemelacavo.turismo2024.models.users.AuthenticatedUser;
import com.speriamochemelacavo.turismo2024.services.AccountsService;

@RestController
public class UserController {

	@Autowired
	AccountsService accountManager;
	
	
	@GetMapping("/users")
	public List<AuthenticatedUser> getUsers(){
		return accountManager.findAll();
	}
	
	@GetMapping("/user/{id}")
	public AuthenticatedUser getUserById(@PathVariable int id){
		return accountManager.findById(id);
	}
	
	@PostMapping("/user/registration")
	public void registerUser(@RequestBody AuthenticatedUser newUser) {
		System.out.println(accountManager.userToString(newUser));
		accountManager.addUser(newUser);
		}
	
	@DeleteMapping("/user/delete/{id}")
	public void deleteUserById(@PathVariable int id) {
		accountManager.deleteUserById(id);
		}
}

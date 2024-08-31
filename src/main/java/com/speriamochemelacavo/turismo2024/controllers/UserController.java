package com.speriamochemelacavo.turismo2024.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.services.AccountsService;

@RestController
public class UserController {
	
	@Autowired
	AccountsService accountService;
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return accountService.findAll();
	}
	
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable int id){
		return accountService.findById(id);
	}
	
	@PostMapping("/user/registration")
	public void registerUser(@RequestBody User newUser) {
		accountService.addUser(newUser);
	}
	

	@PutMapping("/user/update")
	public void updateUser(@RequestBody User newUser) {
		accountService.updateUser(newUser);
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUserById(@PathVariable int id) {
		accountService.deleteUserById(id);
	}
}

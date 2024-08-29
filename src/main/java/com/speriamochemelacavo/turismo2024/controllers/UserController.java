package com.speriamochemelacavo.turismo2024.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.speriamochemelacavo.turismo2024.models.users.AuthenticatedUser;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.services.AccountsService;

@RestController
public class UserController {

	private static boolean isCreatedUsers = false;
	
	@Autowired
	AccountsService accountService;
	
	@GetMapping("/iniziaDb")
	public void insertInitialUsers(){
		if (!isCreatedUsers) {
			List<AuthenticatedUser> initialUsers = new ArrayList<>();
			initialUsers.add(new AuthenticatedUser(101, "Matteo", "Pallotti", "Maverick", "maverick@gmail.com", "3929217858", Role.Administrator));
			initialUsers.add(new AuthenticatedUser(102, "Lorenzo", "Crovace", "AVCP", "avcp@gmail.com", "123456789", Role.Curator));
			initialUsers.add(new AuthenticatedUser(103, "Simone", "Silver", "SimonSilver", "simon@gmail.com", "987654321", Role.Animator));
			accountService.addUsers(initialUsers);
			isCreatedUsers = true;
			}
	}
	
	@GetMapping("/users")
	public List<AuthenticatedUser> getUsers(){
		return accountService.findAll();
	}
	

	@GetMapping("/user/login/{id}")
	public void loginById(@PathVariable int id){
		accountService.saveLoggedUser(id);
	}
	
	@GetMapping("/user/{id}")
	public AuthenticatedUser getUserById(@PathVariable int id){
		return accountService.findById(id);
	}
	
	@PostMapping("/user/registration")
	public void registerUser(@RequestBody AuthenticatedUser newUser) {
		accountService.addUser(newUser);
	}
	

	@PutMapping("/user/update")
	public void updateUser(@RequestBody AuthenticatedUser newUser) {
		accountService.updateUser(newUser);
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUserById(@PathVariable int id) {
		accountService.deleteUserById(id);
	}
}

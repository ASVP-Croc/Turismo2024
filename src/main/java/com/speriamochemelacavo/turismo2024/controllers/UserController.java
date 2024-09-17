package com.speriamochemelacavo.turismo2024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.services.UsersService;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping
public class UserController {

	@Autowired
	private UsersService usersService;
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable int id){
		return usersService.findById(id);
	}

	@PostMapping("/add")
	public RedirectView addUser(User userToAdd){
		usersService.addUser(userToAdd);
		return new RedirectView("/users");
	}
	
	@PutMapping("/users/update")
	public void updateUser(@RequestBody User userToUpdate) {
		usersService.updateUser(userToUpdate);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		usersService.deleteUserById(id);
	}
}

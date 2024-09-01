package com.speriamochemelacavo.turismo2024.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.thymeleaf.spring6.view.ThymeleafView;

import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.services.AccountsService;

@RestController
public class UserController {
	
	@Autowired
	AccountsService accountService;
	
	@Autowired
	PageController pageController;
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return accountService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable int id){
		return accountService.findById(id);
	}

	@PostMapping("/registration")
	public RedirectView registerUser(User newUser) {
		newUser.setRole(Role.AuthenticatedTourist);
		accountService.addUser(newUser);
		accountService.saveLoggedUser(newUser.getUserName());
		return new RedirectView("/");
	}
	
	@PutMapping("/users/update")
	public void updateUser(@RequestBody User newUser) {
		accountService.updateUser(newUser);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		accountService.deleteUserById(id);
	}
}

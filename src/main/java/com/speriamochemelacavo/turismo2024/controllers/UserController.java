package com.speriamochemelacavo.turismo2024.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.category.ElementTypology;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.services.UsersService;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ModelSetter modelSetter;
	
	@GetMapping("")
	public RedirectView getAllUsers(){
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("listUsers", usersService.findAll());
		return new RedirectView("/users/list");
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id){
		return usersService.findById(id);
	}

	@PostMapping("/add")
	public RedirectView addUser(User userToAdd){
		usersService.addUser(userToAdd);
		return new RedirectView("/users");
	}
	
	@PutMapping("/update")
	public void updateUser(@RequestBody User userToUpdate) {
		usersService.addUser(userToUpdate);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable int id) {
		usersService.deleteUserById(id);
	}
}

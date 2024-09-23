package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.exception.UserNotFoundException;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.services.UsersService;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.file.AccessDeniedException;

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
	
//	@GetMapping("/{id}")
//	public User getUserById(@PathVariable int id){
//		return usersService.findById(id);
//	}

	@PostMapping("/add")
	public RedirectView addUser(User userToAdd){
		usersService.add(userToAdd);
		return new RedirectView("/users");
	}
	
	@PutMapping("/update/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User userToUpdate) throws UserNotFoundException {
		return usersService.update(id, userToUpdate);
	}

	@PutMapping("/update/{id}/role")
	public RedirectView updateUserRole(@PathVariable int id, @AuthenticationPrincipal User admin, @RequestBody Role newRole) throws UserNotFoundException, AccessDeniedException {
		User updateUser = usersService.updateUserRole(id, admin, newRole);
		return new RedirectView("/");
	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable int id) {
		usersService.delete(id);
	}
}

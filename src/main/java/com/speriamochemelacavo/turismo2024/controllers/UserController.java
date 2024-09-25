package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.models.users.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.UsersService;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private LoggedUserDetailService loggedUserDetailService;
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private ModelSetter modelSetter;
	
	@GetMapping("")
	public RedirectView getAllUsers(){
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("listUsers", userService.findAll());
		return new RedirectView("/user/list");
	}
	

	@GetMapping("/manager")
	public RedirectView getUserManager() {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("user", loggedUserDetailService.getLoggedUser());
		return new RedirectView("/user/manager");
	}
	
	@GetMapping("/find")
	public RedirectView getUserById(@RequestParam String username){
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		if (loggedUserDetailService.getLoggedUser().getUsername() == username ||
				loggedUserDetailService.getLoggedUser().getRole() == Role.ROLE_ADMINISTRATOR) {
			User toReturn;
			try {
				toReturn = userService.findByUserName(username);
				modelSetter.getAttributes().put("user", toReturn);
			} catch (SQLIntegrityConstraintViolationException e) {
				e.printStackTrace();
				modelSetter.getAttributes().put("userFound", false);
				modelSetter.getAttributes().put("message", e.getMessage());
			}
		} else {
			modelSetter.getAttributes().put("user", loggedUserDetailService.getLoggedUser());
		}
		return new RedirectView("/user/manager");
	}

	@PostMapping("/add")
	public RedirectView addUser(@ModelAttribute User userToAdd){
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		System.out.println(userToAdd.toString());
		if (loggedUserDetailService.getLoggedUser().getUsername().equals(userToAdd.getUsername()) ||
				loggedUserDetailService.getLoggedUser().getRole() == Role.ROLE_ADMINISTRATOR) {
			User toModify;
			try {
				toModify = userService.findByUserName(userToAdd.getUsername());
				userToAdd.setPassword(toModify.getPassword());
				userService.add(userToAdd);
				modelSetter.getAttributes().put("user", userToAdd);
				System.out.println(userToAdd.toString());
			} catch (SQLIntegrityConstraintViolationException e) {
				e.printStackTrace();
				modelSetter.getAttributes().put("userFound", false);
				modelSetter.getAttributes().put("message", e.getMessage());
			}
		} else {
			modelSetter.getAttributes().put("user", loggedUserDetailService.getLoggedUser());
		}
		return new RedirectView("/user/manager");
	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable int id) {
		userService.delete(id);
	}
}

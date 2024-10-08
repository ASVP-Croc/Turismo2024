package com.speriamochemelacavo.turismo2024.controllers;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.services.UsersService;

@Controller
@RequestMapping("access")
public class AccessController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsersService userService;

	@Autowired
	private ModelSetter modelSetter;

	@GetMapping("/login")
	public String login(Model model) {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.setAttributesInModel(model);
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.setAttributesInModel(model);
		return "logout";
	}
	
	@GetMapping("/registration")
	public String getRegistrationPage(Model model) {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.setAttributesInModel(model);
		return "registration";
	}

	@PostMapping("/registration")
	public RedirectView registerUser(Model model, @ModelAttribute User newUser) {
		try {
			userService.findByUserName(newUser.getUsername());
			return new RedirectView("/access/registration?registration=true");
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			modelSetter.clearAllAttributes();
			modelSetter.setBaseVisibility();
			modelSetter.setAttributesInModel(model);
			newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
			newUser.setRole(Role.ROLE_AUTHENTICATED_TOURIST);
			userService.add(newUser);
			return new RedirectView("/access/login?registration=true");
		}
	}
}

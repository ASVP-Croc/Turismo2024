package com.speriamochemelacavo.turismo2024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.security.AccountSecurity;
import com.speriamochemelacavo.turismo2024.services.UsersService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping
public class AccessController {
	
	@Autowired
	AccountSecurity accountSecurity;
	
	@Autowired
	ModelSetter modelSetter;

//	@PostMapping("/login")
//	public void loginByUserName(String userName, String password){
//
//	}
	
//	@GetMapping("/logout")
//	public RedirectView logout(Model model) {
//		return new RedirectView("/");
//	}
}

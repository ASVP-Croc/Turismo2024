package com.speriamochemelacavo.turismo2024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;

import com.speriamochemelacavo.turismo2024.security.AccountSecurity;

@RestController
@RequestMapping
public class AccessController {
	
	@Autowired
	AccountSecurity accountSecurity;
	
	@Autowired
	ModelSetter modelSetter;

	@PostMapping("/login/send")
	public void loginByUserName(@RequestParam String userName,@RequestParam String password){
		System.out.println("prova");
	}
	
//	@GetMapping("/logout")
//	public RedirectView logout(Model model) {
//		return new RedirectView("/");
//	}
}

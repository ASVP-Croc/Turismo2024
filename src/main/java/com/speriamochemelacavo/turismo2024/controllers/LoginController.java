package com.speriamochemelacavo.turismo2024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.speriamochemelacavo.turismo2024.services.AccountsService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	AccountsService accountService;
	
	@GetMapping("/send")
	public void loginById(@RequestParam(value = "myInput", defaultValue = "101") int id){
		accountService.saveLoggedUser(id);
		System.out.println(accountService.userToString(accountService.getLoggedUser()));
	}
}

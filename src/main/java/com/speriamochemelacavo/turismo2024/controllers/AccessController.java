package com.speriamochemelacavo.turismo2024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.speriamochemelacavo.turismo2024.services.AccountsService;

@RestController
@RequestMapping
public class AccessController {
	
	@Autowired
	AccountsService accountService;
	
	@PostMapping("/login/send")
	public RedirectView loginByUserName(String userName){
		accountService.saveLoggedUser(userName);
        return new RedirectView("/");
	}
}

package com.speriamochemelacavo.turismo2024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.speriamochemelacavo.turismo2024.services.AccountsService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping
public class AccessController {
	
	@Autowired
	AccountsService accountService;
	
	@PostMapping("/login/send")
	public RedirectView loginByUserName(String userName, HttpSession session){
		if (accountService.findByUserName(userName) != null) {
			accountService.saveLoggedUser(userName);
			session.setAttribute("userName", userName);
			return new RedirectView("/");
		}
        return new RedirectView("/login");
	}
}

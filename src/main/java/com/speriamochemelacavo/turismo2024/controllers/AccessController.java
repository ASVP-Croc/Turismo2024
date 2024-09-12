package com.speriamochemelacavo.turismo2024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.services.UsersService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping
public class AccessController {
	
	@Autowired
	UsersService accountService;
	
	@PostMapping("/login/send")
	public RedirectView loginByUserName(String userName, HttpSession session){
		User toLogin = accountService.findByUserName(userName);
		if (toLogin != null) {
			session.setAttribute("userId", String.valueOf(toLogin.getId()));
			return new RedirectView("/");
		}
        return new RedirectView("/login");
	}
}

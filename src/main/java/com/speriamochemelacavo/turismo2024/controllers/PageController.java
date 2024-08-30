package com.speriamochemelacavo.turismo2024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speriamochemelacavo.turismo2024.services.AccountsService;

@Controller
public class PageController {
	
	@Autowired
	AccountsService accountService;
	
	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("nameUser", accountService.getLoggedUser().getName().isBlank() ? "Turista" : accountService.getLoggedUser().getName());
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
	 return "login";
	}

}

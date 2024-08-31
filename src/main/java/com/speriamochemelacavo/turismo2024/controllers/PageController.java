package com.speriamochemelacavo.turismo2024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speriamochemelacavo.turismo2024.services.AccountsService;

@Controller
public class PageController {

	@Autowired
	AccountsService accountService;

	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("nameUser",
				!accountService.isLogged() ? "Turista" : accountService.getLoggedUser().getName());
		return "index";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("nameUser",
				!accountService.isLogged() ? "Turista" : accountService.getLoggedUser().getName());
		return "login";
	}

	@RequestMapping("/pois")
	public String getPois() {
		return "poi-list";
	}

	@RequestMapping("/registration")
	public String userRegistration(Model model) {
		model.addAttribute("nameUser",
				!accountService.isLogged() ? "Turista" : accountService.getLoggedUser().getName());
		return "registration";
	}
}

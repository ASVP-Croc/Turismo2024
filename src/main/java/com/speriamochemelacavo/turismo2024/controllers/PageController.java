package com.speriamochemelacavo.turismo2024.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speriamochemelacavo.turismo2024.services.AccountsService;
import com.speriamochemelacavo.turismo2024.services.POIsService;

@Controller
public class PageController {

	@Autowired
	AccountsService accountService;
	
	@Autowired
	POIsService poiService;

	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("nameUser",
				!accountService.isLogged() ? "Turista" : accountService.getLoggedUser().getName());
		model.addAttribute("isLogged", accountService.isLogged());
		model.addAttribute("isLoadedUsers", accountService.isLoaded());
		model.addAttribute("isLoadedPois", poiService.isLoaded());
		boolean isVisible = (accountService.isLoaded()&!poiService.isLoaded());
		model.addAttribute("isVisible", isVisible);
		return "index";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("nameUser",
				!accountService.isLogged() ? "Turista" : accountService.getLoggedUser().getName());
		model.addAttribute("isLoadedUsers", accountService.isLoaded());
		model.addAttribute("isLoadedPois", poiService.isLoaded());
		boolean isVisible = (accountService.isLoaded()&!poiService.isLoaded());
		model.addAttribute("isVisible", isVisible);
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(Model model) {
		accountService.setLogged(false);
		model.addAttribute("nameUser", "Turista");
		model.addAttribute("isLoadedUsers", accountService.isLoaded());
		model.addAttribute("isLoadedPois", poiService.isLoaded());
		boolean isVisible = (accountService.isLoaded()&!poiService.isLoaded());
		model.addAttribute("isVisible", isVisible);
		return "index";
	}

	@RequestMapping("/users")
	public String getUsers(Model model) {
		model.addAttribute("nameUser",
			!accountService.isLogged() ? "Turista" : accountService.getLoggedUser().getName());
		model.addAttribute("isLogged", accountService.isLogged());
		model.addAttribute("listUser", accountService.findAll());
		model.addAttribute("isLoadedUsers", accountService.isLoaded());
		model.addAttribute("isLoadedPois", poiService.isLoaded());
		boolean isVisible = (accountService.isLoaded()&!poiService.isLoaded());
		model.addAttribute("isVisible", isVisible);
		return "users-list";
	}
	
	@RequestMapping("/pois")
	public String getPois(Model model) {
		model.addAttribute("nameUser",
			!accountService.isLogged() ? "Turista" : accountService.getLoggedUser().getName());
		model.addAttribute("isLogged", accountService.isLogged());
		model.addAttribute("listPoi", poiService.findAll());
		model.addAttribute("isLoadedUsers", accountService.isLoaded());
		boolean isVisible = (accountService.isLoaded()&!poiService.isLoaded());
		model.addAttribute("isVisible", isVisible);
		return "poi-list";
	}
	
	@RequestMapping("/registration")
	public String userRegistration(Model model) {
		model.addAttribute("nameUser",
				!accountService.isLogged() ? "Turista" : accountService.getLoggedUser().getName());
		model.addAttribute("isLoadedUsers", accountService.isLoaded());
		model.addAttribute("isLoadedPois", poiService.isLoaded());
		boolean isVisible = (accountService.isLoaded()&!poiService.isLoaded());
		model.addAttribute("isVisible", isVisible);
		return "registration";
	}
}

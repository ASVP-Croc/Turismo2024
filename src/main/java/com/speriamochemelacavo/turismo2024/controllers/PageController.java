package com.speriamochemelacavo.turismo2024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.services.AccountsService;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.POIsService;

@Controller
public class PageController {
	
	@Autowired
	private AccountsService accountService;
	
	@Autowired
	private ElementsService<Element> elementsService;

	@RequestMapping("/")
	public String welcome(Model model) {
		setConditionModelVisibility(model);
		return "index";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		setConditionModelVisibility(model);
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(Model model) {
		accountService.setLogged(false);
		setConditionModelVisibility(model);
		return "index";
	}

	@RequestMapping("/all/users")
	public String getUsers(Model model) {
		model.addAttribute("listUser", accountService.findAll());
		setConditionModelVisibility(model);
		return "users-list";
	}
	
	@RequestMapping("/all/elements")
	public String getAll(Model model) {
		model.addAttribute("listElements", elementsService.findAll());
		setConditionModelVisibility(model);
		return "elements-list";
	}
	
	@RequestMapping("/pois")
	public String getPois(Model model) {
		model.addAttribute("listPoi", elementsService.findAll());
		setConditionModelVisibility(model);
		return "poi-list";
	}
	
	@RequestMapping("/registration")
	public String userRegistration(Model model) {
		setConditionModelVisibility(model);
		return "registration";
	}
	
	private void setConditionModelVisibility(Model model) {
		model.addAttribute("nameUser",
				!accountService.isLogged() ? "Turista" : accountService.findById(accountService.getLoggedUser()).getName());
		model.addAttribute("isLogged", accountService.isLogged());
		model.addAttribute("isLoadedUsers", accountService.isLoaded());
		model.addAttribute("isLoadedPois", elementsService.isLoaded());
		boolean isPOILoadVisible = (!elementsService.isLoaded()&(accountService.isLogged()));
		model.addAttribute("isLoadedPOIs", isPOILoadVisible);
		boolean isNotifLoadVisible = (elementsService.isLoaded()&accountService.isLoaded());
		model.addAttribute("isNotifLoadVisible", isNotifLoadVisible);
		model.addAttribute("numberOfNotifications", !accountService.isLogged() ? 54 : accountService.findById(accountService.getLoggedUser()).getNotifications().size());
	}
}

package com.speriamochemelacavo.turismo2024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speriamochemelacavo.turismo2024.services.AccountsService;
import com.speriamochemelacavo.turismo2024.services.NotificationsService;
import com.speriamochemelacavo.turismo2024.services.POIsService;

@Controller
public class PageController {

	@Autowired
	private AccountsService accountService;
	
	@Autowired
	private POIsService poiService;
	
	@Autowired
	private NotificationsService notificationService;

	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("nameUser",
				!accountService.isLogged() ? "Turista" : accountService.getLoggedUser().getName());
		model.addAttribute("isLogged", accountService.isLogged());
		model.addAttribute("isLoadedUsers", accountService.isLoaded());
		model.addAttribute("isLoadedPois", poiService.isLoaded());
		boolean isPOILoadVisible = (!poiService.isLoaded()&(accountService.isLogged()));
		model.addAttribute("isVisible", isPOILoadVisible);
		boolean isNotifLoadVisible = (poiService.isLoaded()&accountService.isLoaded());
		model.addAttribute("isNotifLoadVisible", isNotifLoadVisible);
		model.addAttribute("numberOfNotifications", !accountService.isLogged() ? 54 : notificationService.findAllByRecipientId(accountService.getLoggedUser().getId()).size());
		return "index";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("nameUser",
				!accountService.isLogged() ? "Turista" : accountService.getLoggedUser().getName());
		model.addAttribute("isLoadedUsers", accountService.isLoaded());
		model.addAttribute("isLoadedPois", poiService.isLoaded());
		boolean isPOILoadVisible = (!poiService.isLoaded()&(accountService.isLogged()));
		model.addAttribute("isVisible", isPOILoadVisible);
		boolean isNotifLoadVisible = (poiService.isLoaded()&accountService.isLoaded());
		model.addAttribute("isNotifLoadVisible", isNotifLoadVisible);
		model.addAttribute("numberOfNotifications", !accountService.isLogged() ? 54 : notificationService.findAllByRecipientId(accountService.getLoggedUser().getId()).size());
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(Model model) {
		accountService.setLogged(false);
		model.addAttribute("nameUser", "Turista");
		model.addAttribute("isLoadedUsers", accountService.isLoaded());
		model.addAttribute("isLoadedPois", poiService.isLoaded());
		boolean isPOILoadVisible = (!poiService.isLoaded()&(accountService.isLogged()));
		model.addAttribute("isVisible", isPOILoadVisible);
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
		boolean isPOILoadVisible = (!poiService.isLoaded()&(accountService.isLogged()));
		model.addAttribute("isVisible", isPOILoadVisible);
		boolean isNotifLoadVisible = (poiService.isLoaded()&accountService.isLoaded());
		model.addAttribute("isNotifLoadVisible", isNotifLoadVisible);
		model.addAttribute("numberOfNotifications", !accountService.isLogged() ? 54 : notificationService.findAllByRecipientId(accountService.getLoggedUser().getId()).size());
		return "users-list";
	}
	
	@RequestMapping("/pois")
	public String getPois(Model model) {
		model.addAttribute("nameUser",
			!accountService.isLogged() ? "Turista" : accountService.getLoggedUser().getName());
		model.addAttribute("isLogged", accountService.isLogged());
		model.addAttribute("listPoi", poiService.findAll());
		model.addAttribute("isLoadedUsers", accountService.isLoaded());
		boolean isPOILoadVisible = (!poiService.isLoaded()&(accountService.isLogged()));
		model.addAttribute("isVisible", isPOILoadVisible);
		boolean isNotifLoadVisible = (poiService.isLoaded()&accountService.isLoaded());
		model.addAttribute("isNotifLoadVisible", isNotifLoadVisible);
		model.addAttribute("numberOfNotifications", !accountService.isLogged() ? 54 : notificationService.findAllByRecipientId(accountService.getLoggedUser().getId()).size());
		return "poi-list";
	}
	
	@RequestMapping("/registration")
	public String userRegistration(Model model) {
		model.addAttribute("nameUser",
				!accountService.isLogged() ? "Turista" : accountService.getLoggedUser().getName());
		model.addAttribute("isLoadedUsers", accountService.isLoaded());
		model.addAttribute("isLoadedPois", poiService.isLoaded());
		boolean isPOILoadVisible = (!poiService.isLoaded()&(accountService.isLogged()));
		model.addAttribute("isVisible", isPOILoadVisible);
		return "registration";
	}
}

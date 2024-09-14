package com.speriamochemelacavo.turismo2024.controllers.modelSetters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.speriamochemelacavo.turismo2024.services.UsersService;

import com.speriamochemelacavo.turismo2024.services.POIsService;

@Component
public class ModelSetter {
	
	@Autowired
	private UsersService accountService;
	
	public void setConditionModelVisibility(Model model) {
		model.addAttribute("username",
				accountService.isLogged() ? accountService.getUsername() : "Turista");
		model.addAttribute("isLoadedUsers", accountService.isLoaded());
		model.addAttribute("isLogged", accountService.isLogged());
		boolean isPOIButtonVisible = (accountService.isLogged() & !POIsService.isLoaded());
		model.addAttribute("isPOIButtonVisible", isPOIButtonVisible);
		model.addAttribute("numberOfNotifications", accountService.isLogged() ? accountService.findByUserName(accountService.getUsername()).getNotifications().size() : 56);
	}
}

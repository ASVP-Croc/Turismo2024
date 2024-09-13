package com.speriamochemelacavo.turismo2024.controllers.modelSetters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;


import com.speriamochemelacavo.turismo2024.security.AccountSecurity;
import com.speriamochemelacavo.turismo2024.services.UsersService;

import com.speriamochemelacavo.turismo2024.services.POIsService;

@Component
public class ModelSetter {
	
	@Autowired
	private AccountSecurity accountSecurity;
	
	@Autowired
	private UsersService accountService;
	
	public void setConditionModelVisibility(Model model) {
		model.addAttribute("nameUser",
				accountSecurity.isLogged() ? accountService.findByUserName(accountSecurity.getLoggedUserName()).getName() : "Turista");
		model.addAttribute("isLoadedUsers", accountService.isLoaded());
		model.addAttribute("isLogged", accountSecurity.isLogged());
		boolean isPOIButtonVisible = (accountSecurity.isLogged() & !POIsService.isLoaded());
		model.addAttribute("isPOIButtonVisible", isPOIButtonVisible);
		model.addAttribute("numberOfNotifications", accountSecurity.isLogged() ? accountService.findByUserName(accountSecurity.getLoggedUserName()).getNotifications().size() : 56);
	}
}

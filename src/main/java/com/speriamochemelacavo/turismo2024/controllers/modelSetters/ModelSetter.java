package com.speriamochemelacavo.turismo2024.controllers.modelSetters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.POIsService;

@Component
public class ModelSetter {
	
	@Autowired
	private LoggedUserDetailService loggedUserService;
	
	public void setConditionModelVisibility(Model model) {
		model.addAttribute("username",
				loggedUserService.isLogged() ? loggedUserService.getLoggedUser().getUsername() : "Turista");
		model.addAttribute("isLoadedUsers", loggedUserService.isLoaded());
		model.addAttribute("isLogged", loggedUserService.isLogged());
		boolean isPOIButtonVisible = (loggedUserService.isLogged() & !POIsService.isLoaded());
		model.addAttribute("isPOIButtonVisible", isPOIButtonVisible);
		model.addAttribute("numberOfNotifications", loggedUserService.isLogged() ? loggedUserService.getLoggedUser().getNotifications().size() : 56);
	}
}

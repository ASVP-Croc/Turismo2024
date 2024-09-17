package com.speriamochemelacavo.turismo2024.controllers.modelSetters;

import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.POIsService;

@Component
public class ModelSetter {
	
	@Autowired
	private LoggedUserDetailService loggedUserService;
	@Autowired
	private ElementsService<PointOfInterest> poiService;
	
	public void setConditionModelVisibility(Model model) {
		model.addAttribute("username",
				loggedUserService.isLogged() ? loggedUserService.getLoggedUser().getUsername() : "Turista");
		model.addAttribute("isLoadedUsers", loggedUserService.isLoaded());
		model.addAttribute("isLogged", loggedUserService.isLogged());
		boolean isPOIButtonVisible = (loggedUserService.isLogged() & !poiService.isLoaded());
		model.addAttribute("isPOIButtonVisible", isPOIButtonVisible);
		model.addAttribute("numberOfNotifications", loggedUserService.isLogged() ? loggedUserService.getLoggedUser().getNotifications().size() : 56);
	}
}

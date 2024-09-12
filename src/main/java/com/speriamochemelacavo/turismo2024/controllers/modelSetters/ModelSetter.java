package com.speriamochemelacavo.turismo2024.controllers.modelSetters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.services.UsersService;

import jakarta.servlet.http.HttpSession;

import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.NotificationsService;
import com.speriamochemelacavo.turismo2024.services.POIsService;

@Component
public class ModelSetter {
	
	@Autowired
	private UsersService accountService;
	
	public void setConditionModelVisibility(Model model, HttpSession session) {
		model.addAttribute("nameUser",
				!accountService.isLogged() ? "Turista" : accountService.findById(accountService.getLoggedUser()).getName());
		model.addAttribute("isLogged", session.getAttribute("userId") != null);
		model.addAttribute("isLoadedUsers", accountService.isLoaded());
		boolean isPOIButtonVisible = (accountService.isLogged() & !POIsService.isLoaded());
		model.addAttribute("isPOIButtonVisible", isPOIButtonVisible);
		model.addAttribute("numberOfNotifications", accountService.isLogged() ? accountService.findById(accountService.getLoggedUser()).getNotifications().size() : 56);
	}
}

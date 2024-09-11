package com.speriamochemelacavo.turismo2024.controllers.modelSetters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.services.UsersService;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.NotificationsService;

@Component
public class ModelSetter {
	
	@Autowired
	private UsersService accountService;
	
	@Autowired
	private NotificationsService notificationService;
	
	@Autowired
	private ElementsService<Element> elementsService;
	
	public void setConditionModelVisibility(Model model) {
		model.addAttribute("nameUser",
				!accountService.isLogged() ? "Turista" : accountService.findById(accountService.getLoggedUser()).getName());
		model.addAttribute("isLogged", accountService.isLogged());
		model.addAttribute("isLoadedUsers", accountService.isLoaded());
		boolean isPOIButtonVisible = (accountService.isLogged() & !elementsService.isLoaded());
		model.addAttribute("isPOIButtonVisible", isPOIButtonVisible);
		model.addAttribute("numberOfNotifications", notificationService.findAllByRecipientUserId(accountService.getLoggedUser()).size());
	}
}

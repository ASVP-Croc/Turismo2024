package com.speriamochemelacavo.turismo2024.controllers.modelSetters;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.TouchableDataBuffer;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.POIsService;

@Component
public class ModelSetter {
	
	@Autowired
	private LoggedUserDetailService loggedUserService;
	
	@Autowired
	private ElementsService<Element> elementService;
	
	@Autowired
	private ElementsService<PointOfInterest> poiService;
	
	@Autowired
	private ElementsService<Tour> tourService;
	
	@Autowired
	private ElementsService<Contest> contestService;
	
	@Autowired
	private ElementsService<Content> contentService;
	
	public void setConditionModelVisibility(Model model) {
		model.addAttribute("username",
				loggedUserService.isLogged() ? loggedUserService.getLoggedUser().getUsername() : "Turista");
		model.addAttribute("isLoadedUsers", loggedUserService.isLoaded());
		model.addAttribute("isLogged", loggedUserService.isLogged());
		boolean isPOIButtonVisible = (loggedUserService.isLogged() & !poiService.isLoaded());
		model.addAttribute("isPOIButtonVisible", isPOIButtonVisible);
		boolean isTourButtonVisible = (loggedUserService.isLogged() & !tourService.isLoaded());
		model.addAttribute("isTourButtonVisible", isTourButtonVisible);
		model.addAttribute("numberOfNotifications", loggedUserService.isLogged() ? loggedUserService.getLoggedUser().getNotifications().size() : 56);
	}
}

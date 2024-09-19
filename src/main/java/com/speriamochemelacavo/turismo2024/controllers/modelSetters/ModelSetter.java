package com.speriamochemelacavo.turismo2024.controllers.modelSetters;

import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.services.ElementsWithContentsService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;

@Component
public class ModelSetter {
	
	private Map<String, Object> attributes = new HashMap<>();
	
	
	@Autowired
	private LoggedUserDetailService loggedUserService;
	
//	@Autowired
//	private ElementsService<Element> elementService;
	
	@Autowired
	private ElementsWithContentsService<PointOfInterest> poiService;
	
	@Autowired
	private ElementsWithContentsService<Tour> tourService;
	
//	@Autowired
//	private ElementsWithContentsService<Contest> contestService;
//	
//	@Autowired
//	private ElementsService<Content> contentService;
	
	public ModelSetter() {
	}

	public void setBaseVisibility() {
		attributes.put("username",
				loggedUserService.isLogged() ? loggedUserService.getLoggedUser().getUsername() : "Turista");
		attributes.put("isAdmin", loggedUserService.isLogged() ? loggedUserService.getLoggedUser().getRole()==Role.ROLE_ADMINISTRATOR : false);
		attributes.put("isCurator", loggedUserService.isLogged() ? loggedUserService.getLoggedUser().getRole()==Role.ROLE_CURATOR : false);
		attributes.put("isAnimator", loggedUserService.isLogged() ? loggedUserService.getLoggedUser().getRole()==Role.ROLE_ANIMATOR : false);
		attributes.put("isContributor", loggedUserService.isLogged() ? loggedUserService.getLoggedUser().getRole()==Role.ROLE_CONTRIBUTOR : false);
		attributes.put("isAuthTourist", loggedUserService.isLogged() ? loggedUserService.getLoggedUser().getRole()==Role.ROLE_AUTHENTICATED_TOURIST : false);
		attributes.put("isTourist", !loggedUserService.isLogged());
		attributes.put("isLoadedUsers", loggedUserService.isLoaded());
		boolean isPOIButtonVisible = (loggedUserService.isLogged() & !poiService.isLoaded());
		attributes.put("isPOIButtonVisible", isPOIButtonVisible);
		boolean isTourButtonVisible = (loggedUserService.isLogged() & !tourService.isLoaded());
		attributes.put("isTourButtonVisible", isTourButtonVisible);
		attributes.put("numberOfNotifications", loggedUserService.isLogged() ? loggedUserService.getLoggedUser().getNotifications().size() : 56);
	}
	
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	public void setAttributesInModel(Model model) {
		attributes.forEach((k, v) -> model.addAttribute(k, v));
	}
	
	public void clearAllAttributes() {
		attributes.clear();
	}
}

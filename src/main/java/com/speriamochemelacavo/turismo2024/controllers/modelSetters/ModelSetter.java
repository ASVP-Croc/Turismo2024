package com.speriamochemelacavo.turismo2024.controllers.modelSetters;

import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.services.POIsService;
import com.speriamochemelacavo.turismo2024.services.ToursService;

import java.sql.SQLIntegrityConstraintViolationException;
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
	
	@Autowired
	private POIsService poiService;
	
	@Autowired
	private ToursService tourService;

	public ModelSetter() {
	}

	public void setBaseVisibility() {
		User user = loggedUserService.getLoggedUser();
		attributes.put("username", user.getUsername());
		attributes.put("isAdmin", user.getRole() == Role.ROLE_ADMINISTRATOR);
		attributes.put("isCurator", user.getRole() == Role.ROLE_CURATOR);
		attributes.put("isAnimator", user.getRole() == Role.ROLE_ANIMATOR);
		attributes.put("isContributor", user.getRole() == Role.ROLE_CONTRIBUTOR);
		attributes.put("isAuthTourist", user.getRole() == Role.ROLE_AUTHENTICATED_TOURIST);
		attributes.put("isTourist", user.getRole() == Role.ROLE_TOURIST);
		attributes.put("isLoadedUsers", loggedUserService.isLoaded());
		boolean isPOIButtonVisible = (user.getRole() != Role.ROLE_TOURIST && user.getRole() != Role.ROLE_AUTHENTICATED_TOURIST && !poiService.isLoaded());
		attributes.put("isPOIButtonVisible", isPOIButtonVisible);
		boolean isTourButtonVisible = (user.getRole() != Role.ROLE_TOURIST && user.getRole() != Role.ROLE_AUTHENTICATED_TOURIST && !tourService.isLoaded());
		attributes.put("isTourButtonVisible", isTourButtonVisible);
		attributes.put("numberOfNotifications", user.getNotifications().size());
	}
	
	public Object getAttribute(String attribute) {
		return attributes.get(attribute);
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

package com.speriamochemelacavo.turismo2024.controllers;


import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.ElementStatus;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.ElementsService;

@Controller
@RequestMapping
public class PageController {

	@Autowired
	private ElementsService<Element> elementService;
	
	@Autowired
	private LoggedUserDetailService loggedUserDetailService;
	
	@Autowired
	private ModelSetter modelSetter;
	
	public PageController() {
	}
	
	@GetMapping("/")
	public String home(Model model) {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		try {
			model.addAttribute("toShow", elementService.findByValidated(ElementStatus.APPROVED));
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		}
		modelSetter.setAttributesInModel(model);
		return "index";
	}
	
	@GetMapping("/users/list")
	public String getUsers(Model model) {
		modelSetter.setAttributesInModel(model);
		return "users-list";
	}
	
	@GetMapping("/user/manager")
	public String getUserManager(Model model) {
		modelSetter.setAttributesInModel(model);
		return "user-manager";
	}
	
	@GetMapping("/creation")
	public String setGenericElement(Model model) {
		modelSetter.setAttributesInModel(model);
		return "creation";
	}

	@GetMapping("/validations")
	public String getValidations(Model model) {
		modelSetter.setAttributesInModel(model);
		return "validation";
	}
	
	@GetMapping("/element/update")
	public String updateElement(Model model) {
		modelSetter.setAttributesInModel(model);
		return "update-element";
	}
	
	@GetMapping("/element")
	public String getElement(Model model, @RequestParam int id) {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		Role userRoleToCheck = loggedUserDetailService.getLoggedUser().getRole();
		try {
			Element elementToCheck = elementService.findById(id);
			if (userRoleToCheck == Role.ROLE_ADMINISTRATOR ||
				elementToCheck.getAuthor().getRole() == userRoleToCheck ||
				elementToCheck.getValidated() == ElementStatus.APPROVED) {
				
					model.addAttribute("element", elementToCheck);
					model.addAttribute("noElement", false);
			} else {
				model.addAttribute("noElement", true);
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			model.addAttribute("noElement", true);
		}
		modelSetter.setAttributesInModel(model);
		return "elementView";
	}
	
	@GetMapping("/elements/site/list")
	public String getElements(Model model) {
		modelSetter.setAttributesInModel(model);
		return "site-list";
	}
	
	@GetMapping("/error")
	public String error(Model model) {
		modelSetter.setBaseVisibility();
		model.addAttribute("message", "Ops, qualcosa è andato storto");
		modelSetter.setAttributesInModel(model);
		return "error";
	}
	
	private Element checkElement(int id, ElementStatus status) {
		Element toCheck = elementService.checkStatusElement(id, status);
		if (toCheck != null) {
			modelSetter.getAttributes().put("element", toCheck);
			modelSetter.getAttributes().put("noElement", false);
			return toCheck;
		} else {
			modelSetter.getAttributes().put("noElement", true);
		}
		return null;
	}
}

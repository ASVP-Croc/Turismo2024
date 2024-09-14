package com.speriamochemelacavo.turismo2024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.services.UsersService;

import com.speriamochemelacavo.turismo2024.services.ElementsService;

@Controller
@RequestMapping
public class PageController {
	
	@Autowired
	private UsersService accountService;
	
//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	@Autowired
	private ElementsService<Element> elementsService;
	
	@Autowired
	private ModelSetter modelSetter;

	@GetMapping("/")
	public String home(Model model) {
		modelSetter.setConditionModelVisibility(model);
		return "index";
	}

	@GetMapping("/login")
	public String login(Model model) {
		modelSetter.setConditionModelVisibility(model);
		return "login";
	}
	
	@GetMapping("/all/users")
	public String getUsers(Model model) {
		model.addAttribute("listUser", accountService.findAll());
		modelSetter.setConditionModelVisibility(model);
		return "users-list";
	}
	
	@GetMapping("/elements")
	public String getElements(Model model) {
		modelSetter.setConditionModelVisibility(model);
		return "elements-site-list";
	}
	
//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	@GetMapping("/all/elements")
	public String getAll(Model model) {
		model.addAttribute("listElements", elementsService.findAll());
		modelSetter.setConditionModelVisibility(model);
		return "elements-site-list";
	}

	//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	@GetMapping("/all/poi")
	public String getAllPoIs(Model model) {
		modelSetter.setConditionModelVisibility(model);
		return "elements-site-list";
	}
	
	@GetMapping("/registration")
	public String userRegistration(Model model) {
		modelSetter.setConditionModelVisibility(model);
		return "registration";
	}
	
	@GetMapping("/error")
	public String error(Model model) {
		modelSetter.setConditionModelVisibility(model);
		return "error";
	}
}

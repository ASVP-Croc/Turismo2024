package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.models.elements.category.ElementTypology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Element;

import com.speriamochemelacavo.turismo2024.services.ElementsService;

@Controller
@RequestMapping
public class PageController {
	
//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	@Autowired
	private ElementsService<Element> elementService;
	
	@Autowired
	private ModelSetter modelSetter;
	
	public PageController() {
	}
	
	@GetMapping("/")
	public String home(Model model) {
		modelSetter.setBaseVisibility();
		model.addAttribute("toShow", elementService.findAll());
		model.addAttribute("typologyTOUR", ElementTypology.TOUR);
		model.addAttribute("typologyPOI", ElementTypology.POI);
		modelSetter.setAttributesInModel(model);
		return "index";
	}

	@GetMapping("/login")
	public String login(Model model) {
		modelSetter.setBaseVisibility();
		modelSetter.setAttributesInModel(model);
		return "login";
	}
	
	@GetMapping("/users/list")
	public String getUsers(Model model) {
		modelSetter.setAttributesInModel(model);
		return "users-list";
	}
	
	@GetMapping("/creation")
	public String setGenericElement(Model model) {
		modelSetter.setAttributesInModel(model);
		return "creation";
	}
	
	@GetMapping("/element")
	public String getElement(Model model) {
		modelSetter.setAttributesInModel(model);
		return "element";
	}
	
//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	@GetMapping("/elements/list")
	public String getAllElements(Model model) {
		modelSetter.setAttributesInModel(model);
		return "elements-list";
	}
	
	@GetMapping("/registration")
	public String userRegistration(Model model) {
		modelSetter.setBaseVisibility();
		modelSetter.setAttributesInModel(model);
		return "registration";
	}
	
	@GetMapping("/error")
	public String error(Model model) {
		modelSetter.setBaseVisibility();
		model.addAttribute("message", "Ops, qualcosa è andato storto");
		modelSetter.setAttributesInModel(model);
		return "error";
	}
}

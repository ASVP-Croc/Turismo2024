package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.models.elements.Tour;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.services.UsersService;

import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.ElementsWithContentsService;
import com.speriamochemelacavo.turismo2024.services.POIsService;

@Controller
@RequestMapping
public class PageController {
	
	@Autowired
	private UsersService accountService;
	
//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	@Autowired
	private ElementsService<Element> elementService;
	
	@Autowired
	private ModelSetter modelSetter;
	
	public PageController() {
	}

	@GetMapping("/")
	public String home(Model model) {
		addAllAttributesToModel(model, Map.of("toShow", elementService.findAll()));
		return "index";
	}

	@GetMapping("/login")
	public String login(Model model) {
		updateModel(model);
		return "login";
	}
	
	@GetMapping("/users")
	public String getUsers(Model model) {
		updateModel(model);
		return "users-list";
	}
	
//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	@GetMapping("/elements/list")
	public String getAll(Model model) {
		updateModel(model);
		return "elements-list";
	}
	
	@GetMapping("/registration")
	public String userRegistration(Model model) {
		updateModel(model);
		return "registration";
	}
	
	@GetMapping("/error")
	public String error(Model model) {
		addAllAttributesToModel(model, Map.of("message", "Ops, qualcosa è andato storto"));
		return "error";
	}
	
	protected void updateModel(Model model) {
		modelSetter.setBaseVisibility(model);
		model = modelSetter.getModel();
	}
	
	protected void addAllAttributesToModel(Model model, Map<String, ?> attributes) {
		model.addAllAttributes(attributes);
		updateModel(model);
	}
}

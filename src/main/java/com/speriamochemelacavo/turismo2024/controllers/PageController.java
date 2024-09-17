package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.services.UsersService;

import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.POIsService;

@Controller
@RequestMapping
public class PageController {
	
	@Autowired
	private UsersService accountService;
	
//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	@Autowired
	private ElementsService<Element> elementsService;
	
	@Autowired
	private ElementsService<PointOfInterest> poiService;
	
	@Autowired
	private ElementsService<Tour> tourService;
	
	@Autowired
	private ElementsService<Contest> contestService;
	
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
	
//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	@GetMapping("/elements")
	public String getAll(Model model) {
		model.addAttribute("listElements", elementsService.findAll());
		modelSetter.setConditionModelVisibility(model);
		return "elements-site-list";
	}

	@GetMapping("/pois")
	public String getAllPoIs(Model model) {
		model.addAttribute("listElements", poiService.findAll());
		modelSetter.setConditionModelVisibility(model);
		return "elements-site-list";
	}

	@GetMapping("/tours")
	public String getAllTours(Model model) {
		model.addAttribute("listElements", tourService.findAll());
		modelSetter.setConditionModelVisibility(model);
		return "elements-site-list";
	}
	
	@GetMapping("/contest")
	public String getAllContests(Model model) {
		model.addAttribute("listElements", contestService.findAll());
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
//		TODO in produzione bisogna rimettere la stringa qui sotto
//		model.addAttribute("message", "Ops, qualcosa è andato storto");
		return "error";
	}
}

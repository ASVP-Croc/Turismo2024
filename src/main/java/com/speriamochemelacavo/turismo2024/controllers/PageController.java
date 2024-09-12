package com.speriamochemelacavo.turismo2024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.services.UsersService;

import jakarta.el.ELManager;
import jakarta.servlet.http.HttpSession;

import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.NotificationsService;

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
	public String welcome(Model model, HttpSession session) {
		modelSetter.setConditionModelVisibility(model, session);
		return "index";
	}

	@GetMapping("/login")
	public String login(Model model, HttpSession session) {
		modelSetter.setConditionModelVisibility(model, session);
		if (!accountService.isLogged()) {
			return "login";
		}
		return "index";
	}

	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
		accountService.resetLoggedUser();
		modelSetter.setConditionModelVisibility(model, session);
		return "index";
	}

	@GetMapping("/all/users")
	public String getUsers(Model model, HttpSession session) {
		model.addAttribute("listUser", accountService.findAll());
		modelSetter.setConditionModelVisibility(model, session);
		return "users-list";
	}
	
	@GetMapping("/elements")
	public String getElements(Model model, HttpSession session) {
		modelSetter.setConditionModelVisibility(model, session);
		return "elements-site-list";
	}
	
//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	@GetMapping("/all/elements")
	public String getAll(Model model, HttpSession session) {
		model.addAttribute("listElements", elementsService.findAll());
		modelSetter.setConditionModelVisibility(model, session);
		return "elements-site-list";
	}
	
//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	@GetMapping("/pois")
	public String getPois(Model model, HttpSession session) {
		model.addAttribute("listPoi", elementsService.findAll());
		modelSetter.setConditionModelVisibility(model, session);
		return "poi-list";
	}
	
	@GetMapping("/registration")
	public String userRegistration(Model model, HttpSession session) {
		modelSetter.setConditionModelVisibility(model, session);
		return "registration";
	}
	
	@GetMapping("/error")
	public String error(Model model, HttpSession session) {
		modelSetter.setConditionModelVisibility(model, session);
		return "error";
	}
}

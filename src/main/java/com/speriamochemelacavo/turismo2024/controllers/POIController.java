package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.services.POIsService;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class POIController {
	
	@Autowired
	private LoggedUserDetailService loggedUserService;
	
	@Autowired
	private POIsService poiService;

	@GetMapping("/poi")
	public RedirectView getPOIs(Model model) {
		model.addAttribute("listElements", poiService.findAll());
		return new RedirectView("/all/poi");

	}
	@GetMapping("/poi/{id}")
	public PointOfInterest getPOIById(@PathVariable int id) {
		return poiService.findById(id);
	}

	@PostMapping("/creation")
	public RedirectView createPoI(@RequestBody PointOfInterest poiToAdd) {
		poiService.add(poiToAdd, loggedUserService.getLoggedUser());
		return new RedirectView("/poi");
	}

	@PutMapping("/poi/{id}")
	public void updatePoI(@RequestBody PointOfInterest pointOfInterestToUpdate) {
		poiService.add(pointOfInterestToUpdate, loggedUserService.getLoggedUser());
	}

	@DeleteMapping("/poi/{id}")
	public void deletePoIById(@PathVariable Integer id) {
		poiService.deleteById(id);
	}


}

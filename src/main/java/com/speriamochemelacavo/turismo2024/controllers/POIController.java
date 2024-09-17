package com.speriamochemelacavo.turismo2024.controllers;

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

	@GetMapping("/pois")
	public RedirectView getPOIs(Model model) {
		model.addAttribute("listElements", poiService.findAll());
		return new RedirectView("/all/poi");

	}
	@GetMapping("/pois/{id}")
	public PointOfInterest getPOIById(@PathVariable int id) {
		return poiService.findById(id);
	}

	@PostMapping("/creation")
	public RedirectView createPoI(@RequestBody PointOfInterest poiToAdd) {
		poiService.add(poiToAdd, loggedUserService.getLoggedUser());
		return new RedirectView("/pois");
	}

	@PutMapping("/pois/update")
	public void updatePoI(@RequestBody PointOfInterest pointOfInterestToUpdate) {
		poiService.add(pointOfInterestToUpdate, loggedUserService.getLoggedUser());
	}

	@DeleteMapping("/pois/{id}")
	public void deletePoIById(@PathVariable Integer id) {
		poiService.deleteById(id);
	}


}

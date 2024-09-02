package com.speriamochemelacavo.turismo2024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.services.AccountsService;
import com.speriamochemelacavo.turismo2024.services.POIsService;

@RestController("/poi")
public class POIController {
	
	@Autowired
	POIsService poiService;
	
	@Autowired
	AccountsService accountService;
	
	@GetMapping("/all")
	public RedirectView getPOIs() {
		return new RedirectView("/pois");
	}

	@GetMapping("/{id}")
	public PointOfInterest getPOIById(@PathVariable int id) {
		return poiService.findById(id);
	}

	@PostMapping("")
	public void addPOI(@RequestBody PointOfInterest newPOI) {
		poiService.addElement(newPOI);
	}

	
}

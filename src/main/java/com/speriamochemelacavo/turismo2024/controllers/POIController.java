package com.speriamochemelacavo.turismo2024.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.services.AccountsService;
import com.speriamochemelacavo.turismo2024.services.ElementsService;

@RestController
public class POIController {
	
	@Autowired
	ElementsService<PointOfInterest> poiService;
	
	@Autowired
	AccountsService accountService;
	
	@GetMapping("/pois")
	public List<PointOfInterest> getPOIs() {
		return poiService.findAll();
	}

	@GetMapping("/poi/{id}")
	public PointOfInterest getPOIById(@PathVariable int id) {
		return poiService.findById(id);
	}

	@PostMapping("/poi")
	public void addPOI(@RequestBody PointOfInterest newPOI) {
		poiService.addElement(newPOI);
	}

	
}

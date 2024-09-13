package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.services.POIsService;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class POIController {
	
	@Autowired
	private POIsService poiService;
	@Autowired
	private ModelSetter modelSetter;


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
	public RedirectView createPoI(@RequestBody PointOfInterest poiToAdd, @RequestBody User author) {
		poiService.add(poiToAdd, author);
		return new RedirectView("/poi");
	}

	@PutMapping("/poi/{id}")
	public void updatePoI(@RequestBody PointOfInterest pointOfInterestToUpdate) {
		poiService.update(pointOfInterestToUpdate);
	}

	@DeleteMapping("/poi/{id}")
	public void deletePoIById(@PathVariable Integer id) {
		poiService.deleteById(id);
	}


}

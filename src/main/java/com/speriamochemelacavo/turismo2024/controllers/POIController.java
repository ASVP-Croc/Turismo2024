package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.POIsService;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequestMapping("/pois")
public class POIController {
	
	@Autowired
	private LoggedUserDetailService loggedUserService;
	
	@Autowired
	private ElementsService<PointOfInterest> poiService;
	
	@Autowired
	private PageController pageController;
	
    @GetMapping("/")
    public RedirectView getAllPOIs(Model model) {
    	
    	HashMap<String, ?> toAdd = (HashMap<String, ?>) Map.ofEntries(
    			Map.entry("isPoi", true), 
    			Map.entry("toShow", poiService.findAll()));
    	
    	pageController.addAllAttributesToModel(model, toAdd);
        return new RedirectView("/elements/list");
    }
	
	@GetMapping("/{id}")
	public PointOfInterest getPOIById(@PathVariable int id) {
		return poiService.findById(id);
	}

	@PostMapping("/creation")
	public RedirectView createPoI(@RequestBody PointOfInterest poiToAdd) {
		poiService.add(poiToAdd, loggedUserService.getLoggedUser());
		return new RedirectView("/pois");
	}

	@PutMapping("/update")
	public void updatePoI(@RequestBody PointOfInterest pointOfInterestToUpdate) {
		poiService.add(pointOfInterestToUpdate, loggedUserService.getLoggedUser());
	}

	@DeleteMapping("/{id}")
	public void deletePoIById(@PathVariable Integer id) {
		poiService.deleteById(id);
	}


}

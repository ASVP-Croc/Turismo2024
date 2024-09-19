package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequestMapping("/pois")
public class POIController {
	
	@Autowired
	private LoggedUserDetailService loggedUserService;
	
	@Autowired
	private ElementsService<PointOfInterest> poiService;
	
	@Autowired
	private ModelSetter modelSetter;
	
    @GetMapping("")
    public RedirectView getAllPOIs() {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("toShow", poiService.findAll());
		modelSetter.getAttributes().put("isPoi", true);
        return new RedirectView("/elements/list");
    }
	
	@GetMapping("/{id}")
	public PointOfInterest getPOIById(@PathVariable int id) {
		return poiService.findById(id);
	}

	@GetMapping("/creation")
	public RedirectView setElement() {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("isPoi", true);
		return new RedirectView("/creation");
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

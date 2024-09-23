package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Address;
import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;
import com.speriamochemelacavo.turismo2024.services.AddressService;
import com.speriamochemelacavo.turismo2024.services.POIsService;
import com.speriamochemelacavo.turismo2024.services.TagsService;
import com.speriamochemelacavo.turismo2024.services.ValidationsService;

import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequestMapping("/pois")
public class POIController {
	
	@Autowired
	private POIsService poiService;
	
    @Autowired
    private AddressService addressService;
	
    @Autowired
    private LoggedUserDetailService loggedUserService;
	
	@Autowired
	private ModelSetter modelSetter;

	@Autowired
	private ValidationsService<PointOfInterest> validationService;

	@Autowired
	private TagsService tagService;
	
    @GetMapping("")
    public RedirectView getAllPOIs() {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("toShow", poiService.findAll());
        return new RedirectView("/elements/site/list");
    }
	
	@GetMapping("/{id}")
	public RedirectView getPOIById(@PathVariable int id) {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		//    	TODO da ricontrollare, è stato fatto così il metodo per gestire temporaneamente l'eccezione
        try {
    		modelSetter.getAttributes().put("element", poiService.findById(id));
    		modelSetter.getAttributes().put("isPoi", true);
			return new RedirectView("/element?id=" + id);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			modelSetter.getAttributes().put("alertMessage", "Punto di Interesse non trovato!");
			return new RedirectView("/");
		}
	}

	@GetMapping("/creation")
	public RedirectView createPoi() {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("isPoi", true);
		modelSetter.getAttributes().put("urlCreationElement", "/pois/add");
		return new RedirectView("/creation");
	}
	
	@GetMapping("/creation/osm")
	public RedirectView createPoiFromOSM(PointOfInterest poi) {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("isPoi", true);
		modelSetter.getAttributes().put("urlCreationElement", "/pois/add");
		modelSetter.getAttributes().put("element", poi);
		return new RedirectView("/creation");
	}

	@PostMapping("/add")
	public RedirectView addPoI(@ModelAttribute PointOfInterest element, @ModelAttribute Address address) {
		element.setAuthor(loggedUserService.getLoggedUser());
		element.setAddress(addressService.add(address));
		PointOfInterest toValidate = poiService.add(element);
		tagService.addAll(tagService.createTagsFromString(
				element.getName() + "," +
				element.getDescription() + "," +
				address.getRoad() + "," +
				element.getCity(), element));
		validationService.requestValidation(toValidate);
		poiService.add(toValidate);
		return new RedirectView("/pois/" + toValidate.getId());
	}
	
	

	@PutMapping("/update")
	public RedirectView updatePoI(@ModelAttribute PointOfInterest element, @ModelAttribute Address address) {
		element.setAddress(addressService.add(address));
		PointOfInterest toValidate = poiService.update(element);
		tagService.addAll(tagService.createTagsFromString(
				element.getName() + "," +
						element.getDescription() + "," +
						address.getRoad() + "," +
						element.getCity(), element));
		validationService.requestValidation(toValidate);
		poiService.add(toValidate);
		return new RedirectView("/pois/" + toValidate.getId());
	}

	@DeleteMapping("/delete/{id}")
	public RedirectView deletePoIById(@PathVariable int id) {
		poiService.deleteById(id);
		return new RedirectView("/");
	}


}

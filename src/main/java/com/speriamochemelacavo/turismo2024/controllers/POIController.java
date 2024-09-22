package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;

import java.sql.SQLIntegrityConstraintViolationException;

import org.hibernate.cfg.ValidationSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.metrics.StartupStep.Tags;
import org.springframework.web.bind.annotation.*;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Address;
import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;
import com.speriamochemelacavo.turismo2024.services.AddressService;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.POIsService;
import com.speriamochemelacavo.turismo2024.services.TagsService;
import com.speriamochemelacavo.turismo2024.services.UsersService;
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
        return new RedirectView("/elements/list");
    }
	
	@GetMapping("/{id}")
	public RedirectView getPOIById(@PathVariable int id) {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		//    	TODO da ricontrollare, è stato fatto così il metodo per gestire temporaneamente l'eccezione
        try {
    		modelSetter.getAttributes().put("element", poiService.findById(id));
    		modelSetter.getAttributes().put("isPoi", true);
			return new RedirectView("/element");
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

	@DeleteMapping("/{id}")
	public void deletePoIById(@PathVariable Integer id) {
		poiService.deleteById(id);
	}


}

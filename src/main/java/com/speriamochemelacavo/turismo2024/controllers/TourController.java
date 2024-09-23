package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import com.speriamochemelacavo.turismo2024.models.elements.poi.POIForTour;
import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.POIsForTourService;
import com.speriamochemelacavo.turismo2024.services.POIsService;
import com.speriamochemelacavo.turismo2024.services.TagsService;
import com.speriamochemelacavo.turismo2024.services.ToursService;
import com.speriamochemelacavo.turismo2024.services.ValidationsService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private LoggedUserDetailService loggedUserService;

    @Autowired
    private ToursService tourService;
    
    @Autowired
    private POIsService poiService;
    
    private POIsForTourService poiForTourService;
    
	@Autowired
	private ModelSetter modelSetter;

	@Autowired
	private ValidationsService<Tour> tourValidationService;
	
	@Autowired
	private ValidationsService<POIForTour> poiForTourValidationService;

	@Autowired
	private TagsService tagService;

    @GetMapping("")
    public RedirectView getAllTours() {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("toShow", tourService.findAll());
        return new RedirectView("/elements/list");
    }
    
    @GetMapping("/{id}")
    public RedirectView getTourById(@PathVariable int id) {
//    	TODO da ricontrollare, è stato fatto così il metodo per gestire temporaneamente l'eccezione
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		//    	TODO da ricontrollare, è stato fatto così il metodo per gestire temporaneamente l'eccezione

        try {
    		modelSetter.getAttributes().put("element", tourService.findById(id));
    		modelSetter.getAttributes().put("isTour", true);
			return new RedirectView("/element?id=" + id);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			modelSetter.getAttributes().put("alertMessage", "Itinerario non trovato!");
			return new RedirectView("/");
		}
	}

    @GetMapping("/creation")
    public RedirectView createTour(Tour tour) {
    	modelSetter.clearAllAttributes();
    	modelSetter.setBaseVisibility();
    	modelSetter.getAttributes().put("isTour", true);
		modelSetter.getAttributes().put("urlCreationElement", "/tours/add");
        return new RedirectView("/creation");
    }

    @PostMapping("/add")
    public RedirectView addTour(@ModelAttribute Tour element) {
		element.setAuthor(loggedUserService.getLoggedUser());
		Tour toValidate = tourService.add(element);
		tagService.addAll(tagService.createTagsFromString(
				element.getName() + "," +
				element.getDescription() + "," +
				element.getCity(), element));
		tourValidationService.requestValidation(toValidate);
		tourService.add(toValidate);
		return new RedirectView("/tours/" + toValidate.getId());    
	}
    
    @PostMapping("/add/pois")
    public RedirectView addPoisToTour(@ModelAttribute Tour element, @ModelAttribute PointOfInterest... pois) {
    	List<POIForTour> toValidate = new ArrayList<>();
    	Arrays.stream(pois).forEach(p -> {
			try {
		    	POIForTour pft = (POIForTour) poiService.findById(p.getId());
		    	pft.setId(0);
		    	toValidate.add(pft);
			} catch (SQLIntegrityConstraintViolationException e) {
				e.printStackTrace();
			}
		});
    	toValidate.stream().forEach(pft -> {
    		pft.setAuthorForTour(loggedUserService.getLoggedUser());
    		poiForTourValidationService.requestValidation(poiForTourService.add(pft));
    		poiForTourService.add(pft);
    		element.getMyPOIs().add(pft);
    		tourService.add(element);
    	});
		return new RedirectView("/tours/" + element.getId());    
	}

    @DeleteMapping("/{id}")
    public void deleteTourById(@PathVariable Integer id) {
        tourService.deleteById(id);
    }
}

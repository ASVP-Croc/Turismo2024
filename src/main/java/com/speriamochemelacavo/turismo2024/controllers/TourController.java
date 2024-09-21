package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.ToursService;

import java.sql.SQLIntegrityConstraintViolationException;

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
	private ModelSetter modelSetter;

    @GetMapping("")
    public RedirectView getAllTours() {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("toShow", tourService.findAll());
		modelSetter.getAttributes().put("isTour", true);
        return new RedirectView("/elements/list");
    }
    
    @GetMapping("/{id}")
    public RedirectView getTourById(@PathVariable int id) {
//    	TODO da ricontrollare, è stato fatto così il metodo per gestire temporaneamente l'eccezione
        try {
			tourService.findById(id);
			return new RedirectView("element");
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			return new RedirectView("element");
		}
    }

    @GetMapping("/creation")
    public RedirectView createTour(Tour tour) {
    	modelSetter.clearAllAttributes();
    	modelSetter.setBaseVisibility();
    	modelSetter.getAttributes().put("isTour", true);
        return new RedirectView("/creation");
    }

    @PutMapping("/update")
    public void updateTour(@RequestBody Tour tourToUpdate) {
        tourService.add(tourToUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteTourById(@PathVariable Integer id) {
        tourService.deleteById(id);
    }
}

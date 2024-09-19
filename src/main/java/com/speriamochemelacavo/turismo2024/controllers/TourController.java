package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.ToursService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private LoggedUserDetailService loggedUserService;

    @Autowired
    private ElementsService<Tour> tourService;
    
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
    public Tour getTourById(@PathVariable int id) {
        return tourService.findById(id);
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
        tourService.add(tourToUpdate, loggedUserService.getLoggedUser());
    }

    @DeleteMapping("/{id}")
    public void deleteTourById(@PathVariable Integer id) {
        tourService.deleteById(id);
    }
}

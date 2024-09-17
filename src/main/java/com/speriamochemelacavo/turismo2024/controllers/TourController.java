package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.ToursService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private LoggedUserDetailService loggedUserService;

    @Autowired
    private ElementsService<Tour> tourService;

    @GetMapping("/")
    public RedirectView getAllContests(Model model) {
		model.addAttribute("isTour", true);
		model.addAttribute("toShow", tourService.findAll());
        return new RedirectView("/elements/list");
    }
    
    @GetMapping("/{id}")
    public Tour getTourById(@PathVariable int id) {
        return tourService.findById(id);
    }

    @PostMapping("/creation")
    public RedirectView createTour(Tour tour) {
        tourService.add(tour, loggedUserService.getLoggedUser());
        return new RedirectView("/tours");
    }

    @PutMapping("/update")
    public void updatePoI(@RequestBody Tour tourToUpdate) {
        tourService.add(tourToUpdate, loggedUserService.getLoggedUser());
    }

    @DeleteMapping("/{id}")
    public void deleteTourById(@PathVariable Integer id) {
        tourService.deleteById(id);
    }
}

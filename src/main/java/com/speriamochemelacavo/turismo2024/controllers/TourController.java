package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.ToursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private LoggedUserDetailService loggedUserService;

    @Autowired
    private ElementsService<Tour> toursService;

    @GetMapping("/{id}")
    public Tour getTourById(@PathVariable int id) {
        return toursService.findById(id);
    }

    @GetMapping("/all")
    public List<Tour> getAllTours() {
        return toursService.findAll();
    }

    @PostMapping("/creation")
    public RedirectView createTour(Tour tour) {
        toursService.add(tour, loggedUserService.getLoggedUser());
        return new RedirectView("/tours");
    }

    @PutMapping("/{id}")
    public void updatePoI(@RequestBody Tour tourToUpdate) {
        toursService.add(tourToUpdate, loggedUserService.getLoggedUser());
    }

    @DeleteMapping("/{id}")
    public void deleteTourById(@PathVariable Integer id) {
        toursService.deleteById(id);
    }
}

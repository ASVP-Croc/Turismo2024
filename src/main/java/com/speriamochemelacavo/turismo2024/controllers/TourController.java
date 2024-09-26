package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.exception.ElementNotFoundException;
import com.speriamochemelacavo.turismo2024.models.elements.Address;
import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.ElementStatus;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import com.speriamochemelacavo.turismo2024.models.elements.poi.POIForTour;
import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.services.ContentsService;
import com.speriamochemelacavo.turismo2024.services.POIsForTourService;
import com.speriamochemelacavo.turismo2024.services.TagsService;
import com.speriamochemelacavo.turismo2024.services.ToursService;
import com.speriamochemelacavo.turismo2024.services.ValidationsService;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private LoggedUserDetailService loggedUserService;

    @Autowired
    private ToursService tourService;
    
    @Autowired
    private ContentsService contentService;
    
    @Autowired
    private POIsForTourService poiForTourService;
    
	@Autowired
	private ModelSetter modelSetter;
	
	@Autowired
	private ValidationsService validationService;

	@Autowired
	private TagsService tagService;

    @GetMapping("")
    public RedirectView getAllTours() {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		List<Tour> toReturn;
		try {
			toReturn = tourService.findByValidated(ElementStatus.APPROVED);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			toReturn = new ArrayList<>();
		}
		modelSetter.getAttributes().put("toShow", toReturn);
        return new RedirectView("/elements/site/list");
    }

    @GetMapping("/creation")
    public RedirectView createTour(Tour tour) {
    	modelSetter.clearAllAttributes();
    	modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("isSite", true);
    	modelSetter.getAttributes().put("isTour", true);
		modelSetter.getAttributes().put("urlCreationElement", "/tours/add");
        return new RedirectView("/creation");
    }

    @PostMapping("/add")
    public RedirectView addTour(@ModelAttribute Tour element) {
    	modelSetter.clearAllAttributes();
    	modelSetter.setBaseVisibility();
		Set<Tag> toCompare = tagService.createTagsFromString(
				element.getName() + "," +
				element.getDescription() + "," +
				element.getCity(), element);
    
    	for (Tag tag : toCompare) {
			try {
				tagService.findByTag(tag.getTagName());
			} catch (SQLIntegrityConstraintViolationException e) {
				e.printStackTrace();
				element.setAuthor(loggedUserService.getLoggedUser());
				Tour toValidate = tourService.add(element);
				tagService.addAll(toCompare);
				validationService.requestValidation(toValidate);
				tourService.add(toValidate);
				return new RedirectView("/tours/" + toValidate.getId());
			}
    	}
		return new RedirectView("/tours/creation");    
	}
    
	@PostMapping("/addWithContent")
	public RedirectView addTourWithContent(@ModelAttribute Tour element,
			@RequestParam MultipartFile file,
			@ModelAttribute Content content) {
		RedirectView toReturn = addTour(element);
		content.setAuthor(loggedUserService.getLoggedUser());
		try {
			Content toValidate = contentService.add(content, element, file);
			validationService.requestValidation(toValidate);
			contentService.add(content);
			} catch (IOException e) {
			e.printStackTrace();
			modelSetter.getAttributes().put("alertMessage", e.getMessage());
		}
		return toReturn;
	}
//    
//    @PostMapping("/add/pois")
//    public RedirectView addPoisToTour(@ModelAttribute Tour element, @ModelAttribute POIForTour... pois) {
//    	
//    	Arrays.stream(pois).forEach(pft -> {
//    		pft.setAuthor(loggedUserService.getLoggedUser());
//    		validationService.requestValidation(poiForTourService.add(pft));
//    		poiForTourService.add(pft);
//    		element.getMyPOIs().add(pft);
//    	});
//    	
//		tourService.add(element);
//		return new RedirectView("/tours/" + element.getId());    
//	}

//	@PostMapping("/update")
//	public RedirectView updateTour(@ModelAttribute Tour element) {
//		tagService.addAll(tagService.createTagsFromString(
//				element.getName() + "," +
//				element.getDescription() + "," +
//				element.getCity(), element));
//		tourValidationService.requestValidation(element);
//		tourService.add(element);
//		return new RedirectView("/tours/" + element.getId());
//	}

	@PostMapping("/update/tours/status")
	public RedirectView updateTourStatus(@PathVariable int id, @RequestBody ElementStatus elementStatus) throws ElementNotFoundException {
		Tour tour = tourService.updateStatus(id, elementStatus);
		return new RedirectView("/tours/" + tour.getId());
	}

//	@PostMapping("/update/tours/poi")
//	public RedirectView updatePOIsTour(@PathVariable int id, @RequestBody List<POIForTour> pointOfInterestList) throws SQLIntegrityConstraintViolationException {
//		Tour tour = tourService.findById(id);
//		poiForTourService.addAll(pointOfInterestList);
//		tour.getMyPOIs().addAll(pointOfInterestList);
//		return new RedirectView("/" + tour.getId());
//	}

    @DeleteMapping("/delete/{id}")
    public RedirectView deleteTourById(@PathVariable Integer id) {
        tourService.deleteById(id);
		return new RedirectView("/");
    }
}

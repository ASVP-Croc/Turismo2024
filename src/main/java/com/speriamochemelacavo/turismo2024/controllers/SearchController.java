package com.speriamochemelacavo.turismo2024.controllers;

import java.io.IOException;
import java.util.*;

import com.speriamochemelacavo.turismo2024.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	private LoggedUserDetailService loggedUserService;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private PageController pageController;

	@GetMapping("/site")
	public String searchElementsSite(Model model, @RequestParam String tag){
		
    	HashMap<String, ?> toAdd = (HashMap<String, ?>) Map.ofEntries(
    			Map.entry("listElements", searchService.searchElementsSite(tag)));
    	
    	pageController.addAllAttributesToModel(model, toAdd);
		return "elements-list";
	}
	
	@GetMapping("/osm/detail")
	public String searchElementsOSMWithDetails(Model model,
			@RequestParam String amenity, 
			@RequestParam String street,
			@RequestParam String houseNumber,
			@RequestParam(defaultValue = "loggedUser") String postalCode) throws IOException{
		
		List<PointOfInterest> toReturn = searchService.searchElementsOSMWithDetails(
				amenity,
				street,
				houseNumber,
				postalCode.contains("loggedUser") ? loggedUserService.getLoggedUser().getCAP() : postalCode);
		
    	HashMap<String, ?> toAdd = (HashMap<String, ?>) Map.ofEntries(
    			Map.entry("listElements", toReturn), 
    			Map.entry("isPOI", true));
    	
    	pageController.addAllAttributesToModel(model, toAdd);
		return "elements-list";
	}
	
	@GetMapping("/osm/query")
	public String searchElementsOSMWithQuery(Model model, @RequestParam String query) throws IOException{
		
    	HashMap<String, ?> toAdd = (HashMap<String, ?>) Map.ofEntries(
    			Map.entry("listElements", searchService.searchElementsOSMWithQuery(query)),
    			Map.entry("isPOI", true));
    	
    	pageController.addAllAttributesToModel(model, toAdd);
		return "elements-list";
	}


}

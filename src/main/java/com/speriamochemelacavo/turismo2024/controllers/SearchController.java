package com.speriamochemelacavo.turismo2024.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.security.AccountSecurity;
import com.speriamochemelacavo.turismo2024.services.UsersService;

import jakarta.servlet.http.HttpSession;

import com.speriamochemelacavo.turismo2024.services.ElementResolver;
import com.speriamochemelacavo.turismo2024.services.NominatimService;
import com.speriamochemelacavo.turismo2024.services.TagsService;

@Controller
@RequestMapping
public class SearchController {
	
	@Autowired
	private AccountSecurity accountSecurity;
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private NominatimService nominatimService;
	
	@Autowired
	private ElementResolver<PointOfInterest> POIResolver;
	
	@Autowired
	private TagsService tagService;
	
	@Autowired
	private ModelSetter modelSetter;

	@GetMapping("/search/site")
	public String searchElementsSite(Model model, String tag, HttpSession session){
		List<Element> toReturn = new ArrayList<>();
		String tagClean = tag.replaceAll("\\s*,\\s*", ",");
		List<String> tagValuesList = Arrays.stream(tagClean.split("[\\s,]+")).filter(t -> !t.isEmpty()).collect(Collectors.toList());
		tagValuesList.stream().forEach(t ->{
			if (tagService.findByTag(t) != null) toReturn.addAll(tagService.findByTag(t).getElements());
			});
		modelSetter.setConditionModelVisibility(model);
		model.addAttribute("listElements", sortListByOccurrences(toReturn));
		return "elements-site-list";
	}
	
	@GetMapping("/search/osm/detail")
	public String searchElementsOSMWithDetails(Model model,
			@RequestParam String amenity, 
			@RequestParam String street,
			@RequestParam String houseNumber,
			@RequestParam String postalCode,
			HttpSession session) throws IOException{
		List<PointOfInterest> toReturn = new ArrayList<>();
		toReturn.addAll(
				POIResolver.resolveElements(
						nominatimService.getInfoFromParameter(
								amenity,
								(street == "" && houseNumber == "") ? "" : street + " " + houseNumber,
								accountSecurity.isLogged() ? userService.findByUserName(accountSecurity.getLoggedUserName()).getCAP() : postalCode)
								));
		toReturn.forEach(p -> System.out.println(p.toString()));
		modelSetter.setConditionModelVisibility(model);
		model.addAttribute("listElements", toReturn);
		return "elements-osm-list";
	}
	
	@GetMapping("/search/osm/query")
	public String searchElementsOSMWithQuery(Model model, HttpSession session,
			@RequestParam String query) throws IOException{
		List<PointOfInterest> toReturn = new ArrayList<>();
		toReturn.addAll(
				POIResolver.resolveElements(
						nominatimService.getInfoFromQuery(query)));
		toReturn.forEach(p -> System.out.println(p.toString()));
		modelSetter.setConditionModelVisibility(model);
		model.addAttribute("listElements", toReturn);
		return "elements-osm-list";
	}
	
	private List<Element> sortListByOccurrences(List<Element> elemtsList) {
        Map<Element, Integer> occurrences = new HashMap<>();
        for (Element element : elemtsList) {occurrences.put(element, occurrences.getOrDefault(element, 0) + 1);}
        List<Map.Entry<Element, Integer>> entries = new ArrayList<>(occurrences.entrySet());
        Collections.sort(entries, (e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()));
        List<Element> risultato = new ArrayList<>();
        for (Map.Entry<Element, Integer> entry : entries) {
            risultato.add(entry.getKey());
        }
        return risultato;
    }
}

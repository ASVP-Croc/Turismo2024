package com.speriamochemelacavo.turismo2024.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.services.AccountsService;
import com.speriamochemelacavo.turismo2024.services.ElementResolver;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.NominatimService;
import com.speriamochemelacavo.turismo2024.services.NotificationsService;
import com.speriamochemelacavo.turismo2024.services.POIResolver;
import com.speriamochemelacavo.turismo2024.services.TagsService;

@Controller
@RequestMapping
public class SearchController {
	
	@Autowired
	private NominatimService nominatimService;
	
	@Autowired
	private ElementResolver<PointOfInterest> POIResolver;
	
	@Autowired
	private TagsService tagService;
	
	@Autowired
	private ModelSetter modelSetter;

	@GetMapping("/search/site")
	public String searchElementsSite(Model model, String tag){
		List<Element> toReturn = new ArrayList<Element>();
		String tagClean = tag.replaceAll("\\s*,\\s*", ",");
		List<String> tagValuesList = Arrays.stream(tagClean.split("[\\s,]+")).filter(t -> !t.isEmpty()).collect(Collectors.toList());
		tagValuesList.stream().forEach(t ->{
			if (tagService.findByTag(t) != null) toReturn.addAll(tagService.findByTag(t).getElements());
			});
		modelSetter.setConditionModelVisibility(model);
		model.addAttribute("listElements", sortListByOccurrences(toReturn));
		return "elements-site-list";
	}
	
	@GetMapping("/search/osm")
	public String searchElementsOSM(Model model, @RequestParam("tag") String tag) throws JsonProcessingException{
		List<PointOfInterest> toReturn = new ArrayList<PointOfInterest>();
//		String tagClean = tag.replaceAll("\\s*,\\s*", ",");
//		List<String> tagsList = Arrays.stream(tagClean.split(",")).filter(t -> !t.isEmpty()).collect(Collectors.toList());
//		System.out.println(tagClean);
		toReturn.addAll(POIResolver.resolveElements(nominatimService.getElemntsInfoWithQuery(tag)));
		
//		tagsList.stream().forEach(t -> {
//			try {
//				toReturn.addAll(POIResolver.resolveElements(nominatimService.getElemntsInfoWithQuery(tagClean + (accountService.findById(accountService.getLoggedUser()).getCAP()))));
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//			}
//		});
		modelSetter.setConditionModelVisibility(model);
		model.addAttribute("listElements", toReturn);
		return "elements-osm-list";
	}
	
	private List<Element> sortListByOccurrences(List<Element> elemtsList) {
        Map<Element, Integer> occurrences = new HashMap<>();
        for (Element element : elemtsList) {occurrences.put(element, occurrences.getOrDefault(element, 0) + 1);}
        List<Map.Entry<Element, Integer>> entries = new ArrayList<>(occurrences.entrySet());
        Collections.sort(entries, (e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()));
        List<Element> risultato = new ArrayList<Element>();
        for (Map.Entry<Element, Integer> entry : entries) {
            risultato.add(entry.getKey());
        }
        return risultato;
    }
}

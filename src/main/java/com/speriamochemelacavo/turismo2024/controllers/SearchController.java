package com.speriamochemelacavo.turismo2024.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class SearchController {
	
	@Autowired
	private PageController pageController;
	
	@Autowired
	private ElementsService<Element> elementsService;
	
	@Autowired
	private NominatimService nominatimService;
	
	@Autowired
	private AccountsService accountService;
	
	@Autowired
	private ElementResolver<PointOfInterest> POIResolver;
	
	@Autowired
	private TagsService tagService;

	@Autowired
	private NotificationsService notificationService;

	@GetMapping("/search/site")
	public RedirectView searchElementsSite(Model model, String tag){
		List<Element> toReturn = new ArrayList<Element>();
		String tagClean = tag.replaceAll("\\s*,\\s*", ",");
		List<String> tagsList = Arrays.stream(tagClean.split(",")).filter(t -> !t.isEmpty()).collect(Collectors.toList());
		tagsList.stream().forEach(t -> toReturn.addAll(elementsService.findByTag(tag)));
		model.addAttribute("listElements", toReturn);
		return new RedirectView("/elements");
	}
	
	@RequestMapping("/search/osm")
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
		model.addAttribute("listElements", toReturn);
		setConditionModelVisibility(model);
		return "elements-list";
	}
	
	private void setConditionModelVisibility(Model model) {
		model.addAttribute("nameUser",
				!accountService.isLogged() ? "Turista" : accountService.findById(accountService.getLoggedUser()).getName());
		model.addAttribute("isLogged", accountService.isLogged());
		model.addAttribute("isLoadedUsers", accountService.isLoaded());
		boolean isPOIButtonVisible = (accountService.isLogged() & !elementsService.isLoaded());
		model.addAttribute("isPOIButtonVisible", isPOIButtonVisible);
		model.addAttribute("numberOfNotifications", notificationService.findAllByRecipientId(accountService.getLoggedUser()).size());
	}
}

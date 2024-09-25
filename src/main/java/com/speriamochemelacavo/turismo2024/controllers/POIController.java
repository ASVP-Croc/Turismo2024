package com.speriamochemelacavo.turismo2024.controllers;

import com.speriamochemelacavo.turismo2024.exception.ElementNotFoundException;
import com.speriamochemelacavo.turismo2024.models.elements.ElementStatus;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.elements.poi.PoIType;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Address;
import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;
import com.speriamochemelacavo.turismo2024.services.AddressService;
import com.speriamochemelacavo.turismo2024.services.ContentsService;
import com.speriamochemelacavo.turismo2024.services.POIsService;
import com.speriamochemelacavo.turismo2024.services.TagsService;
import com.speriamochemelacavo.turismo2024.services.ValidationsService;

import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/pois")
public class POIController {
	
	@Autowired
	private POIsService poiService;
	
    @Autowired
    private AddressService addressService;
	
    @Autowired
    private LoggedUserDetailService loggedUserService;
	
	@Autowired
	private ModelSetter modelSetter;

	@Autowired
	private ValidationsService<PointOfInterest> validationService;
	
	@Autowired
	private TagsService tagService;
	
	@Autowired
	ContentsService contentService;
	
    @GetMapping("")
    public RedirectView getAllPOIs() {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		List<PointOfInterest> toReturn;
		try {
			toReturn = poiService.findByValidated(ElementStatus.APPROVED);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			toReturn = new ArrayList<>();
		}
		modelSetter.getAttributes().put("toShow", toReturn);
        return new RedirectView("/elements/site/list");
    }
	
	@GetMapping("/{id}")
	public RedirectView getPOIById(@PathVariable int id) {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
        try {
    		modelSetter.getAttributes().put("element", poiService.findById(id));
    		modelSetter.getAttributes().put("isPoi", true);
			return new RedirectView("/element?id=" + id);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			modelSetter.getAttributes().put("alertMessage", "Punto di Interesse non trovato!");
			return new RedirectView("/");
		}
	}

	@GetMapping("/creation/site")
	public RedirectView createPoi() {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("isSite", true);
		modelSetter.getAttributes().put("isOSM", false);
		modelSetter.getAttributes().put("isPoi", true);
		modelSetter.getAttributes().put("urlCreationElement", "/pois/add");
		return new RedirectView("/creation");
	}
	
	@PostMapping("/creation/osm")
	public RedirectView createPoiFromOSM(@ModelAttribute PointOfInterest element, @ModelAttribute Address address) {
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("urlCreationElement", "/pois/add");
		modelSetter.getAttributes().put("element", element);
		modelSetter.getAttributes().put("address", address);
		modelSetter.getAttributes().put("isSite", false);
		modelSetter.getAttributes().put("isOSM", true);
		return new RedirectView("/creation");
	}

	@PostMapping("/add")
	public RedirectView addPoI(@ModelAttribute PointOfInterest element, @ModelAttribute Address address) {
		Set<Tag> toCompare = tagService.createTagsFromString(
				element.getName() + "," +
				element.getDescription() + "," +
				address.getRoad() + "," +
				element.getCity(), element);
		for (Tag tag : toCompare) {
			try {
				tagService.findByTag(tag.getTagName());
			} catch (SQLIntegrityConstraintViolationException e) {
				e.printStackTrace();
				element.setAuthor(loggedUserService.getLoggedUser());
				element.setAddress(addressService.add(address));
				PointOfInterest toValidate = poiService.add(element);
				tagService.addAll(toCompare);
				validationService.requestValidation(toValidate);
				poiService.add(toValidate);
				return new RedirectView("/pois/" + toValidate.getId());
			}
		}
		return new RedirectView("/pois/creation/site");
	}
	
	@PostMapping("/addWithContent")
	public RedirectView addPOIWithContent(@ModelAttribute PointOfInterest element,
			@ModelAttribute Address address,
			@RequestParam MultipartFile file,
			@ModelAttribute Content content) throws IOException {
		RedirectView toReturn = addPoI(element, address);
		content.setAuthor(loggedUserService.getLoggedUser());
		poiService.addContent(contentService.add(content, element, file), element);
		return toReturn;
	}

	@PostMapping("/add/type")
	public RedirectView addPoIType(@ModelAttribute PoIType type) {
		PointOfInterest poi = poiService.createPoIType(type);
		poiService.add(poi);
		return new RedirectView("/");
	}

	@PostMapping("/update")
	public RedirectView updatePoI(@ModelAttribute PointOfInterest element, @ModelAttribute Address address) {
		element.setAddress(addressService.add(address));
		tagService.addAll(tagService.createTagsFromString(
				element.getName() + "," +
						element.getDescription() + "," +
						address.getRoad() + "," +
						element.getCity(), element));
		validationService.requestValidation(element);
		poiService.add(element);
		return new RedirectView("/pois/" + element.getId());
	}

	@PostMapping("/update/status")
	public RedirectView updatePoIStatus(@PathVariable int id, @RequestBody ElementStatus elementStatus) throws ElementNotFoundException {
		PointOfInterest poi = poiService.updateStatus(id, elementStatus);
		return new RedirectView("/pois/" + poi.getId());
	}

	@DeleteMapping("/delete/{id}")
	public RedirectView deletePoIById(@PathVariable int id) {
		poiService.deleteById(id);
		return new RedirectView("/");
	}


}

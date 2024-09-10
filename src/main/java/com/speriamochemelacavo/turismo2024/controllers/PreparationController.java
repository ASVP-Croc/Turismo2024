package com.speriamochemelacavo.turismo2024.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.metrics.StartupStep.Tags;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.services.AccountsService;
import com.speriamochemelacavo.turismo2024.services.ElementResolver;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.NominatimService;
import com.speriamochemelacavo.turismo2024.services.NotificationsService;
import com.speriamochemelacavo.turismo2024.services.TagsService;

@RestController
public class PreparationController {
	
	@Autowired
	ElementResolver<PointOfInterest> poiResolver;
	
	@Autowired
	NominatimService nominatimService;
	
	@Autowired
	ElementsService<PointOfInterest> poiService;
	
	@Autowired
	AccountsService accountService;
	
	@Autowired
	NotificationsService notificationService;
	
	@Autowired
	TagsService tagsService;

	@GetMapping("/startDbUsers")
	public RedirectView insertInitialUserRecords(){
		if (!accountService.isLoaded()) {
			List<User> initialUsers = new ArrayList<>();
			initialUsers.add(new User("Matteo", "Pallotti", "Maverick", "maverick@gmail.com", "3929217858", "C.da San Pietro Orgiano, 13", "Fermo", 63900, Role.Administrator));
			initialUsers.add(new User("Lorenzo", "Crovace", "AVCP", "avcp@gmail.com", "123456789", "Via Ancona, 188", "Macerata", 62100, Role.Curator));
			initialUsers.add(new User("Simone", "Silver", "SilverSimon", "simon@gmail.com", "987654321", "Via Pluto", "Ancona", 60100, Role.AuthenticatedTourist));
			accountService.addUsers(initialUsers);
			accountService.setLoaded(true);
			}
		return new RedirectView("/");
	}
	
	@GetMapping("/startDbPOIs")
	public RedirectView insertInitialPOIRecords() throws JsonProcessingException{
		if (!poiService.isLoaded()) {
			poiResolver.resolveElements(nominatimService.getElemntsInfoWithQuery("pizzeria,passetto,ancona")).forEach(p -> {
				List<Tag> toAdd = new ArrayList<Tag>();
				poiService.addElement(p);
				toAdd.add(new Tag(p.getName(), p));
				toAdd.add(new Tag("passetto", p));
				toAdd.add(new Tag("pizzeria", p));
				tagsService.addAllTag(toAdd);
				});
			poiResolver.resolveElements(nominatimService.getElemntsInfoWithQuery("stadio,fermo")).forEach(p -> {
				poiService.addElement(p);
				List<Tag> toAdd = new ArrayList<Tag>();
				toAdd.add(new Tag(p.getName(), p));
				toAdd.add(new Tag("stadio", p));
				tagsService.addAllTag(toAdd);
				});
			poiService.setLoaded(true);
			}
		return new RedirectView("/");
	}
}

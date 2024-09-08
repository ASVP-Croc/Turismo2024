package com.speriamochemelacavo.turismo2024.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.notifications.Notification;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.services.AccountsService;
import com.speriamochemelacavo.turismo2024.services.ElementResolver;
import com.speriamochemelacavo.turismo2024.services.NominatimService;
import com.speriamochemelacavo.turismo2024.services.NotificationsService;
import com.speriamochemelacavo.turismo2024.services.POIResolver;
import com.speriamochemelacavo.turismo2024.services.POIsService;

@RestController
public class PreparationController {
	
	@Autowired
	ElementResolver elementResolver;
	
	@Autowired
	NominatimService nominatimService;
	
	@Autowired
	POIsService poiService;
	
	@Autowired
	AccountsService accountService;
	
	@Autowired
	NotificationsService notificationService;

	@GetMapping("/startDbUsers")
	public RedirectView insertInitialUserRecords(){
		if (!accountService.isLoaded()) {
			List<User> initialUsers = new ArrayList<>();
			initialUsers.add(new User("Matteo", "Pallotti", "Maverick", "maverick@gmail.com", "3929217858", "C.da San Pietro Orgiano, 13", 63900, Role.Administrator));
			initialUsers.add(new User("Lorenzo", "Crovace", "AVCP", "avcp@gmail.com", "123456789", "Via Pippo 51", 61000, Role.Curator));
			initialUsers.add(new User("Simone", "Silver", "SilverSimon", "simon@gmail.com", "987654321", "Via Pluto", 61234, Role.AuthenticatedTourist));
			accountService.addUsers(initialUsers);
			accountService.setLoaded(true);
			}
		return new RedirectView("/");
	}
	
	@GetMapping("/startDbPOIs")
	public RedirectView insertInitialPOIRecords() throws JsonProcessingException{
		if (!poiService.isLoaded()) {
			poiService.addElements(elementResolver.resolveElements(nominatimService.getPOIsInfoWithQuery("pizzeria,passetto,ancona")));
			poiService.addElements(elementResolver.resolveElements(nominatimService.getPOIsInfoWithQuery("stadio,fermo")));
			poiService.setLoaded(true);
			}
		return new RedirectView("/");
	}
}

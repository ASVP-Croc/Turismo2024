package com.speriamochemelacavo.turismo2024.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.notifications.Notification;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.services.AccountsService;
import com.speriamochemelacavo.turismo2024.services.NotificationsService;
import com.speriamochemelacavo.turismo2024.services.POIsService;

@RestController
public class PreparationController {
	
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
			initialUsers.add(new User("Simone", "Silver", "SimonSilver", "simon@gmail.com", "987654321", "Via Pluto", 61234, Role.Animator));
			accountService.addUsers(initialUsers);
			accountService.setLoaded(true);
			}
		return new RedirectView("/");
	}
	
	@GetMapping("/startDbPOIs")
	public RedirectView insertInitialPOIRecords(){
		if (!poiService.isLoaded()) {
			poiService.addElement(new PointOfInterest("passetto", "prova", "Via Passetto"));
			poiService.addElement(new PointOfInterest("centro città", "provicchia", "È in centro"));
			poiService.addElement(new PointOfInterest("lungomare", "provetta", "Vicino al mare"));
			poiService.addElement(new PointOfInterest("autostrada", "provona", "fuori città"));
			poiService.addElement(new PointOfInterest("museo", "provaccia", "vicino al centro"));
			poiService.addElement(new PointOfInterest("museo", "provaccia", "vicino al centro"));
			poiService.setLoaded(true);
			}
		return new RedirectView("/");
	}
	
	@GetMapping("/sendNotifications")
	public RedirectView sendNotifications(){
		if (!notificationService.isLoaded()&poiService.isLoaded()) {
			notificationService.addNotification(new Notification("Primo Elemento: ", "proviamo il primo elemento", poiService.findById(1)));
			notificationService.addNotification(new Notification("Secondo Elemento: ", "proviamo il secondo elemento", poiService.findById(2)));
			notificationService.addNotification(new Notification("Terzo Elemento: ", "proviamo il terzo elemento", poiService.findById(3)));
			notificationService.addNotification(new Notification("Quarto Elemento: ", "proviamo il quarto elemento", poiService.findById(4)));
			notificationService.addNotification(new Notification("Quinto Elemento: ", "proviamo il quinto elemento", poiService.findById(5)));
			notificationService.setLoaded(true);
			}
		return new RedirectView("/");
	}
}

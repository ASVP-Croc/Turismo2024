package com.speriamochemelacavo.turismo2024.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.users.AuthenticatedUser;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.services.AccountsService;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.POIsService;

@RestController
public class PreparationController {

	private static boolean isCreatedUsers = false;
	private static boolean isCreatedPOIs = false;
	
	@Autowired
	ElementsService<PointOfInterest> poiService;
	
	@Autowired
	AccountsService accountService;

	@GetMapping("/startDbUsers")
	public void insertInitialUserRecords(){
		if (!isCreatedUsers) {
			List<AuthenticatedUser> initialUsers = new ArrayList<>();
			initialUsers.add(new AuthenticatedUser(101, "Matteo", "Pallotti", "Maverick", "maverick@gmail.com", "3929217858", Role.Administrator));
			initialUsers.add(new AuthenticatedUser(102, "Lorenzo", "Crovace", "AVCP", "avcp@gmail.com", "123456789", Role.Curator));
			initialUsers.add(new AuthenticatedUser(103, "Simone", "Silver", "SimonSilver", "simon@gmail.com", "987654321", Role.Animator));
			accountService.addUsers(initialUsers);
			isCreatedUsers = true;
			}
	}
	
	@GetMapping("/startDbPOIs")
	public void insertInitialPOIRecords(){
		if (!isCreatedPOIs) {
			poiService.addElement(new PointOfInterest(1, "prova"), accountService.getLoggedUser());
			poiService.addElement(new PointOfInterest(2, "provicchia"), accountService.getLoggedUser());
			poiService.addElement(new PointOfInterest(3, "provetta"), accountService.getLoggedUser());
			isCreatedPOIs  = true;
			}
	}
}

package com.speriamochemelacavo.turismo2024.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.services.AccountsService;
import com.speriamochemelacavo.turismo2024.services.ElementsService;

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
			List<User> initialUsers = new ArrayList<>();
			initialUsers.add(new User(101, "Matteo", "Pallotti", "Maverick", "maverick@gmail.com", "3929217858", Role.Administrator));
			initialUsers.add(new User(102, "Lorenzo", "Crovace", "AVCP", "avcp@gmail.com", "123456789", Role.Curator));
			initialUsers.add(new User(103, "Simone", "Silver", "SimonSilver", "simon@gmail.com", "987654321", Role.Animator));
			accountService.addUsers(initialUsers);
			isCreatedUsers = true;
			}
	}
	
	@GetMapping("/startDbPOIs")
	public void insertInitialPOIRecords(){
		if (!isCreatedPOIs) {
			poiService.addElement(new PointOfInterest(1, "prova"));
			poiService.addElement(new PointOfInterest(2, "provicchia"));
			poiService.addElement(new PointOfInterest(3, "provetta"));
			isCreatedPOIs  = true;
			}
	}
}

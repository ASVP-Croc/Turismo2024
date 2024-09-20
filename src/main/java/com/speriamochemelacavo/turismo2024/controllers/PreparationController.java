package com.speriamochemelacavo.turismo2024.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import com.speriamochemelacavo.turismo2024.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.models.users.Role;

@RestController
public class PreparationController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ElementResolver<PointOfInterest> poiResolver;
	
	@Autowired
	private NominatimService nominatimService;
	
	@Autowired
	private ElementsWithContentsService<PointOfInterest> poiService;

	@Autowired
	private LoggedUserDetailService loggedUserService;
	
	@Autowired
	private TagsService tagService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private ElementsWithContentsService<Tour> tourService;

	@GetMapping("/startDbUsers")
	public RedirectView insertInitialUserRecords(){
		if (!loggedUserService.isLoaded()) {
			List<User> initialUsers = new ArrayList<>();
			initialUsers.add(new User("Matteo", "Pallotti", "Maverick", passwordEncoder.encode("12345678"), "maverick@gmail.com", "3929217858", "C.da San Pietro Orgiano, 13", "Fermo", "63900", Role.ROLE_ADMINISTRATOR));
			initialUsers.add(new User("Lorenzo", "Crovace", "AVCP", passwordEncoder.encode("12345678"), "avcp@gmail.com", "369852147", "Via Ancona, 188", "Macerata", "62100", Role.ROLE_CURATOR));
			initialUsers.add(new User("Simone", "Silver", "SilverSimon", passwordEncoder.encode("12345678"), "simon@gmail.com", "987654321", "Via Pluto", "Ancona", "60100", Role.ROLE_ANIMATOR));
			initialUsers.stream().forEach(u -> loggedUserService.addUser(u));
			loggedUserService.setLoaded(true);
			}
		return new RedirectView("/");
	}
	
	@GetMapping("/startDbPOIs")
	@Transactional
	public RedirectView insertInitialPOIRecords() throws IOException{
		if (!poiService.isLoaded()) {
			
			poiResolver.resolveElements(nominatimService.getInfoFromQuery("stadio, Fermo")).forEach(p -> {
				addressService.add(p.getAddress());
				p.setAuthor(loggedUserService.getLoggedUser());
				poiService.add(p);
				Set<Tag> toAdd = new HashSet<>();
				toAdd.addAll(tagService.createTagsFromString(p.getName(), p));
				toAdd.addAll(tagService.createTagsFromString(p.getDescription(), p));
				toAdd.addAll(tagService.createTagsFromString(p.getAddress().getAmenity(), p));
				toAdd.addAll(tagService.createTagsFromString(p.getAddress().getRoad(), p));
				toAdd.addAll(tagService.createTagsFromString(p.getAddress().getAmenity(), p));
				toAdd.addAll(tagService.createTagsFromString(p.getCity(), p));
				toAdd.forEach(t -> {
					tagService.add(t);
					p.getTags().add(t);
					poiService.add(p);});
				});
			
			poiResolver.resolveElements(nominatimService.getInfoFromParameter("pizzeria", "", "Ancona")).forEach(p -> {
				addressService.add(p.getAddress());
				p.setAuthor(loggedUserService.getLoggedUser());
				poiService.add(p);
				Set<Tag> toAdd = new HashSet<>();
				toAdd.addAll(tagService.createTagsFromString(p.getName(), p));
				toAdd.addAll(tagService.createTagsFromString(p.getDescription(), p));
				toAdd.addAll(tagService.createTagsFromString(p.getAddress().getAmenity(), p));
				toAdd.addAll(tagService.createTagsFromString(p.getAddress().getRoad(), p));
				toAdd.addAll(tagService.createTagsFromString(p.getAddress().getAmenity(), p));
				toAdd.addAll(tagService.createTagsFromString(p.getCity(), p));
				toAdd.forEach(t -> {
					tagService.add(t);
					p.getTags().add(t);
					poiService.add(p);});
				});
			
			poiService.setLoaded(true);
			}
		return new RedirectView("/");
	}

	@GetMapping("/startDbTours")
	public RedirectView insertInitialToursRecords() throws IOException{
		if (!tourService.isLoaded()) {
			tourService.add(new Tour("tour1", "tour della porchetta", loggedUserService.getLoggedUser(), "Ancona", "60100", new ArrayList<>(), new ArrayList<>()));
			tourService.add(new Tour("tour2", "tour della fontana", loggedUserService.getLoggedUser(), "Fermo", "63900", new ArrayList<>(), new ArrayList<>()));

		}
		tourService.setLoaded(true);

		return new RedirectView("/");
	}
}

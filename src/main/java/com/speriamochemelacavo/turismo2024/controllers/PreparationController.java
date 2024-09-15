package com.speriamochemelacavo.turismo2024.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.services.AddressService;
import com.speriamochemelacavo.turismo2024.services.ElementResolver;
import com.speriamochemelacavo.turismo2024.services.ElementsService;
import com.speriamochemelacavo.turismo2024.services.NominatimService;
import com.speriamochemelacavo.turismo2024.services.POIsService;
import com.speriamochemelacavo.turismo2024.services.TagsService;

@RestController
public class PreparationController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ElementResolver<PointOfInterest> poiResolver;
	
	@Autowired
	private NominatimService nominatimService;
	
	@Autowired
	private ElementsService<PointOfInterest> poiService;
	
	@Autowired
	private LoggedUserDetailService loggedUserService;
	
	@Autowired
	private TagsService tagsService;

	@Autowired
	private AddressService addressService;

	@GetMapping("/startDbUsers")
	public RedirectView insertInitialUserRecords(){
		if (!loggedUserService.isLoaded()) {
			List<User> initialUsers = new ArrayList<>();
			initialUsers.add(new User("Matteo", "Pallotti", "Maverick", passwordEncoder.encode("12345678"), "maverick@gmail.com", "3929217858", "C.da San Pietro Orgiano, 13", "Fermo", "63900", Role.ADMINISTRATOR));
			initialUsers.add(new User("Lorenzo", "Crovace", "AVCP", passwordEncoder.encode("12345678"), "avcp@gmail.com", "369852147", "Via Ancona, 188", "Macerata", "62100", Role.CURATOR));
			initialUsers.add(new User("Simone", "Silver", "SilverSimon", passwordEncoder.encode("12345678"), "simon@gmail.com", "987654321", "Via Pluto", "Ancona", "60100", Role.AUTHENTICATED_TOURIST));
			initialUsers.stream().forEach(u -> loggedUserService.addUser(u));
			loggedUserService.setLoaded(true);
			}
		return new RedirectView("/");
	}
	
	@GetMapping("/startDbPOIs")
	public RedirectView insertInitialPOIRecords() throws IOException{
		if (!POIsService.isLoaded()) {
			List<PointOfInterest> toCheck = new ArrayList<>();
			poiResolver.resolveElements(nominatimService.getInfoFromQuery("stadio, Fermo")).forEach(p -> {
				addressService.add(p.getAddress());
				List<Tag> toAdd = new ArrayList<>();
				List<PointOfInterest> pointToAdd = new ArrayList<>();
				pointToAdd.add(p);
				toAdd.add(new Tag(p.getName(), pointToAdd));
				toAdd.add(new Tag("passetto", p));
				toAdd.add(new Tag("pizzeria", p));
				tagsService.addAll(toAdd);
				toCheck.add(p);
				poiService.add(p, loggedUserService.getLoggedUser());
				});
			poiResolver.resolveElements(nominatimService.getInfoFromParameter("pizzeria", "", "Ancona")).forEach(p -> {
				addressService.add(p.getAddress());
				List<Tag> toAdd = new ArrayList<Tag>();
				toAdd.add(new Tag(p.getName(), p));
				toAdd.add(new Tag("stadio", p));
				poiService.add(p, loggedUserService.getLoggedUser());
				tagsService.addAll(toAdd);
				toCheck.add(p);
				});
			
			POIsService.setLoaded(true);
			}
		return new RedirectView("/");
	}
}

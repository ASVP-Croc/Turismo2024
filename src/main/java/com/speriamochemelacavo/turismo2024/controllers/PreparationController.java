package com.speriamochemelacavo.turismo2024.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import com.speriamochemelacavo.turismo2024.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;
import com.speriamochemelacavo.turismo2024.controllers.modelSetters.ModelSetter;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.security.LoggedUserDetailService;
import com.speriamochemelacavo.turismo2024.models.users.Role;

@RestController
public class PreparationController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private LoggedUserDetailService loggedUserService;

	@Autowired
	private UsersService userService;
	
	@Autowired
	private ElementResolver<PointOfInterest> poiResolver;
	
	@Autowired
	private NominatimService nominatimService;
	
	@Autowired
	private ElementsWithContentsService<PointOfInterest> poiService;
	
	@Autowired
	private TagsService tagService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private ElementsWithContentsService<Tour> tourService;
	
	@Autowired
	private ModelSetter modelSetter;	
	
	@Autowired
	private ValidationsService<Element> valisationService;

	@GetMapping("/startDbUsers")
	public RedirectView insertInitialUserRecords(){
		if (!loggedUserService.isLoaded()) {
			List<User> initialUsers = new ArrayList<>();
			initialUsers.add(new User("Matteo", "Pallotti", "Maverick", passwordEncoder.encode("12345678"), "maverick@gmail.com", "3929217858", "C.da San Pietro Orgiano, 13", "Fermo", "63900", Role.ROLE_ADMINISTRATOR));
			initialUsers.add(new User("Lorenzo", "Crovace", "AVCP", passwordEncoder.encode("12345678"), "avcp@gmail.com", "369852147", "Via Ancona, 188", "Macerata", "62100", Role.ROLE_CURATOR));
			initialUsers.add(new User("Simone", "Silver", "SilverSimon", passwordEncoder.encode("12345678"), "simon@gmail.com", "987654321", "Via Pluto", "Ancona", "60100", Role.ROLE_CONTRIBUTOR));
			userService.addAll(initialUsers);
			loggedUserService.setLoaded(true);
			}
		return new RedirectView("/");
	}
	
	@GetMapping("/startDbPOIs")
	public RedirectView insertInitialPOIRecords() throws IOException{
		
		if (!poiService.isLoaded()) {
			savePoi(poiResolver.resolveElements(nominatimService.getInfoFromQuery("stadio, Fermo")));
			savePoi(poiResolver.resolveElements(nominatimService.getInfoFromParameter("pizzeria", "", "Ancona")));
			poiService.setLoaded(true);
			}
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("toShow", poiService.findAll());
		return new RedirectView("/");
	}

	@GetMapping("/startDbTours")
	public RedirectView insertInitialToursRecords() {
		if (!tourService.isLoaded()) {
			saveTour(List.of(new Tour("tour1", "tour della porchetta", loggedUserService.getLoggedUser(), "Ancona", "60100", new ArrayList<>())));
			saveTour(List.of(new Tour("tour2", "tour della fontana", loggedUserService.getLoggedUser(), "Fermo", "63900", new ArrayList<>())));
			tourService.setLoaded(true);
		}
		modelSetter.clearAllAttributes();
		modelSetter.setBaseVisibility();
		modelSetter.getAttributes().put("toShow", tourService.findAll());
		return new RedirectView("/");
	}


	
	private void savePoi(List<PointOfInterest> poisToSave) {
		poisToSave.stream().forEach(p -> {
			p.setAuthor(loggedUserService.getLoggedUser());
			p.setAddress(addressService.findById(addressService.add(p.getAddress()).getId()));
			valisationService.requestValidation(poiService.add(p));
			poiService.add(p);
			tagService.addAll(
					tagService.createTagsFromString(
							p.getName() + "," +
							p.getDescription() + "," +
							p.getAddress().getAmenity() + "," +
							p.getAddress().getRoad() + "," +
							p.getCity(), p));
			});
	}
	

	private void saveTour(List<Tour> tourToSave) {
		tourToSave.stream().forEach(t -> {
			t.setAuthor(loggedUserService.getLoggedUser());
			valisationService.requestValidation(tourService.add(t));
			tourService.add(t);
			tagService.addAll(
					tagService.createTagsFromString(
							t.getName() + "," +
							t.getDescription() + "," +
							t.getCity(), t));
			});
	}
}

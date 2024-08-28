package com.speriamochemelacavo.turismo2024;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.speriamochemelacavo.turismo2024.models.users.AuthenticatedUser;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.services.ToursService;

class ToursManagerTest {
//
//	@Test
//	void testCreateTours() {
//		AuthenticatedUser contributor = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393", Role.Contributor);
//		AuthenticatedUser contributorAutorizzato = new AuthenticatedUser("Matteo", "Pallotti", "Maverick",  "pallotti@gmail.it", "3331001003", Role.AuthorizedContributor);
//		AuthenticatedUser turistaAutenticato = new AuthenticatedUser("Simone", "Nonsoilcognome", "SilverSimon", "simon@gmail.it", "3331001002", Role.AuthenticatedTourist);
//		Request creaTour1 = new Request(contributor, Action.CreateTour);
//		Request creaTour2 = new Request(contributorAutorizzato, Action.CreateTour);
//		Request creaTour3 = new Request(turistaAutenticato, Action.CreateTour);
//		ToursManager.execute(creaTour1);
//		ToursManager.execute(creaTour2);
//		ToursManager.execute(creaTour3);
//		assertTrue(ToursManager.getTours().count()==3);
//	}
//	
//	@Test
//	void testCreateContentInTour() {
//		AuthenticatedUser contributor = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393", Role.Contributor);
//		AuthenticatedUser contributorAutorizzato = new AuthenticatedUser("Matteo", "Pallotti", "Maverick",  "pallotti@gmail.it", "3331001003", Role.AuthorizedContributor);
//		AuthenticatedUser turistaAutenticato = new AuthenticatedUser("Simone", "Nonsoilcognome", "SilverSimon", "simon@gmail.it", "3331001002", Role.AuthenticatedTourist);
//		Request creaTour1 = new Request(contributor, Action.CreateTour);
//		Request creaTour2 = new Request(contributorAutorizzato, Action.CreateTour);
//		ToursManager.execute(creaTour1);
//		ToursManager.execute(creaTour2);
//		Request addContentInTour1 = new Request(turistaAutenticato, Action.CreateContentInTour);
//		Request addContentInTour2 = new Request(turistaAutenticato, Action.CreateContentInTour);
//		ToursManager.execute(addContentInTour1);
//		ToursManager.execute(addContentInTour2);
//		assertEquals(2, ToursManager.getTour(1).getContents().count());
//		
//	}
//	
//	@Test
//	void testAddPOItInTour() {
//		AuthenticatedUser contributor = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393", Role.Contributor);
//		AuthenticatedUser contributorAutorizzato = new AuthenticatedUser("Matteo", "Pallotti", "Maverick",  "pallotti@gmail.it", "3331001003", Role.AuthorizedContributor);
//		AuthenticatedUser turistaAutenticato = new AuthenticatedUser("Simone", "Nonsoilcognome", "SilverSimon", "simon@gmail.it", "3331001002", Role.AuthenticatedTourist);
//		Request creaTour1 = new Request(contributor, Action.CreateTour);
//		Request creaTour2 = new Request(contributorAutorizzato, Action.CreateTour);
//		ToursManager.execute(creaTour1);
//		ToursManager.execute(creaTour2);
//		Request addContentInTour1 = new Request(turistaAutenticato, Action.AddPOIInTour);
//		Request addContentInTour2 = new Request(turistaAutenticato, Action.AddPOIInTour);
//		ToursManager.execute(addContentInTour1);
//		ToursManager.execute(addContentInTour2);
//		assertEquals(4, ToursManager.getTour(1).getPois().count());
//		//modifica con clonazione. Creo un nuovo tour e aggiungo uno o più POI. il vecchio rimane invariato.
//	}
//
//	@Test
//	void testGetTour() {
//		AuthenticatedUser contributor = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393", Role.Contributor);
//		AuthenticatedUser contributorAutorizzato = new AuthenticatedUser("Matteo", "Pallotti", "Maverick",  "pallotti@gmail.it", "3331001003", Role.AuthorizedContributor);
//		AuthenticatedUser turistaAutenticato = new AuthenticatedUser("Simone", "Nonsoilcognome", "SilverSimon", "simon@gmail.it", "3331001002", Role.AuthenticatedTourist);
//		Request creaTour1 = new Request(contributor, Action.CreateTour);
//		Request creaTour2 = new Request(contributorAutorizzato, Action.CreateTour);
//		Request getTourContent = new Request(turistaAutenticato, Action.GetTours);
//		Request getTourPOI = new Request(turistaAutenticato, Action.GetTours);
//		ToursManager.execute(creaTour1);
//		ToursManager.execute(creaTour2);
//		ToursManager.execute(getTourContent);
//		ToursManager.execute(getTourPOI);
//	}
//
//	@Test
//	void testExecuteRequestInteger() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testExecuteRequestIntegerInteger() {
//		fail("Not yet implemented");
//	}

}

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import elements.Request;
import managers.POIsManager;
import managers.ToursManager;
import users.AbstractUser;
import users.Action;
import users.AuthenticatedTourist;
import users.AuthorizedContributor;
import users.Contributor;

class ToursManagerTest {

	@Test
	void testCreateTours() {
		AbstractUser contributor = new Contributor(1, "Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393");
		AbstractUser contributorAutorizzato = new AuthorizedContributor(2, "Matteo", "Pallotti", "Maverick",  "pallotti@gmail.it", "3331001003");
		AbstractUser turistaAutenticato = new AuthenticatedTourist(3, "Simone", "Nonsoilcognome", "SilverSimon", "simon@gmail.it", "3331001002");
		Request creaTour1 = new Request(contributor, Action.CreateTour);
		Request creaTour2 = new Request(contributorAutorizzato, Action.CreateTour);
		Request creaTour3 = new Request(turistaAutenticato, Action.CreateTour);
		ToursManager.execute(creaTour1);
		ToursManager.execute(creaTour2);
		ToursManager.execute(creaTour3);
		assertTrue(ToursManager.getTours().count()==3);
	}
	
	@Test
	void testCreateContentInTour() {
		AbstractUser contributor = new Contributor(1, "Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393");
		AbstractUser contributorAutorizzato = new AuthorizedContributor(2, "Matteo", "Pallotti", "Maverick",  "pallotti@gmail.it", "3331001003");
		AbstractUser turistaAutenticato = new AuthenticatedTourist(3, "Simone", "Nonsoilcognome", "SilverSimon", "simon@gmail.it", "3331001002");
		Request creaTour1 = new Request(contributor, Action.CreateTour);
		Request creaTour2 = new Request(contributorAutorizzato, Action.CreateTour);
		ToursManager.execute(creaTour1);
		ToursManager.execute(creaTour2);
		Request addContentInTour1 = new Request(turistaAutenticato, Action.CreateContentInTour);
		Request addContentInTour2 = new Request(turistaAutenticato, Action.CreateContentInTour);
		ToursManager.execute(addContentInTour1);
		ToursManager.execute(addContentInTour2);
		assertEquals(2, ToursManager.getTour(1).getContents().count());
		
	}
	
	@Test
	void testAddPOItInTour() {
		AbstractUser contributor = new Contributor(1, "Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393");
		AbstractUser contributorAutorizzato = new AuthorizedContributor(2, "Matteo", "Pallotti", "Maverick",  "pallotti@gmail.it", "3331001003");
		AbstractUser turistaAutenticato = new AuthenticatedTourist(3, "Simone", "Nonsoilcognome", "SilverSimon", "simon@gmail.it", "3331001002");
		Request creaTour1 = new Request(contributor, Action.CreateTour);
		Request creaTour2 = new Request(contributorAutorizzato, Action.CreateTour);
		ToursManager.execute(creaTour1);
		ToursManager.execute(creaTour2);
		Request addContentInTour1 = new Request(turistaAutenticato, Action.AddPOIInTour);
		Request addContentInTour2 = new Request(turistaAutenticato, Action.AddPOIInTour);
		ToursManager.execute(addContentInTour1);
		ToursManager.execute(addContentInTour2);
		assertEquals(4, ToursManager.getTour(1).getPois().count());
		//modifica con clonazione. Creo un nuovo tour e aggiungo uno o più POI. il vecchio rimane invariato.
	}

	@Test
	void testGetTour() {
		AbstractUser contributor = new Contributor(1, "Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393");
		AbstractUser contributorAutorizzato = new AuthorizedContributor(2, "Matteo", "Pallotti", "Maverick",  "pallotti@gmail.it", "3331001003");
		AbstractUser turistaAutenticato = new AuthenticatedTourist(3, "Simone", "Nonsoilcognome", "SilverSimon", "simon@gmail.it", "3331001002");
		Request creaTour1 = new Request(contributor, Action.CreateTour);
		Request creaTour2 = new Request(contributorAutorizzato, Action.CreateTour);
		Request getTourContent = new Request(turistaAutenticato, Action.GetTours);
		Request getTourPOI = new Request(turistaAutenticato, Action.GetTours);
		ToursManager.execute(creaTour1);
		ToursManager.execute(creaTour2);
		ToursManager.execute(getTourContent);
		ToursManager.execute(getTourPOI);
	}

	@Test
	void testExecuteRequestInteger() {
		fail("Not yet implemented");
	}

	@Test
	void testExecuteRequestIntegerInteger() {
		fail("Not yet implemented");
	}

}

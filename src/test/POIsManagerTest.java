package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import elements.PointOfInterest;
import elements.Request;
import users.*;
import managers.*;

class POIsManagerTest {
	
	@Test
	void testCreatePOI() {
		AbstractAuthenticatedUser contributor = new Contributor(1, "Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393");
		AbstractAuthenticatedUser contributorAutorizzato = new AuthorizedContributor(2, "Matteo", "Pallotti", "Maverick",  "pallotti@gmail.it", "3331001003");
		AbstractAuthenticatedUser turistaAutenticato = new AuthenticatedTourist(3, "Simone", "Nonsoilcognome", "SilverSimon", "simon@gmail.it", "3331001002");
		Request creaPOI1 = new Request(contributor, Action.CreatePOI);
		Request creaPOI2 = new Request(contributorAutorizzato, Action.CreatePOI);
		Request creaPOI3 = new Request(turistaAutenticato, Action.CreatePOI);
		POIsManager.execute(creaPOI1);
		POIsManager.execute(creaPOI2);
		POIsManager.execute(creaPOI3);
		assertTrue(POIsManager.getPOIs().count()==3);
	}

	@Test
	void testAddContentToPOI() {
		AbstractAuthenticatedUser contributorAutorizzato = new AuthorizedContributor(2, "Matteo", "Pallotti", "Maverick",  "pallotti@gmail.it", "3331001003");
		Request creaPOI2 = new Request(contributorAutorizzato, Action.CreatePOI);
		Request addContentToPOI = new Request(contributorAutorizzato, Action.CreateContentInPOI);
		POIsManager.execute(creaPOI2);
		POIsManager.execute(addContentToPOI);
		assertEquals(2,POIsManager.getPOI(1).getContents().count());
	}
	
	@Test
	void testGetPOIs() {
		AbstractAuthenticatedUser contributor = new Contributor(1, "Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393");
		AbstractAuthenticatedUser contributorAutorizzato = new AuthorizedContributor(2, "Matteo", "Pallotti", "Maverick",  "pallotti@gmail.it", "3331001003");
		AbstractAuthenticatedUser turistaAutenticato = new AuthenticatedTourist(3, "Simone", "Nonsoilcognome", "SilverSimon", "simon@gmail.it", "3331001002");
		Request creaPOI1 = new Request(contributor, Action.CreatePOI);
		Request creaPOI2 = new Request(contributorAutorizzato, Action.CreatePOI);
		Request getPOIs = new Request(turistaAutenticato, Action.GetPOIs);
		POIsManager.execute(creaPOI1);
		POIsManager.execute(creaPOI2);
		POIsManager.execute(getPOIs);
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

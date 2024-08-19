package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import managers.POIsManager;
import managers.RequestsManager;
import managers.ToursManager;
import users.*;

class ValidationsManagerTest {

	@Test
	void testExecuteRequest() {
		//Creo gli users
		AbstractAuthenticatedUser contributor = new Contributor(1, "Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393");
		AbstractAuthenticatedUser contributorAuth = new AuthorizedContributor(2, "Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393");
		AbstractAuthenticatedUser curatore = new Curator(3, "Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393");
		AbstractAuthenticatedUser animatore = new Animator(4, "Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393");
		//Creo i POI con users differenti
		RequestsManager.execute(contributor.sendRequest());
		RequestsManager.execute(contributorAuth.sendRequest());
		//Creo i Tour con users differenti
		RequestsManager.execute(contributor.sendRequest());
		RequestsManager.execute(contributorAuth.sendRequest());
		//Creo un contest
		RequestsManager.execute(animatore.sendRequest());
		//Aggiungo contenuti al contest con users differenti
		RequestsManager.execute(contributor.sendRequest());
		RequestsManager.execute(contributorAuth.sendRequest());
		//Validazione con Curatore e Animatore
		RequestsManager.execute(curatore.sendRequest());
		RequestsManager.execute(animatore.sendRequest());
		
	}

	@Test
	void testExecuteRequestContest() {
		AbstractAuthenticatedUser contributor = new Contributor(1, "Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393");
		AbstractAuthenticatedUser curatore = new Curator(3, "Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393");
		RequestsManager.execute(contributor.sendRequest());
		RequestsManager.execute(contributor.sendRequest());
		RequestsManager.execute(curatore.sendRequest());
		//creo 2 tour con 2 contenuti ciascuno, ognuno con 2 poi e ogni poi con un contenuto.
		assertTrue(ToursManager.getTours().count()==2);
		assertTrue(ToursManager.getTour(0).getContents().count()==2);
		assertTrue(ToursManager.getTour(3).getContents().count()==2);
		assertTrue(POIsManager.getPOIs().count()==4);
		assertTrue(POIsManager.getPOI(1).getContents().count()==1);
	}

	@Test
	void testExecuteRequestElement() {
		AbstractAuthenticatedUser contributor = new Contributor(1, "Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393");
		AbstractAuthenticatedUser curatore = new Curator(3, "Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393");
		RequestsManager.execute(contributor.sendRequest());
		RequestsManager.execute(contributor.sendRequest());
		RequestsManager.execute(curatore.sendRequest());
		//come sopra ma non valido alcun elemento
		assertTrue(ToursManager.getTours().count()==0);
		assertTrue(POIsManager.getPOIs().count()==0);
	}
}

package com.speriamochemelacavo.turismo2024;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.speriamochemelacavo.turismo2024.managers.POIsManager;
import com.speriamochemelacavo.turismo2024.managers.RequestsManager;
import com.speriamochemelacavo.turismo2024.managers.ToursManager;
import com.speriamochemelacavo.turismo2024.users.*;

class ValidationsManagerTest {

	@Test
	void testExecuteRequest() {
		//Creo gli users
		AuthenticatedUser contributor = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393", Role.Contributor);
		AuthenticatedUser contributorAuth = new AuthenticatedUser("Matteo", "Pallotti", "Maverick",  "pallotti@gmail.it", "3331001003", Role.AuthorizedContributor);
		AuthenticatedUser curatore = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393", Role.Curator);
		AuthenticatedUser animatore = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393", Role.Animator);
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
		AuthenticatedUser contributor = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393", Role.Contributor);
		AuthenticatedUser curatore = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393", Role.Curator);
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
		AuthenticatedUser contributor = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393", Role.Contributor);
		AuthenticatedUser curatore = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393", Role.Curator);
		RequestsManager.execute(contributor.sendRequest());
		RequestsManager.execute(contributor.sendRequest());
		RequestsManager.execute(curatore.sendRequest());
		//come sopra ma non valido alcun elemento
		assertTrue(ToursManager.getTours().count()==0);
		assertTrue(POIsManager.getPOIs().count()==0);
	}
}

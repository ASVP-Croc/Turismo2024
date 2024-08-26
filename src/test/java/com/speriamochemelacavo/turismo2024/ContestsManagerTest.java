package com.speriamochemelacavo.turismo2024;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.speriamochemelacavo.turismo2024.elements.Request;
import com.speriamochemelacavo.turismo2024.managers.ContestsManager;
import com.speriamochemelacavo.turismo2024.managers.ToursManager;
import com.speriamochemelacavo.turismo2024.users.*;


class ContestsManagerTest extends ContestsManager {

	@Test
	void testCreateContest() {
		AuthenticatedUser animatore = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393", Role.Animator);
		Request creaContest = new Request(animatore, Action.CreateContest);
		Request creaTour = new Request(animatore, Action.CreateTour);
		ToursManager.execute(creaTour);
		ContestsManager.execute(creaContest);
		ContestsManager.execute(creaContest);
		assertTrue(ContestsManager.getContests().count()==2);
	}
	@Test
	void testGetContests() {
		AuthenticatedUser animatore = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393", Role.Animator);
		Request creaContest = new Request(animatore, Action.CreateContest);
		Request creaTour = new Request(animatore, Action.CreateTour);
		ToursManager.execute(creaTour);
		ContestsManager.execute(creaContest);
		ContestsManager.execute(creaContest);
		Request getContests = new Request(animatore, Action.GetContests);
		ContestsManager.execute(getContests);
	}

	@Test
	void testAddContentToContest() {
		AuthenticatedUser animatore = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393", Role.Animator);
		Request creaContest = new Request(animatore, Action.CreateContest);
		Request creaTour = new Request(animatore, Action.CreateTour);
		ToursManager.execute(creaTour);
		ContestsManager.execute(creaContest);
		ContestsManager.execute(creaContest);
		Request createContent1 = new Request(animatore, Action.CreateContentInContest);
		Request createContent2 = new Request(animatore, Action.CreateContentInContest);
		ContestsManager.execute(createContent1);
		ContestsManager.execute(createContent2);
		assertEquals(2,ContestsManager.getContest(5).getContents().count());
		
	}

	@Test
	void testExecuteRequest() {
		fail("Not yet implemented");
	}

	@Test
	void testExecuteRequestIntegerInteger() {
		fail("Not yet implemented");
	}

}

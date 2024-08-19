package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import elements.Request;
import managers.ContestsManager;
import managers.POIsManager;
import managers.ToursManager;
import users.AbstractAuthenticatedUser;
import users.Action;
import users.Animator;
import users.AuthenticatedTourist;
import users.AuthorizedContributor;
import users.Contributor;

class ContestsManagerTest extends ContestsManager {

	@Test
	void testCreateContest() {
		AbstractAuthenticatedUser animatore = new Animator(1, "Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393");
		Request creaContest = new Request(animatore, Action.CreateContest);
		Request creaTour = new Request(animatore, Action.CreateTour);
		ToursManager.execute(creaTour);
		ContestsManager.execute(creaContest);
		ContestsManager.execute(creaContest);
		assertTrue(ContestsManager.getContests().count()==2);
	}
	@Test
	void testGetContests() {
		AbstractAuthenticatedUser animatore = new Animator(1, "Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393");
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
		AbstractAuthenticatedUser animatore = new Animator(1, "Lorenzo", "Crovace", "ASVP_CROC", "lorenzo.crovace@studenti.unicam.it", "3492913393");
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

package test;

import managers.RequestsManager;
import users.*;

public class ProvaUtente {
	
	public void testUser() {
		GeneralUser = new AuthenticatedUser("L", "C", "A", "e", "3", Role.Administrator);
	GeneralUser user = new User();
	user.getRole();
	RequestsManager.execute(user.sendRequest());
	AbstractAuthenticatedUser user2 = new AuthenticatedUser("L", "C", "A", "e", "3");
	user.
	
	}

}


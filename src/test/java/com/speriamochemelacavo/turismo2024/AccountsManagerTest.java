package com.speriamochemelacavo.turismo2024;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.speriamochemelacavo.turismo2024.models.users.AuthenticatedUser;
import com.speriamochemelacavo.turismo2024.models.users.Role;
import com.speriamochemelacavo.turismo2024.services.AccountsService;

class AccountsManagerTest {
//	
//	@Test
//	void defineRoleTest() {
//		//admin
//		AuthenticatedUser admin = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP","email", "numero",Role.Administrator);
//		//Tourist
//		GeneralUser user1 = new User();
//		//Registration
//		RequestsManager.execute(user1.sendRequest());
//		//Check registration
//		AccountsManager.getUsers().forEach(user->System.out.println("Id: "+user.getId()+ " Username: "+user.getUserName()+ " Ruolo: "+
//		user.getRole()));
//		//Define Role
//		RequestsManager.execute(admin.sendRequest());
//		//check change
//		AccountsManager.getUsers().forEach(user->System.out.println("Id: "+user.getId()+ " Username: "+user.getUserName()+ " Ruolo: "+
//				user.getRole()));
//	}
//
//	@Test
//	void saveElements() {
//		AuthenticatedUser admin = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP","email", "numero",Role.Administrator);
//		GeneralUser user1 = new User();
//		//Registration
//		RequestsManager.execute(user1.sendRequest());
//		//Change Role
//		RequestsManager.execute(admin.sendRequest());
//		user1 = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP","email", "numero",Role.AuthorizedContributor);
//		//Create elements
//		RequestsManager.execute(user1.sendRequest());
//		//Registration
//		GeneralUser user2 = new User();
//		RequestsManager.execute(user2.sendRequest());
//		user2 = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP","email", "numero",Role.AuthenticatedTourist);
//		//Visualise & save element
//		RequestsManager.execute(user2.sendRequest());
//		AccountsManager.getUsers().forEach(user->user.getSavedElement().forEach(elem->System.out.println("Dsc: "+ elem.getDescription()+
//		" Id: "+ elem.getId())));
//		
//	}
//
//	@Test
//	void notification() {
//		AuthenticatedUser admin = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP","email", "numero",Role.Administrator);
//		GeneralUser user1 = new User();
//		GeneralUser user2 = new User();
//		//Registrazione
//		RequestsManager.execute(user1.sendRequest());
//		RequestsManager.execute(user2.sendRequest());
//		//Change Role
//		RequestsManager.execute(admin.sendRequest());
//		user1 = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP","email", "numero",Role.Curator);
//		RequestsManager.execute(admin.sendRequest());
//		user2 = new AuthenticatedUser("Lorenzo", "Crovace", "ASVP","email", "numero",Role.Contributor);
//		//Create elements
//		RequestsManager.execute(user2.sendRequest());
//		//Verifica notifica Curatore
//		AccountsManager.getUsers().forEach(user->user.getNotifications());
//		//Validazione
//		RequestsManager.execute(user1.sendRequest());
//		//verifica notifica Contributor
//		AccountsManager.getUsers().forEach(user->user.getNotifications());
//	}
//
//	@Test
//	void testExecuteRequestNotificationInteger() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testExecuteRequestNotification() {
//		fail("Not yet implemented");
//	}

}

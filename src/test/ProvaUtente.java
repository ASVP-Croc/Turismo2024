package test;

import managers.AccountsManager;
import managers.RequestsManager;
import users.*;

class ProvaUtente{
	
	public void testUser() {
		//Creazione admin
		GeneralUser user1 = new User();
		RequestsManager.execute(user1.sendRequest());
		//creazione utente semplice+registrazione
		GeneralUser user2 = new User();
		RequestsManager.execute(user2.sendRequest());
		//cambio ruolo
		RequestsManager.execute(user1.sendRequest());
	
	}

}


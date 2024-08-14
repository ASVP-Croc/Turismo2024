import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import managers.ContentsManager;
import managers.NotificationsManager;
import managers.POIsManager;
import elements.Request;
import grafic.HomePage;

import managers.RequestsManager;
import managers.ToursManager;
import managers.ViewManager;
import users.AbstractUser;
import users.Action;
import users.AuthorizedContributor;
import users.Contributor;
import users.Curator;

public class Main {
	public static void main(String[] args) throws IOException {
		

		AbstractUser userLogged = new Contributor(1, "Lorenzo", "Crovace", "ASVP_Croc", "crovace@gmail.it", "3331001001");
		
		

//		AbstractUser.addUsers(new Contributor(1, "Lorenzo", "Crovace", "ASVP_Croc", "crovace@gmail.it", "3331001001"));
//		AbstractUser.addUsers(new AuthorizedContributor(2, "Simone", "Nonsoilcognome", "SilverSimon", "simon@gmail.it", "3331001002"));
//		AbstractUser.addUsers(new Curator(3, "Matteo", "Pallotti", "Maverick", "pallotti@gmail.it", "3331001003"));

		start();
	}

	private static void start() throws IOException{
		ViewManager.start();
	}

//	requestManager.addRequest(user1.sendRequest());
//	requestManager.addRequest(user2.sendRequest());
//	requestManager.addRequest(user3.sendRequest());
//	
//	requestManager.execute();
//	
//	for(int i=0; i<3; i++) {
//		System.out.println(poiManager.getPois().get(i).toString());
//	}
//	
//	poiManager.getPOI(0).getContent(0).toString();
//	
//	System.out.println(tourManager.getTours().toString());
//	tourManager.getTour(0).getContent(0);

}

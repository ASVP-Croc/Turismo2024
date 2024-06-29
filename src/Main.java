import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import elements.Request;
import managers.NotificationManager;
import managers.POIManager;
import managers.RequestsManager;
import managers.TourManager;
import users.AbstractUser;
import users.Action;
import users.AuthorizedContributor;
import users.Contributor;
import users.Curator;

public class Main {
	public static void main(String[] args) throws IOException {

		AbstractUser userLogged = null;
		POIManager poiManager = new POIManager();
		TourManager tourManager = new TourManager();
		NotificationManager notificationManager = new NotificationManager();
		RequestsManager requestManager = new RequestsManager(poiManager, tourManager, notificationManager);
		List<AbstractUser> users = new ArrayList<AbstractUser>();

		users.add(new Contributor(1, "Lorenzo", "Crovace", "ASVP_Croc", "crovace@gmail.it", "3331001001"));
		users.add(new AuthorizedContributor(2, "Simone", "Nonsoilcognome", "SilverSimon", "simon@gmail.it",
				"3331001002"));
		users.add(new Curator(3, "Matteo", "Pallotti", "Maverick", "pallotti@gmail.it", "3331001003"));

		start(users, userLogged);

//		requestManager.addRequest(user1.sendRequest());
//		requestManager.addRequest(user2.sendRequest());
//		requestManager.addRequest(user3.sendRequest());
//		
//		requestManager.execute();
//		
//		for(int i=0; i<3; i++) {
//			System.out.println(poiManager.getPois().get(i).toString());
//		}
//		
//		poiManager.getPOI(0).getContent(0).toString();
//		
//		System.out.println(tourManager.getTours().toString());
//		tourManager.getTour(0).getContent(0);

	}

	private static void start(List<AbstractUser> users, AbstractUser userLogged) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Benvenuto!");
		
		boolean chosen = false;
		
		while(!chosen) {
		try {
			System.out.println("Seleziona il tuo account, oppure < 0 > per uscire:\n");

			for (int i = 0; i < users.size(); i++) {
				AbstractUser tmp = users.get(i);
				System.out.println(tmp.getId() + ": "
								+ tmp.getSurname() +
								", " + tmp.getName() +
								" --> " + tmp.getUserName() +
								"(" + tmp.getRole() + ")");
				}

			System.out.print("\n");
			int result = Integer.parseInt(reader.readLine()) - 1;
			
			if (result == -1) {
				System.out.println("\nDisconnessione effettuata. A Presto!");
				chosen = true;
				}
			else if (result > -1 && result <= users.size()) {
				userLogged = users.get(result);
				System.out.println("\nHai eseguito l'accesso come - " + userLogged.getUserName() + " -");
				chosen = true;
				}
			else if (result < -1 || result > users.size() - 1) {
				System.out.println("\nUtente non trovato");
				}
			}
		catch (NumberFormatException e) {
			System.out.println("Scelta non valida");
			}
		catch (IOException e) {
			System.out.println("Scelta non valida");
			}
		}
	}
}

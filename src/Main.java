import java.io.IOException;

import managers.ContentManager;
import managers.NotificationManager;
import managers.POIManager;
import managers.RequestsManager;
import managers.TourManager;
import users.AbstractUser;
import users.AuthorizedContributor;
import users.Contributor;
import users.Curator;

public class Main {
	public static void main(String[] args) throws IOException {
		ContentManager contentManager = new ContentManager();
		POIManager poiManager = new POIManager(contentManager);
		TourManager tourManager = new TourManager(contentManager);
		NotificationManager notificationManager = new NotificationManager();
		RequestsManager controller = new RequestsManager(poiManager, tourManager, notificationManager);
		
	
		AbstractUser user1 = new Contributor(1, "Lorenzo", "Crovace", "ASVP_Croc", "crovace@gmail.it",
				"3331001001");
		AbstractUser user2 = new AuthorizedContributor(2, "Simone", "Nonsoilcognome", "SilverSimon", "simon@gmail.it",
				"3331001002");
		AbstractUser user3 = new Curator(3, "Matteo", "Pallotti", "Maverick", "pallotti@gmail.it",
				"3331001003");
		
		controller.addRequest(user1.sendRequest());
		controller.addRequest(user2.sendRequest());
		controller.addRequest(user3.sendRequest());
		
		controller.execute();
		
		for(int i=0; i<3; i++) {
			System.out.println(poiManager.getPois().get(i).toString());
		}
		//poiManager.getPOI(0).getContent(0).toString();
		
		//System.out.println(tourManager.getTours().toString());
		//tourManager.getTour(0).getContent(0);
	}

}

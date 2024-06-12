import java.io.IOException;

public class Demo1Iterazione {
	public static void main(String[] args) throws IOException {
		POIManager poiManager = new POIManager();
		TourManager tourManager = new TourManager();
		NotificationManager notificationManager = new NotificationManager();
		RequestsManager controller = new RequestsManager(poiManager, tourManager, notificationManager);
		
	
		AbstractUser user1 = new Contributor(1, "Fabio", "Grandi", "Fabiobig", "fabiograndi@gradi.it",
				"3331001001");
		AbstractUser user2 = new AuthorizedContributor(2, "Fabio", "Grandi", "Fabiobig", "fabiograndi@gradi.it",
				"3331001001");
		AbstractUser user3 = new Curator(3, "Fabio", "Grandi", "Fabiobig", "fabiograndi@gradi.it",
				"3331001001");
		
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

package managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import elements.*;
import users.Action;

public class ContestsManager {
	private final POIsManager poiManager;
	private final ToursManager tourManager;
	private final Map<Integer, Contest> contests;
	
	public ContestsManager(POIsManager poiManager, ToursManager tourManager) {
		this.poiManager = poiManager;
		this.tourManager = tourManager;
		this.contests = new HashMap<>();
	}
	
	public Contest execute(Request request) {
		Action action = request.getAction();
		if(action==Action.CreateContest) {
			Contest contest = createContest();
			
			return contest;
		} else if(action==Action.CreateContentInContest) {
			return addContentToContest(request);
		} else return null;
	}
	
	private Contest addContentToContest(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	private Contest createContest() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleziona 1-POI o 2-Itinerario per associare un luogo al Contest.");
		int select = scanner.nextInt();
		if(select==1) {
			poiManager.getPOIs();
			System.out.println("Seleziona un POI inserendo l'ID.");
			Integer id = scanner.nextInt();
			poiManager.getPOI(id);
			System.out.println("Inserisci una descrizione per il Contest: ");
			String description = scanner.nextLine();
			return new Contest(description,poiManager.getPOI(id));
		} else if(select==2) {
			tourManager.getTours();
			System.out.println("Seleziona un Tour inserendo l'ID.");
			Integer id = scanner.nextInt();
			tourManager.getTour(id);
			System.out.println("Inserisci una descrizione per il Contest: ");
			String description = scanner.nextLine();
			return new Contest(description,tourManager.getTour(id));
		} return null;
	}
	
	public boolean execute(Request request, Integer id1, Integer id2) {
		if(request.getAction()==Action.Post) {
			getContest(id1).getContent(id2).setVisibility();
			return true;
		} else if(request.getAction()==Action.Delete) {
			getContest(id1).deleteContent(id2);
			return true;
		} else return false;
	}
		
	private Contest getContest(Integer id) {
		return contests.get(id);
		
	}

	public Stream<Contest> getContests() {
		return contests.values().stream();
	}
	
}

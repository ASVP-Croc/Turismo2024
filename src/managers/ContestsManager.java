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
	private final ContentsManager contentManager;
	private final Map<Integer, Contest> contests;
	
	public ContestsManager(POIsManager poiManager, ToursManager tourManager, ContentsManager contentManager) {
		this.poiManager = poiManager;
		this.tourManager = tourManager;
		this.contentManager = contentManager;
		this.contests = new HashMap<>();
	}
	
	public Contest getContest(Integer id) {
		return contests.get(id);
	}
	
	public Stream<Contest> getContests() {
		return contests.values().stream();
	}
	
	public Contest execute(Request request) {
		Action action = request.getAction();
		if(action==Action.CreateContest) {
			return createContest(request);
		} else if(action==Action.CreateContentInContest) {
			return addContentToContest(request);
		} else if(action==Action.GetContests) showContests();
		return null;
	}
	
	private void showContests() {
		Scanner scanner = new Scanner(System.in);
		getContests().forEach(elem->System.out.println(elem));
		System.out.println("Inserisci l'ID per visualizzare il Contenuto: ");
		Integer id = scanner.nextInt();
		showContentsInPOI(id);
	}
	
	private void showContentsInPOI(Integer id) {
		getContest(id).getContents().forEach(elem->System.out.println(elem));
	}
	
	private Contest addContentToContest(Request request) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleziona un Tour a cui aggiungere un Contenuto:");
		getContests().forEach(elem->System.out.println(elem));
		System.out.println("Insrisci l'ID per selzionare il Contest: ");
		Integer id = scanner.nextInt();
		return addContentToContest(id, request);
		}
	
	private Contest addContentToContest(Integer id, Request request) {
		getContest(id).addContent(contentManager.execute(request, getContest(id)));
		return getContest(id);
	}

	private Contest createContest(Request request) {
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
			Contest contest = new Contest(description,poiManager.getPOI(id));
			System.out.println("Desideri inserire ora un contenuto per il Contest creato? Si/No");
			String scan = scanner.nextLine();
			if(scan=="Si") {
				addContentToContest(contest.getId(), request);
			} else return contest;
		} else if(select==2) {
			tourManager.getTours();
			System.out.println("Seleziona un Tour inserendo l'ID.");
			Integer id = scanner.nextInt();
			tourManager.getTour(id);
			System.out.println("Inserisci una descrizione per il Contest: ");
			String description = scanner.nextLine();
			Contest contest = new Contest(description,poiManager.getPOI(id));
			System.out.println("Desideri inserire ora un contenuto per il Contest creato? Si/No");
			String scan = scanner.nextLine();
			if(scan=="Si") {
				addContentToContest(contest.getId(), request);
			} else return contest;
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
	
}

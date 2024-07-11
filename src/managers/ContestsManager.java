package managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import elements.*;
import users.Action;

public class ContestsManager  {
	private final static Map<Integer, Contest> contests = new HashMap<>();
	
	public static Contest getContest(Integer id) {
		return contests.get(id);
	}
	
	public static Stream<Contest> getContests() {
		return contests.values().stream();
	}
	
	public static Contest execute(Request request) {
		Action action = request.getAction();
		if(action==Action.CreateContest) {
			return createContest(request);
		} else if(action==Action.CreateContentInContest) {
			return addContentToContest(request);
		} else if(action==Action.GetContests) showContests();
		return null;
	}
	
	private static void showContests() {
		Scanner scanner = new Scanner(System.in);
		getContests().forEach(elem->System.out.println(elem));
		System.out.println("Inserisci l'ID per visualizzare il Contenuto: ");
		Integer id = scanner.nextInt();
		showContentsInPOI(id);
	}
	
	private static void showContentsInPOI(Integer id) {
		getContest(id).getContents().forEach(elem->System.out.println(elem));
	}
	
	private static Contest addContentToContest(Request request) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleziona un Tour a cui aggiungere un Contenuto:");
		getContests().forEach(elem->System.out.println(elem));
		System.out.println("Insrisci l'ID per selzionare il Contest: ");
		Integer id = scanner.nextInt();
		return addContentToContest(id, request);
		}
	
	private static Contest addContentToContest(Integer id, Request request) {
		getContest(id).addContent(ContentsManager.execute(request, getContest(id)));
		return getContest(id);
	}

	private static Contest createContest(Request request) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleziona 1-POI o 2-Itinerario per associare un luogo al Contest.");
		int select = scanner.nextInt();
		if(select==1) {
			POIsManager.getPOIs();
			System.out.println("Seleziona un POI inserendo l'ID.");
			Integer id = scanner.nextInt();
			POIsManager.getPOI(id);
			System.out.println("Inserisci una descrizione per il Contest: ");
			String description = scanner.nextLine();
			Contest contest = new Contest(description,POIsManager.getPOI(id));
			System.out.println("Desideri inserire ora un contenuto per il Contest creato? Si/No");
			String scan = scanner.nextLine();
			if(scan=="Si") {
				addContentToContest(contest.getId(), request);
			} else return contest;
		} else if(select==2) {
			ToursManager.getTours();
			System.out.println("Seleziona un Tour inserendo l'ID.");
			Integer id = scanner.nextInt();
			ToursManager.getTour(id);
			System.out.println("Inserisci una descrizione per il Contest: ");
			String description = scanner.nextLine();
			Contest contest = new Contest(description, ToursManager.getTour(id));
			System.out.println("Desideri inserire ora un contenuto per il Contest creato? Si/No");
			String scan = scanner.nextLine();
			if(scan=="Si") {
				return addContentToContest(contest.getId(), request);
			} else return contest;
		} return null;
			
	}
	
	public static boolean execute(Request request, Integer id1, Integer id2) {
		if(request.getAction()==Action.Post) {
			getContest(id1).getContent(id2).setVisibility();
			return true;
		} else if(request.getAction()==Action.Delete) {
			getContest(id1).deleteContent(id2);
			return true;
		} else return false;
	}
	
}

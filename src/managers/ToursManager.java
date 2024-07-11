package managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import elements.*;
import users.Action;

public class ToursManager {
	private final static Map<Integer, Tour> tours = new HashMap<>();

	public static Stream<Tour> getTours() {
		return tours.values().stream();
	}
	
	public static Tour getTour(Integer id) {
		return tours.get(id);
	}
	
	public static Stream<Content> getContents(Integer id){
		return getTour(id).getContents();
	}

	private static Tour createTour(Request request) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci una descrizione per il Tour da creare: ");
		String text = scanner.nextLine();
		Tour tour = new Tour(text);
		tours.put(tour.getId(), tour);
		System.out.println("Inserisci almeno 2 POI per completare il Tour: ");
		//rendere possibile in loop
		System.out.println("1- per Creare un nuovo POI!");
		System.out.println("2- per aggiungere un POI già esistente!");
		//fine loop
		System.out.println("3- per Creare un nuovo Contenuto da aggiungere al Tour!");
		int select = scanner.nextInt();
		if(select==1) {
			POIsManager.execute(request);
			return addPOIToTour(request, tour.getId());
		} else if(select==2) {
			return addPOIToTour(request, tour.getId());
		}
		else if(select==3) {
			return addContentToTour(request,tour.getId());
		} sendValidation(request, tour);
		return tour;
	}
	
	private static Tour addPOIToTour(Request request, Integer id) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleziona un POI da aggiungere scrivendo l'ID!");
		POIsManager.getPOIs().forEach(poi->System.out.println(poi));
		Integer integer = scanner.nextInt();
		Request nextRequest = new Request(request.getUser(), Action.AddPOIInTour);
		sendValidation(nextRequest, tours.get(id));
		tours.get(id).addPoi(POIsManager.getPOI(integer));
		return tours.get(id);
	}
	
	private static Tour addPOIToTour() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleziona un Tour inserendo l'ID");
		getTours().forEach(tour->System.out.println(tour));
		Integer id = scanner.nextInt();
		System.out.println("Seleziona un POI da aggiungere scrivendo l'ID!");
		POIsManager.getPOIs().forEach(poi->System.out.println(poi));
		Integer integer = scanner.nextInt();
		tours.get(id).addPoi(POIsManager.getPOI(integer));
		return tours.get(id);
	}//gestione aggiunta poi con validazione
	
	
	
	private static Tour addContentToTour(Request request, Integer id) {
		tours.get(id).addContent(ContentsManager.execute(request, tours.get(id)));
		return tours.get(id);
	}
	
	private static Tour addContentToTour(Request request) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleziona un Tour inserendo l'ID");
		getTours().forEach(tour->System.out.println(tour));
		Integer id = scanner.nextInt();
		tours.get(id).addContent(ContentsManager.execute(request, tours.get(id)));
		return tours.get(id);
	}
	
	private static void showTours() {
		Scanner scanner = new Scanner(System.in);
		getTours().forEach(elem->System.out.println(elem));
		System.out.println("Inserisci l'Id per selezionare un Tour: ");
		Integer id = scanner.nextInt();
		System.out.println("Seleziona 1 per visualizzare i POIs, 2 per visualizzare i Contenuti");
		Integer select = scanner.nextInt();
		if(select==1) showPOIsInTour(id);
		else if(select==2) showContentsInTour(id);
	}
	
	private static void showContentsInTour(Integer id) {
		getTour(id).getContents().forEach(elem->System.out.println(elem));
	}
	
	private static void showPOIsInTour(Integer id) {
		getTour(id).getPois().forEach(elem->System.out.println(elem));
	}

	public static Tour execute(Request request) {
		Action action = request.getAction();
		if(action==Action.CreateTour) {
			return createTour(request);
		} else if(action==Action.CreateContentInTour) {
			return addContentToTour(request);
		} else if(action==Action.AddPOIInTour) {
			return addPOIToTour();
		} else if(action==Action.GetTours) {
			showTours();
		}
		return null;
	}
	
	private static boolean sendValidation(Request request, Tour tour) {
		return ValidationsManager.execute(request, tour);
	}
	
	public static boolean execute(Request request, Integer id) {
		if(request.getAction()==Action.Post) {
			getTour(id).setVisibility();
			return true;
		} else if(request.getAction()==Action.Delete) {
			tours.remove(id);
			return true;
		} else return false;
	}
		
	public static boolean execute(Request request, Integer id1, Integer id2) {
		if(request.getAction()==Action.Post) {
			getTour(id1).getContent(id2).setVisibility();
			return true;
		} else if(request.getAction()==Action.Delete) {
			getTour(id1).deleteContent(id2);
			return true;
		} else return false;
	}

}

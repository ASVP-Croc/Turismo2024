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

	private static Tour createTour(Request request) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci una descrizione per il Tour da creare: ");
		String text = scanner.nextLine();
		Tour tour = new Tour(text);
		tours.put(tour.getId(), tour);
		System.out.println("Inserisci almeno 2 POI per completare il Tour: ");
		for(int i=0; i<2;i++) {
		System.out.println("1- per Creare un nuovo POI!");
		System.out.println("2- per aggiungere un POI già esistente!");
		int select = scanner.nextInt();
		if(select==1) {
			Request nextRequest = new Request(request.getUser(), Action.CreatePOI);
			POIsManager.execute(nextRequest);
			addPOIToTour(request, tour);
		} else if(select==2) {
			addPOIToTour(request,tour);
		}
		}
		System.out.println("1- per Creare un nuovo POI!");
		System.out.println("2- per aggiungere un POI già esistente!");
		System.out.println("3- per Creare un nuovo Contenuto da aggiungere al Tour!");
		int select1 = scanner.nextInt();
		if(select1==1) {
			Request nextRequest = new Request(request.getUser(), Action.CreatePOI);
			POIsManager.execute(nextRequest);
			return addPOIToTour(request, tour);
		} else if(select1==2) {
			return addPOIToTour(request,tour);
		}
		else if(select1==3) {
			return addContentToTour(request,tour);
		} sendValidation(request, tour);
		return tour;
	}
	
	private static Tour addPOIToTour(Request request, Tour tour) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleziona un POI da aggiungere scrivendo l'ID!");
		POIsManager.getPOIs().forEach(poi->System.out.println("Dsc: " + poi.getDescription()+ " Id: "+poi.getId()));
		Integer id = scanner.nextInt();
		tour.addPOI(POIsManager.getPOI(id));
		Request nextRequest = new Request(request.getUser(), Action.AddPOIInTour);
		sendValidation(nextRequest, tour);
		return tour;
	}
	
	private static Tour addPOIToTour(Request request) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleziona un Tour inserendo l'ID");
		getTours().forEach(tour->System.out.println("Dsc: " + tour.getDescription()+ " Id: "+tour.getId()));
		Integer id = scanner.nextInt();
		return addPOIToTour(request, tours.get(id));
	}//gestione aggiunta poi con validazione
	
	
	
	private static Tour addContentToTour(Request request, Tour tour) {
		Request nextRequest = new Request(request.getUser(), Action.CreateContentInTour);
		tour.addContent(ContentsManager.execute(nextRequest,tour));
		return tour;
	}
	
	private static Tour addContentToTour(Request request) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleziona un Tour inserendo l'ID");
		getTours().forEach(tour->System.out.println("Dsc: " + tour.getDescription()+ " Id: "+tour.getId()));
		Integer id = scanner.nextInt();
		Tour tour = tours.get(id);
		return addContentToTour(request, tour);
	}
	
	private static void showTours() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci l'Id per selezionare un Tour: ");
		getTours().forEach(elem->System.out.println("Dsc: " + elem.getDescription()+ " Id: "+elem.getId()));
		Integer id = scanner.nextInt();
		Tour tour = tours.get(id);
		System.out.println("Dsc: " + tour.getDescription()+ " Id: "+tour.getId());
		System.out.println("Seleziona 1 per visualizzare i POIs, 2 per visualizzare i Contenuti");
		Integer select = scanner.nextInt();
		if(select==1) showPOIsInTour(id);
		else if(select==2) showContentsInTour(id);
	}
	
	private static void showContentsInTour(Integer id) {
		getTour(id).getContents().forEach(elem->System.out.println("Dsc: " + elem.getText()+ " Id: "+elem.getId()));
	}
	
	private static void showPOIsInTour(Integer id) {
		getTour(id).getPois().forEach(elem->System.out.println("Dsc: " + elem.getDescription()+ " Id: "+elem.getId()));
	}

	public static Tour execute(Request request) {
		Action action = request.getAction();
		if(action==Action.CreateTour) {
			return createTour(request);
		} else if(action==Action.CreateContentInTour) {
			return addContentToTour(request);
		} else if(action==Action.AddPOIInTour) {
			return addPOIToTour(request);
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

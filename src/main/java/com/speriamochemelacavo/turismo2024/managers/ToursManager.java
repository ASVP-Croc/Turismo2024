package com.speriamochemelacavo.turismo2024.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import com.speriamochemelacavo.turismo2024.elements.*;
import com.speriamochemelacavo.turismo2024.users.Action;

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
		Tour tour = new Tour(text, request.getUser().getId());
		tours.put(tour.getId(), tour);
		sendValidation(request, tour);
		System.out.println("Inserisci almeno 2 POI per completare il Tour: ");
		while(tour.getPois().count()<2){
		addPOIInTour(request, tour);
		}
		addContentToTour(request, tour);
		return tour;
	}
	
	private static void addPOIInTour(Request request, Tour tour) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1- per Creare un nuovo POI, 2- per aggiungere un POI già esistente, 3-Esci");
		int select = scanner.nextInt();
		while(select==1 || select==2) {

		if(select==1) {
			PointOfInterest poi = createPOI(request);
			addPOIToTour(request, tour, poi);
			} else if(select==2) {
			addPOIToTour(request,tour);
			} 
		System.out.println("1- per Creare un nuovo POI, 2- per aggiungere un POI già esistente, 3-Esci");
		select = scanner.nextInt();
		}
	}
	
	private static Tour addPOIToTour(Request request, Tour tour, PointOfInterest poi) {
		tour.addPOI(poi);
		return tour;
	}

	private static PointOfInterest createPOI(Request request) {
		Request nextRequest = new Request(request.getUser(), Action.CreatePOI);
		return POIsManager.execute(nextRequest);
	}
	
	private static Tour addPOIToTour(Request request, Tour tour) {
		Scanner scanner = new Scanner(System.in);
		int select = 1;
		while(select==1) {
		System.out.println("Seleziona un POI da aggiungere scrivendo l'ID!");
		POIsManager.getPOIs().forEach(poi->System.out.println("Dsc: " + poi.getDescription()+ " Id: "+poi.getId()));
		Integer id = scanner.nextInt();
		tour.addPOI(POIsManager.getPOI(id));
		System.out.println("1-aggiungi un altro POI, 2-esci");
		select = scanner.nextInt();
		}
		return tour;
	}
	
	private static Tour addPOIToTour(Request request) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleziona un Tour inserendo l'ID");
		getTours().forEach(tour->System.out.println("Dsc: " + tour.getDescription()+ " Id: "+tour.getId()));
		Integer id = scanner.nextInt();
		Tour tour = new Tour(tours.get(id), request.getUser().getId());
		sendValidation(request, tour);
		return addPOIToTour(request, tour);
	}
	
	private static Tour addContentToTour(Request request, Tour tour) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1- per Creare un nuovo Contenuto da aggiungere al Tour, 2-Esci");
		int select = scanner.nextInt();
		while(select==1) {
			Request nextRequest = new Request(request.getUser(), Action.CreateContentInTour);
			tour.addContent(ContentsManager.execute(nextRequest,tour));
			System.out.println("1- per Creare un nuovo Contenuto da aggiungere al Tour, 2-Esci");
			select = scanner.nextInt();
		} return tour;
	}
	
	private static Tour addContentToTour(Request request) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleziona un Tour inserendo l'ID");
		getTours().forEach(tour->System.out.println("Dsc: " + tour.getDescription()+ " Id: "+tour.getId()));
		Integer id = scanner.nextInt();
		return addContentToTour(request, tours.get(id));
	}
	
	private static void showTours(Request request) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci l'Id per selezionare un Tour: ");
		getTours().forEach(elem->System.out.println("Dsc: " + elem.getDescription()+ " Id: "+elem.getId()));
		Integer id = scanner.nextInt();
		Tour tour = tours.get(id);
		System.out.println("Dsc: " + tour.getDescription()+ " Id: "+tour.getId());
		System.out.println("Seleziona 1 per visualizzare i POIs, 2 per visualizzare i Contenuti");
		Integer select = scanner.nextInt();
		if(select==1) showPOIsInTour(request, id);
		else if(select==2) showContentsInTour(request, id);
	}
	
	private static void showContentsInTour(Request request, Integer id) {
		Scanner scanner = new Scanner(System.in);
		getTour(id).getContents().forEach(elem->System.out.println("Dsc: " + elem.getText()+ " Id: "+elem.getId()));
		System.out.println("1-Salva Elemento, 2-SegnalaContenuti, 3-Esci");
		int select = scanner.nextInt();
		if(select==1) {
			Request nextRequest = new Request(request.getUser(), Action.SaveElement);
			AccountsManager.execute(nextRequest, getTour(id));
		} else if(select==2) {
			Request nextRequest = new Request(request.getUser(), Action.ReportContent);
		} else showTours(request);
	}
	
	private static void showPOIsInTour(Request request, Integer id) {
		Scanner scanner = new Scanner(System.in);
		getTour(id).getPois().forEach(elem->System.out.println("Dsc: " + elem.getDescription()+ " Id: "+elem.getId()));
		System.out.println("1-Salva Elemento, 2-Esci");
		int select = scanner.nextInt();
		if(select==1) {
			Request nextRequest = new Request(request.getUser(), Action.SaveElement);
			AccountsManager.execute(nextRequest, getTour(id));
		} else showTours(request);
			
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
			showTours(request);
		}
		return null;
	}
	
	private static boolean sendValidation(Request request, Tour tour) {
		return ValidationsManager.execute(request, tour);
	}
	
	public static boolean execute(Request request, Integer id) {
		if(request.getAction()==Action.Post) {
			getTour(id).setPublished(true);;
			return true;
		} else if(request.getAction()==Action.Delete) {
			tours.remove(id);
			return true;
		} else return false;
	}
		
	public static boolean execute(Request request, Integer id1, Integer id2) {
		if(request.getAction()==Action.Post) {
			getTour(id1).getContent(id2).setPublished(true);;
			return true;
		} else if(request.getAction()==Action.Delete) {
			getTour(id1).deleteContent(id2);
			return true;
		} else return false;
	}

}

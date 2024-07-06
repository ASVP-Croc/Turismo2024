package managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import elements.*;
import users.Action;

public class ToursManager {
	private final ContentsManager contentManager;
	private final POIsManager poiManager;
	private final ValidationsManager validationManager;
	private final Map<Integer, Tour> tours;

	public ToursManager(ContentsManager contentManager, POIsManager poiManager, ValidationsManager validationManager) {
		this.contentManager = contentManager;
		this.poiManager = poiManager;
		this.validationManager = validationManager;
		this.tours = new HashMap<>();
	}

	public Stream<Tour> getTours() {
		return tours.values().stream();
	}
	
	public Tour getTour(Integer id) {
		return tours.get(id);
	}
	
	public Stream<Content> getContents(Integer id){
		return getTour(id).getContents();
	}

	private Tour createTour(Request request) {
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
			addPOIToTour(tour.getId(),poiManager.execute(request));
		} else if(select==2) {
			System.out.println("Seleziona un POI da aggiungere scrivendo l'ID!");
			poiManager.getPOIs().forEach(poi->System.out.println(poi));
			Integer integer = scanner.nextInt();
			addPOIToTour(tour.getId(), poiManager.getPOI(integer));
		}
		else if(select==3) {
			addContentToTour(request,tour.getId());
		}
		//fino a quando l'utente vuole.
		return tour;
	}
	
	private Tour addPOIToTour(Integer id, PointOfInterest poi) {
		tours.get(id).addPoi(poi);
		return tours.get(id);
	}
	
	public Tour addPOIToTour() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleziona un Tour inserendo l'ID");
		getTours().forEach(tour->System.out.println(tour));
		Integer id = scanner.nextInt();
		System.out.println("Seleziona un POI da aggiungere scrivendo l'ID!");
		poiManager.getPOIs().forEach(poi->System.out.println(poi));
		Integer integer = scanner.nextInt();
		tours.get(id).addPoi(poiManager.getPOI(integer));
		return tours.get(id);
	}
	
	
	
	private Content addContentToTour(Request request, Integer id) {
		return tours.get(id).addContent(contentManager.execute(null));
	}
	
	private Tour addContentToTour(Request request) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleziona un Tour inserendo l'ID");
		getTours().forEach(tour->System.out.println(tour));
		Integer id = scanner.nextInt();
		tours.get(id).addContent(contentManager.execute(request));
		return tours.get(id);
	}

	public Tour execute(Request request) {
		Action action = request.getAction();
		if(action==Action.CreateTour) {
			return createTour(request);
		} else if(action==Action.CreateContentInTour) {
			return addContentToTour(request);
		} else if(action==Action.AddPOIInTour) {
			addPOIToTour();
		}
		return null;
	}
	
	private boolean sendValidation(Request request, Tour tour) {
		return validationManager.execute(request, tour);
	}
	
	private boolean sendValidation(Request request, Content content) {
		return validationManager.execute(request, content);
	}
	
	public boolean execute(Request request, Integer id) {
		if(request.getAction()==Action.Post) {
			getTour(id).setVisibility();
			return true;
		} else if(request.getAction()==Action.Delete) {
			tours.remove(id);
			return true;
		} else return false;
	}
		
	public boolean execute(Request request, Integer id1, Integer id2) {
		if(request.getAction()==Action.Post) {
			getTour(id1).getContent(id2).setVisibility();
			return true;
		} else if(request.getAction()==Action.Delete) {
			getTour(id1).deleteContent(id2);
			return true;
		} else return false;
	}

}

package com.speriamochemelacavo.turismo2024.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import com.speriamochemelacavo.turismo2024.elements.*;
import com.speriamochemelacavo.turismo2024.users.Action;

public class POIsManager {
	private static final Map<Integer, PointOfInterest> pois = new HashMap<>();

	public static Stream<PointOfInterest> getPOIs() {
		return pois.values().stream();
	}
	
	public static PointOfInterest getPOI(Integer id) {
		return pois.get(id);
	}

	private static PointOfInterest createPOI(Request request) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci la coordinata X per il POI da creare: ");
		double dx = scanner.nextDouble();
		System.out.println("Inserisci la coordinata Y per il POI da creare: ");
		double dy = scanner.nextDouble();
		scanner.nextLine(); // Consuma il carattere di newline
		System.out.println("Inserisci un titolo per il POI da creare: ");
		String text = scanner.nextLine();
		PointOfInterest poi = new PointOfInterest(text, request.getUser().getId());
		pois.put(poi.getId(),poi);
		sendValidation(request, poi);
		System.out.println("1- per aggiungere ora un Contenuto, 2 per completare la creazione.");
		int select = scanner.nextInt();
		if(select==1) {
			addContentToPOI(request, poi);
		} return poi;
	}
	
	private static PointOfInterest addContentToPOI(Request request, PointOfInterest poi) {
		Scanner scanner = new Scanner(System.in);
		Request nextRequest = new Request(request.getUser(), Action.CreateContentInPOI);
		int select = 1;
		while(select==1) {
		Content content = ContentsManager.execute(nextRequest, poi);
		poi.addContent(content);
		System.out.println("1-Aggiungi un nuovo contenuto, 2-Esci");
		select = scanner.nextInt();
		}
		return poi;
	}
	
	private static PointOfInterest addContentToPOI(Request request) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleziona un POI a cui aggiungere un Contenuto:");
		getPOIs().forEach(elem->System.out.println("Dsc: " + elem.getDescription()+ " Id: "+elem.getId()));
		System.out.println("Inserisci l'ID per selezionare il POI: ");
		Integer id = scanner.nextInt();
		return addContentToPOI(request,getPOI(id));
	}
	
	private static boolean sendValidation(Request request, PointOfInterest poi) {
		return ValidationsManager.execute(request, poi);
	}
	
	private static void showPOIs(Request request) {
		Scanner scanner = new Scanner(System.in);
		getPOIs().forEach(elem->System.out.println("Dsc: " + elem.getDescription()+ " Id: "+elem.getId()));
		System.out.println("Inserisci l'ID per selezionare un elemento e visualizzare il Contenuto: ");
		Integer id = scanner.nextInt();
		showContentsInPOI(request, id);
	}
	
	private static void showContentsInPOI(Request request, Integer id) {
		Scanner scanner = new Scanner(System.in);
		getPOI(id).getContents().forEach(elem->System.out.println("Dsc: " + elem.getText()+ " Id: "+elem.getId()));
		System.out.println("1-Salva elemento, 2-Segnala Contenuto, 3-Esci");
		int select = scanner.nextInt();
		if(select==1) {
			Request nextRequest = new Request(request.getUser(), Action.SaveElement);
			AccountsManager.execute(nextRequest, getPOI(id));
		} else if(select==2) {
			Request nextRequest = new Request(request.getUser(), Action.ReportContent);
			ReportsManager.execute(request);
		} else showPOIs(request);
	}

	
	public static PointOfInterest execute(Request request) {
		Action action = request.getAction();
		if(action==Action.CreatePOI) {
			return createPOI(request);
		} else if(action==Action.CreateContentInPOI) {
			return addContentToPOI(request);
		} else if(action==Action.GetPOIs) {
			showPOIs(request);
		} return null;
	}
	
	public static boolean execute(Request request, Integer id) {
		if(request.getAction()==Action.Post) {
			getPOI(id).setVisibility();
			return true;
		} else if(request.getAction()==Action.Delete) {
			pois.remove(id);
			return true;
		} else return false;
	}
	
	public static boolean execute(Request request, Integer id1, Integer id2) {
		if(request.getAction()==Action.Post) {
			getPOI(id1).getContent(id2).setVisibility();
			return true;
		} else if(request.getAction()==Action.Delete) {
			getPOI(id1).deleteContent(id2);
			return true;
		} else return false;
	}
	
	private static void saveElement() {
		System.out.println("");
	}
	
	private static void reportContent() {
		
	}
	
	
}


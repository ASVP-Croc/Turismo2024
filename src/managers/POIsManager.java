package managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import elements.*;
import users.Action;

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
		PointOfInterest poi = new PointOfInterest(text, new Coordinate(dx, dy));
		pois.put(poi.getId(),poi);
		sendValidation(request, poi);
		System.out.println("Inserisci 1 per aggiungere ora un Contenuto, 2 per completare la creazione.");
		int select = scanner.nextInt();
		if(select==1) {
			addContentToPOI(request, poi);
			//manca iterazione aggiunta contenuti
		} return poi;
	}
	
	private static PointOfInterest addContentToPOI(Request request, PointOfInterest poi) {
		Request nextRequest = new Request(request.getUser(), Action.CreateContentInPOI);
		Content content = ContentsManager.execute(nextRequest, poi);
		poi.addContent(content);
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
	
	private static void showPOIs() {
		Scanner scanner = new Scanner(System.in);
		getPOIs().forEach(elem->System.out.println("Dsc: " + elem.getDescription()+ " Id: "+elem.getId()));
		System.out.println("Inserisci l'ID per visualizzare il Contenuto: ");
		Integer id = scanner.nextInt();
		showContentsInPOI(id);
	}
	
	private static void showContentsInPOI(Integer id) {
		getPOI(id).getContents().forEach(elem->System.out.println("Dsc: " + elem.getText()+ " Id: "+elem.getId()));
	}

	
	public static PointOfInterest execute(Request request) {
		Action action = request.getAction();
		if(action==Action.CreatePOI) {
			return createPOI(request);
		} else if(action==Action.CreateContentInPOI) {
			return addContentToPOI(request);
		} else if(action==Action.GetPOIs) {
			showPOIs();
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
	
	
}


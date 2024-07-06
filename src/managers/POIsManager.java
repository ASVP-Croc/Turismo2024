package managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import elements.*;
import users.Action;

public class POIsManager {
	private final ContentsManager contentManager;
	private final ValidationsManager validationManager;
	private final Map<Integer, PointOfInterest> pois;

	public POIsManager(ContentsManager contentManager, ValidationsManager validationManager) {
		this.contentManager = contentManager;
		this.validationManager = validationManager;
		this.pois = new HashMap<>();
	}

	public Stream<PointOfInterest> getPOIs() {
		return pois.values().stream();
	}
	
	public PointOfInterest getPOI(Integer index) {
		return pois.get(index);
	}

	public PointOfInterest createPOI(Request request) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci la coordinata X per il POI da creare: ");
		double dx = scanner.nextDouble();
		System.out.println("Inserisci la coordinata Y per il POI da creare: ");
		double dy = scanner.nextDouble();
		System.out.println("Inserisci una descrizione per il POI da creare: ");
		String text = scanner.nextLine();
		PointOfInterest poi = new PointOfInterest(text, new Coordinate(dx, dy));
		System.out.println("Vuoi aggiungere ora un Contenuto al POI creato? Si/No");
		String select = scanner.nextLine();
		if(scanner.equals("Si")) {
			addContentToPOI(request, poi.getId());
		} return pois.put(poi.getId(),poi);
	}
	
	private PointOfInterest addContentToPOI(Request request, Integer id) {
		getPOI(id).addContent(contentManager.execute(request));
		return getPOI(id);
		
	}
	
	private PointOfInterest addContentToPOI(Request request) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleziona un POI a cui aggiungere un Contenuto:");
		getPOIs().forEach(elem->System.out.println(elem));
		System.out.println("Insrisci l'ID per selzionare il POI: ");
		Integer id = scanner.nextInt();
		getPOI(id).addContent(contentManager.execute(request));
		return getPOI(id);
	}
	
	private boolean sendValidation(Request request, PointOfInterest poi) {
		return validationManager.execute(request, poi);
	}

	public PointOfInterest execute(Request request) {
		Action action = request.getAction();
		if(action==Action.CreatePOI) {
			PointOfInterest poi = createPOI(request);
			sendValidation(request, poi);
			return poi;
		} else if(action==Action.CreateContentInPOI) {
			return addContentToPOI(request);
		} else return null;
	}
	
	public boolean execute(Request request, Integer id) {
		if(request.getAction()==Action.Post) {
			getPOI(id).setVisibility();
			return true;
		} else if(request.getAction()==Action.Delete) {
			pois.remove(id);
			return true;
		} else return false;
	}
	
	public boolean execute(Request request, Integer id1, Integer id2) {
		if(request.getAction()==Action.Post) {
			getPOI(id1).getContent(id2).setVisibility();
			return true;
		} else if(request.getAction()==Action.Delete) {
			getPOI(id1).deleteContent(id2);
			return true;
		} else return false;
	}
	
	
}


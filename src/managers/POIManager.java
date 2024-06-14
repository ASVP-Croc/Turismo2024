package managers;
import java.util.ArrayList;
import java.util.List;

import elements.Content;
import elements.Coordinate;
import elements.PointOfInterest;
import users.Action;

public class POIManager {
	private final List<PointOfInterest> pois;

	public POIManager() {
		this.pois = new ArrayList<>();
	}

	public List<PointOfInterest> getPois() {
		return pois;
	}

	public boolean createPOI(String description, Coordinate coordinate) {
		return pois.add(new PointOfInterest(description, coordinate));
	}
	
	public PointOfInterest getPOI(int i) {
		return pois.get(i);
	}
	
	public boolean addContentToPOI(int i, Content content ) {
		return pois.get(i).addContent(content);
	}

	public boolean execute(Action action) {
		if(action==Action.CreatePOI) {
			return createPOI("Monumento", new Coordinate(5,10));
		} else if(action==Action.CreateContentPOI) {
			return addContentToPOI(0, new Content("Prova Aggiunta Contenuto su POI"));
		}
			return false;
		
	}
}

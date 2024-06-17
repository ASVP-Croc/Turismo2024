import java.util.ArrayList;
import java.util.List;

public class POIManager {
	private final ContentManager contentManager;
	private final List<PointOfInterest> pois;

	public POIManager(ContentManager contentManager) {
		this.contentManager = contentManager;
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
			return addContentToPOI(0, contentManager.create("Prova aggiunta contenuto POI"));
		}
			return false;
		
	}
}

import java.util.ArrayList;
import java.util.List;

public class POIManager {
	private final List<PointOfInterest> pois;

	public POIManager() {
		this.pois = new ArrayList<>();
	}

	public List<PointOfInterest> getPois() {
		return pois;
	}

	public boolean create(String description, Coordinate coordinate) {
		return pois.add(new PointOfInterest(description, coordinate));
	}
	
	public PointOfInterest getPOI(int i) {
		return pois.get(i);
	}
	
	public boolean addContentToPOI(int i, Content content ) {
		return pois.get(i).addContent(content);
	}
}

import java.util.ArrayList;
import java.util.List;

public class Tour {
	private final List<PointOfInterest> pois;

	private final String description;

	public Tour(String description) {
		this.description = description;
		this.pois = new ArrayList<>();
	}

	public List<PointOfInterest> getPois() {
		return pois;
	}

	public boolean addPoi(PointOfInterest poi) {
		return pois.add(poi);
	}

	public String getDescription() {
		return description;
	}

}

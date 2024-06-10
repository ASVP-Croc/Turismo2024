import java.util.ArrayList;
import java.util.List;

public class TourManager {
	private final List<Tour> tours;

	public TourManager() {
		this.tours = new ArrayList<>();
	}

	public List<Tour> getTours() {
		return tours;
	}

	public boolean create(String description) {
		return tours.add(new Tour(description));
	}
	
	public boolean addPOIToTour(int i, PointOfInterest poi) {
		return tours.get(i).addPoi(poi);
	}
	
	public boolean addContentToTour(int i,Content content) {
		return tours.get(i).addContent(content);
	}

}

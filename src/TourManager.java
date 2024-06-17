import java.util.ArrayList;
import java.util.List;

public class TourManager {
	private final ContentManager contentManager;
	private final List<Tour> tours;

	public TourManager(ContentManager contentManager) {
		this.contentManager = contentManager;
		this.tours = new ArrayList<>();
	}

	public List<Tour> getTours() {
		return tours;
	}
	
	public Tour getTour(int i) {
		return tours.get(i);
	}
	
	public List<Content> getContents(int i){
		return getTour(i).getContents();
	}

	public boolean createTour(String description) {
		return tours.add(new Tour(description));
	}
	
	public boolean addPOIToTour(int i, PointOfInterest poi) {
		return tours.get(i).addPoi(poi);
	}
	
	public boolean addContentToTour(int i,Content content) {
		return tours.get(i).addContent(content);
	}

	public boolean execute(Action action) {
		if(action==Action.CreateTour) {
			return createTour("Primo Tour Creato!");
		} if(action==Action.CreateContentTour) {
			return addContentToTour(0, contentManager.create("Prva aggiunta Contentuto su Tour"));
		}
		return false;
	}

}

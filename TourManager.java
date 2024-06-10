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

}

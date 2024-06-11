import java.util.ArrayList;
import java.util.List;

public class PointOfInterest {
	private final List<Content> contents;
	private final String description;
	private final Coordinate coordinate;

	public PointOfInterest(String description, Coordinate coordinate) {
		this.description = description;
		this.coordinate = coordinate;
		this.contents = new ArrayList<>();
	}

	public List<Content> getContents() {
		return contents;
	}
	
	public Content getContent(int i) {
		return contents.get(i);
	}

	public String getDescription() {
		return description;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	
	public boolean addContent(Content content) {
		return contents.add(content);
	}
}

package elements;
import java.util.ArrayList;
import java.util.List;

public class Tour {
	private final List<PointOfInterest> pois;

	private final List<Content> contents;

	private String description;

	public Tour(String description) {
		this.description = description;
		this.pois = new ArrayList<>();
		this.contents = new ArrayList<>();
	}

	public List<PointOfInterest> getPois() {
		return pois;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Content> getContents() {
		return contents;
	}

	public boolean addContent(Content content) {
		return contents.add(content);
	}

	public Content getContent(int i) {
		return contents.get(i);
	}

	public boolean addPoi(PointOfInterest poi) {
		return pois.add(poi);
	}

}

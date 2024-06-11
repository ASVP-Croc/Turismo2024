import java.util.ArrayList;
import java.util.List;

public class Tour {
	private final List<PointOfInterest> pois;
	
	private final List<Content> contents;

	private final String description;

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
	
	public List<Content> getContents(){
		return contents;
	}
	
	public Content getContent(int i) {
		return contents.get(i);
	}
	
	public boolean addPoi(PointOfInterest poi) {
		return pois.add(poi);
	}
	
	public boolean addContent(Content content) {
		return contents.add(content);
	}


}

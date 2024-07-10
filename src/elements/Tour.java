package elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Tour extends Element{
	private final List<PointOfInterest> pois;

	public Tour(String description) {
		super(description);
		this.pois = new ArrayList<>();
		}
	
	public Integer getId() {
		return super.getId();
	}

	public List<PointOfInterest> getPois() {
		return pois;
	}

	public String getDescription() {
		return super.getDescritpion();
	}

	public Stream<Content> getContents() {
		return super.getContents();
	}

	public Content addContent(Content content) {
		return super.addContent(content);
	}

	public Content getContent(int index) {
		return super.getContent(index);
	}

	public boolean addPoi(PointOfInterest poi) {
		return pois.add(poi);
	}
	
	public void deleteContent(Integer id) {
		getContents();//ricontorlla
	}

}

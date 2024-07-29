package elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Tour extends Element{
	private final List<PointOfInterest> myPOIs;
	

	public Tour(String description) {
		super(description);
		this.myPOIs = new ArrayList<>();
	}
	
	//costruttore di copia
	public Tour(Tour other) {
	    super(other.getDescription());
	    this.myPOIs = new ArrayList<>(other.myPOIs);
	}
	
	public Integer getId() {
		return super.getId();
	}

	public Stream<PointOfInterest> getPois() {
		return myPOIs.stream();
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

	public Content getContent(Integer id) {
		return super.getContent(id);
	}

	public boolean addPOI(PointOfInterest poi) {
		return myPOIs.add(poi);
	}
	
	public void deleteContent(Integer id) {
		getContents();//ricontorlla
	}

}

package elements;

import java.util.stream.Stream;

import users.Role;

public class PointOfInterest extends Element{
	private final Coordinate coordinate;
	private Role addedBy;

	public PointOfInterest(String description, Coordinate coordinate) {
		super(description);
		this.coordinate = coordinate;
		this.addedBy = null;
	}
	
	public Integer getId() {
		return super.getId();
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

	public String getDescription() {
		return super.getDescritpion();
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	public void deleteContent(Integer id) {
		getContents();//ricontrolla
	}
	
	public void setAddedBy(Role role) {
		addedBy= role;
	}
}

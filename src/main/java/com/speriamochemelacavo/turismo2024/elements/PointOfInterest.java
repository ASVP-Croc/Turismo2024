package com.speriamochemelacavo.turismo2024.elements;

import java.util.stream.Stream;


public class PointOfInterest extends Element{
	private final Coordinate coordinate;

	public PointOfInterest(String description, Coordinate coordinate, Integer id) {
		super(description, id);
		this.coordinate = coordinate;
	}
	
	public Integer getId() {
		return super.getId();
	}

	public Stream<Content> getContents() {
		return super.getContents();
	}

	public boolean addContent(Content content) {
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
	
	public boolean deleteContent(Integer id) {
		return super.deleteContent(id);
	}

}

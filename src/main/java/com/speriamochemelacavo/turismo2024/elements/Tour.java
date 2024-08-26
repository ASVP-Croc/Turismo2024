package com.speriamochemelacavo.turismo2024.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Tour extends Element{
	private final List<PointOfInterest> myPOIs;
	

	public Tour(String description, Integer id) {
		super(description, id);
		this.myPOIs = new ArrayList<>();
	}
	
	//costruttore di copia
	public Tour(Tour other, Integer id) {
	    super(other.getDescription(), id);
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

	public boolean addContent(Content content) {
		return super.addContent(content);
	}

	public Content getContent(Integer id) {
		return super.getContent(id);
	}

	public boolean addPOI(PointOfInterest poi) {
		return myPOIs.add(poi);
	}
	
	public boolean deleteContent(Integer id) {
		return super.deleteContent(id);
	}

}

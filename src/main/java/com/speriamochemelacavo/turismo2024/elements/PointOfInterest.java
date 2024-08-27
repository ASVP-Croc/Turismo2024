package com.speriamochemelacavo.turismo2024.elements;

import java.util.stream.Stream;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class PointOfInterest extends Element{
	
	@Column(name = "latitude")
	private float latitude;
	@Column(name = "longitude")
	private float longitude;
	
	public PointOfInterest() {}

	public PointOfInterest(String description,  int id) {
		super(description, id);
	}
	
	public float getLongitude() {
		return longitude;
	}
	
	public void setLongitude(float longitude) {
		this.longitude=longitude;
	}
	
	public void setLatitude(float latitude) {
		this.latitude=latitude;
	}
	
	
	public float getLatitude() {
		return latitude;
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
	
	public boolean deleteContent(Integer id) {
		return super.deleteContent(id);
	}

}

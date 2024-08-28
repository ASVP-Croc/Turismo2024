package com.speriamochemelacavo.turismo2024.elements;

import java.util.stream.Stream;

import com.speriamochemelacavo.turismo2024.users.AuthenticatedUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class PointOfInterest extends Element{
	
	@Column(name = "latitude")
	private float latitude;
	@Column(name = "longitude")
	private float longitude;
	
	public PointOfInterest() {}

	public PointOfInterest(String description,  AuthenticatedUser author, float latitude, float longitude) {
		super(description, author);
		this.latitude = latitude;
		this.longitude = longitude;
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

}

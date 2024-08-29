package com.speriamochemelacavo.turismo2024.models.elements;


import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.users.AuthenticatedUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Component
@Entity
public class PointOfInterest extends Element{
	
	public PointOfInterest(int id, String description, AuthenticatedUser author) {
		super(id, description, author);
	}

	private float latitude;
	private float longitude;
	private String address;
	private int CAP;
	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCAP() {
		return CAP;
	}

	public void setCAP(int cAP) {
		this.CAP = cAP;
	}

}

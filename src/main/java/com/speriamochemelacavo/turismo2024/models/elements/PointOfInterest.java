package com.speriamochemelacavo.turismo2024.models.elements;


import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.users.AuthenticatedUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Component
@Entity
public class PointOfInterest extends Element{
	
	private float latitude;
	private float longitude;
	
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

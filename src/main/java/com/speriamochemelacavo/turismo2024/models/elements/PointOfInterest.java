package com.speriamochemelacavo.turismo2024.models.elements;

import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.users.User;

import jakarta.persistence.Entity;

/**
 * È un {@link Element Elemento} associato ad un indirizzo del comune di riferimento, assieme alla coppia latitudine - longitudione.
 * Rappresenta un punto geografico in cui è presente un elemento importante del comune di appartenenza ( un monumeto, un'attività commerciale, etc.).
 *
 * @field float latitude: la latitudine del punto geografico riferito al {@link PointOfInterest Punto di Interesse}.
 * @field float longitude: la longitudine del punto geografico riferito al {@link PointOfInterest Punto di Interesse}.
 * @field String address: l'indirizzo del {@link PointOfInterest Punto di Interesse}.
 * 
 */

@Component
@Entity
public class PointOfInterest extends Element{


	private float latitude;
	private float longitude;
	private String address;
	
	public PointOfInterest() {
		super();
	}

	public PointOfInterest(int id, String description) {
		super(id, description);
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}

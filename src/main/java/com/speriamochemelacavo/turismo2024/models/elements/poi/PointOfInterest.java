package com.speriamochemelacavo.turismo2024.models.elements.poi;

import java.util.List;

import com.speriamochemelacavo.turismo2024.models.elements.Address;
import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.speriamochemelacavo.turismo2024.models.elements.category.ElementTypology;
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
public abstract class PointOfInterest extends ElementWithContents {

	@JsonProperty("lat")
	private float latitude;
	@JsonProperty("lon")
	private float longitude;
	@JsonProperty("address")
	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;
	
	public PointOfInterest() {
		super();
		this.typology = ElementTypology.POI.toString();
	}
	
//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	public PointOfInterest(String name, String description, User author, String city, String postcode, List<Content> contents, float latitude, float longitude, Address address) {
		super(name, description, author, contents, city, postcode);
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.typology = ElementTypology.POI.toString();
	}

	public float getLongitude() {
		return longitude;
	}
	
	public void setLongitude(float longitude) {
		this.longitude=longitude;
	}
	
	public float getLatitude() {
		return latitude;
	}
	
	public void setLatitude(float latitude) {
		this.latitude=latitude;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		String toReturn = "";
		return toReturn 
				+ ("Name: " + getName() + "\n") 
				+ ("Descrizione: " + getDescription() + "\n")
				+ ("Indirizzo: " + getAddress().toString() + "\n")
				+ ("Città: " + getCity() + "\n")
				+ ("CAP: " + getPostcode() + "\n");
	}

	public abstract PoIType getType();
}
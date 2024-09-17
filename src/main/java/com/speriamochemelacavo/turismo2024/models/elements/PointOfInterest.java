package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PointOfInterest extends ElementWithContents{

	@JsonProperty("lat")
	private float latitude;
	@JsonProperty("lon")
	private float longitude;
	@JsonProperty("address")
	@ManyToOne(cascade = CascadeType.MERGE)
	private Address address;
	
	public PointOfInterest() {
		super();
	}
	
//	TODO Questo dovrà essere tolto, usato solo per creare oggetti per i test
	public PointOfInterest(String name, String description, User author, String city, String postcode, List<Content> contents, float latitude, float longitude, Address address) {
		super(name, description, author, city, postcode, contents);
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.typology = ElementTypology.POI;
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
		return toReturn.concat("Name: " + getName() + "\n")
		.concat("Descrizione: " + getDescription() + "\n")
		.concat("Indirizzo: " + getAddress().toString() + "\n")
		.concat("Città: " + getCity() + "\n")
		.concat("CAP: " + getPostcode() + "\n");
	}
}
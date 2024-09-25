package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.speriamochemelacavo.turismo2024.models.elements.poi.POIForTour;
import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;
import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.elements.category.ElementTypology;
import com.speriamochemelacavo.turismo2024.models.users.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

/**
 * È un {@link Element Elemento} che rappresenta un insieme odinato di {@link PointOfInterest Punti di Interesse}, collegati tra loro da un argomento comune.
 * 
 * Un qualsiasi {@link User Utente} ha la possibilità di intraprendere il {@link Tour} a partire da un {@link PointOfInterest Punto di Interesse} qualsiasi all'interno della lista,
 * per poi arrivare ordinatamente fino al {@link PointOfInterest Punto di Interesse} finale.
 *
 * @field List<PointOfInterest> myPOIs: la lista dei {@link PointOfInterest Punti di Interesse} presenti nel {@link Tour}.
 * 
 */

@Component
@Entity
public class Tour extends ElementWithContents{
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<POIForTour> myPOIs = new HashSet<>();
	
	
	public Tour() {
		super();
		this.typology = ElementTypology.TOUR;
	}

	public Tour(String name, String description, User author, String city, String postcode, List<Content> contents) {
		super(name, description, author, contents, city, postcode);
		this.typology = ElementTypology.TOUR;
	}

	public Set<POIForTour> getMyPOIs() {
		return myPOIs;
	}
}

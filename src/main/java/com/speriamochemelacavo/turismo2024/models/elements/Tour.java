package com.speriamochemelacavo.turismo2024.models.elements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

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
public class Tour extends Element{
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<PointOfInterest> myPOIs = new ArrayList<>();
	
	public Tour() {
		super();
	}

	public Tour(String name, String description) {
		super(name, description);
	}

	public List<PointOfInterest> getMyPOIs() {
		return myPOIs;
	}
}

package com.speriamochemelacavo.turismo2024.models.elements.poi;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;

@Component
@Entity
public class POIForTour extends PointOfInterest {

	private boolean forTour = false;

	public POIForTour() {
		super();
	}

	public boolean isForTour() {
		return forTour;
	}

	public void setForTour(boolean forTour) {
		this.forTour = forTour;
	}

	@Override
	public PoIType getType() {
		return null;
	}
	
	
}

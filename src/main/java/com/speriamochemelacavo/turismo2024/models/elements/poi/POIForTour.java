package com.speriamochemelacavo.turismo2024.models.elements.poi;

import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.elements.Element;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;
import com.speriamochemelacavo.turismo2024.models.users.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Component
@Entity
public class POIForTour extends ElementWithContents{
	
	@OneToOne
	private PointOfInterest referenced;
	@ManyToOne(cascade = CascadeType.ALL)
	private User authorForTour;
	private boolean forTour = false;

	public POIForTour() {
		super();
	}
	public POIForTour(PointOfInterest poi) {

	}

	public PointOfInterest getReferenced() {
		return referenced;
	}
	public void setReferenced(PointOfInterest referenced) {
		this.referenced = referenced;
	}
	public User getAuthorForTour() {
		return authorForTour;
	}

	public void setAuthorForTour(User authorForTour) {
		this.authorForTour = authorForTour;
	}

	public boolean isForTour() {
		return forTour;
	}
	public void setForTour(boolean forTour) {
		this.forTour = forTour;
	}
}

package com.speriamochemelacavo.turismo2024.models.elements.poi;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.data.geo.format.PointFormatter;
import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.elements.ElementStatus;
import com.speriamochemelacavo.turismo2024.models.users.User;
import com.speriamochemelacavo.turismo2024.services.POIsService;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Component
@Entity
public class POIForTour extends PointOfInterest {
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User authorForTour;
	private boolean forTour = false;

	public POIForTour() {
		super();
	}
	public POIForTour(PointOfInterest poi) {

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

	@Override
	public PoIType getPoiType() {
		return PoIType.PHYSICS;
	}	
}

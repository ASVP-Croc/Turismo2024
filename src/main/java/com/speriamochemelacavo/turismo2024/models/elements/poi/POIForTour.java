package com.speriamochemelacavo.turismo2024.models.elements.poi;

import org.springframework.stereotype.Component;

import com.speriamochemelacavo.turismo2024.models.elements.ElementStatus;
import com.speriamochemelacavo.turismo2024.models.elements.Tour;
import com.speriamochemelacavo.turismo2024.models.notifications.Notificable;
import com.speriamochemelacavo.turismo2024.models.users.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Component
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class POIForTour extends Notificable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "tour_id", referencedColumnName = "id")
	private Tour tourReferenced;
	
	@ManyToOne
	@JoinColumn(name = "poi_id", referencedColumnName = "id")
	private PointOfInterest poiReferenced;
	
	@ManyToOne
	@JoinColumn(name = "author_id", referencedColumnName = "id")
	private User authorForTour;
	
	private ElementStatus statusForTour;

	public POIForTour() {
		super();
	}
	
	public POIForTour(PointOfInterest poiToRefer, User author) {
		this.poiReferenced = poiToRefer;
		this.authorForTour = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tour getTourReferenced() {
		return tourReferenced;
	}

	public void setTourReferenced(Tour tourReferenced) {
		this.tourReferenced = tourReferenced;
	}

	public PointOfInterest getPoiReferenced() {
		return poiReferenced;
	}
	
	@Override
	public String getName() {
		return poiReferenced.getName();
	}
	
	public void setPoiReferenced(PointOfInterest referenced) {
		this.poiReferenced = referenced;
	}

	public User getAuthor() {
		return authorForTour;
	}

	public void setAuthor(User author) {
		this.authorForTour = author;
	}

	@Override
	public ElementStatus getStatus() {
		return statusForTour;
	}

	@Override
	public void setStatus(ElementStatus status) {
		this.statusForTour = status;
	}
}

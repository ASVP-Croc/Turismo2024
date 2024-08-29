package com.speriamochemelacavo.turismo2024.services;

import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tour;

@Service
public class ToursService extends ElementsService<Tour>{
	
	public void addPOIToTourById(PointOfInterest poi, int tourById) {
		findById(tourById).getMyPOIs().add(poi);
	}

}

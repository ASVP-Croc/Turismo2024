package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tour;

@Service
public class ToursService extends ElementsWithContentsService<Tour>{
	
	@Autowired
	POIsService poiService;
	
	public void addPOIToTour(PointOfInterest poi, int tourById) {
		findById(tourById).getMyPOIs().add(poi);
		updateElement(findById(tourById));
	}
	
	public void addPOIsToTour(List<PointOfInterest> poisToAdd, int tourById) {
		findById(tourById).getMyPOIs().addAll(poisToAdd);
		updateElement(findById(tourById));
	}
	
	public void addNewPOIToTour(PointOfInterest poi, int tourById) {
		poiService.addElement(poi);
		addPOIToTour(poi, tourById);
		updateElement(findById(tourById));
	}
	
	public void addNewPOIsToTour(List<PointOfInterest> poisToAdd, int tourById) {
		poiService.addElements(poisToAdd);
		addPOIsToTour(poisToAdd, tourById);
		updateElement(findById(tourById));
	}
	
	public void deletePOIToTour(PointOfInterest poi,  int tourById) {
		findById(tourById).getMyPOIs().remove(poi);
		if(findById(tourById).getMyPOIs().size()<2) {
			deleteElement(findById(tourById));
		}
		updateElement(findById(tourById));
	}
}

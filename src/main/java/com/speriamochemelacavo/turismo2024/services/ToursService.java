package com.speriamochemelacavo.turismo2024.services;

import java.util.List;
import java.util.Map.Entry;

import com.speriamochemelacavo.turismo2024.models.elements.Contest;
import com.speriamochemelacavo.turismo2024.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.poi.POIForTour;
import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.elements.Tour;

@Service
public class ToursService extends ElementsWithContentsService<Tour>{
	
	private boolean isToursLoaded;
	
//	@Autowired
//	private MultimediaMaterialRepository multimediaMaterialRepository;
	
	@Override
	public boolean isLoaded() {
		return isToursLoaded;
	}

	@Override
	public void setLoaded(boolean isLoaded) {
		isToursLoaded = isLoaded;
	}
//	
//	public void addPOIToTour(Tour tour, PointOfInterest poi) {
//		tour.getMyPOIs().add(poi);
//		elementRepository.save(tour);
//	}
//	
//	public void removePOIToTour(Tour tour, PointOfInterest poi) {
//		tour.getMyPOIs().remove(poi);
//		elementRepository.save(tour);
//	}
}

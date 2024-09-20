package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;
import com.speriamochemelacavo.turismo2024.models.elements.Tour;

@Service
public class ToursService extends ElementsWithContentsService<Tour>{
	
	private boolean isToursLoaded;
	
//	@Autowired
//	private MultimediaMaterialRepository multimediaMaterialRepository;

	public void add(Tour tourToAdd, List<PointOfInterest> poisToAdd) {
		tourToAdd.getMyPOIs().addAll(poisToAdd);
		super.add(tourToAdd);
	}
	
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

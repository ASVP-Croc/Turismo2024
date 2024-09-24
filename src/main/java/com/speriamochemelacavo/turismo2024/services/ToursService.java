package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import com.speriamochemelacavo.turismo2024.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Tour;

@Service
public class ToursService extends ElementsWithContentsService<Tour>{
	
	@Autowired
	private TourRepository tourRepository;
	
	private boolean isToursLoaded;

	@Override
	public List<Tour> findAll() {
		return tourRepository.findAll();
	}	
	
	@Override
	public boolean isLoaded() {
		return isToursLoaded;
	}

	@Override
	public void setLoaded(boolean isLoaded) {
		isToursLoaded = isLoaded;
	}


}

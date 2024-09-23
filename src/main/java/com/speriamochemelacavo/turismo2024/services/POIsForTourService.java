package com.speriamochemelacavo.turismo2024.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.speriamochemelacavo.turismo2024.repository.POIForTourRepository;
import com.speriamochemelacavo.turismo2024.repository.POIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.poi.POIForTour;
import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;

@Service
public class POIsForTourService extends ElementsWithContentsService<POIForTour> {

	@Autowired
	private POIForTourRepository poiForTourRepository;
	
	public POIsForTourService(){
		super();
	}

	@Override
	public List<POIForTour> findAll() {
		return poiForTourRepository.findAll();
	}

	@Override
	public boolean isLoaded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setLoaded(boolean isLoaded) {
		// TODO Auto-generated method stub
		
	}
}




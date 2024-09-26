package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import com.speriamochemelacavo.turismo2024.repository.POIForTourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.poi.POIForTour;

@Service
public class POIsForTourService {

	@Autowired
	private POIForTourRepository poiForTourRepository;
	
	public POIsForTourService(){
		super();
	}
	
	public List<POIForTour> findAll() {
		return poiForTourRepository.findAll();
	}
}




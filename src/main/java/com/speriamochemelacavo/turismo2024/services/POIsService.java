package com.speriamochemelacavo.turismo2024.services;

import com.speriamochemelacavo.turismo2024.models.elements.Address;
import com.speriamochemelacavo.turismo2024.models.elements.Content;
import java.util.List;

import com.speriamochemelacavo.turismo2024.repository.POIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.poi.PointOfInterest;
import com.speriamochemelacavo.turismo2024.models.elements.Tag;

@Service
public class POIsService extends ElementsWithContentsService<PointOfInterest> {

	@Autowired
	private POIRepository poiRepository;
	
	private boolean isPoisLoaded;

	public POIsService(){
		super();
	}

	
	@Override
	public List<PointOfInterest> findAll() {
		return poiRepository.findAll();
	}


	@Override
	public boolean isLoaded() {
		return isPoisLoaded;
	}

	@Override
	public void setLoaded(boolean isLoaded) {
		isPoisLoaded = isLoaded;
	}
}




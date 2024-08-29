package com.speriamochemelacavo.turismo2024.services;

import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;

@Service
public class POIsService extends ElementsService<PointOfInterest> {
	
	public float getLatitudeById(int poiId) {
		return findById(poiId).getLatitude();
	}
	
	public float getLongitudeById(int poiId) {
		return findById(poiId).getLongitude();
	}
	
	public String getAddressById(int poiId) {
		return findById(poiId).getAddress();
	}
	
	public int getCAPById(int poiId) {
		return findById(poiId).getCAP();
	}
	
	
}


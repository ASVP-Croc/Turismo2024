package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;

@Service
public class POIsService extends ElementsWithContentsService<PointOfInterest> {
	
	private boolean isLoaded;
	
	public boolean isLoaded() {
		return isLoaded;
	}

	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

	public float getLatitudeById(int poiId) {
		return findById(poiId).getLatitude();
	}
	
	public float getLongitudeById(int poiId) {
		return findById(poiId).getLongitude();
	}
	
	public String getAddressById(int poiId) {
		return findById(poiId).getAddress();
	}
	
	public void setLatitudeById(int poiId, float latitude) {
		findById(poiId).setLatitude(latitude);
	}
	
	public void setLongitudeById(int poiId, float longitude) {
		findById(poiId).setLongitude(longitude);
	}
	
	public void setAddressById(int poiId, String address) {
		findById(poiId).setAddress(address);
	}
}


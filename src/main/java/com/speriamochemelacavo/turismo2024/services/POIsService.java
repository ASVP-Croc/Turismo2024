package com.speriamochemelacavo.turismo2024.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.PointOfInterest;

@Service
public class POIsService extends ElementsService<PointOfInterest> {
	
	@Autowired
	ContentsService<PointOfInterest> contentService;
	@Autowired
	ValidationsService validationService;
	@Autowired
	ReportsService reportService;
	
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
	
	public List<PointOfInterest> findAll() {
		return repository.findAll();
	}
	
	public PointOfInterest findById(int poiToFindId) {
		return repository.findById(poiToFindId).orElseThrow();
	}

	public void addPOI(PointOfInterest poiToAdd) {
		sendToValidator(repository.save(poiToAdd));
	}
	
	public void addPOIs(List<PointOfInterest> poisToAdd) {
		repository.saveAll(poisToAdd);
	}

	public void updatePOI(PointOfInterest poiToUptade) {
		sendToValidator(repository.save(poiToUptade));
	}
	
	public void deletePOIById(int poiToDeleteId) {
		repository.deleteById(poiToDeleteId);
	}
	
	public void addContent(PointOfInterest poi, Content content) {
		contentService.addContent(content, poi);
	}
	
	public void deleteContent(Content content) {
		contentService.deleteContent(content);
	}
	
	public void sendToValidator(PointOfInterest poi) {
		
	}
	
	
	public void reportPOI() {
		
	}
}

